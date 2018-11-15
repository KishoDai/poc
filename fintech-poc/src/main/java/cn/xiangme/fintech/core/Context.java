package cn.xiangme.fintech.core;

import java.util.List;

public class Context<T> {

    private String channel;
    private String id;
    private List<String> levels;
    private T data;

    public Context() {

    }

    public Context(String channel, String id, List<String> levels, T data) {
        this.channel = channel;
        this.id = id;
        this.levels = levels;
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

    public List<String> getLevels() {
        return levels;
    }

    public void setLevels(List<String> levels) {
        this.levels = levels;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
