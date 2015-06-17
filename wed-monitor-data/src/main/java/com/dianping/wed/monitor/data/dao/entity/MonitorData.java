package com.dianping.wed.monitor.data.dao.entity;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import lombok.Data;
import org.bson.types.ObjectId;

import java.io.Serializable;

/**
 * @author dan.shan
 * @since 2015-06-03 10:59
 */
@Entity(noClassnameStored = true)
@Data
public class MonitorData implements Serializable {
    @Id
    private ObjectId id = new ObjectId();
}
