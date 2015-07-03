package com.dianping.wed.monitor.data.dao.impl;

import com.alibaba.fastjson.JSONObject;
import com.dianping.wed.monitor.data.dao.MonitorDataMongoDao;
import com.dianping.wed.monitor.data.dao.entity.MongoDataQuery;
import com.dianping.wed.monitor.data.dao.entity.MonitorData;
import com.google.code.morphia.Morphia;
import com.google.code.morphia.dao.BasicDAO;
import com.google.common.collect.Lists;
import com.mongodb.CommandResult;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.util.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

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
    public List<JSONObject> findByQuery(MongoDataQuery query) {
        List<JSONObject> result = Lists.newLinkedList();

        CommandResult command = this.getDatastore().getDB().command(query.getQuery());
        if (command.ok()) {

        } else {
            throw command.getException();
        }

        return result;
    }
}
