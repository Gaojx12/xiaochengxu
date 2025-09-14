package cn.homyit.service.impl;

import cn.homyit.Dto.CartItemDto;
import cn.homyit.entity.CartItem;
import cn.homyit.entity.ProductSku;
import cn.homyit.entity.Result;
import cn.homyit.mapper.CartItemMapper;
import cn.homyit.mapper.ProductSkuMapper;
import cn.homyit.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CartItemServiceImpl implements CartItemService {

    @Autowired
    private CartItemMapper cartItemMapper;
    @Autowired
    private ProductSkuMapper productSkuMapper;

    //将商品加入购物车
    public Result addCartItem(Long userId, Long productId,Long skuId, Integer quantity) {
        // 先检查 SKU 是否存在
        ProductSku sku = productSkuMapper.selectById(skuId);
        if (sku == null) {
            return Result.fail("该商品的规格不存在");
        }

        // 检查购物车是否已有该 SKU
        CartItem existing = cartItemMapper.findCartItem(userId, productId, skuId);
        if (existing != null) {
            existing.setQuantity(quantity);
            cartItemMapper.updateCartItemQuantity(existing);
            return Result.success("购物车数量已更新");
        } else {
            CartItem item = new CartItem();
            item.setUserId(userId);
            item.setProductId(productId);
            item.setSkuId(skuId);
            item.setQuantity(quantity);
            item.setCreateTime(LocalDateTime.now());
            cartItemMapper.insertCartItem(item);
            return Result.success("成功加入购物车");
        }

    }


    //从购物车中移除商品
    public Result removeCartItem(Long userId, Long productId,Long skuId) {
        int rows = cartItemMapper.deleteCartItem(userId, productId,skuId);
        if (rows > 0) {
            return Result.success("成功删除购物车商品");
        } else {
            return Result.fail("购物车商品不存在");
        }

    }

    //获取用户购物车的所有商品
    public List<CartItemDto> getCartItems(Long userId) {
        List<CartItemDto> cartItems = cartItemMapper.findCartItemsByUserId(userId);

        // 确保返回的价格和规格是最新的
        for (CartItemDto item : cartItems) {
            if (item.getSpecs() == null) {
                continue;
            }
            // 如果用户更改了SKU价格，动态读取product_sku价格
            ProductSku sku = productSkuMapper.selectById(item.getProductId());
            if (sku != null) {
                item.setPrice(sku.getPrice());
            }
        }
        return cartItems;
    }
}

