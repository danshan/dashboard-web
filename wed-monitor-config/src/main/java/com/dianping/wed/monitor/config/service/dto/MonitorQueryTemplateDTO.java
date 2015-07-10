package com.dianping.wed.monitor.config.service.dto;

import lombok.Data;

/**
 * 查询数据入参
 * @author dan.shan
 * @since 2015-06-03 13:59
 */
@Data
public class MonitorQueryTemplateDTO {

    /** 监控页的pageId */
    private String pageId;
    /** 查询条件 */
    private String query;
    /** 数据源 */
    private String datasource;

    private String xAxis;

}
