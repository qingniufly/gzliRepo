package com.simon.springmvc.common.dao;

import java.util.List;

/**
 * @Author  : simon
 * @version : Aug 1, 2014 12:32:55 PM
 *
 **/
public interface BaseDao<T> {

	List<T> get(String id);

}
