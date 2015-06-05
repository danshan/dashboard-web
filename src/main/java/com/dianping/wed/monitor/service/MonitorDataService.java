package com.dianping.wed.monitor.service;

import com.dianping.wed.monitor.service.bean.MonitorDataDTO;
import com.dianping.wed.monitor.service.bean.MonitorQueryTemplateDTO;

import java.util.Map;

/**
 * @author dan.shan
 * @since 2015-06-05 10:49
 */
public interface MonitorDataService {

    public MonitorDataDTO findDataByQuery(MonitorQueryTemplateDTO queryTemplateDTO, Map<String, String> filterMap);

}
