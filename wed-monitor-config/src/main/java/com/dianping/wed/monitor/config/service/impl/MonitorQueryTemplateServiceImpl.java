package com.dianping.wed.monitor.config.service.impl;

import com.dianping.wed.monitor.config.dao.MonitorQueryTemplateDao;
import com.dianping.wed.monitor.config.dao.entity.MonitorQueryTemplate;
import com.dianping.wed.monitor.config.service.MonitorQueryTemplateService;
import com.dianping.wed.monitor.config.service.dto.MonitorQueryTemplateDTO;
import com.dianping.wed.monitor.common.util.BeanListUtil;
import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;

/**
 * @author dan.shan
 * @since 2015-06-18 15:00
 */
@Service
public class MonitorQueryTemplateServiceImpl implements MonitorQueryTemplateService {

    @Resource
    private MonitorQueryTemplateDao monitorQueryTemplateDao;

    @Override
    public MonitorQueryTemplateDTO loadQueryTemplateByPageId(String pageId) {
        Assert.isTrue(StringUtils.isNotBlank(pageId), "page id should be blank.");
        MonitorQueryTemplate template = monitorQueryTemplateDao.loadQueryTemplateByPageId(pageId);
        if (template == null) {
            return new MonitorQueryTemplateDTO();
        }

        MonitorQueryTemplateDTO result = BeanListUtil.copyProperties(template, MonitorQueryTemplateDTO.class);
        return result;
    }

    @Override
    public String addQueryTemplate(MonitorQueryTemplateDTO queryTemplate) {
        Assert.notNull(queryTemplate, "query template should not be null.");

        MonitorQueryTemplate po = BeanListUtil.copyProperties(queryTemplate, MonitorQueryTemplate.class);
        return monitorQueryTemplateDao.addQueryTemplate(po);
    }

    @Override
    public String updateQueryTemplateByPageId(MonitorQueryTemplateDTO queryTemplate) {
        Assert.notNull(queryTemplate, "query template should not be null.");
        Assert.isTrue(StringUtils.isNotBlank(queryTemplate.getPageId()), "page id should be blank.");

        MonitorQueryTemplate po = BeanListUtil.copyProperties(queryTemplate, MonitorQueryTemplate.class);

        return monitorQueryTemplateDao.updateQueryTemplateByPageId(po);
    }

    @Override
    public String deleteQueryTemplateByPageId(String pageId) {
        Assert.isTrue(StringUtils.isNotBlank(pageId), "page id should be blank.");

        return monitorQueryTemplateDao.deleteQueryTemplateByPageId(pageId);
    }


}
