package com.dianping.wed.monitor.service.impl;

import com.dianping.wed.monitor.dao.MonitorDataMongoDao;

/**
 * @author dan.shan
 * @since 2015-06-05 15:14
 */
public class MongoWeddingDataServiceImpl extends MongoDataService {
    @Override
    public void setMonitorDataMongoDao(MonitorDataMongoDao monitorDataMongoDao) {
        this.monitorDataMongoDao = monitorDataMongoDao;
    }
}
