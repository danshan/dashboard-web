package com.dianping.wed.monitor.web.action.monitor;

import com.dianping.wed.monitor.config.service.dto.MonitorChartOptionDTO;
import com.dianping.wed.monitor.service.MonitorService;
import com.dianping.wed.monitor.web.action.BaseAction;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import javax.annotation.Resource;

/**
 * @author dan.shan
 * @since 2015-06-03 15:23
 */
public class PreviewOptionAction extends BaseAction {

    @Setter @Getter
    private String pageId;
    @Getter
    private MonitorChartOptionDTO chartOption;

    @Getter
    private String demoOption = "option = {\n" +
            "    title : {\n" +
            "        text: '未来一周气温变化',\n" +
            "        subtext: '纯属虚构'\n" +
            "    },\n" +
            "    tooltip : {\n" +
            "        trigger: 'axis'\n" +
            "    },\n" +
            "    legend: {\n" +
            "        data:[]\n" +
            "    },\n" +
            "    toolbox: {\n" +
            "        show : true,\n" +
            "        feature : {\n" +
            "            mark : {show: true},\n" +
            "            dataView : {show: true, readOnly: false},\n" +
            "            magicType : {show: true, type: ['line', 'bar']},\n" +
            "            restore : {show: true},\n" +
            "            saveAsImage : {show: true}\n" +
            "        }\n" +
            "    },\n" +
            "    calculable : true,\n" +
            "    xAxis : [\n" +
            "        {\n" +
            "            type : 'category',\n" +
            "            boundaryGap : false,\n" +
            "            data : []\n" +
            "        }\n" +
            "    ],\n" +
            "    yAxis : [\n" +
            "        {\n" +
            "            type : 'value',\n" +
            "            axisLabel : {\n" +
            "                formatter: '{value} °C'\n" +
            "            }\n" +
            "        }\n" +
            "    ],\n" +
            "    series : [\n" +
            "        {\n" +
            "            name:'最高气温',\n" +
            "            type:'line',\n" +
            "            data:[],\n" +
            "            markPoint : {\n" +
            "                data : [\n" +
            "                    {type : 'max', name: '最大值'},\n" +
            "                    {type : 'min', name: '最小值'}\n" +
            "                ]\n" +
            "            },\n" +
            "            markLine : {\n" +
            "                data : [\n" +
            "                    {type : 'average', name: '平均值'}\n" +
            "                ]\n" +
            "            }\n" +
            "        },\n" +
            "        {\n" +
            "            name:'最低气温',\n" +
            "            type:'line',\n" +
            "            data:[],\n" +
            "            markPoint : {\n" +
            "                data : [\n" +
            "                    {name : '周最低', value : -2, xAxis: 1, yAxis: -1.5}\n" +
            "                ]\n" +
            "            },\n" +
            "            markLine : {\n" +
            "                data : [\n" +
            "                    {type : 'average', name : '平均值'}\n" +
            "                ]\n" +
            "            }\n" +
            "        }\n" +
            "    ]\n" +
            "};\n";

    @Resource
    private MonitorService monitorService;

    @Override
    protected String doExecute() throws Exception {
        Assert.isTrue(StringUtils.isNotBlank(pageId), "page id should not be null.");

        monitorService.loadChartOptionByPageId(pageId);
        return SUCCESS;
    }

    @Override
    protected void doValidate() throws Exception {

    }

    @Override
    protected void doPrepare() throws Exception {

    }
}
