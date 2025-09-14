package cn.homyit.mapper;

import cn.homyit.entity.Coupon;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CouponMapper {
    //获取可用优惠券
    @Select("SELECT * FROM coupon WHERE status = 'ACTIVE' AND end_date > NOW()")
    List<Coupon> findActiveCoupons();

    @Select("SELECT * FROM coupon WHERE id = #{id}")
    Coupon findCouponById(Long id);

}

