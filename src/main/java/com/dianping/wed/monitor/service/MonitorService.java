package com.dianping.wed.monitor.service;

import com.dianping.wed.monitor.service.bean.MonitorQueryDTO;

import java.util.List;
import java.util.Map;

/**
 * @author dan.shan
 * @since 2015-06-03 10:07
 */
public interface MonitorService {

    public List<List<String>> findDataByQuery(String collectionName, MonitorQueryDTO query);

    public MonitorQueryDTO loadQueryTemplateByPageId(int pageId);

    public MonitorQueryDTO renderQuery(MonitorQueryDTO queryTemplate, Map<String, String> filterMap);
}
