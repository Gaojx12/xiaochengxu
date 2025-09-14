package cn.homyit.service;

import cn.homyit.Dto.CartItemDto;
import cn.homyit.entity.Result;

import java.util.List;

public interface CartItemService {
    Result addCartItem(Long userId, Long productId, Long skuId,Integer quantity);

    Result removeCartItem(Long userId, Long productId,Long skuId);

    List<CartItemDto> getCartItems(Long userId);


}
