package com.dianping.wed.monitor.web.action.monitor.ajax;

import com.dianping.wed.monitor.service.MonitorService;
import com.dianping.wed.monitor.service.bean.MonitorDataDTO;
import com.dianping.wed.monitor.service.bean.MonitorQueryDTO;
import com.dianping.wed.monitor.web.action.AjaxBaseAction;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author dan.shan
 * @since 2015-06-02 16:05
 */
public class DataJsonAction extends AjaxBaseAction {

    @Setter
    private int pageId;
    @Setter
    private String filters;

    @Resource
    private MonitorService monitorService;

    @Override
    protected int doAjaxExecute(Map<String, Object> result) throws Exception {
        Assert.isTrue(pageId > 0, "page id should by positive number.");

        MonitorDataDTO dataDTO = fetchData();

        List<Object> data = Lists.newLinkedList();
        data.add(Arrays.asList(11, 11, 15, 13, 12, 13, 10));

        getMsg().put("data", dataDTO.getData());
        getMsg().put("columns", dataDTO.getColumns());

        return CODE_SUCCESS;
    }

    private MonitorDataDTO fetchData() {
        Map<String, String> filterMap = parseFilters();
        MonitorQueryDTO query = monitorService.loadQueryTemplateByPageId(this.pageId);
        query = monitorService.renderQuery(query, filterMap);

        MonitorDataDTO dataDTO = monitorService.findDataByQuery("Event", query);
        return dataDTO;
    }

    private Map<String, String> parseFilters() {
        Map<String, String> filterMap = Maps.newHashMap();
        for (String pairs : StringUtils.trimToEmpty(this.filters).split(",")) {
            if (StringUtils.isEmpty(pairs)) {
                continue;
            }
            filterMap.put(pairs.split(":")[0], pairs.split(":")[1]);
        }
        return filterMap;
    }

}
