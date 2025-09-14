package cn.homyit.service;

import cn.homyit.Dto.UserLoginDto;
import cn.homyit.Dto.UserRegisterDto;
import cn.homyit.Dto.UserUpdateDto;
import cn.homyit.entity.Result;
import cn.homyit.entity.UserSignIn;

import java.util.List;

public interface UserService {

    Result login(UserLoginDto dto);

    Result logout();

    Result register(UserRegisterDto dto);

    Result getUserInfo(Long id);

    Result updateUserInfo(UserUpdateDto dto);

    Result updatePhoto(String photoUrl);

    Result resetPassword(String username, String newPassword);

    Result userSignIn(Long userId);

    List<UserSignIn> getSignInHistory(Long userId);



}