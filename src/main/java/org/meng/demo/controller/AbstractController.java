package org.meng.demo.controller;


import org.apache.shiro.SecurityUtils;
import org.meng.demo.model.SysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Controller公共组件
 *
 * @author Lu1FFy
 * @date 2018-09-25 16:50:49
 */
public abstract class AbstractController {

    /**
     * 日志
     */
    protected Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 获取用户信息
     *
     * @return
     */
    protected SysUser getUser() {
        return (SysUser) SecurityUtils.getSubject().getPrincipal();
    }

    /**
     * 获取用户ID
     *
     * @return
     */
    protected long userId() {
        return getUser().getId();
    }
}
