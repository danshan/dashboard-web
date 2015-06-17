package com.dianping.wed.monitor.dao.entity;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import lombok.Data;
import org.bson.types.ObjectId;

/**
 * @author dan.shan
 * @since 2015-06-03 13:55
 */
@Entity(noClassnameStored = true)
@Data
public class MonitorQueryTemplate {
    @Id
    private ObjectId id = new ObjectId();

    private int pageId;
    private String collectionName;
    private String query;
    /** 作为横坐标的列名, 该列应该同时出现在query中 */
    private String xAxis;
    /** 数据源 */
    private String datasource;

}
