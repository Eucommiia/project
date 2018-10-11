package org.meng.demo.service;

import org.meng.demo.dao.sys.SysMenuRoleDao;
import org.meng.demo.model.SysMenuRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import java.util.ArrayList;
import java.util.List;

/**
 * @author
 * @date
 */
@Service
public class SysMenuRoleService {

    @Autowired
    private SysMenuRoleDao sysMenuRoleDao;

    public List<Integer> getMenuId(List<Integer> roleId) {
        List<Integer> rids = new ArrayList<Integer>();
        if (null != roleId && roleId.size() > 0) {
            Example example = new Example(SysMenuRole.class);
            Criteria criteria = example.createCriteria();
            criteria.andIn("roleId", roleId);
            List<SysMenuRole> sysMenuRoles = sysMenuRoleDao.selectByExample(example);
            for (SysMenuRole roles : sysMenuRoles) {
                rids.add(roles.getMenuId());
            }
        }
        return rids;
    }
}
