package com.dianping.wed.monitor.config.dao;

import com.dianping.wed.monitor.config.dao.entity.MonitorQueryTemplate;

/**
 * @author dan.shan
 * @since 2015-06-03 10:10
 */
public interface MonitorQueryTemplateDao {

    public MonitorQueryTemplate loadQueryTemplateByPageId(String pageId);

    public String addQueryTemplate(MonitorQueryTemplate queryTemplate);

    public String updateQueryTemplateByPageId(MonitorQueryTemplate queryTemplate);

    public String deleteQueryTemplateByPageId(String pageId);
}
