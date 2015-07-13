package com.dianping.wed.monitor.config.service;

import com.dianping.wed.monitor.config.service.dto.MonitorQueryTemplateDTO;

import java.util.Map;

/**
 * @author dan.shan
 * @since 2015-06-18 15:00
 */
public interface MonitorQueryTemplateService {

    public MonitorQueryTemplateDTO loadQueryTemplateByPageId(String pageId);

    public String addQueryTemplate(MonitorQueryTemplateDTO queryTemplate);

    public String updateQueryTemplateByPageId(MonitorQueryTemplateDTO queryTemplate);

    public String deleteQueryTemplateByPageId(String pageId);
}
