package com.dianping.wed.monitor.web.action.monitor.ajax;

import com.dianping.wed.monitor.config.service.dto.MonitorChartOptionDTO;
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
public class OptionPreviewOpJsonAction extends AjaxBaseAction {

    @Setter
    private String action; // add / update / delete

    @Setter
    private String pageId;
    @Setter
    private String chartOption;

    @Resource
    private MonitorService monitorService;

    @Override
    protected int doAjaxExecute(Map<String, Object> result) throws Exception {

        Assert.isTrue(StringUtils.isNotBlank(action), "action should not be null.");

        String opresult;
        if ("update".equals(action)) {
            opresult = updateChartOption();
        } else if ("delete".equals(action)) {
            opresult = deleteChartOption();
        } else {
            opresult = "known action";
        }

        getMsg().put("result", opresult);
        return CODE_SUCCESS;
    }

    private String deleteChartOption() {
        Assert.isTrue(StringUtils.isNotBlank(pageId), "page id should not be null.");

        return monitorService.deleteChartOptionByPageId(pageId);
    }

    private String updateChartOption() {
        Assert.isTrue(StringUtils.isNotBlank(pageId), "page id should not be null.");
        Assert.isTrue(StringUtils.isNotBlank(chartOption), "chart option should not be null.");

        MonitorChartOptionDTO option = buildOption();
        return monitorService.updateChartOptionByPageId(option);
    }

    private MonitorChartOptionDTO buildOption() {
        MonitorChartOptionDTO option = new MonitorChartOptionDTO();
        option.setPageId(pageId.trim());
        option.setOption(chartOption.trim());

        return option;
    }
}
