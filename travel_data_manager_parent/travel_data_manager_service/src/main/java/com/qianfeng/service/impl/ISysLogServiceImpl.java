package com.qianfeng.service.impl;

import com.qianfeng.dao.ISysLogDao;
import com.qianfeng.domain.Syslog;
import com.qianfeng.service.ISysLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ISysLogServiceImpl implements ISysLogService {
    @Resource
    private ISysLogDao sysLogDao;
    @Override
    public void addSysLog(Syslog syslog) {
        sysLogDao.addSysLog(syslog);
    }

    @Override
    public List<Syslog> findAll() {
        return sysLogDao.findAll();
    }
}
