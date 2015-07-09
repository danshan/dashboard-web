package com.dianping.wed.monitor.config.dao.impl;

import com.dianping.wed.monitor.config.dao.MonitorPageConfigDao;
import com.dianping.wed.monitor.config.dao.entity.MonitorPageConfig;
import com.dianping.wed.monitor.config.service.dto.MonitorPageConfigDTO;
import com.google.code.morphia.Morphia;
import com.google.code.morphia.dao.BasicDAO;
import com.google.code.morphia.query.Query;
import com.google.code.morphia.query.UpdateOperations;
import com.mongodb.Mongo;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;

/**
 * @author dan.shan
 * @since 2015-06-03 17:07
 */
public class MonitorPageConfigDaoImpl extends BasicDAO<MonitorPageConfig, String> implements MonitorPageConfigDao {

    private static final Logger logger = LoggerFactory.getLogger(MonitorPageConfigDaoImpl.class);

    protected MonitorPageConfigDaoImpl(Mongo mongo, Morphia morphia, String dbName) {
        super(mongo, morphia, dbName);
        ds.ensureCaps();
        ds.ensureIndexes();
    }

    @Override
    public MonitorPageConfig loadConfigByPageId(String pageId) {
        MonitorPageConfig config = new MonitorPageConfig();
        config.setPageId(new ObjectId(pageId));

        Query<MonitorPageConfig> query = ds.createQuery(MonitorPageConfig.class)
                .field("_id").equal(new ObjectId(pageId))
                .field("isDeleted").equal(0);
        return query.get();
    }

    @Override
    public String addPageConfig(MonitorPageConfig po) {
        po.setAddTime(new Date());
        po.setUpdateTime(po.getAddTime());

        ds.save(po);
        return po.getPageId() == null ? null : po.getPageId().toString();
    }

    @Override
    public String deletePageConfigByPageId(String pageId) {
        Query<MonitorPageConfig> query = ds.createQuery(MonitorPageConfig.class)
                .field("_id").equal(new ObjectId(pageId))
                .field("isDeleted").equal(0);
        UpdateOperations<MonitorPageConfig> update = ds.createUpdateOperations(MonitorPageConfig.class)
                .set("isDeleted", 1)
                .set("updateTime", new Date());

        return ds.update(query, update).getError();
    }

    @Override
    public String updatePageConfigByPageId(MonitorPageConfigDTO pageConfig) {
        Query<MonitorPageConfig> query = ds.createQuery(MonitorPageConfig.class)
                .field("_id").equal(new ObjectId(pageConfig.getPageId()))
                .field("isDeleted").equal(0);

        UpdateOperations<MonitorPageConfig> update = ds.createUpdateOperations(MonitorPageConfig.class)
                .set("updateTime", new Date())
                .set("pageName", pageConfig.getPageName())
                .set("pageDesc", pageConfig.getPageDesc())
                .set("inputFilters", pageConfig.getInputFilters())
                .set("timeFilter", pageConfig.getTimeFilter());
        return ds.update(query, update).getError();
    }

    @Override
    public List<MonitorPageConfig> findPageConfigs() {
        Query<MonitorPageConfig> query = ds.createQuery(MonitorPageConfig.class)
                .field("isDeleted").equal(0);
        return query.asList();
    }
}
