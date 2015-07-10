package com.dianping.wed.monitor.web.action.monitor;

import com.dianping.wed.monitor.config.service.dto.MonitorChartOptionDTO;
import com.dianping.wed.monitor.config.service.dto.MonitorPageConfigDTO;
import com.dianping.wed.monitor.config.service.dto.MonitorQueryTemplateDTO;
import com.dianping.wed.monitor.service.MonitorService;
import com.dianping.wed.monitor.web.action.BaseAction;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import javax.annotation.Resource;

/**
 * @author dan.shan
 * @since 2015-06-02 14:07
 */
public class MonitorAction extends BaseAction {

    @Setter @Getter
    private String pageId;

    @Getter
    private MonitorPageConfigDTO pageConfig;

    @Resource
    private MonitorService monitorService;

    @Getter
    private MonitorQueryTemplateDTO queryTemplate;
    @Getter
    private MonitorChartOptionDTO chartOption;

    @Override
    protected String doExecute() throws Exception {
        Assert.isTrue(StringUtils.isNotBlank(pageId), "page id should not be blank.");
        this.pageConfig = monitorService.loadPageConfigByPageId(this.pageId);
        this.queryTemplate = monitorService.loadQueryTemplateByPageId(pageId);
        this.chartOption= monitorService.loadChartOptionByPageId(pageId);

        return SUCCESS;
    }

    @Override
    protected void doValidate() throws Exception {

    }

    @Override
    protected void doPrepare() throws Exception {

    }
}
