package com.dianping.wed.monitor.config.service.impl;

import com.dianping.wed.monitor.config.dao.MonitorQueryTemplateDao;
import com.dianping.wed.monitor.config.dao.entity.MonitorQueryTemplate;
import com.dianping.wed.monitor.config.service.MonitorQueryTemplateService;
import com.dianping.wed.monitor.config.service.dto.MonitorQueryTemplateDTO;
import com.dinaping.wed.monitor.common.util.BeanListUtil;
import org.springframework.stereotype.Service;

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
    public MonitorQueryTemplateDTO loadQueryTemplateByPageId(int pageId) {
        MonitorQueryTemplate template = monitorQueryTemplateDao.loadQueryTemplateByPageId(pageId);
        if (template == null) {
            return new MonitorQueryTemplateDTO();
        }

        MonitorQueryTemplateDTO result = BeanListUtil.copyProperties(template, MonitorQueryTemplateDTO.class);
        return result;
    }

    @Override
    public String addQueryTemplate(MonitorQueryTemplateDTO queryTemplate) {
        return null;
    }


}
