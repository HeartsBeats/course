package com.course.server.service;

import com.course.server.domain.Category;
import com.course.server.domain.CategoryExample;
import com.course.server.dto.CategoryDto;
import com.course.server.mapper.CategoryMapper;
import com.course.server.util.CopyUtils;
import com.course.server.util.UuidUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class CategoryService {

    @Resource
    private CategoryMapper categoryMapper;

        /**
        * 列表查询
        */
        public List<CategoryDto> all() {
            CategoryExample categoryExample = new CategoryExample();
            categoryExample.setOrderByClause("sort asc");
            List<Category> categoryList = categoryMapper.selectByExample(categoryExample);
            List<CategoryDto> categoryDtoList = CopyUtils.copyList(categoryList, CategoryDto.class);
            return categoryDtoList;
        }

        /**
        * 保存，id有值时更新，无值时新增
        */
        public void save(CategoryDto categoryDto) {
            Category category = CopyUtils.copy(categoryDto, Category.class);
            if (StringUtils.isEmpty(categoryDto.getId())) {
            this.insert(category);
            } else {
            this.update(category);
            }
        }

        /**
        * 新增
        */
        private void insert(Category category) {
            Date now = new Date();
            category.setId(UuidUtil.getShortUuid());
            categoryMapper.insert(category);
        }

        /**
        * 更新
        */
        private void update(Category category) {
            categoryMapper.updateByPrimaryKey(category);
        }

        /**
        * 删除
        */
        public void delete(String id) {
            categoryMapper.deleteByPrimaryKey(id);
        }
}