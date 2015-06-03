package com.dianping.wed.monitor.service.bean;

import lombok.Data;

import java.util.List;

/**
 * @author dan.shan
 * @since 2015-06-03 17:05
 */
@Data
public class MonitorPageConfigDTO {

    private int pageId;
    /** 可选搜索条件 */
    private List<InputFilterDTO> inputFilters;

    @Data
    public static final class InputFilterDTO {
        private String desc;
        private String name;
        private String value;
    }
}
