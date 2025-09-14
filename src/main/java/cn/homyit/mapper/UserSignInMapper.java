package cn.homyit.mapper;

import cn.homyit.entity.UserSignIn;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface UserSignInMapper extends BaseMapper<UserSignIn> {

    // 根据用户ID和签到日期查询签到记录
    @Select("SELECT * FROM user_sign_in WHERE user_id = #{userId} AND sign_in_date = #{signInDate} LIMIT 1")
    UserSignIn findByUserIdAndDate(@Param("userId") Long userId, @Param("signInDate") LocalDate signInDate);

    // 插入签到记录
    @Insert("INSERT INTO user_sign_in(user_id, sign_in_date, points) VALUES(#{userId}, #{signInDate}, #{points})")
    int insert(UserSignIn userSignIn);

    // 查询用户的签到历史
    @Select("SELECT * FROM user_sign_in WHERE user_id = #{userId} ORDER BY sign_in_date DESC")
    List<UserSignIn> findSignInHistory(@Param("userId") Long userId);
}
