package com.dianping.wed.monitor.config.dao.entity;

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
    private String query;
    /** 数据源 */
    private String datasource;

}
