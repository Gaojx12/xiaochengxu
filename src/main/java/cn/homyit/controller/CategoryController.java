package cn.homyit.controller;

import cn.homyit.entity.Category;
import cn.homyit.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 获取全部类别
     *
     * @return
     */
    @GetMapping("/list")
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();  // 返回所有类别
    }


    /**
     * 获取单个分类
     *
     * @param categoryId
     * @return
     */
    @GetMapping("/{categoryId}")
    public List<Category> getCategoryById(@PathVariable Long categoryId) {
        return categoryService.getCategoryById(categoryId);
    }
}

