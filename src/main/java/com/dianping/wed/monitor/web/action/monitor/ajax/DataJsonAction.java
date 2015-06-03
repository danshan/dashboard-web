package com.dianping.wed.monitor.web.action.monitor.ajax;

import com.dianping.wed.monitor.web.action.AjaxBaseAction;
import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author dan.shan
 * @since 2015-06-02 16:05
 */
public class DataJsonAction extends AjaxBaseAction {

    @Override
    protected int doAjaxExecute(Map<String, Object> result) throws Exception {

        List<Object> data = Lists.newLinkedList();
        data.add(Arrays.asList(11, 11, 15, 13, 12, 13, 10));

        getMsg().put("data", data);

        return CODE_SUCCESS;
    }

}
