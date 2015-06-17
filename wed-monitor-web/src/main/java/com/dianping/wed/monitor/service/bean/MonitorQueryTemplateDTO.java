package com.dianping.wed.monitor.service.bean;

import com.dianping.wed.monitor.data.enums.Datasource;
import lombok.Data;

/**
 * 查询数据入参
 * @author dan.shan
 * @since 2015-06-03 13:59
 */
@Data
public class MonitorQueryTemplateDTO {

    private int pageId;
    private String collectionName;
    /** 查询条件 */
    private String query;
    /** 作为横坐标的列名, 该列应该同时出现在query中 */
    private String xAxis;
    /** 数据源 */
    private Datasource datasource;

}
