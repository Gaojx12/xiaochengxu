package cn.homyit.service;

import cn.homyit.Dto.CarouselDto;
import cn.homyit.entity.Carousel;
import cn.homyit.entity.Result;

import java.util.List;


public interface CarouselService {

    //1.获取轮播图
    List<Carousel> listAll();

    //3.按id获取轮播图
    Result getCarouselById(Long id);

    //4.增加轮播图
    Result addCarousel(CarouselDto carouselDto);

    //5.更新轮播图
    Result updateCarousel(CarouselDto carouselDto);

    //6.删除轮播图
    Result deleteCarousel(Long id);

    //boolean addCarouselWithImage(Carousel carousel);


}