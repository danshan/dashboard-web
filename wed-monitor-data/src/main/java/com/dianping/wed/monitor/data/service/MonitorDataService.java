package com.dianping.wed.monitor.data.service;

import com.dianping.wed.monitor.data.service.dto.MonitorDataDTO;
import com.dianping.wed.monitor.data.service.dto.MonitorQueryDTO;

import java.util.Map;

/**
 * 和数据源相关的service
 * @author dan.shan
 * @since 2015-06-17 16:48
 */
public interface MonitorDataService {

    public MonitorDataDTO findDataByQuery(MonitorQueryDTO query);

}
