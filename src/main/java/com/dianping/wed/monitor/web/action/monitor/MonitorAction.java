package com.dianping.wed.monitor.web.action.monitor;

import com.dianping.wed.monitor.web.action.BaseAction;
import com.dianping.wed.monitor.web.bean.monitor.InputFilter;
import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author dan.shan
 * @since 2015-06-02 14:07
 */
public class MonitorAction extends BaseAction {

    @Setter
    private int pageId;

    @Getter
    private List<InputFilter> inputFilters;

    @Override
    protected String doExecute() throws Exception {
        this.inputFilters = fetchInputFilters();
        return SUCCESS;
    }

    private List<InputFilter> fetchInputFilters() {
        List<InputFilter> inputFilters = loadFilterByPageId(this.pageId);
        return inputFilters;
    }

    private List<InputFilter> loadFilterByPageId(int pageId) {
        // TODO 从mongodb获取pageid对应的筛选模块
        List<InputFilter> filters = Lists.newLinkedList();

        InputFilter filter1 = new InputFilter();
        filter1.setDesc("开始时间");
        filter1.setName("startDate");
        filters.add(filter1);

        InputFilter filter2 = new InputFilter();
        filter2.setDesc("结束时间");
        filter2.setName("endDate");
        filters.add(filter2);

        return filters;
    }

    @Override
    protected void doValidate() throws Exception {

    }

    @Override
    protected void doPrepare() throws Exception {

    }
}
