package com.dianping.wed.monitor.data.dao.entity;

import lombok.Data;

/**
 * @author dan.shan
 * @since 2015-06-05 10:54
 */
@Data
public class MongoDataQuery implements IMonitorDataQuery {

    /** 查询条件 */
    private String query;

    /** 查询的key */
    private String keys;

    /** collection */
    private String collection;

    private String xAxis;

}
