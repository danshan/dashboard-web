package com.dianping.wed.monitor.data.service.dto;

import lombok.Data;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @author dan.shan
 * @since 2015-06-04 11:09
 */
@Data
public class MonitorDataDTO {

    private List<List<String>> data;
    private List<String> columns;

}
