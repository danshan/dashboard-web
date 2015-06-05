package com.dianping.wed.monitor.service.impl;

import com.dianping.wed.monitor.dao.MonitorOptionDao;
import com.dianping.wed.monitor.dao.MonitorPageConfigDao;
import com.dianping.wed.monitor.dao.MonitorQueryTemplateDao;
import com.dianping.wed.monitor.dao.entity.MonitorOption;
import com.dianping.wed.monitor.dao.entity.MonitorPageConfig;
import com.dianping.wed.monitor.dao.entity.MonitorQueryTemplate;
import com.dianping.wed.monitor.enums.Datasource;
import com.dianping.wed.monitor.service.MonitorService;
import com.dianping.wed.monitor.service.bean.MonitorOptionDTO;
import com.dianping.wed.monitor.service.bean.MonitorPageConfigDTO;
import com.dianping.wed.monitor.service.bean.MonitorQueryTemplateDTO;
import com.dianping.wed.monitor.util.BeanListUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

/**
 * @author dan.shan
 * @since 2015-06-03 10:08
 */
public class MonitorServiceImpl implements MonitorService {

    private static final Logger logger = LoggerFactory.getLogger(MonitorServiceImpl.class);

    @Resource
    private MonitorQueryTemplateDao monitorQueryTemplateDao;
    @Resource
    private MonitorPageConfigDao monitorPageConfigDao;
    @Resource
    private MonitorOptionDao monitorOptionDao;


    @Override
    public MonitorQueryTemplateDTO loadQueryTemplateByPageId(int pageId) {
        MonitorQueryTemplate template = monitorQueryTemplateDao.loadQueryTemplateByPageId(pageId);
        if (template == null) {
            return new MonitorQueryTemplateDTO();
        }

        MonitorQueryTemplateDTO result = BeanListUtil.copyProperties(template, MonitorQueryTemplateDTO.class, new String[]{"datasource"});
        result.setDatasource(Datasource.valueOf(template.getDatasource()));
        return result;
    }

    @Override
    public MonitorPageConfigDTO loadPageConfigByPageId(int pageId) {
        MonitorPageConfig config = monitorPageConfigDao.loadConfigByPageId(pageId);
        if (config == null) {
            return new MonitorPageConfigDTO();
        }

        return BeanListUtil.copyProperties(config, MonitorPageConfigDTO.class);
    }

    @Override
    public MonitorOptionDTO loadOptionByPageId(int pageId) {
        MonitorOption option = monitorOptionDao.loadOptionByPageId(pageId);
        if (option == null) {
            return new MonitorOptionDTO();
        }
        return BeanListUtil.copyProperties(option, MonitorOptionDTO.class);
    }

}
