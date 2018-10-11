package org.meng.demo.dao.sys;

import org.apache.ibatis.annotations.Mapper;
import org.meng.demo.dao.SysBaseMapper;
import org.meng.demo.model.SysMenu;

import java.util.List;

/**
 * 菜单
 *
 * @author
 * @date
 */
@Mapper
public interface SysMenuDao extends SysBaseMapper<SysMenu> {

    //List<String> selectPermList(Integer userId);
}
