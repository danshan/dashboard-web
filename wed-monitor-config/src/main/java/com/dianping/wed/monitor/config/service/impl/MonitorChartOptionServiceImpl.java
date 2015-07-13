package com.dianping.wed.monitor.config.service.impl;

import com.dianping.wed.monitor.config.dao.MonitorChartOptionDao;
import com.dianping.wed.monitor.config.dao.entity.MonitorChartOption;
import com.dianping.wed.monitor.config.dao.entity.MonitorPageConfig;
import com.dianping.wed.monitor.config.service.MonitorChartOptionService;
import com.dianping.wed.monitor.config.service.dto.MonitorChartOptionDTO;
import com.dianping.wed.monitor.common.util.BeanListUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

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
        Assert.notNull(chartOption, "chart option should not be null.");

        MonitorChartOption po = BeanListUtil.copyProperties(chartOption, MonitorChartOption.class);
        return monitorChartOptionDao.addChartOption(po);
    }

    @Override
    public String deleteChartOptionByPageId(String pageId) {
        Assert.isTrue(StringUtils.isNotBlank(pageId), "page id should be blank.");

        return monitorChartOptionDao.deleteChartOptionByPageId(pageId);
    }

    @Override
    public String updateChartOptionByPageId(MonitorChartOptionDTO chartOption) {
        Assert.notNull(chartOption, "chart option should not be null.");
        Assert.isTrue(StringUtils.isNotBlank(chartOption.getPageId()), "page id should be blank.");

        MonitorChartOption po = BeanListUtil.copyProperties(chartOption, MonitorChartOption.class);
        return monitorChartOptionDao.updateChartOptionByPageId(po);
    }

}
