package cn.homyit.service;

import cn.homyit.entity.Coupon;
import cn.homyit.entity.Result;
import cn.homyit.entity.UserCoupon;

import java.util.List;

public interface CouponService {
    //获取全部可用的优惠券
    List<Coupon> getAvailableCoupons();

    //获取用户全部的优惠券
    List<UserCoupon> getUserCoupons(Long userId);

    //领取优惠券
    Result receiveCoupon(Long userId, Long couponId);

    //使用优惠券
    Result useCoupon(Long userId, Long couponId);


}
