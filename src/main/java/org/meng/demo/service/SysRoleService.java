package org.meng.demo.service;


import org.meng.demo.dao.sys.SysUserRoleDao;
import org.meng.demo.model.SysUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author
 * @date
 */
@Service
public class SysRoleService {

    @Autowired
    private SysUserRoleDao sysUserRoleDao;

    public void saveUserRole(Integer userId, List<Integer> roleId) {
        Example example = new Example(SysUserRole.class);
        Example.Criteria
                criteria = example.createCriteria();
        criteria.andEqualTo("userId", userId);
        // 删除原有的关联
        sysUserRoleDao.deleteByExample(example);
        if (roleId==null||roleId.size() == 0) {
            return;
        }
        SysUserRole sysUserRole;
        for (Integer roleIds : roleId) {
            sysUserRole = new SysUserRole();
            sysUserRole.setUserId(userId);
            sysUserRole.setRoleId(roleIds);
            sysUserRoleDao.insertSelective(sysUserRole);
        }
    }


}
