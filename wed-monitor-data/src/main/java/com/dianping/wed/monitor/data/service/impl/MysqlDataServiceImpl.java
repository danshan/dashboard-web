package com.dianping.wed.monitor.data.service.impl;

import com.dianping.wed.monitor.data.service.MonitorDataService;
import com.dianping.wed.monitor.data.service.dto.MonitorDataDTO;
import com.dianping.wed.monitor.data.service.dto.MonitorQueryDTO;
import com.google.common.collect.Lists;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import lombok.Setter;
import org.apache.commons.collections.ListUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author dan.shan
 * @since 2015-06-18 16:09
 */
public class MysqlDataServiceImpl implements MonitorDataService {

    @Setter
    private JdbcTemplate jdbcTemplate;

    @Override
    public MonitorDataDTO findDataByQuery(MonitorQueryDTO query) {
        SqlRowSet rs = jdbcTemplate.queryForRowSet(query.getQuery());
        List<String> keys = parseKeys(query, rs);

        MonitorDataDTO dataDTO = new MonitorDataDTO();
        List<List<String>> data = Lists.newLinkedList();

        List<String> list;
        while (rs.next()) {
            list = Lists.newLinkedList();
            for (String key : keys) {
                list.add(rs.getObject(key).toString());
            }
            data.add(list);
        }
        dataDTO.setData(data);
        dataDTO.setColumns(keys);

        return dataDTO;
    }

    private List<String> parseKeys(MonitorQueryDTO query, SqlRowSet rs) {
        List<String> list = new LinkedList<String>(Arrays.asList(rs.getMetaData().getColumnNames()));
        // 把xAxis 提前
        list.remove(query.getXaxis());
        list.add(0, query.getXaxis());
        return list;
    }

}
