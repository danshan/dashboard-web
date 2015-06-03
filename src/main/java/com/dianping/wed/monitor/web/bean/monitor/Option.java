package com.dianping.wed.monitor.web.bean.monitor;

import lombok.Data;
import org.apache.struts2.json.annotations.JSON;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * @author dan.shan
 * @since 2015-06-02 16:07
 */
@Data
public class Option implements Serializable {

    private Title title;
    private Tooltip tooltip;
    private Legend legend;
    private XAxis xAxis;
    private YAxis yAxis;
    private List<Series> series = new LinkedList<Series>();

    @JSON(name = "xAxis")
    public XAxis getxAxis() {
        return xAxis;
    }

    @JSON(name = "yAxis")
    public YAxis getyAxis() {
        return yAxis;
    }

    @Data
    public static class Title {
        private String text;
        private String subtext;
    }

    @Data
    public static class Tooltip {
        private String trigger;
    }

    @Data
    public static class Legend {
        private List<String> data = new LinkedList<String>();
    }

    @Data
    public static class XAxis {
        private String type;
        private List<String> data = new LinkedList<String>();
    }

    @Data
    public static class YAxis {
        private String type;
    }

    @Data
    public static class Series {
        private String name;
        private String type;
        private List<Object> data = new LinkedList<Object>();
    }
}
