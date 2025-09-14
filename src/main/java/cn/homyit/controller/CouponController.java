package cn.homyit.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import cn.homyit.entity.Coupon;
import cn.homyit.entity.Result;
import cn.homyit.entity.UserCoupon;
import cn.homyit.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coupons")
public class CouponController {

    @Autowired
    private CouponService couponService;

    /**
     * 获取所有可用优惠券
     *
     * @return
     */
    @GetMapping("/list")
    public List<Coupon> getAvailableCoupons() {
        return couponService.getAvailableCoupons();
    }

    /**
     * 获取用户优惠券
     *
     * @return
     */
    @GetMapping("/listuser")
    @SaCheckLogin
    public List<UserCoupon> getUserCoupons() {
        return couponService.getUserCoupons(StpUtil.getLoginIdAsLong());
    }

    /**
     * 用户领取优惠券
     *
     * @param couponId
     * @return
     */
    @PostMapping("/receive")
    @SaCheckLogin
    public Result receiveCoupon(@RequestParam Long couponId) {
        return couponService.receiveCoupon(StpUtil.getLoginIdAsLong(), couponId);
    }


    /**
     * 用户使用优惠券
     *
     * @param couponId
     * @return
     */
    @PostMapping("/use")
    @SaCheckLogin
    public Result useCoupon(@RequestParam Long couponId) {
        return couponService.useCoupon(StpUtil.getLoginIdAsLong(), couponId);
    }
}

