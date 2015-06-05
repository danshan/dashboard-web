package com.dianping.wed.monitor.service;

import com.dianping.wed.monitor.service.bean.MonitorOptionDTO;
import com.dianping.wed.monitor.service.bean.MonitorPageConfigDTO;
import com.dianping.wed.monitor.service.bean.MonitorQueryTemplateDTO;

/**
 * @author dan.shan
 * @since 2015-06-03 10:07
 */
public interface MonitorService {

    public MonitorQueryTemplateDTO loadQueryTemplateByPageId(int pageId);

    public MonitorPageConfigDTO loadPageConfigByPageId(int pageId);

    public MonitorOptionDTO loadOptionByPageId(int pageId);
}
