package com.dianping.wed.monitor.config.dao.entity;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import lombok.Data;
import org.bson.types.ObjectId;

import java.util.Date;
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
    private ObjectId pageId = new ObjectId();

    private String pageName;
    private String pageDesc;

    /** 可选搜索条件 */
    private List<InputFilter> inputFilters;
    /** 时间筛选条件, 如果该列不为空, 那么页面应该有针对dateFilter的startTime和endTime筛选功能 */
    private String timeFilter;

    private int isDeleted;
    private Date addTime;
    private Date updateTime;

    @Data
    public static final class InputFilter {
        private String desc;
        private String name;
        private String value;
    }
}
