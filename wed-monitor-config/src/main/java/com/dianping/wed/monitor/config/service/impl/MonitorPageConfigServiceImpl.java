package com.dianping.wed.monitor.config.service.impl;

import com.dianping.wed.monitor.config.dao.MonitorPageConfigDao;
import com.dianping.wed.monitor.config.dao.entity.MonitorPageConfig;
import com.dianping.wed.monitor.config.service.MonitorPageConfigService;
import com.dianping.wed.monitor.config.service.dto.MonitorPageConfigDTO;
import com.dianping.wed.monitor.common.util.BeanListUtil;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

/**
 * @author dan.shan
 * @since 2015-06-18 14:43
 */
@Service
public class MonitorPageConfigServiceImpl implements MonitorPageConfigService {

    @Resource
    private MonitorPageConfigDao monitorPageConfigDao;

    @Override
    public MonitorPageConfigDTO loadPageConfigByPageId(String pageId) {
        Assert.isTrue(StringUtils.isNotBlank(pageId), "page id should be blank.");
        MonitorPageConfig config = monitorPageConfigDao.loadConfigByPageId(pageId);
        if (config == null) {
            return new MonitorPageConfigDTO();
        }

        MonitorPageConfigDTO dto = BeanListUtil.copyProperties(config, MonitorPageConfigDTO.class, new String[]{"pageId"});
        dto.setPageId(pageId);
        return dto;

    }

    @Override
    public String addPageConfig(MonitorPageConfigDTO pageConfig) {
        Assert.notNull(pageConfig, "page config should not be null.");

        MonitorPageConfig po = BeanListUtil.copyProperties(pageConfig, MonitorPageConfig.class, new String[]{"pageId"});
        return monitorPageConfigDao.addPageConfig(po);
    }

    @Override
    public String deletePageConfigByPageId(String pageId) {
        Assert.isTrue(StringUtils.isNotBlank(pageId), "page id should be blank.");

        return monitorPageConfigDao.deletePageConfigByPageId(pageId);
    }

    @Override
    public String updatePageConfigByPageId(MonitorPageConfigDTO pageConfig) {
        Assert.notNull(pageConfig, "page config should not be null.");
        Assert.isTrue(StringUtils.isNotBlank(pageConfig.getPageId()), "page id should be blank.");

        return monitorPageConfigDao.updatePageConfigByPageId(pageConfig);
    }

    @Override
    public List<MonitorPageConfigDTO> findPageConfigs() {
        List<MonitorPageConfig> polist = monitorPageConfigDao.findPageConfigs();
        if (CollectionUtils.isEmpty(polist)) {
            return new LinkedList<MonitorPageConfigDTO>();
        }

        List<MonitorPageConfigDTO> result = Lists.newLinkedList();
        MonitorPageConfigDTO dto;
        for (MonitorPageConfig po : polist) {
            dto = BeanListUtil.copyProperties(po, MonitorPageConfigDTO.class, new String[]{"pageId"});
            dto.setPageId(po.getPageId().toString());
            result.add(dto);
        }
        return result;
    }

}
