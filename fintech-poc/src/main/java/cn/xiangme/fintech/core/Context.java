package cn.xiangme.fintech.core;

import java.util.List;

public class Context<T> {

    private String channel;
    private String id;
    private String level;
    private T data;

    public Context() {

    }

    public Context(String channel, String id, String level, T data) {
        this.channel = channel;
        this.id = id;
        this.level = level;
        this.data = data;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
