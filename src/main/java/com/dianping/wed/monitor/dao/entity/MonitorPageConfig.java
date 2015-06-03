package com.dianping.wed.monitor.dao.entity;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import lombok.Data;
import org.bson.types.ObjectId;

import java.util.List;

/**
 * 关于单独页面的配置
 * @author dan.shan
 * @since 2015-06-03 17:02
 */
@Entity(noClassnameStored = true)
@Data
public class MonitorPageConfig {

    @Id
    private ObjectId id = new ObjectId();

    private int pageId;
    /** 可选搜索条件 */
    private List<InputFilter> inputFilters;

    @Data
    public static final class InputFilter {
        private String desc;
        private String name;
        private String value;
    }
}
