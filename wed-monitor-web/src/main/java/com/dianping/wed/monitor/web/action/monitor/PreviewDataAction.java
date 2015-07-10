package com.dianping.wed.monitor.web.action.monitor;

import com.dianping.wed.monitor.web.action.BaseAction;
import lombok.Getter;
import lombok.Setter;

/**
 * @author dan.shan
 * @since 2015-06-03 15:23
 */
public class PreviewDataAction extends BaseAction {

    @Setter @Getter
    private String pageId;

    @Override
    protected String doExecute() throws Exception {
        return SUCCESS;
    }

    @Override
    protected void doValidate() throws Exception {

    }

    @Override
    protected void doPrepare() throws Exception {

    }
}
