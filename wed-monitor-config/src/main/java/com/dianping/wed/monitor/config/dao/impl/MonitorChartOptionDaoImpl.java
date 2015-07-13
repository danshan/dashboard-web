package com.dianping.wed.monitor.config.dao.impl;

import com.dianping.wed.monitor.config.dao.MonitorChartOptionDao;
import com.dianping.wed.monitor.config.dao.entity.MonitorChartOption;
import com.google.code.morphia.Morphia;
import com.google.code.morphia.dao.BasicDAO;
import com.google.code.morphia.query.Query;
import com.google.code.morphia.query.UpdateOperations;
import com.mongodb.Mongo;

import java.util.Date;

/**
 * @author dan.shan
 * @since 2015-06-04 16:27
 */
public class MonitorChartOptionDaoImpl extends BasicDAO<MonitorChartOption, String> implements MonitorChartOptionDao {

    protected MonitorChartOptionDaoImpl(Mongo mongo, Morphia morphia, String dbName) {
        super(mongo, morphia, dbName);
        ds.ensureCaps();
        ds.ensureIndexes();
    }

    @Override
    public MonitorChartOption loadOptionByPageId(String pageId) {
        Query<MonitorChartOption> query = ds.createQuery(MonitorChartOption.class)
                .field("pageId").equal(pageId)
                .field("isDeleted").equal(0);
        return query.get();
    }

    @Override
    public String updateChartOptionByPageId(MonitorChartOption po) {
        Query<MonitorChartOption> query = ds.createQuery(MonitorChartOption.class)
                .field("pageId").equal(po.getPageId())
                .field("isDeleted").equal(0);

        UpdateOperations<MonitorChartOption> update = ds.createUpdateOperations(MonitorChartOption.class)
                .set("updateTime", new Date())
                .set("option", po.getOption());

        return ds.update(query, update).getError();
    }

    @Override
    public String deleteChartOptionByPageId(String pageId) {
        Query<MonitorChartOption> query = ds.createQuery(MonitorChartOption.class)
                .field("pageId").equal(pageId)
                .field("isDeleted").equal(0);

        UpdateOperations<MonitorChartOption> update = ds.createUpdateOperations(MonitorChartOption.class)
                .set("updateTime", new Date())
                .set("isDeleted", 1);
        return ds.update(query, update).getError();
    }

    @Override
    public String addChartOption(MonitorChartOption po) {
        po.setAddTime(new Date());
        po.setUpdateTime(po.getAddTime());

        ds.save(po);
        return po.getId() == null ? null : po.getId().toString();
    }
}
