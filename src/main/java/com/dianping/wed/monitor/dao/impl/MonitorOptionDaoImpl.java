package com.dianping.wed.monitor.dao.impl;

import com.alibaba.fastjson.JSON;
import com.dianping.wed.monitor.dao.MonitorOptionDao;
import com.dianping.wed.monitor.dao.entity.MonitorOption;
import com.google.code.morphia.Morphia;
import com.google.code.morphia.dao.BasicDAO;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mongodb.Mongo;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author dan.shan
 * @since 2015-06-04 16:27
 */
public class MonitorOptionDaoImpl extends BasicDAO<MonitorOption, String> implements MonitorOptionDao {

    protected MonitorOptionDaoImpl(Mongo mongo, Morphia morphia, String dbName) {
        super(mongo, morphia, dbName);
        ds.ensureCaps();
        ds.ensureIndexes();
    }

    @Override
    public MonitorOption loadOptionByPageId(int pageId) {
        MonitorOption option = new MonitorOption();

        Map<String, Object> map = Maps.newHashMap();

        Map<String, Object> title = Maps.newHashMap();
        title.put("text", "未来一周气温变化");
        title.put("subtext", "纯属虚构");
        map.put("title", title);

        Map<String, Object> tooltip = Maps.newHashMap();
        tooltip.put("trigger", "axis");
        map.put("tooltip", tooltip);

        Map<String, Object> legend = Maps.newHashMap();
        legend.put("data", new LinkedList<String>());
        map.put("legend", legend);

        Map<String, Object> xAxis = Maps.newHashMap();
        xAxis.put("type", "category");
        map.put("xAxis", xAxis);

        Map<String, Object> yAxis = Maps.newHashMap();
        yAxis.put("type", "value");
        map.put("yAxis", yAxis);

        List<Map<String, Object>> series = Lists.newLinkedList();
        Map<String, Object> series1 = Maps.newHashMap();
        series1.put("name", "line1");
        series1.put("type", "line");
        series.add(series1);

        Map<String, Object> series2 = Maps.newHashMap();
        series2.put("name", "line2");
        series2.put("type", "line");
        series.add(series2);
        map.put("series", series);

        option.setOption(JSON.toJSONString(map));
        return option;
    }
}
