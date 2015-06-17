package com.dianping.wed.monitor.data.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Beanlist处理工具类
 * 
 * @author pumaboyd
 * 
 */
public class BeanListUtil {


	
	/**
	 * list对象Copy，主要满足 DTO多Entity的List对象copy
	 * @param source
	 * @param target
	 * @return
	 */
	public static <T, E> List<E> copyPropertiesArrayList(List<T> source, Class<E> target) {

		List<E> es = new ArrayList<E>();

		try {

			if (CollectionUtils.isNotEmpty(source)) {

				for (T t : source) {
					E e = target.newInstance();
					BeanUtils.copyProperties(t, e);
					es.add(e);
				}
			}

		} catch (Exception e) {

		}

		return es;

	}

    public static <T, E> E copyProperties(T source, Class<E> target) {
        if (source == null) {
            return null;
        }

        try {
            return JSON.parseObject(JSON.toJSONString(source), target);
        } catch (Exception e) {
        }
        return null;
    }


	public static <T, E> E copyProperties(T source, Class<E> target, String[] ignorePropertie) {
		if (source == null) {
			return null;
		}

		try {
			if (ignorePropertie == null || ignorePropertie.length == 0) {
				return JSON.parseObject(JSON.toJSONString(source), target);
			} else {
				SimplePropertyPreFilter filter = new SimplePropertyPreFilter();
				for (String ignore : ignorePropertie) {
					filter.getExcludes().add(ignore);
				}
				return JSON.parseObject(JSON.toJSONString(source, filter), target);
			}
		} catch (Exception e) {
		}
		return null;
	}

}
