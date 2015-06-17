package com.dianping.wed.monitor.data.dao.impl;

import com.dianping.wed.monitor.data.dao.MonitorQueryTemplateDao;
import com.dianping.wed.monitor.data.dao.entity.MonitorQueryTemplate;
import com.google.code.morphia.Morphia;
import com.google.code.morphia.dao.BasicDAO;
import com.mongodb.Mongo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    public MonitorQueryTemplate loadQueryTemplateByPageId(int pageId) {
        //TODO
        MonitorQueryTemplate template = new MonitorQueryTemplate();
        template.setPageId(pageId);
        template.setQuery("{}, {\"eventId\":1, \"pcUrlRewriteID\": 1, \"mUrlRewriteID\": 1}");
        template.setXAxis("eventId");
        template.setDatasource("MongoWedding");
        template.setCollectionName("Event");
        return template;
    }

}
