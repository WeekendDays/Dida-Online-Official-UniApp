package com.yang.emos.wx.controller;

import com.yang.emos.wx.common.util.R;
import com.yang.emos.wx.config.shiro.JWTUtil;
import com.yang.emos.wx.controller.form.RegisterForm;
import com.yang.emos.wx.controller.form.loginForm;
import com.yang.emos.wx.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
@Api("用户模块web接口")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private RedisTemplate redisTemplate;
    @Value("${emos.jwt.cache-expire}")
    private int cacheExpire; //过期时间
    //注册方法，接收客户端提交的数据
    @PostMapping("/register")
    @ApiOperation("注册用户")
    public R register(@Valid @RequestBody RegisterForm form){
        int id = userService.registerUser(form.getRegisterCode(), form.getCode(), form.getNickname(), form.getPhoto());
        String token = jwtUtil.createToken(id);
        Set<String> permsSet = userService.searchUserPermissions(id);
        saveCacheToken(token,id);
        return R.ok("用户注册成功").put("token",token).put("permission",permsSet);
    }
    @PostMapping("/login")
    @ApiOperation("登录系统")
    public R login(@Valid @RequestBody loginForm form){
        int id = userService.login(form.getCode());
        String token = jwtUtil.createToken(id);
        saveCacheToken(token,id);
        Set<String> permSet = userService.searchUserPermissions(id);
        return R.ok("登录成功").put("token",token).put("permission",permSet);
    }
    @RequestMapping("/searchUserSummary")
    @ApiOperation(("查询用户摘要信息"))
    public R searchUserSummary(@RequestHeader("token") String token){
        int userId = jwtUtil.getUserId(token);
        HashMap map = userService.searchUserSummary(userId);
        return R.ok().put("result",map);
    }
    private void saveCacheToken(String token, int userId){
        redisTemplate.opsForValue().set(token,userId+"",cacheExpire, TimeUnit.DAYS);
    }
}
