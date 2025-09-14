package cn.homyit.mapper;

import cn.homyit.entity.Category;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
    // 查询所有类别
    @Select("SELECT * FROM category")
    List<Category> findAllCategories();

    @Select("SELECT * FROM category WHERE id=#{id}")
    List<Category> getById(Long categoryId);
}
