package com.dianping.wed.monitor.config.service;

import com.dianping.wed.monitor.config.service.dto.MonitorPageConfigDTO;

import java.util.List;

/**
 * @author dan.shan
 * @since 2015-06-18 14:40
 */
public interface MonitorPageConfigService {

    public MonitorPageConfigDTO loadPageConfigByPageId(String pageId);

    public String addPageConfig(MonitorPageConfigDTO pageConfig);

    public String deletePageConfigByPageId(String pageId);

    public String updatePageConfigByPageId(MonitorPageConfigDTO exists);

    public List<MonitorPageConfigDTO> findPageConfigs();
}
