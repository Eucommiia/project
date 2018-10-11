package org.meng.demo.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.meng.demo.common.ResultModel;
import org.meng.demo.common.validator.ValidatorUtils;
import org.meng.demo.common.validator.group.AddGroup;
import org.meng.demo.model.SysUser;
import org.meng.demo.model.SysUserVo;
import org.meng.demo.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户列表
 *
 * @author
 * @date 2018-09-25 09:30:31
 */
@RestController
@RequestMapping("/api")
@Api(value = "用户列表")
public class SysUserController extends AbstractController {

    @Autowired
    private SysUserService userService;

    /**
     * 用户信息
     *
     * @return
     */
    @GetMapping("/userInfo")
    @ApiOperation(value = "用户信息")
    public ResultModel getUserInfo() {
        return ResultModel.success(getUser());
    }

    /**
     * 获取用户列表
     *
     * @return
     */
    @GetMapping("/userList")
    @ApiOperation(value = "用户列表➕分页信息")
    public ResultModel getUserList(@RequestParam(name = "pageNum") int pageNum, @RequestParam(name = "pageSize") int pageSize) {
        PageInfo<SysUser> userInfo = userService.getUserList(pageNum, pageSize);
        return ResultModel.success(userInfo);
    }

    /**
     * 获取用户列表不包含密码
     *
     * @return
     */
    @GetMapping("/list")
    @ApiOperation(value = "获取用户列表不包含密码")
    @RequiresPermissions("sys:user:list")
    public List<SysUserVo> userVoList() {
        List<SysUserVo> list = userService.getList();
        return list;
    }

    /**
     * 用户注册
     *
     * @param sysUser
     * @return
     */
    @PostMapping("/regUser")
    @ApiOperation(value = "用户注册")
    public ResultModel regUser(@RequestBody SysUser sysUser) {
        ValidatorUtils.validateEntity(sysUser, AddGroup.class);
        int flag = userService.regUser(sysUser);
        if (flag == -1) {
            return ResultModel.fail(400, "已存在用户名");
        } else {
            return ResultModel.success(flag);
        }
    }
}
