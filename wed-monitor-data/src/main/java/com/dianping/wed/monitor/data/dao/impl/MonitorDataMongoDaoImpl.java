package com.dianping.wed.monitor.data.dao.impl;

import com.dianping.wed.monitor.data.dao.MonitorDataMongoDao;
import com.dianping.wed.monitor.data.dao.entity.MongoDataQuery;
import com.dianping.wed.monitor.data.dao.entity.MonitorData;
import com.google.code.morphia.Morphia;
import com.google.code.morphia.dao.BasicDAO;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.util.JSON;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author dan.shan
 * @since 2015-06-03 10:11
 */
public class MonitorDataMongoDaoImpl extends BasicDAO<MonitorData, String> implements MonitorDataMongoDao {

    private static final Logger logger = LoggerFactory.getLogger(MonitorDataMongoDaoImpl.class);

    protected MonitorDataMongoDaoImpl(Mongo mongo, Morphia morphia, String dbName) {
        super(mongo, morphia, dbName);
        ds.ensureCaps();
        ds.ensureIndexes();
    }

    @Override
    public DBCursor findByQuery(MongoDataQuery query) {
        DBObject queryObj = (DBObject) JSON.parse(query.getQuery());
        if (StringUtils.isNotEmpty(query.getKeys())) {
            DBObject keysObj = (DBObject) JSON.parse(query.getKeys());
            return this.getDatastore().getDB().getCollection(query.getCollection()).find(queryObj, keysObj);
        } else {
            return this.getDatastore().getDB().getCollection(query.getCollection()).find(queryObj);
        }
    }
}
