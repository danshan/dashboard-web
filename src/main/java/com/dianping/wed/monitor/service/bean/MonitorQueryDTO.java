package com.dianping.wed.monitor.service.bean;

import lombok.Data;

/**
 * @author dan.shan
 * @since 2015-06-03 13:59
 */
@Data
public class MonitorQueryDTO {

    private int pageId;
    private String query;
    private String keys;
    private String columnName;

}
