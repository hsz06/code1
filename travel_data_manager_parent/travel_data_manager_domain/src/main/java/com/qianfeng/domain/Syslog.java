package com.qianfeng.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Syslog {
    private String id;
    private Date visitTime;
    private String visitTimeStr;
    private String url;
    private String ip;
    private String username;
    private Long executionTime;
    private String method;

    public Syslog() {
    }

    public Syslog(String id, Date visitTime, String visitTimeStr, String url, String ip, String username, Long executionTime, String method) {
        this.id = id;
        this.visitTime = visitTime;
        this.visitTimeStr = visitTimeStr;
        this.url = url;
        this.ip = ip;
        this.username = username;
        this.executionTime = executionTime;
        this.method = method;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(Date visitTime) {
        this.visitTime = visitTime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(Long executionTime) {
        this.executionTime = executionTime;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getVisitTimeStr() {
        if (visitTime!=null){
            SimpleDateFormat dateFormat =new SimpleDateFormat("yyyy-MM-dd");
            visitTimeStr=dateFormat.format(visitTime);

        }
        return visitTimeStr;
    }

    public void setVisitTimeStr(String visitTimeStr) {
        this.visitTimeStr = visitTimeStr;
    }

    @Override
    public String toString() {
        return "Syslog{" +
                "id='" + id + '\'' +
                ", visitTime=" + visitTime +
                ", visitTimeStr='" + visitTimeStr + '\'' +
                ", url='" + url + '\'' +
                ", ip='" + ip + '\'' +
                ", username='" + username + '\'' +
                ", executionTime=" + executionTime +
                ", method='" + method + '\'' +
                '}';
    }
}
