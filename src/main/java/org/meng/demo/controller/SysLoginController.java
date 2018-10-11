package org.meng.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.meng.demo.common.ResultModel;
import org.meng.demo.model.SysUser;
import org.meng.demo.shiro.ShiroUtils;
import org.meng.demo.utils.TokenGenerator;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 登录
 *
 * @author Lu1FFy
 * @date 2018年09月19日14:40:41
 */
@RestController
@RequestMapping("/api")
@Api(value = "用户登录")
public class SysLoginController extends AbstractController {

    @PostMapping("/login")
    @ApiOperation(value = "用户登录")
    public ResultModel login(@RequestBody SysUser sysUser) {
        try {
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(sysUser.getUsername(), sysUser.getPassword());
            subject.login(token);
        } catch (IncorrectCredentialsException e) {
            return ResultModel.fail(400, "账号或密码不正确");
        } catch (AuthenticationException e) {
            return ResultModel.fail(401, "验证失败");
        }
        return ResultModel.success(TokenGenerator.generateValue(getUser().toString()));
    }

    @ApiOperation(value = "用户注销")
    @PostMapping("/loginOut")
    public ResultModel loginOut(HttpServletRequest request) {
        ShiroUtils.logout();
        return ResultModel.success("注销成功");
    }

}
