package com.dianping.wed.monitor.dao.entity;

import lombok.Data;

/**
 * @author dan.shan
 * @since 2015-06-05 10:54
 */
@Data
public class MongoDataQuery implements IMonitorDataQuery {

    private String collectionName;
    /** 查询条件 */
    private String query;
    /** 筛选的列 */
    private String keys;

}
