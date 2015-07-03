package com.dianping.wed.monitor.config.service;

import com.dianping.wed.monitor.config.service.dto.MonitorPageConfigDTO;

/**
 * @author dan.shan
 * @since 2015-06-18 14:40
 */
public interface MonitorPageConfigService {

    public MonitorPageConfigDTO loadPageConfigByPageId(int pageId);

    public String addPageConfig(MonitorPageConfigDTO pageConfig);
}
