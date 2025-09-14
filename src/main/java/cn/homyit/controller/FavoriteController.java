package cn.homyit.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import cn.homyit.Dto.FavoriteDto;
import cn.homyit.entity.Result;
import cn.homyit.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favorite")
@SaCheckLogin
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    /**
     * 增加商品到收藏夹中
     * @param productId
     * @param skuId
     * @return
     */
    @PostMapping("/add")
    public Result addFavorite(@RequestParam Long productId,@RequestParam Long skuId) {
        return favoriteService.addFavorite(StpUtil.getLoginIdAsLong(), productId,skuId);

    }

    /**
     * 从收藏夹中移除商品
     * @param productId
     * @param skuId
     * @return
     */
    @DeleteMapping("/remove")
    public Result removeFavorite(@RequestParam Long productId,@RequestParam Long skuId) {
        return favoriteService.removeFavorite(StpUtil.getLoginIdAsLong(), productId,skuId);

    }


    /**
     * 获取用户所有的收藏商品
     * @return
     */
    @GetMapping("/list")
    public List<FavoriteDto> getFavorites() {
        return favoriteService.getFavorites(StpUtil.getLoginIdAsLong());

    }
}

