package com.dianping.wed.monitor.service;

import com.dianping.wed.monitor.config.service.dto.MonitorChartOptionDTO;
import com.dianping.wed.monitor.config.service.dto.MonitorPageConfigDTO;
import com.dianping.wed.monitor.config.service.dto.MonitorQueryTemplateDTO;
import com.dianping.wed.monitor.data.service.dto.MonitorDataDTO;
import com.dianping.wed.monitor.data.service.dto.MonitorQueryDTO;

import java.util.List;
import java.util.Map;

/**
 * @author dan.shan
 * @since 2015-06-03 10:07
 */
public interface MonitorService {

    MonitorDataDTO findDataByPageId(String pageId, Map<String, String> params);

    MonitorPageConfigDTO loadPageConfigByPageId(String pageId);

    String deletePageConfigByPageId(String pageId);

    String updatePageConfigByPageId(MonitorPageConfigDTO pageConfig);

    String addPageConfig(MonitorPageConfigDTO pageConfig);

    MonitorChartOptionDTO loadChartOptionByPageId(String pageId);

    MonitorQueryTemplateDTO loadQueryTemplateByPageId(String pageId);

    MonitorQueryDTO renderMonitorQuery(MonitorQueryTemplateDTO template, Map<String, String> params);

    List<MonitorPageConfigDTO> findPageConfigs();
}
