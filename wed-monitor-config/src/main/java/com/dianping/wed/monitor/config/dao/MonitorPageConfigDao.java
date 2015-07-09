package com.dianping.wed.monitor.config.dao;

import com.dianping.wed.monitor.config.dao.entity.MonitorPageConfig;
import com.dianping.wed.monitor.config.service.dto.MonitorPageConfigDTO;

/**
 * @author dan.shan
 * @since 2015-06-03 10:10
 */
public interface MonitorPageConfigDao {

    public MonitorPageConfig loadConfigByPageId(String pageId);

    public String addPageConfig(MonitorPageConfig po);

    public String deletePageConfigByPageId(String pageId);

    public String updatePageConfigByPageId(MonitorPageConfigDTO pageConfig);
}
