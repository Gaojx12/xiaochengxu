package cn.homyit.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import cn.homyit.Dto.UserLoginDto;
import cn.homyit.Dto.UserRegisterDto;
import cn.homyit.Dto.UserUpdateDto;
import cn.homyit.entity.Result;
import cn.homyit.entity.UserSignIn;
import cn.homyit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
@SaCheckLogin
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 用户登录
     *
     * @param dto
     * @return
     */
    @PostMapping("/login")
    public Result login(@Validated @RequestBody UserLoginDto dto) {
        return userService.login(dto);
    }

    /**
     * 退出
     *
     * @return
     */

    @PostMapping("/logout")
    @SaCheckLogin
    public Result logout() {
        return userService.logout();
    }

    /**
     * 注册用户
     *
     * @param dto
     * @return
     */
    @PostMapping("/register")
    public Result register(@Validated @RequestBody UserRegisterDto dto) {

        return userService.register(dto);
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    @GetMapping("/get")
    @SaCheckLogin
    public Result getUserInfo() {
        return userService.getUserInfo(StpUtil.getLoginIdAsLong());
    }

    /**
     * 修改用户信息
     *
     * @param dto
     * @return
     */
    @PostMapping("/update")
    @SaCheckLogin
    public Result updateUserInfo(@Validated @RequestBody UserUpdateDto dto) {
        return userService.updateUserInfo(dto);
    }

    /**
     * 更新头像
     *
     * @param photoUrl
     * @return
     */
    @PostMapping("/photo")
    @SaCheckLogin
    public Result updatePhoto(@RequestParam String photoUrl) {
        return userService.updatePhoto(photoUrl);
    }

    /**
     * 忘记密码
     *
     * @param username
     * @param newPassword
     * @return
     */
    @PostMapping("/reset-password")
    @SaCheckLogin
    public Result resetPassword(@RequestParam String username,
                                @RequestParam String newPassword) {
        return userService.resetPassword(username, newPassword);
    }

    /**
     * 用户签到
     *
     * @return
     */
    @PostMapping("/sign-in")
    @SaCheckLogin
    public Result signIn() {
        return userService.userSignIn(StpUtil.getLoginIdAsLong());
    }

    /**
     * 获取用户签到历史
     *
     * @return
     */
    @GetMapping("/sign-in/history")
    @SaCheckLogin
    public List<UserSignIn> getSignInHistory() {
        return userService.getSignInHistory(StpUtil.getLoginIdAsLong());
    }

}