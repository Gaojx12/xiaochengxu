package cn.homyit.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.homyit.Dto.UserLoginDto;
import cn.homyit.Dto.UserRegisterDto;
import cn.homyit.Dto.UserUpdateDto;
import cn.homyit.entity.Result;
import cn.homyit.entity.User;
import cn.homyit.entity.UserSignIn;
import cn.homyit.mapper.UserMapper;
import cn.homyit.mapper.UserSignInMapper;
import cn.homyit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    //用户积分
    @Autowired
    private UserSignInMapper userSignInMapper;

    @Override
    public Result login(UserLoginDto dto) {
        //查询用户
        User user = userMapper.selectByUsername(dto.getUsername());
        if (user == null) {
            return Result.fail("用户名不存在");
        }
        String encrypted = DigestUtils.md5DigestAsHex(dto.getPassword().getBytes());
        if (!encrypted.equals(user.getPassword())) {
            return Result.fail("用户名或密码错误");
        }
        //登录操作
        StpUtil.login(user.getId());
        return Result.success("登录成功", StpUtil.getTokenValue());
    }

    @Override
    public Result logout() {
        StpUtil.logout();
        return Result.success("退出成功", true);
    }

    @Override
    public Result register(UserRegisterDto dto) {
        User user1 = userMapper.selectByUsername(dto.getUsername());
        if (user1 != null) {
            return Result.fail("注册失败 该用户名已存在");
        }

        // 转换 DTO -> 实体
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(DigestUtils.md5DigestAsHex(dto.getPassword().getBytes()));
        user.setTel(dto.getTel());
        user.setSex(dto.getSex());
        user.setBirth(dto.getBirth());
        user.setCity(dto.getCity());
        user.setPoint(0);
        user.setCreateTime(LocalDateTime.now());
        userMapper.insert(user);

        // 自动登录
//        StpUtil.login(user.getId());
        return Result.success("注册成功");

    }

    //获取用户信息
    @Override
    public Result getUserInfo(Long id) {
        User user = userMapper.selectById(id);
        if (user == null) {
            return Result.fail("用户不存在");
        }
        Map<String, Object> userInfo = new LinkedHashMap<>();
        userInfo.put("username", user.getUsername());
        userInfo.put("sex", user.getSex());
        userInfo.put("birth", user.getBirth());
        userInfo.put("city", user.getCity());
        userInfo.put("tel", user.getTel());
        userInfo.put("point", user.getPoint());
        userInfo.put("createTime", user.getCreateTime());
        userInfo.put("photoUrl", user.getPhotoUrl());
        return Result.success("查询成功", userInfo);
    }

    /**
     * 修改个人信息
     */
    @Override
    public Result updateUserInfo(UserUpdateDto dto) {
        Long userId = StpUtil.getLoginIdAsLong();
        User user = userMapper.selectById(userId);
        if (user == null) {
            return Result.fail("用户不存在");
        }

        // 更新可修改字段
        if (dto.getUsername() != null) user.setUsername(dto.getUsername());
        if (dto.getTel() != null) user.setTel(dto.getTel());
        if (dto.getSex() != null) user.setSex(dto.getSex());
        if (dto.getCity() != null) user.setCity(dto.getCity());
        if (dto.getBirth() != null) user.setBirth(dto.getBirth());
        if (dto.getPassword() != null) user.setPassword(dto.getPassword());

        int flag = userMapper.updateUserById(user);
        if (flag > 0) {
            return Result.success("个人信息更新成功");
        } else {
            return Result.fail("更新失败");
        }
    }

    /**
     * 修改头像
     */
    @Override
    public Result updatePhoto(String photoUrl) {
        Long id = StpUtil.getLoginIdAsLong();
        int rows = userMapper.updatePhoto(id, photoUrl);
        if (rows > 0) {
            return Result.success("头像更新成功");
        } else {
            return Result.fail("更新失败");
        }
    }

    //忘记密码
    public Result resetPassword(String username, String newPassword) {
        User user = userMapper.selectByUsername(username);
        if (user == null) {
            return Result.fail("用户不存在");
        }
        userMapper.updatePassword(user.getId(), DigestUtils.md5DigestAsHex(newPassword.getBytes()));
        return Result.success("密码重置成功");
    }

    @Override
    public Result userSignIn(Long userId) {
        // 获取当前日期
        LocalDate currentDate = LocalDate.now();

        // 检查该用户今天是否已签到
        UserSignIn existingSignIn = userSignInMapper.findByUserIdAndDate(userId, currentDate);
        if (existingSignIn != null) {
            return Result.fail("今天已经签过到了"); // 用户已签到
        }

        // 插入签到记录，奖励积分
        UserSignIn signInRecord = new UserSignIn();
        signInRecord.setUserId(userId);
        signInRecord.setSignInDate(currentDate);
        signInRecord.setPoints(10);

        int rows = userSignInMapper.insert(signInRecord);
        if (rows <= 0) {
            return Result.fail("签到失败，请重试");
        }

        // 更新用户积分
        userMapper.updateUserPoints(userId, 10);

        return Result.success("签到成功！您获得了 10 积分。");
    }


    // 获取用户签到历史
    @Override
    public List<UserSignIn> getSignInHistory(Long userId) {
        return userSignInMapper.findSignInHistory(userId);  // 返回签到历史
    }
}