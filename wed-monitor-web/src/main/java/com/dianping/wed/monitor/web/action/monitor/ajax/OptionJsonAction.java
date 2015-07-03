package com.dianping.wed.monitor.web.action.monitor.ajax;

import com.alibaba.fastjson.JSON;
import com.dianping.wed.monitor.config.service.dto.MonitorChartOptionDTO;
import com.dianping.wed.monitor.service.MonitorService;
import com.dianping.wed.monitor.web.action.AjaxBaseAction;
import lombok.Setter;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author dan.shan
 * @since 2015-06-02 16:04
 */
public class OptionJsonAction extends AjaxBaseAction {

    @Setter
    private int pageId;

    @Resource
    private MonitorService monitorService;

    @Override
    protected int doAjaxExecute(Map<String, Object> result) throws Exception {
        Assert.isTrue(pageId > 0, "page id should be positive number.");
        MonitorChartOptionDTO option = monitorService.loadChartOptionByPageId(pageId);
        Map<String, Object> optionObj = JSON.parseObject(option.getOption());

        getMsg().put("option", optionObj);
        return CODE_SUCCESS;
    }

}
