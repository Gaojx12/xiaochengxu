package cn.homyit.service.impl;

import cn.homyit.entity.Coupon;
import cn.homyit.entity.Result;
import cn.homyit.entity.UserCoupon;
import cn.homyit.mapper.CouponMapper;
import cn.homyit.mapper.UserCouponMapper;
import cn.homyit.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CouponServiceImpl implements CouponService {

    @Autowired
    private CouponMapper couponMapper;

    @Autowired
    private UserCouponMapper userCouponMapper;

    //查询所有可用的优惠券
    public List<Coupon> getAvailableCoupons() {
        return couponMapper.findActiveCoupons();
    }

    //查询用户所有的优惠券
    public List<UserCoupon> getUserCoupons(Long userId) {
        return userCouponMapper.findUserCoupons(userId);
    }


    //领取优惠券
    public Result receiveCoupon(Long userId, Long couponId) {

        Coupon coupon = couponMapper.findCouponById(couponId);
        if (coupon == null) {
            return Result.fail("优惠券不存在");
        }
        if (!"ACTIVE".equals(coupon.getStatus()) || coupon.getEndDate().isBefore(LocalDateTime.now())) {
            return Result.fail("优惠券已失效");
        }

        // 查询用户是否已领取该优惠券
        UserCoupon userCoupon = userCouponMapper.findUserCoupon(userId, couponId);
        if (userCoupon != null) {
            return Result.fail("已领取过该优惠券");
        }

        // 领取优惠券
        UserCoupon newUserCoupon = new UserCoupon();
        newUserCoupon.setUserId(userId);
        newUserCoupon.setCouponId(couponId);
        newUserCoupon.setStatus("UNUSED");
        newUserCoupon.setCreateTime(LocalDateTime.now());
        userCouponMapper.insertUserCoupon(newUserCoupon);
        return Result.success("成功领取该优惠券");

    }


    //使用优惠券
    public Result useCoupon(Long userId, Long couponId) {
        // 查询用户是否有未使用的优惠券
        UserCoupon userCoupon = userCouponMapper.findUserCoupon(userId, couponId);
        if (userCoupon == null) {
            return Result.fail("未领取该优惠券");
        }
        if (!"UNUSED".equals(userCoupon.getStatus())) {
            return Result.fail("优惠券已使用或过期");
        }

        // 使用优惠券
        userCouponMapper.useCoupon(userId, couponId);

        return Result.success("已使用该优惠券");
    }
}

