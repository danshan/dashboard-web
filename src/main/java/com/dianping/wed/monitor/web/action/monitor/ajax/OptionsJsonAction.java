package com.dianping.wed.monitor.web.action.monitor.ajax;

import com.dianping.wed.monitor.web.action.AjaxBaseAction;
import com.dianping.wed.monitor.web.bean.monitor.Option;
import com.google.common.collect.Lists;
import lombok.Setter;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author dan.shan
 * @since 2015-06-02 16:04
 */
public class OptionsJsonAction extends AjaxBaseAction {

    @Setter
    private int pageId;

    @Override
    protected int doAjaxExecute(Map<String, Object> result) throws Exception {
        Assert.isTrue(pageId > 0, "page id should be positive number.");
        Option option = buildOption();

        getMsg().put("option", option);
        return CODE_SUCCESS;
    }

    private Option buildOption() {
        Option option = new Option();

        Option.Title title = new Option.Title();
        title.setText("未来一周气温变化");
        title.setSubtext("纯属虚构");
        option.setTitle(title);

        Option.Tooltip tooltip = new Option.Tooltip();
        tooltip.setTrigger("axis");
        option.setTooltip(tooltip);

        Option.Legend legend = new Option.Legend();
        legend.getData().add("line1");
        legend.getData().add("line2");
        option.setLegend(legend);

        Option.XAxis xAxis = new Option.XAxis();
        xAxis.setType("category");
        option.setXAxis(xAxis);

        Option.YAxis yAxis= new Option.YAxis();
        yAxis.setType("value");
        option.setYAxis(yAxis);

        Option.Series series1 = new Option.Series();
        series1.setName("line1");
        series1.setType("line");
        option.getSeries().add(series1);
        Option.Series series2 = new Option.Series();
        series2.setName("line2");
        series2.setType("bar");
        option.getSeries().add(series2);

        return option;
    }

}
