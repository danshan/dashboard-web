package com.dianping.wed.monitor.config.service.impl;

import com.dianping.wed.monitor.config.dao.MonitorPageConfigDao;
import com.dianping.wed.monitor.config.dao.entity.MonitorPageConfig;
import com.dianping.wed.monitor.config.service.MonitorPageConfigService;
import com.dianping.wed.monitor.config.service.dto.MonitorPageConfigDTO;
import com.dinaping.wed.monitor.common.util.BeanListUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author dan.shan
 * @since 2015-06-18 14:43
 */
@Service
public class MonitorPageConfigServiceImpl implements MonitorPageConfigService{

    @Resource
    private MonitorPageConfigDao monitorPageConfigDao;

    @Override
    public MonitorPageConfigDTO loadPageConfigByPageId(int pageId) {
        MonitorPageConfig config = monitorPageConfigDao.loadConfigByPageId(pageId);
        if (config == null) {
            return new MonitorPageConfigDTO();
        }

        return BeanListUtil.copyProperties(config, MonitorPageConfigDTO.class);
    }

    @Override
    public String addPageConfig(MonitorPageConfigDTO pageConfig) {
        return null;
    }

}
