package com.dianping.wed.monitor.data.dao;

import com.alibaba.fastjson.JSONObject;
import com.dianping.wed.monitor.data.dao.entity.MongoDataQuery;

import java.util.List;

/**
 * @author dan.shan
 * @since 2015-06-03 10:10
 */
public interface MonitorDataMongoDao {

    public List<JSONObject> findByQuery(MongoDataQuery query);

}
