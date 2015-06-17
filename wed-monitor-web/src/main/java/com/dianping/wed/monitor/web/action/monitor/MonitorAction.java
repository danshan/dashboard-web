package com.dianping.wed.monitor.web.action.monitor;

import com.dianping.wed.monitor.service.MonitorService;
import com.dianping.wed.monitor.service.bean.MonitorPageConfigDTO;
import com.dianping.wed.monitor.web.action.BaseAction;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

import javax.annotation.Resource;

/**
 * @author dan.shan
 * @since 2015-06-02 14:07
 */
public class MonitorAction extends BaseAction {

    @Setter @Getter
    private int pageId;

    @Getter
    private MonitorPageConfigDTO pageConfig;

    @Resource
    private MonitorService monitorService;

    @Override
    protected String doExecute() throws Exception {
        this.pageConfig = loadPageConfig();

        return SUCCESS;
    }

    private MonitorPageConfigDTO loadPageConfig() {
        Assert.isTrue(this.pageId > 0, "page id should be positive number.");

        MonitorPageConfigDTO pageConfig = monitorService.loadPageConfigByPageId(this.pageId);
        return pageConfig;
    }

    @Override
    protected void doValidate() throws Exception {

    }

    @Override
    protected void doPrepare() throws Exception {

    }
}
