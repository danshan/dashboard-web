package com.dianping.wed.monitor.service;

import com.dianping.wed.monitor.enums.Datasource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * @author dan.shan
 * @since 2015-06-05 10:45
 */
@Component
public class MonitorDataServiceFactory implements ApplicationContextAware {

    private static final Logger logger = LoggerFactory.getLogger(MonitorDataServiceFactory.class);

    private static ApplicationContext applicationContext;

    private static final String PREFIX = "ds";

    public static MonitorDataService getDataService(Datasource datasource) {
        Assert.notNull(datasource, "datasource should not be null");
        String name = PREFIX + datasource.name() + "Service";
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
