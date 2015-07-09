package com.dianping.wed.monitor.web.action.monitor.ajax;

import com.dianping.wed.monitor.config.service.dto.MonitorQueryTemplateDTO;
import com.dianping.wed.monitor.service.MonitorService;
import com.dianping.wed.monitor.web.action.AjaxBaseAction;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author dan.shan
 * @since 2015-07-06 13:33
 */
public class DataPreviewJsonAction extends AjaxBaseAction {

    @Setter
    private String datasoruce;
    @Setter
    private String query;
    @Setter
    private String xAxis;

    @Resource
    private MonitorService monitorService;

    @Override
    protected int doAjaxExecute(Map<String, Object> result) throws Exception {
        Assert.isTrue(StringUtils.isNotBlank(datasoruce), "datasource should not be null.");
        Assert.isTrue(StringUtils.isNotBlank(query), "query should not be null.");
        Assert.isTrue(StringUtils.isNotBlank(xAxis), "xAxis should not be null.");

        MonitorQueryTemplateDTO template = new MonitorQueryTemplateDTO();
        template.setXAxis(xAxis.trim());
        template.setQuery(query.trim());
        template.setDatasource(datasoruce.trim());

        getMsg().put("data", "");
        getMsg().put("columns", "");

        return CODE_SUCCESS;
    }
}
