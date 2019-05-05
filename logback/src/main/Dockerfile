FROM centos:7

RUN yum install -y java-1.8.0-openjdk java-1.8.0-openjdk-devel wget vim
RUN yum install -y bitmap-fonts bitmap-fonts-cjk
RUN yum install -y kde-l10n-Chinese

VOLUME /tmp
ADD logback.jar logback.jar
ADD logback.xml logback.xml
RUN bash -c 'touch /logback.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/logback.jar"]

