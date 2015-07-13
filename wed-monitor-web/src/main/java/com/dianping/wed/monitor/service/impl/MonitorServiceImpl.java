package com.dianping.wed.monitor.service.impl;

import com.dianping.wed.monitor.common.util.StringTemplateUtil;
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
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;
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
    public MonitorDataDTO findDataByPageId(String pageId, Map<String, String> params) {
        Assert.isTrue(StringUtils.isNotBlank(pageId), "page id should not be blank.");

        MonitorQueryTemplateDTO queryTemplate = monitorQueryTemplateService.loadQueryTemplateByPageId(pageId);
        return this.findDataByTemplate(queryTemplate, params);
    }

    @Override
    public MonitorDataDTO findDataByTemplate(MonitorQueryTemplateDTO queryTemplate, Map<String, String> params) {
        MonitorQueryDTO monitorQuery = this.renderMonitorQuery(queryTemplate, params);
        Datasource datasource = Datasource.valueOf(queryTemplate.getDatasource());

        MonitorDataService dataService = DataServiceFactory.getDataService(datasource);

        return dataService.findDataByQuery(monitorQuery);
    }

    @Override
    public MonitorPageConfigDTO loadPageConfigByPageId(String pageId) {
        Assert.isTrue(StringUtils.isNotBlank(pageId), "page id should not be blank.");
        return monitorPageConfigService.loadPageConfigByPageId(pageId);
    }

    @Override
    public String deletePageConfigByPageId(String pageId) {
        Assert.isTrue(StringUtils.isNotBlank(pageId), "page id should not be blank.");
        return monitorPageConfigService.deletePageConfigByPageId(pageId);
    }

    @Override
    public String updatePageConfigByPageId(MonitorPageConfigDTO pageConfig) {
        Assert.isTrue(StringUtils.isNotBlank(pageConfig.getPageId()), "page id should not be blank.");

        MonitorPageConfigDTO exists = monitorPageConfigService.loadPageConfigByPageId(pageConfig.getPageId());
        Assert.notNull(exists);
        exists.setPageName(pageConfig.getPageName());
        exists.setPageDesc(pageConfig.getPageDesc());

        return monitorPageConfigService.updatePageConfigByPageId(exists);
    }

    @Override
    public String addPageConfig(MonitorPageConfigDTO pageConfig) {
        return monitorPageConfigService.addPageConfig(pageConfig);
    }

    @Override
    public MonitorChartOptionDTO loadChartOptionByPageId(String pageId) {
        Assert.isTrue(StringUtils.isNotBlank(pageId), "page id should not be blank.");
        return monitorChartOptionService.loadChartOptionByPageId(pageId);

    }

    @Override
    public MonitorQueryTemplateDTO loadQueryTemplateByPageId(String pageId) {
        Assert.isTrue(StringUtils.isNotBlank(pageId), "page id should not be blank.");
        return monitorQueryTemplateService.loadQueryTemplateByPageId(pageId);
    }

    @Override
    public MonitorQueryDTO renderMonitorQuery(MonitorQueryTemplateDTO template, Map<String, String> params) {
        Assert.notNull(template, "query template should not be null.");
        Assert.isTrue(StringUtils.isNotBlank(template.getQuery()), "query.query should not be blank");

        MonitorQueryDTO query = new MonitorQueryDTO();
        query.setQuery(renderQuery(template.getQuery(), params));
        query.setDatasource(Datasource.valueOf(template.getDatasource()));
        query.setXaxis(template.getXaxis());

        return query;
    }

    @Override
    public List<MonitorPageConfigDTO> findPageConfigs() {
        return monitorPageConfigService.findPageConfigs();
    }

    @Override
    public String addQueryTemplate(MonitorQueryTemplateDTO template) {
        return monitorQueryTemplateService.addQueryTemplate(template);
    }

    @Override
    public String updateQueryTemplateByPageId(MonitorQueryTemplateDTO template) {
        Assert.isTrue(StringUtils.isNotBlank(template.getPageId()), "page id should not be blank.");
        MonitorQueryTemplateDTO exists = monitorQueryTemplateService.loadQueryTemplateByPageId(template.getPageId());
        if (exists == null || StringUtils.isBlank(exists.getPageId())) {
            return this.addQueryTemplate(template);
        } else {
            return monitorQueryTemplateService.updateQueryTemplateByPageId(template);
        }
    }

    @Override
    public String deleteQueryTemplateByPageId(String pageId) {
        Assert.isTrue(StringUtils.isNotBlank(pageId), "page id should not be blank.");
        return monitorQueryTemplateService.deleteQueryTemplateByPageId(pageId);
    }

    @Override
    public String deleteChartOptionByPageId(String pageId) {
        Assert.isTrue(StringUtils.isNotBlank(pageId), "page id should not be blank.");
        return monitorChartOptionService.deleteChartOptionByPageId(pageId);
    }

    @Override
    public String updateChartOptionByPageId(MonitorChartOptionDTO option) {
        Assert.isTrue(StringUtils.isNotBlank(option.getPageId()), "page id should not be blank.");
        MonitorChartOptionDTO exists = monitorChartOptionService.loadChartOptionByPageId(option.getPageId());
        if (exists == null || StringUtils.isBlank(exists.getPageId())) {
            return this.addChartOption(option);
        } else {
            return monitorChartOptionService.updateChartOptionByPageId(option);
        }
    }

    @Override
    public String addChartOption(MonitorChartOptionDTO option) {
        return monitorChartOptionService.addChartOption(option);
    }

    private String renderQuery(String query, Map<String, String> params) {
        return StringTemplateUtil.replaceTemplateTag(query, params);
    }
}
