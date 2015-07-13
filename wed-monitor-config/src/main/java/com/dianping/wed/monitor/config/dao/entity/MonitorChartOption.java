package com.dianping.wed.monitor.config.dao.entity;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import lombok.Data;
import org.bson.types.ObjectId;

import java.io.Serializable;
import java.util.Date;

/**
 * @author dan.shan
 * @since 2015-06-04 16:19
 */
@Entity(noClassnameStored = true)
@Data
public class MonitorChartOption implements Serializable {
    @Id
    private ObjectId id = new ObjectId();

    private String pageId;
    private String option;

    private int isDeleted;
    private Date addTime;
    private Date updateTime;
}
