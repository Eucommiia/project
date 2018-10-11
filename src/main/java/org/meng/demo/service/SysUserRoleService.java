package org.meng.demo.service;

import org.meng.demo.dao.sys.SysUserRoleDao;
import org.meng.demo.model.SysUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * @author
 * @date
 */
@Service
public class SysUserRoleService {

    @Autowired
    private SysUserRoleDao sysUserRoleDao;

    /**
     * 根据用户ID查询角色ID
     * @param userId
     * @return
     */
    public List<Integer> getRoleId(Integer userId) {
        List<Integer> roleIds = new ArrayList<Integer>();
        Example example = new Example(SysUserRole.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId", userId);
        List<SysUserRole>  userRoles = sysUserRoleDao.selectByExample(example);
        for(SysUserRole user :userRoles){
            roleIds.add(user.getRoleId());
        }
        return roleIds;
    }
}
