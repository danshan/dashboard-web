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

    // services of data

    MonitorDataDTO findDataByPageId(String pageId, Map<String, String> params);

    MonitorDataDTO findDataByTemplate(MonitorQueryTemplateDTO queryTemplate, Map<String, String> params);

    // services of page config

    MonitorPageConfigDTO loadPageConfigByPageId(String pageId);

    String deletePageConfigByPageId(String pageId);

    String updatePageConfigByPageId(MonitorPageConfigDTO pageConfig);

    String addPageConfig(MonitorPageConfigDTO pageConfig);

    List<MonitorPageConfigDTO> findPageConfigs();

    // services of chart option

    MonitorChartOptionDTO loadChartOptionByPageId(String pageId);

    String deleteChartOptionByPageId(String pageId);

    String updateChartOptionByPageId(MonitorChartOptionDTO option);

    String addChartOption(MonitorChartOptionDTO option);

    // servcies of query template

    MonitorQueryTemplateDTO loadQueryTemplateByPageId(String pageId);

    MonitorQueryDTO renderMonitorQuery(MonitorQueryTemplateDTO template, Map<String, String> params);

    String addQueryTemplate(MonitorQueryTemplateDTO template);

    String updateQueryTemplateByPageId(MonitorQueryTemplateDTO template);

    String deleteQueryTemplateByPageId(String pageId);

}
