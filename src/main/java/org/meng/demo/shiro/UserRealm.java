package org.meng.demo.shiro;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.meng.demo.common.Constant;
import org.meng.demo.common.exception.DemoException;
import org.meng.demo.dao.sys.MenuDao;
import org.meng.demo.dao.sys.SysUserDao;
import org.meng.demo.model.Menu;
import org.meng.demo.model.SysUser;
import org.meng.demo.service.MenuService;
import org.meng.demo.service.SysMenuRoleService;
import org.meng.demo.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * 自定义用户登录Realm
 *
 * @author
 * @date 2018-09-25 16:58:46
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private SysUserDao sysUserDao;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Autowired
    private MenuDao menuDao;


    @Autowired
    private MenuService menuService;

    @Autowired
    private SysMenuRoleService sysMenuRoleService;

    /**
     * 授权
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SysUser user = (SysUser) principalCollection.getPrimaryPrincipal();
        Integer userId = user.getId();
        List<String> permsList = null;
        List<Integer> menuList;
        List<Integer> role;
        // 如果是管理员查询所有的权限
        if (userId == Constant.SUPER_ADMIN) {
            // 拥有所有的权限
            List<Menu> menus = menuDao.selectAll();
            permsList = new ArrayList<>(menus.size());
            for (Menu menu : menus) {
                permsList.add(menu.getPerms());
            }
        } else {
            // 根据用户查询权限集合
            // 根据用户ID查询角色ID
            role = sysUserRoleService.getRoleId(userId);
            if (role.size() > 0) {
                // 根据角色Id查询资源Id;
                menuList = sysMenuRoleService.getMenuId(role);
                if (menuList.size() > 0) {
                    // 根据资源ID查询权限
                    List<Menu> menuList1 = menuService.getPers(menuList);
                    for (Menu item : menuList1) {
                        permsList = new ArrayList<>();
                        permsList.add(item.getPerms());
                    }
                } else {
                    throw new DemoException("您还没有权限");
                }
            } else {
                throw new DemoException("您还没有分配角色");
            }
        }
        Set<String> permSet = new HashSet<>();
        for (String perms : permsList) {
            if (StringUtils.isBlank(perms)) {
                continue;
            }
            permSet.addAll(Arrays.asList(perms.trim().split(",")));
        }
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setStringPermissions(permSet);
        return simpleAuthorizationInfo;
    }

    /**
     * 认证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        System.out.println("用户名===" + token.getPrincipal());
        System.out.println("密码===" + token.getCredentials());
        SysUser sysUser = new SysUser();
        sysUser.setUsername(token.getUsername());
        sysUser = sysUserDao.selectOne(sysUser);

        if (sysUser == null) {
            throw new UnknownAccountException("账号名或密码不正确");
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(sysUser, sysUser.getPassword(), ByteSource.Util.bytes(sysUser.getSalt()), getName());
        System.out.println("info的信息+++++++" + info.getCredentials());
        return info;
    }

    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        HashedCredentialsMatcher shaCredentialsMatcher = new HashedCredentialsMatcher();
        shaCredentialsMatcher.setHashAlgorithmName(ShiroUtils.hashAlgorithmName);
        shaCredentialsMatcher.setHashIterations(ShiroUtils.hashIterations);
        super.setCredentialsMatcher(shaCredentialsMatcher);
    }
}
