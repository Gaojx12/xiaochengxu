package cn.homyit.service;

import cn.homyit.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();  // 获取所有类别

    // 根据ID查询单个分类
    List<Category> getCategoryById(Long categoryId);
}

