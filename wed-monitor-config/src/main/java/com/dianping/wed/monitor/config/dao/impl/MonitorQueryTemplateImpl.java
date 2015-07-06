package com.dianping.wed.monitor.config.dao.impl;

import com.dianping.wed.monitor.config.dao.MonitorQueryTemplateDao;
import com.dianping.wed.monitor.config.dao.entity.MonitorQueryTemplate;
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
        template.setQuery("{" +
                "\"collection\": \"Event\",\n" +
                "\"query\": \"{}\",\n" +
                "\"keys\": {\"pcUrlRewriteID\" : 1, \"mUrlRewriteID\": 1, \"createTime\": 1}\n" +
                "}");
        template.setDatasource("MongoWedding");
        template.setXAxis("createTime");

        return template;
    }

}
