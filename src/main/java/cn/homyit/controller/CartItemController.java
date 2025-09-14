package cn.homyit.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import cn.homyit.Dto.CartItemDto;
import cn.homyit.entity.Result;
import cn.homyit.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
@SaCheckLogin
public class CartItemController {

    @Autowired
    private CartItemService cartService;

    /**
     * 将商品加入购物车
     * @param productId
     * @param skuId
     * @param quantity
     * @return
     */
    @PostMapping("/add")
    public Result addCartItem(@RequestParam Long productId,@RequestParam Long skuId, @RequestParam Integer quantity) {
        return cartService.addCartItem(StpUtil.getLoginIdAsLong(), productId, skuId,quantity);
    }


    /**
     * 从购物车中移除商品
     * @param productId
     * @param skuId
     * @return
     */
    @DeleteMapping("/remove")
    public Result removeCartItem(@RequestParam Long productId,@RequestParam Long skuId) {
        return cartService.removeCartItem(StpUtil.getLoginIdAsLong(), productId,skuId);
    }


    /**
     * 获取用户购物车的所有商品
     *
     * @return
     */
    @GetMapping("/list")
    public List<CartItemDto> getCartItems() {
        return cartService.getCartItems(StpUtil.getLoginIdAsLong());
    }
}

