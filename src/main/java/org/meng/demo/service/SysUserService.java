package org.meng.demo.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.RandomStringUtils;
import org.meng.demo.dao.sys.SysUserDao;
import org.meng.demo.model.SysUser;
import org.meng.demo.model.SysUserVo;
import org.meng.demo.shiro.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户
 *
 * @author Lu1FFy
 * @date 2018-09-19 14:49:01
 */
@Service
public class SysUserService {

    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 判断用户是否存在
     *
     * @param sysUser
     * @return
     */
    public SysUser userExits(SysUser sysUser) {
        return sysUserDao.selectOne(sysUser);
    }

    /**
     * 获取所有的用户列表
     *
     * @return
     */
    public PageInfo<SysUser> getUserList(int pageSiNum, int pageSize) {
        PageHelper.startPage(pageSiNum, pageSize);
        List<SysUser> userList = sysUserDao.selectAll();
        PageInfo<SysUser> userInfo = new PageInfo<>(userList);
        return userInfo;
    }

    public List<SysUserVo> getList() {
        return sysUserDao.userList();
    }

    /**
     * @param sysUser
     * @return
     */
    public Integer regUser(SysUser sysUser) {
        Integer userId = 0;
        String salt = RandomStringUtils.randomAlphanumeric(20);
        sysUser.setSalt(salt);
        sysUser.setPassword(ShiroUtils.sha256(sysUser.getPassword(), sysUser.getSalt()));
        // 判断是否存在该用户
        SysUser user = sysUserDao.selectOne(sysUser);
        if (user == null) {
            sysUserDao.insert(sysUser);
            userId = sysUser.getId();
            sysRoleService.saveUserRole(sysUser.getId(), sysUser.getRoleId());
            return userId;
        } else {
            return -1;
        }
    }

}
