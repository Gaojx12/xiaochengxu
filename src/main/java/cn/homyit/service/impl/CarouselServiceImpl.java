package cn.homyit.service.impl;

import cn.homyit.Dto.CarouselDto;
import cn.homyit.entity.Carousel;
import cn.homyit.entity.Result;
import cn.homyit.mapper.CarouselMapper;
import cn.homyit.service.CarouselService;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;


import java.util.List;

//具体实现类
@Service
public class CarouselServiceImpl extends ServiceImpl<CarouselMapper, Carousel> implements CarouselService {

    @Override
    public List<Carousel> listAll() {
        return list();
    }

    @Override
    public Result getCarouselById(Long id) {
        //检查id是否存在数据库中
        Carousel carousel = this.getById(id);
        if (carousel == null) {
            return Result.fail("轮播图获取失败");
        }
        return Result.<Carousel>success("增加成功", carousel);
    }

    @Override
    public Result addCarousel(CarouselDto carouselDto) {

        Carousel carousel = new Carousel();
        carousel.setTitle(carouselDto.getTitle());
        carousel.setImageUrl(carouselDto.getImageUrl());
        carousel.setDescription(carouselDto.getDescription());

        boolean success = save(carousel);
        if (!success) {
            return Result.fail("新增轮播图失败");
        }
        return Result.success(carousel);
    }


    @Override
    public Result updateCarousel(CarouselDto carouselDto) {
        // 创建 UpdateWrapper，用于设置更新条件
        UpdateWrapper<Carousel> updateWrapper = new UpdateWrapper<>();

        // 根据 id 作为更新条件
        updateWrapper.eq("id", carouselDto.getId());

        Carousel carousel = new Carousel();


        if (carouselDto.getTitle() != null) {
            carousel.setTitle(carouselDto.getTitle());
        }
        if (carouselDto.getImageUrl() != null) {
            carousel.setImageUrl(carouselDto.getImageUrl());
        }
        if (carouselDto.getDescription() != null) {
            carousel.setDescription(carouselDto.getDescription());
        }

        if (update(carousel, updateWrapper)) {
            return Result.success("轮播图更新成功");
        } else {

            return Result.fail("轮播图更新失败");
        }
    }


    @Override
    public Result deleteCarousel(Long id) {
        if (removeById(id)) {
            return Result.success("删除成功");
        } else {
            return Result.fail("删除失败");
        }
    }

//    @Override
//    public boolean addCarouselWithImage(Carousel carousel) {
//        return save(carousel);  // MyBatis-Plus 提供的保存方法
//    }
}
