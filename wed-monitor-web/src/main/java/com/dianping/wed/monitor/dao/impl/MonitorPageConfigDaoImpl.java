package com.dianping.wed.monitor.dao.impl;

import com.dianping.wed.monitor.dao.MonitorPageConfigDao;
import com.dianping.wed.monitor.dao.entity.MonitorPageConfig;
import com.google.code.morphia.Morphia;
import com.google.code.morphia.dao.BasicDAO;
import com.google.common.collect.Lists;
import com.mongodb.Mongo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    public MonitorPageConfig loadConfigByPageId(int pageId) {
        // TODO
        MonitorPageConfig config = new MonitorPageConfig();
        config.setPageId(pageId);

        List<MonitorPageConfig.InputFilter> inputFilterList = Lists.newLinkedList();
        MonitorPageConfig.InputFilter filter = new MonitorPageConfig.InputFilter();
        filter.setDesc("活动ID");
        filter.setName("eventId");
        inputFilterList.add(filter);

        config.setInputFilters(inputFilterList);
        config.setTimeFilter("addDate");

        return config;
    }
}
