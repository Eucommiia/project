package org.meng.demo.dao;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 通用mapper，所有的mapper都继承她
 *
 * @param <T>
 */
public interface SysBaseMapper<T> extends Mapper<T>,MySqlMapper<T> {
}
