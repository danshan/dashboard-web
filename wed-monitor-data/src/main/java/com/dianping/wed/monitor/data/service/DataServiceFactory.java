package com.dianping.wed.monitor.data.service;

import com.dianping.wed.monitor.data.enums.Datasource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * @author dan.shan
 * @since 2015-06-18 13:57
 */
@Component
public class DataServiceFactory implements ApplicationContextAware {

    private static final Logger logger = LoggerFactory.getLogger(DataServiceFactory.class);

    private static ApplicationContext applicationContext;

    private static final String PREFIX = "ds";
    private static final String SUFFIX = "Service";

    public static MonitorDataService getDataService(Datasource datasource) {
        Assert.notNull(datasource, "datasource should not be null");
        String name = PREFIX + datasource.name() + SUFFIX;
        try {
            return (MonitorDataService) applicationContext.getBean(name);
        } catch (BeansException e) {
            logger.error("cannot find data service: " + name, e);
            return null;
        }

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

}
