package cn.homyit.mapper;

import cn.homyit.entity.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM user WHERE username = #{username}")
    User selectByUsername(String username);

    @Insert("INSERT INTO user (username, password, sex,birth,city,tel,point,create_time ) " +
            "VALUES( #{username}, #{password},#{sex},#{birth},#{city},#{tel},#{point},#{createTime} )")
    void insert(User user);


    @Update("UPDATE user SET username = #{username},tel = #{tel}, sex = #{sex},city = #{city},birth = #{birth},password = #{password} WHERE id = #{id}")
    int updateUserById(User user);

    @Select("SELECT * FROM user WHERE id = #{id}")
    User selectById(Long id);

    @Update("UPDATE user SET photo_url = #{photoUrl} WHERE id = #{id}")
    int updatePhoto(@Param("id") Long id, @Param("photoUrl") String photoUrl);

    @Update("UPDATE user SET password = #{password} WHERE id = #{id}")
    int updatePassword(@Param("id") Long id, @Param("password") String password);


    // 更新用户的积分
    @Update("UPDATE user SET point = point + #{point} WHERE id = #{id}")
    void updateUserPoints(@Param("id") Long userId, @Param("point") int point);
}