package com.dianping.wed.monitor.web.action.monitor.ajax;

import com.dianping.wed.monitor.service.MonitorDataService;
import com.dianping.wed.monitor.service.MonitorDataServiceFactory;
import com.dianping.wed.monitor.service.MonitorService;
import com.dianping.wed.monitor.service.bean.MonitorDataDTO;
import com.dianping.wed.monitor.service.bean.MonitorPageConfigDTO;
import com.dianping.wed.monitor.service.bean.MonitorQueryTemplateDTO;
import com.dianping.wed.monitor.web.action.AjaxBaseAction;
import com.google.common.collect.Maps;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import javax.annotation.Resource;
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

        getMsg().put("data", dataDTO.getData());
        getMsg().put("columns", dataDTO.getColumns());

        return CODE_SUCCESS;
    }

    private MonitorDataDTO fetchData() {

        MonitorQueryTemplateDTO queryTemplate = monitorService.loadQueryTemplateByPageId(this.pageId);
        Map<String, String> filterMap = parseFilters();

        MonitorDataService dataService = MonitorDataServiceFactory.getDataService(queryTemplate.getDatasource());
        MonitorDataDTO dataDTO = dataService.findDataByQuery(queryTemplate, filterMap);
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
