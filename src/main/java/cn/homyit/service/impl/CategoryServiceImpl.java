package cn.homyit.service.impl;

import cn.homyit.entity.Category;
import cn.homyit.mapper.CategoryMapper;
import cn.homyit.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> getAllCategories() {
        return categoryMapper.findAllCategories();
    }

    @Override
    public List<Category> getCategoryById(Long categoryId) {
        return categoryMapper.getById(categoryId);
    }
}

