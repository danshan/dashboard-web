package com.dianping.wed.monitor.config.dao.impl;

import com.dianping.wed.monitor.config.dao.MonitorQueryTemplateDao;
import com.dianping.wed.monitor.config.dao.entity.MonitorQueryTemplate;
import com.google.code.morphia.Morphia;
import com.google.code.morphia.dao.BasicDAO;
import com.google.code.morphia.query.Query;
import com.google.code.morphia.query.UpdateOperations;
import com.mongodb.Mongo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * @author dan.shan
 * @since 2015-06-03 14:01
 */
public class MonitorQueryTemplateImpl extends BasicDAO<MonitorQueryTemplate, String> implements MonitorQueryTemplateDao {

    private static final Logger logger = LoggerFactory.getLogger(MonitorQueryTemplateImpl.class);

    protected MonitorQueryTemplateImpl(Mongo mongo, Morphia morphia, String dbName) {
        super(mongo, morphia, dbName);
        ds.ensureCaps();
        ds.ensureIndexes();
    }

    @Override
    public MonitorQueryTemplate loadQueryTemplateByPageId(String pageId) {
        Query<MonitorQueryTemplate> query = ds.createQuery(MonitorQueryTemplate.class)
                .field("pageId").equal(pageId)
                .field("isDeleted").equal(0);
        return query.get();
    }

    @Override
    public String addQueryTemplate(MonitorQueryTemplate queryTemplate) {
        queryTemplate.setAddTime(new Date());
        queryTemplate.setUpdateTime(queryTemplate.getAddTime());

        ds.save(queryTemplate);
        return queryTemplate.getId() == null ? null : queryTemplate.getId().toString();
    }

    @Override
    public String updateQueryTemplateByPageId(MonitorQueryTemplate queryTemplate) {
        Query<MonitorQueryTemplate> query = ds.createQuery(MonitorQueryTemplate.class)
                .field("pageId").equal(queryTemplate.getPageId())
                .field("isDeleted").equal(0);

        UpdateOperations<MonitorQueryTemplate> update = ds.createUpdateOperations(MonitorQueryTemplate.class)
                .set("updateTime", new Date())
                .set("query", queryTemplate.getQuery())
                .set("xAxis", queryTemplate.getXAxis())
                .set("datasource", queryTemplate.getDatasource());
        return ds.update(query, update).getError();
    }

    @Override
    public String deleteQueryTemplateByPageId(String pageId) {
        Query<MonitorQueryTemplate> query = ds.createQuery(MonitorQueryTemplate.class)
                .field("pageId").equal(pageId)
                .field("isDeleted").equal(0);
        UpdateOperations<MonitorQueryTemplate> update = ds.createUpdateOperations(MonitorQueryTemplate.class)
                .set("isDeleted", 1)
                .set("updateTime", new Date());

        return ds.update(query, update).getError();
    }

}
