package com.dianping.wed.monitor.service.impl;

import com.dianping.wed.monitor.config.service.MonitorChartOptionService;
import com.dianping.wed.monitor.config.service.MonitorPageConfigService;
import com.dianping.wed.monitor.config.service.MonitorQueryTemplateService;
import com.dianping.wed.monitor.config.service.dto.MonitorChartOptionDTO;
import com.dianping.wed.monitor.config.service.dto.MonitorPageConfigDTO;
import com.dianping.wed.monitor.config.service.dto.MonitorQueryTemplateDTO;
import com.dianping.wed.monitor.data.enums.Datasource;
import com.dianping.wed.monitor.data.service.DataServiceFactory;
import com.dianping.wed.monitor.data.service.MonitorDataService;
import com.dianping.wed.monitor.data.service.dto.MonitorDataDTO;
import com.dianping.wed.monitor.data.service.dto.MonitorQueryDTO;
import com.dianping.wed.monitor.service.MonitorService;
import com.dinaping.wed.monitor.common.util.StringTemplateUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author dan.shan
 * @since 2015-06-03 10:08
 */
public class MonitorServiceImpl implements MonitorService {

    private static final Logger logger = LoggerFactory.getLogger(MonitorServiceImpl.class);

    @Resource
    private MonitorQueryTemplateService monitorQueryTemplateService;
    @Resource
    private MonitorPageConfigService monitorPageConfigService;
    @Resource
    private MonitorChartOptionService monitorChartOptionService;

    @Override
    public MonitorDataDTO findDataByPageId(int pageId, Map<String, String> params) {
        Assert.isTrue(pageId > 0, "page id should be positive number.");

        MonitorQueryTemplateDTO queryTemplate = monitorQueryTemplateService.loadQueryTemplateByPageId(pageId);
        MonitorQueryDTO monitorQuery = this.renderMonitorQuery(queryTemplate, params);
        Datasource datasource = Datasource.valueOf(queryTemplate.getDatasource());

        MonitorDataService dataService = DataServiceFactory.getDataService(datasource);

        return dataService.findDataByQuery(monitorQuery);
    }

    @Override
    public MonitorPageConfigDTO loadPageConfigByPageId(int pageId) {
        Assert.isTrue(pageId > 0, "page id should be positive number.");
        return monitorPageConfigService.loadPageConfigByPageId(pageId);
    }

    @Override
    public MonitorChartOptionDTO loadChartOptionByPageId(int pageId) {
        Assert.isTrue(pageId > 0, "page id should be positive number.");
        return monitorChartOptionService.loadChartOptionByPageId(pageId);

    }

    @Override
    public MonitorQueryTemplateDTO loadQueryTemplateByPageId(int pageId) {
        Assert.isTrue(pageId > 0, "page id should be positive number.");
        return monitorQueryTemplateService.loadQueryTemplateByPageId(pageId);
    }

    @Override
    public MonitorQueryDTO renderMonitorQuery(MonitorQueryTemplateDTO template, Map<String, String> params) {
        Assert.notNull(template, "query template should not be null.");
        Assert.isTrue(StringUtils.isNotBlank(template.getQuery()), "query.query should not be blank");

        MonitorQueryDTO query = new MonitorQueryDTO();
        query.setQuery(renderQuery(template.getQuery(), params));
        query.setDatasource(Datasource.valueOf(template.getDatasource()));
        return query;
    }

    private String renderQuery(String query, Map<String, String> params) {
        return StringTemplateUtil.replaceTemplateTag(query, params);
    }
}
