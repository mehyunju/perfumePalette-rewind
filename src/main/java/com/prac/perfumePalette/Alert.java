package com.prac.perfumePalette;

public class Alert {

    private String url;
    private String msg;

    public Alert() {
    }

    public Alert(String url, String msg) {
        this.url = url;
        this.msg = msg;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Alert{" +
                "url='" + url + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
