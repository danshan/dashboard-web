package com.dianping.wed.monitor.dao;

import com.dianping.wed.monitor.dao.entity.MonitorQueryTemplate;

/**
 * @author dan.shan
 * @since 2015-06-03 10:10
 */
public interface MonitorQueryTemplateDao {

    public MonitorQueryTemplate loadQueryTemplateByPageId(int pageId);

}
