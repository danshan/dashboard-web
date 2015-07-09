package com.dianping.wed.monitor.config.service.impl;

import com.dianping.wed.monitor.config.dao.MonitorChartOptionDao;
import com.dianping.wed.monitor.config.dao.entity.MonitorChartOption;
import com.dianping.wed.monitor.config.service.MonitorChartOptionService;
import com.dianping.wed.monitor.config.service.dto.MonitorChartOptionDTO;
import com.dianping.wed.monitor.common.util.BeanListUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author dan.shan
 * @since 2015-06-18 14:45
 */
@Service
public class MonitorChartOptionServiceImpl implements MonitorChartOptionService {

    @Resource
    private MonitorChartOptionDao monitorChartOptionDao;

    @Override
    public MonitorChartOptionDTO loadChartOptionByPageId(String pageId) {
        MonitorChartOption option = monitorChartOptionDao.loadOptionByPageId(pageId);
        if (option == null) {
            return new MonitorChartOptionDTO();
        }

        return BeanListUtil.copyProperties(option, MonitorChartOptionDTO.class);
    }

    @Override
    public String addChartOption(MonitorChartOptionDTO chartOption) {
        return null;
    }

}
