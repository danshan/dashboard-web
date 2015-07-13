package com.dianping.wed.monitor.config.dao;

import com.dianping.wed.monitor.config.dao.entity.MonitorChartOption;

/**
 * @author dan.shan
 * @since 2015-06-04 16:24
 */
public interface MonitorChartOptionDao {

    public MonitorChartOption loadOptionByPageId(String pageId);

    public String updateChartOptionByPageId(MonitorChartOption po);

    public String deleteChartOptionByPageId(String pageId);

    public String addChartOption(MonitorChartOption po);
}
