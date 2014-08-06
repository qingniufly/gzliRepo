package com.simon.springmvc.common.dao;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

/**
 * @Author  : simon
 * @version : Aug 1, 2014 12:32:14 PM
 *
 **/
public abstract class ImplBaseDao<T> extends JdbcDaoSupport implements BaseDao<T> {

}
