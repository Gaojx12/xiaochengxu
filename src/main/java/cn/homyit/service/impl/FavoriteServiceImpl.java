package cn.homyit.service.impl;

import cn.homyit.Dto.FavoriteDto;
import cn.homyit.entity.CartItem;
import cn.homyit.entity.Favorite;
import cn.homyit.entity.ProductSku;
import cn.homyit.entity.Result;
import cn.homyit.mapper.CartItemMapper;
import cn.homyit.mapper.FavoriteMapper;
import cn.homyit.mapper.ProductSkuMapper;
import cn.homyit.service.FavoriteService;
import cn.homyit.service.ProductSpecService;
import cn.homyit.service.ProductSpecSkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FavoriteServiceImpl implements FavoriteService {

    @Autowired
    private FavoriteMapper favoriteMapper;
    @Autowired
    private ProductSkuMapper productSkuMapper;

    //添加商品到收藏夹
    @Override
    public Result addFavorite(Long userId, Long productId,Long skuId) {
        ProductSku sku = productSkuMapper.selectById(skuId);
        if (sku == null) {
            return Result.fail("该商品的规格不存在");
        }

        Favorite existing = favoriteMapper.findByUserIdAndSkuId(userId, productId, skuId);
        if (existing != null) {
            return Result.fail("商品已收藏");
        } else {
            Favorite item = new Favorite();
            item.setUserId(userId);
            item.setProductId(productId);
            item.setSkuId(skuId);
            item.setCreateTime(LocalDateTime.now());
            favoriteMapper.addFavorite(item);
            return Result.success("已成功收藏");
        }
    }


    //从收藏夹中移除商品
    @Override
    public Result removeFavorite(Long userId, Long productId,Long skuId) {
        if (favoriteMapper.exists(userId, productId,skuId) == 0) {
            return Result.fail("商品未收藏，无法取消");
        }
        favoriteMapper.removeFavorite(userId, productId,skuId);
        return Result.success("取消收藏成功");
    }


    //获取用户的所有收藏商品
    @Override
    public List<FavoriteDto> getFavorites(Long userId) {

        return favoriteMapper.getFavoriteProducts(userId);
    }
}
