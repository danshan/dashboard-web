package com.dianping.wed.monitor.data.dao;

import com.dianping.wed.monitor.data.dao.entity.MongoDataQuery;
import com.mongodb.DBCursor;

/**
 * @author dan.shan
 * @since 2015-06-03 10:10
 */
public interface MonitorDataMongoDao {

    public DBCursor findByQuery(MongoDataQuery query);

}
