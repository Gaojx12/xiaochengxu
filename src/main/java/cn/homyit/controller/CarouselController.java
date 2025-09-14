package cn.homyit.controller;

import cn.homyit.Dto.CarouselDto;
import cn.homyit.entity.Carousel;
import cn.homyit.entity.Result;
import cn.homyit.service.CarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carousel")
public class CarouselController {

    @Autowired
    private CarouselService carouselService;

    /**
     * 获取轮播图列表
     *
     * @return
     */
    @GetMapping("/list")
    public List<Carousel> listAll() {
        return carouselService.listAll();
    }

    /**
     * 按id获取轮播图
     *
     * @param id
     * @return
     */

    @GetMapping("/get/{id}")
    public Result getCarouselById(@PathVariable Long id) {
        return carouselService.getCarouselById(id);
    }

    /**
     * 增加轮播图
     *
     * @param carouselDto
     * @return
     */
    @PostMapping("/add")
    public Result addCarousel(@RequestBody CarouselDto carouselDto) {
        return carouselService.addCarousel(carouselDto);

    }


    /**
     * 轮播图更新
     *
     * @param carouselDto
     * @return
     */
    @PutMapping("/update")

    public Result updateCarousel(@RequestBody CarouselDto carouselDto) {
        return carouselService.updateCarousel(carouselDto);
    }

    /**
     * 删除轮播图
     *
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")

    public Result deleteCarousel(@PathVariable Long id) {
        return carouselService.deleteCarousel(id);
    }


}
