package com.dianping.wed.monitor.web.action.monitor.ajax;

import com.alibaba.fastjson.JSON;
import com.dianping.wed.monitor.config.service.dto.MonitorChartOptionDTO;
import com.dianping.wed.monitor.service.MonitorService;
import com.dianping.wed.monitor.web.action.AjaxBaseAction;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author dan.shan
 * @since 2015-06-02 16:04
 */
public class OptionJsonAction extends AjaxBaseAction {

    @Setter
    private String pageId;

    @Resource
    private MonitorService monitorService;

    @Override
    protected int doAjaxExecute(Map<String, Object> result) throws Exception {
        Assert.isTrue(StringUtils.isNotBlank(pageId), "page id should not be blank.");
        MonitorChartOptionDTO option = monitorService.loadChartOptionByPageId(pageId);

        getMsg().put("option", option.getOption());
        return CODE_SUCCESS;
    }

}
