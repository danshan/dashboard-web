package com.dianping.wed.monitor.data.service.dto;

import com.dianping.wed.monitor.data.enums.Datasource;
import lombok.Data;

/**
 * @author dan.shan
 * @since 2015-06-18 15:03
 */
@Data
public class MonitorQueryDTO {

    private String query;
    private Datasource datasource;
    private String xAxis;

}
