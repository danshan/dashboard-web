package com.dianping.wed.monitor.dao;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * @author dan.shan
 * @since 2015-06-03 10:10
 */
public interface MonitorDao {

    public List<JSONObject> findByQuery(String collectionName, String query);

}
