package com.dianping.wed.monitor.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.dianping.wed.monitor.dao.MonitorDao;
import com.dianping.wed.monitor.dao.MonitorOptionDao;
import com.dianping.wed.monitor.dao.MonitorPageConfigDao;
import com.dianping.wed.monitor.dao.MonitorQueryTemplateDao;
import com.dianping.wed.monitor.dao.entity.MonitorOption;
import com.dianping.wed.monitor.dao.entity.MonitorPageConfig;
import com.dianping.wed.monitor.dao.entity.MonitorQueryTemplate;
import com.dianping.wed.monitor.service.MonitorService;
import com.dianping.wed.monitor.service.bean.MonitorDataDTO;
import com.dianping.wed.monitor.service.bean.MonitorOptionDTO;
import com.dianping.wed.monitor.service.bean.MonitorPageConfigDTO;
import com.dianping.wed.monitor.service.bean.MonitorQueryDTO;
import com.dianping.wed.monitor.util.BeanListUtil;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author dan.shan
 * @since 2015-06-03 10:08
 */
public class MonitorServiceImpl implements MonitorService {

    private static final Logger logger = LoggerFactory.getLogger(MonitorServiceImpl.class);

    @Resource
    private MonitorDao monitorDao;
    @Resource
    private MonitorQueryTemplateDao monitorQueryTemplateDao;
    @Resource
    private MonitorPageConfigDao monitorPageConfigDao;
    @Resource
    private MonitorOptionDao monitorOptionDao;

    @Override
    public MonitorDataDTO findDataByQuery(String collectionName, MonitorQueryDTO query) {
        Assert.notNull(query, "query should not be empty");
        Assert.isTrue(StringUtils.isNotBlank(query.getQuery()), "query.query should not be empty");
        Assert.isTrue(StringUtils.isNotBlank(query.getKeys()), "query.keys should not be empty");
        Assert.isTrue(StringUtils.isNotBlank(query.getColumnName()), "query.columnName should not be empty");
        Assert.isTrue(StringUtils.isNotBlank(collectionName), "collection name should not be empty");

        List<JSONObject> list = monitorDao.findByQuery(collectionName, query);

        MonitorDataDTO dataDTO = new MonitorDataDTO();
        List<String> columns = Lists.newLinkedList();
        List<List<String>> data = Lists.newLinkedList();

        columns.addAll(getMongoKeys(query.getKeys()));
        columns.remove(query.getColumnName());
        columns.add(0, query.getColumnName());

        for (JSONObject jsonObject : list) {
            List<String> row = Lists.newLinkedList();
            for (String key : columns) {
                row.add(jsonObject.getString(key));
            }
            data.add(row);
        }
        dataDTO.setData(data);
        dataDTO.setColumns(columns);
        return dataDTO;
    }

    private Set<String> getMongoKeys(String keysStr) {
        return JSONObject.parseObject(keysStr).keySet();
    }

    @Override
    public MonitorQueryDTO loadQueryTemplateByPageId(int pageId) {
        MonitorQueryTemplate template = monitorQueryTemplateDao.loadQueryTemplateByPageId(pageId);
        if (template == null) {
            return new MonitorQueryDTO();
        }

        return BeanListUtil.copyProperties(template, MonitorQueryDTO.class);
    }

    @Override
    public MonitorQueryDTO renderQuery(MonitorQueryDTO queryTemplate, Map<String, String> filterMap) {
        // TODO 渲染
        return queryTemplate;
    }

    @Override
    public MonitorPageConfigDTO loadPageConfigByPageId(int pageId) {
        MonitorPageConfig config = monitorPageConfigDao.loadConfigByPageId(pageId);
        if (config == null) {
            return new MonitorPageConfigDTO();
        }

        return BeanListUtil.copyProperties(config, MonitorPageConfigDTO.class);
    }

    @Override
    public MonitorOptionDTO loadOptionByPageId(int pageId) {
        MonitorOption option = monitorOptionDao.loadOptionByPageId(pageId);
        if (option == null) {
            return new MonitorOptionDTO();
        }
        return BeanListUtil.copyProperties(option, MonitorOptionDTO.class);
    }

}
