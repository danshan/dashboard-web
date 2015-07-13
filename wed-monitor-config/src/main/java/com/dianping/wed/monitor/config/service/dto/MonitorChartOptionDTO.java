package com.dianping.wed.monitor.config.service.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * @author dan.shan
 * @since 2015-06-02 16:07
 */
@Data
public class MonitorChartOptionDTO {

    private String pageId;
    private String option;

    private int isDeleted;
    private Date addTime;
    private Date updateTime;
}
