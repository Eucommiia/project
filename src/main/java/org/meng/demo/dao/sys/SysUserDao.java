package org.meng.demo.dao.sys;

import org.apache.ibatis.annotations.Mapper;
import org.meng.demo.dao.SysBaseMapper;
import org.meng.demo.model.SysUser;
import org.meng.demo.model.SysUserVo;

import java.util.List;

/**
 * 用户
 *
 * @date 2018-09-19 14:32:15
 * @author Lu1FFy
 */
@Mapper
public interface SysUserDao extends SysBaseMapper<SysUser> {

    /**
     * 用户列表 不包含密码
     *
     * @return
     */
    List<SysUserVo>  userList();
}
