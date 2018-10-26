package com.example.rocketmq.config.bean;

/**
 * @author WangKun
 * @create 2018-09-04
 * @desc
 **/
public class Subscription {
    private String topic;
    private String tag;
    private String type;

    public Subscription() {
    }

    public String getTopic() {
        return this.topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int hashCode() {
        int result = 1;
        result = 31 * result + (this.topic == null ? 0 : this.topic.hashCode());
        return result;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (this.getClass() != obj.getClass()) {
            return false;
        } else {
            Subscription other = (Subscription) obj;
            if (this.topic == null) {
                if (other.topic != null) {
                    return false;
                }
            } else if (!this.topic.equals(other.topic)) {
                return false;
            }

            return true;
        }
    }

    public String toString() {
        return "Subscription [topic=" + this.topic + ", tag=" + this.tag + ", type=" + this.type + "]";
    }
}
