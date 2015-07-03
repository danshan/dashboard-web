package com.dianping.wed.monitor.service.impl;

import com.dianping.wed.monitor.data.dao.MonitorDataMongoDao;
import com.dianping.wed.monitor.data.service.impl.MongoDataServiceImpl;

/**
 * @author dan.shan
 * @since 2015-06-05 15:14
 */
public class MongoWeddingDataServiceImpl extends MongoDataServiceImpl {
    @Override
    public void setMonitorDataMongoDao(MonitorDataMongoDao monitorDataMongoDao) {
        this.monitorDataMongoDao = monitorDataMongoDao;
    }
}
