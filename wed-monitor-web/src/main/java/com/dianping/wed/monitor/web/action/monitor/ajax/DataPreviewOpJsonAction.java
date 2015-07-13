package com.dianping.wed.monitor.web.action.monitor.ajax;

import com.dianping.wed.monitor.config.service.dto.MonitorQueryTemplateDTO;
import com.dianping.wed.monitor.service.MonitorService;
import com.dianping.wed.monitor.web.action.AjaxBaseAction;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author dan.shan
 * @since 2015-07-06 13:33
 */
public class DataPreviewOpJsonAction extends AjaxBaseAction {

    @Setter
    private String action; // add / update / delete

    @Setter
    private String pageId;
    @Setter
    private String queryTemplate;
    @Setter
    private String datasource;
    @Setter
    private String xaxis;

    @Resource
    private MonitorService monitorService;

    @Override
    protected int doAjaxExecute(Map<String, Object> result) throws Exception {

        Assert.isTrue(StringUtils.isNotBlank(action), "action should not be null.");

        String opresult;
        if ("update".equals(action)) {
            opresult = updaetQueryTemplate();
        } else if ("delete".equals(action)) {
            opresult = deleteQueryTemplate();
        } else {
            opresult = "known action";
        }

        getMsg().put("result", opresult);
        return CODE_SUCCESS;
    }

    private String deleteQueryTemplate() {
        Assert.isTrue(StringUtils.isNotBlank(pageId), "page id should not be null.");

        return monitorService.deleteQueryTemplateByPageId(pageId);
    }

    private String updaetQueryTemplate() {
        Assert.isTrue(StringUtils.isNotBlank(pageId), "page id should not be null.");
        Assert.isTrue(StringUtils.isNotBlank(queryTemplate), "query template should not be null.");
        Assert.isTrue(StringUtils.isNotBlank(datasource), "datasource should not be null.");
        Assert.isTrue(StringUtils.isNotBlank(xaxis), "xaxis should not be null.");

        MonitorQueryTemplateDTO template = buildTemplate();
        return monitorService.updateQueryTemplateByPageId(template);
    }

    private Map<String, String> buildParams() {
        // TODO
        return new HashMap<String, String>();
    }

    private MonitorQueryTemplateDTO buildTemplate() {
        MonitorQueryTemplateDTO template = new MonitorQueryTemplateDTO();
        template.setPageId(pageId.trim());
        template.setDatasource(datasource.trim());
        template.setXaxis(xaxis.trim());
        template.setQuery(queryTemplate);

        Assert.isTrue(StringUtils.isNotBlank(template.getQuery()), "query should not be null.");
        Assert.isTrue(StringUtils.isNotBlank(template.getXaxis()), "xaxis should not be null.");

        return template;
    }
}
