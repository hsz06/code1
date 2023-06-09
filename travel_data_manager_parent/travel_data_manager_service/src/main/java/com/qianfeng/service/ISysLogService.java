package com.qianfeng.service;

import com.qianfeng.domain.Syslog;

import java.util.List;

public interface ISysLogService {
    void addSysLog(Syslog syslog);

    List<Syslog> findAll();
}
