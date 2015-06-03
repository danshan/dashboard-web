package com.dianping.wed.monitor.web.action.monitor.ajax;

import com.dianping.wed.monitor.web.action.AjaxBaseAction;
import com.dianping.wed.monitor.web.bean.monitor.Option;
import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author dan.shan
 * @since 2015-06-02 16:04
 */
public class OptionsJsonAction extends AjaxBaseAction {

    @Override
    protected int doAjaxExecute(Map<String, Object> result) throws Exception {
        List<Option> options = buildOptions();

        getMsg().put("options", options);
        return CODE_SUCCESS;
    }

    private List<Option> buildOptions() {
        List<Option> options = Lists.newLinkedList();

        Option option = new Option();

        Option.Title title = new Option.Title();
        title.setText("未来一周气温变化");
        title.setSubtext("纯属虚构");
        option.setTitle(title);

        Option.Tooltip tooltip = new Option.Tooltip();
        tooltip.setTrigger("axis");
        option.setTooltip(tooltip);

        Option.Legend legend = new Option.Legend();
        legend.getData().add("最高气温");
        legend.getData().add("最低气温");
        option.setLegend(legend);

        Option.XAxis xAxis = new Option.XAxis();
        xAxis.setType("category");
        xAxis.getData().addAll(Arrays.asList("周一", "周二", "周三", "周四", "周五", "周六", "周日"));
        option.setXAxis(xAxis);

        Option.YAxis yAxis= new Option.YAxis();
        yAxis.setType("value");
        option.setYAxis(yAxis);

        Option.Series series = new Option.Series();
        series.setName("最高气温");
        series.setType("line");
        option.getSeries().add(series);

        options.add(option);
        return options;
    }

}
