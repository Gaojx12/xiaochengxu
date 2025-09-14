package cn.homyit.mapper;

import cn.homyit.entity.UserCoupon;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserCouponMapper {
    //领取完优惠券，加入用户优惠券中
    @Insert("INSERT INTO user_coupon (user_id, coupon_id, status,create_time) VALUES (#{userId}, #{couponId}, 'UNUSED',#{createTime})")
    void insertUserCoupon(UserCoupon userCoupon);

    //获取该用户的某张优惠券的信息
    @Select("SELECT * FROM user_coupon WHERE user_id = #{userId} AND coupon_id = #{couponId} AND status = 'UNUSED'")
    UserCoupon findUserCoupon(@Param("userId") Long userId, @Param("couponId") Long couponId);

    //修改用户优惠券的使用状态
    @Update("UPDATE user_coupon SET status = 'USED', create_time = NOW() WHERE user_id = #{userId} AND coupon_id = #{couponId} AND status = 'UNUSED'")
    void useCoupon(@Param("userId") Long userId, @Param("couponId") Long couponId);

    //获取用户所有可用的优惠券
    @Select("SELECT * FROM user_coupon WHERE user_id = #{userId}")
    List<UserCoupon> findUserCoupons(@Param("userId") Long userId);
}
