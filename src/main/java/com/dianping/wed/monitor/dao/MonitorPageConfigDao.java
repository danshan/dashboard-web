package com.dianping.wed.monitor.dao;

import com.dianping.wed.monitor.dao.entity.MonitorPageConfig;

/**
 * @author dan.shan
 * @since 2015-06-03 10:10
 */
public interface MonitorPageConfigDao {

    public MonitorPageConfig loadConfigByPageId(int pageId);

}
