package cn.homyit.service;


import cn.homyit.Dto.FavoriteDto;
import cn.homyit.entity.Result;

import java.util.List;

public interface FavoriteService {
    Result addFavorite(Long userId, Long productId,Long skuId);

    Result removeFavorite(Long userId, Long productId,Long skuId);

    List<FavoriteDto> getFavorites(Long userId);
}

