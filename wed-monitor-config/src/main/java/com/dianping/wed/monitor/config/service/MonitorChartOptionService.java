package com.dianping.wed.monitor.config.service;

import com.dianping.wed.monitor.config.service.dto.MonitorChartOptionDTO;

/**
 * @author dan.shan
 * @since 2015-06-18 14:45
 */
public interface MonitorChartOptionService {

    public MonitorChartOptionDTO loadChartOptionByPageId(int pageId);

    public String addChartOption(MonitorChartOptionDTO chartOption);
}
