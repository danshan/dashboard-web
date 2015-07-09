package com.dianping.wed.monitor.web.action.monitor;

import com.dianping.wed.monitor.config.service.dto.MonitorPageConfigDTO;
import com.dianping.wed.monitor.service.MonitorService;
import com.dianping.wed.monitor.web.action.BaseAction;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Resource;

/**
 * @author dan.shan
 * @since 2015-07-09 14:57
 */
public class PageConfigDetailAction extends BaseAction {

    @Setter
    private String pageId;

    @Getter
    private MonitorPageConfigDTO pageConfig;

    @Resource
    private MonitorService monitorService;

    @Override
    protected String doExecute() throws Exception {
        if (StringUtils.isNotBlank(pageId)) {
            pageConfig = monitorService.loadPageConfigByPageId(pageId);
        }
        return SUCCESS;
    }

    @Override
    protected void doValidate() throws Exception {

    }

    @Override
    protected void doPrepare() throws Exception {

    }
}
