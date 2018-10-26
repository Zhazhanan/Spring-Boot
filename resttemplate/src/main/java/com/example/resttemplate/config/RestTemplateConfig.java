package com.example.resttemplate.config;

import org.apache.http.*;
import org.apache.http.client.CookieStore;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.AuthSchemes;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.*;
import org.apache.http.conn.DnsResolver;
import org.apache.http.conn.HttpConnectionFactory;
import org.apache.http.conn.ManagedHttpClientConnection;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.DefaultHttpResponseFactory;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.*;
import org.apache.http.impl.io.DefaultHttpRequestWriterFactory;
import org.apache.http.io.HttpMessageParser;
import org.apache.http.io.HttpMessageParserFactory;
import org.apache.http.io.HttpMessageWriterFactory;
import org.apache.http.io.SessionInputBuffer;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicLineParser;
import org.apache.http.message.LineParser;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.CharArrayBuffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.CodingErrorAction;
import java.util.Arrays;

/**
 * @author WangKun
 * @create 2018-10-23
 * @desc
 **/
@Configuration
public class RestTemplateConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(RestTemplateConfig.class);
    //连接超时（默认值 ：-1）
    @Value("${resttemplate.httpclient.requestconfig.connect-timeout:0}")
    private Integer connectTimeout;

    //读超时（默认值 ：-1）
    @Value("${resttemplate.httpclient.requestconfig.read-timeout:0}")
    private Integer readTimeout;

    //连接不够用的等待时间（默认值 ：-1）
    @Value("${resttemplate.httpclient.requestconfig.connection-request-timeout:0}")
    private Integer connectionRequestTimeout;

    //最大连接数（默认值 ：10）
    @Value("${resttemplate.httpclient.pool.max-total:10}")
    private Integer maxTotal;

    //每个路由的最大连接数（默认值 ：5）
    @Value("${resttemplate.httpclient.pool.default-max-per-route:5}")
    private Integer defaultMaxPerRoute;


    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate(clientHttpRequestFactory());
    }

    @Bean
    public HttpComponentsClientHttpRequestFactory clientHttpRequestFactory() {

        // httpClient连接配置
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(httpClient());

        //连接超时
        if (connectTimeout > 0)
            clientHttpRequestFactory.setConnectTimeout(connectTimeout);
        //数据读取超时时间
        if (readTimeout > 0)
            clientHttpRequestFactory.setReadTimeout(readTimeout);
        //连接不够用的等待时间
        if (connectionRequestTimeout > 0)
            clientHttpRequestFactory.setConnectionRequestTimeout(connectionRequestTimeout);

        return clientHttpRequestFactory;
    }

    @Bean
    public HttpClient httpClient() {
        HttpClient httpClient = HttpClients.custom()
                .setConnectionManager(poolingHttpClientConnectionManager())
                .setDefaultCookieStore(cookieStore())
                .setDefaultCredentialsProvider(credentialsProvider())
                .setDefaultRequestConfig(globalRequestConfig())
                .build();
        return httpClient;
    }

    /**
     * @auther: WangKun
     * @date: 2018-10-23 20:16
     * @description: 设置连接池
     */
    @Bean
    public PoolingHttpClientConnectionManager poolingHttpClientConnectionManager() {

        /**---------------------------支持http/https start-----------------------------------------*/
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.INSTANCE)
                .register("https", new SSLConnectionSocketFactory(SSLContexts.createSystemDefault()))
                .build();
        /**---------------------------支持http/https end-----------------------------------------*/


        /**---------------------------自定义消息解析器/编写器 start-----------------------------------------*/

        // 使用自定义消息解析器/编写器定制HTTP的方式
        // 消息从数据流中解析并写入数据流。
        HttpMessageParserFactory<HttpResponse> responseParserFactory = new DefaultHttpResponseParserFactory() {

            @Override
            public HttpMessageParser<HttpResponse> create(
                    SessionInputBuffer buffer, MessageConstraints constraints) {
                LineParser lineParser = new BasicLineParser() {

                    @Override
                    public Header parseHeader(final CharArrayBuffer buffer) {
                        try {
                            return super.parseHeader(buffer);
                        } catch (ParseException ex) {
                            return new BasicHeader(buffer.toString(), null);
                        }
                    }

                };
                return new DefaultHttpResponseParser(
                        buffer, lineParser, DefaultHttpResponseFactory.INSTANCE, constraints) {

                    @Override
                    protected boolean reject(final CharArrayBuffer line, int count) {
                        // 尝试无限地忽略状态行前面的所有垃圾
                        return false;
                    }

                };
            }

        };
        HttpMessageWriterFactory<HttpRequest> requestWriterFactory = new DefaultHttpRequestWriterFactory();

        // 使用自定义连接工厂定制的过程
        // 初始化传出HTTP连接。除了标准的连接
        // 配置参数HTTP连接工厂可以定义消息
        // /解析器/编写器例程将用于各个连接
        HttpConnectionFactory<HttpRoute, ManagedHttpClientConnection> connFactory = new ManagedHttpClientConnectionFactory(
                requestWriterFactory, responseParserFactory);
        /**---------------------------自定义消息解析器/编写器 end-----------------------------------------*/

        /**---------------------------自定义DNS解析器 start-----------------------------------------*/
        // 使用自定义DNS解析器覆盖系统DNS解析。
        DnsResolver dnsResolver = new SystemDefaultDnsResolver() {

            @Override
            public InetAddress[] resolve(final String host) throws UnknownHostException {
                if (host.equalsIgnoreCase("myhost")) {
                    return new InetAddress[]{InetAddress.getByAddress(new byte[]{127, 0, 0, 1})};
                } else {
                    return super.resolve(host);
                }
            }

        };
        /**---------------------------自定义DNS解析器 end-----------------------------------------*/

        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry, connFactory, dnsResolver);
        connManager.setMaxTotal(maxTotal);  //最大连接数，默认10
        connManager.setDefaultMaxPerRoute(defaultMaxPerRoute);

        // 为持久连接配置总最大值或每个路由限制
        // 可以保存在池中，也可以由连接管理器租用
        connManager.setMaxTotal(maxTotal);//最大连接数
        connManager.setDefaultMaxPerRoute(defaultMaxPerRoute);//同路由并发数
//        connManager.setMaxPerRoute(new HttpRoute(new HttpHost("somehost", 80)), 20);


        /**------------------------------------创建套接字配置 start-----------------------------------------------------*/
        // 创建套接字配置
        SocketConfig socketConfig = SocketConfig.custom()
                .setTcpNoDelay(true)
                .build();
        // 将连接管理器配置为使用套接字配置
        // 默认情况下或特定主机
        connManager.setDefaultSocketConfig(socketConfig);
//        connManager.setSocketConfig(new HttpHost("somehost", 80), socketConfig);
        // 在1秒不活动之后验证连接
        connManager.setValidateAfterInactivity(1000);
        /**------------------------------------创建套接字配置 end-----------------------------------------------------*/

        /**------------------------------------创建消息的约束 start-----------------------------------------------------*/
        MessageConstraints messageConstraints = MessageConstraints.custom()
                .setMaxHeaderCount(200)
                .setMaxLineLength(2000)
                .build();
        // 创建连接配置
        ConnectionConfig connectionConfig = ConnectionConfig.custom()
                .setMalformedInputAction(CodingErrorAction.IGNORE)
                .setUnmappableInputAction(CodingErrorAction.IGNORE)
                .setCharset(Consts.UTF_8)
                .setMessageConstraints(messageConstraints)
                .build();
        // 配置连接管理器以使用连接配置
        // 默认情况下或特定主机
        connManager.setDefaultConnectionConfig(connectionConfig);
//        connManager.setConnectionConfig(new HttpHost("somehost", 80), ConnectionConfig.DEFAULT);
        /**------------------------------------创建消息的约束 end-----------------------------------------------------*/

        return connManager;
    }

    /**
     * @auther: WangKun
     * @date: 2018-10-23 16:36
     * @description: 如果需要，使用自定义cookie存储
     */
    public CookieStore cookieStore() {
        CookieStore cookieStore = new BasicCookieStore();
        return cookieStore;
    }

    /**
     * @auther: WangKun
     * @date: 2018-10-23 16:37
     * @description: 如果需要，请使用自定义凭证提供程序。
     */
    public CredentialsProvider credentialsProvider() {
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        return credentialsProvider;
    }


    /**
     * @auther: WangKun
     * @date: 2018-10-23 16:12
     * @description: 创建全局请求配置
     */
    public RequestConfig globalRequestConfig() {
        RequestConfig defaultRequestConfig = RequestConfig.custom()
                .setCookieSpec(CookieSpecs.DEFAULT)
                .setExpectContinueEnabled(true)
                .setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM, AuthSchemes.DIGEST))
                .setProxyPreferredAuthSchemes(Arrays.asList(AuthSchemes.BASIC))
                .build();
        return defaultRequestConfig;
    }
}
