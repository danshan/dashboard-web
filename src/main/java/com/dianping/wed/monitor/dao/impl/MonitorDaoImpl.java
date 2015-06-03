package com.dianping.wed.monitor.dao.impl;

import com.alibaba.fastjson.JSONObject;
import com.dianping.wed.monitor.dao.MonitorDao;
import com.dianping.wed.monitor.dao.entity.MonitorData;
import com.dianping.wed.monitor.dao.entity.MonitorQueryTemplate;
import com.dianping.wed.monitor.service.bean.MonitorQueryDTO;
import com.google.code.morphia.Morphia;
import com.google.code.morphia.dao.BasicDAO;
import com.google.common.collect.Lists;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.util.JSON;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * @author dan.shan
 * @since 2015-06-03 10:11
 */
public class MonitorDaoImpl extends BasicDAO<MonitorData, String> implements MonitorDao {

    private static final Logger logger = Logger.getLogger(MonitorDaoImpl.class);

    protected MonitorDaoImpl(Mongo mongo, Morphia morphia, String dbName) {
        super(mongo, morphia, dbName);
        ds.ensureCaps();
        ds.ensureIndexes();
    }

    @Override
    public List<JSONObject> findByQuery(String collectionName, MonitorQueryDTO query) {
        List<JSONObject> result = Lists.newLinkedList();

        DBObject dbObjQeury = (DBObject) JSON.parse(query.getQuery());
        DBObject dbObjKeys = (DBObject) JSON.parse(query.getKeys());
        DBCursor dbCursor = this.getDatastore().getDB().getCollection(collectionName).find(dbObjQeury, dbObjKeys).limit(100);

        while (dbCursor.hasNext()) {
            result.add(com.alibaba.fastjson.JSON.parseObject(dbCursor.next().toString()));
        }

        return result;
    }

}
