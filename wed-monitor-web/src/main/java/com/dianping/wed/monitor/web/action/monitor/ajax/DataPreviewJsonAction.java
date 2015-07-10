package com.dianping.wed.monitor.web.action.monitor.ajax;

import com.alibaba.fastjson.JSONObject;
import com.dianping.wed.monitor.config.service.dto.MonitorQueryTemplateDTO;
import com.dianping.wed.monitor.data.service.dto.MonitorDataDTO;
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
public class DataPreviewJsonAction extends AjaxBaseAction {

    @Setter
    private String queryTemplate;
    @Setter
    private String datasource;
    @Setter
    private String pageId;

    @Resource
    private MonitorService monitorService;

    @Override
    protected int doAjaxExecute(Map<String, Object> result) throws Exception {

        Assert.isTrue(StringUtils.isNotBlank(pageId), "page id should not be null.");
        Assert.isTrue(StringUtils.isNotBlank(queryTemplate), "query template should not be null.");
        Assert.isTrue(StringUtils.isNotBlank(datasource), "datasource should not be null.");

        MonitorQueryTemplateDTO template = buildTemplate();
        Map<String, String> params = buildParams();

        MonitorDataDTO data = monitorService.findDataByTemplate(template, params);
        getMsg().put("data", data);

        return CODE_SUCCESS;
    }

    private Map<String, String> buildParams() {
        return new HashMap<String, String>();
    }

    private MonitorQueryTemplateDTO buildTemplate() {
        JSONObject jsonObject = JSONObject.parseObject(queryTemplate);

        MonitorQueryTemplateDTO template = new MonitorQueryTemplateDTO();
        template.setPageId(pageId.trim());
        template.setDatasource(datasource.trim());
        template.setXAxis(jsonObject.getString("xAxis"));
        template.setQuery(queryTemplate);

        Assert.isTrue(StringUtils.isNotBlank(template.getQuery()), "query should not be null.");
        Assert.isTrue(StringUtils.isNotBlank(template.getXAxis()), "xAxis should not be null.");

        return template;
    }
}
