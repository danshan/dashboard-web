package com.dianping.wed.monitor.service.bean;

import lombok.Data;

/**
 * 查询数据入参
 * @author dan.shan
 * @since 2015-06-03 13:59
 */
@Data
public class MonitorQueryDTO {

    private int pageId;
    /** 查询条件 */
    private String query;
    /** 筛选的列 */
    private String keys;
    /** 作为横坐标的列名, 该列应该同时出现在keys中 */
    private String columnName;

}
