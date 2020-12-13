package com.course.server.service;

import com.course.server.domain.Resource;
import com.course.server.domain.ResourceExample;
import com.course.server.dto.ResourceDto;
import com.course.server.dto.PageDto;
import com.course.server.mapper.ResourceMapper;
import com.course.server.util.CopyUtils;
import com.course.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author 游佳琪
 */
@Service
public class ResourceService {

    @javax.annotation.Resource
    private ResourceMapper resourceMapper;

    /**
     * 列表查询
     */
    public void list(PageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        ResourceExample resourceExample = new ResourceExample();
        List<Resource> resourceList = resourceMapper.selectByExample(resourceExample);
        PageInfo<Resource> pageInfo = new PageInfo<>(resourceList);
        pageDto.setTotal(pageInfo.getTotal());
        List<ResourceDto> resourceDtoList = CopyUtils.copyList(resourceList, ResourceDto.class);
        pageDto.setList(resourceDtoList);
    }

    /**
     * 保存，id有值时更新，无值时新增
     */
    public void save(ResourceDto resourceDto) {
        Resource resource = CopyUtils.copy(resourceDto, Resource.class);
        if (StringUtils.isEmpty(resourceDto.getId())) {
            this.insert(resource);
        } else {
            this.update(resource);
        }
    }

    /**
     * 新增
     */
    private void insert(Resource resource) {
        resource.setId(UuidUtil.getShortUuid());
        resourceMapper.insert(resource);
    }

    /**
     * 更新
     */
    private void update(Resource resource) {
        resourceMapper.updateByPrimaryKey(resource);
    }

    /**
     * 删除
     */
    public void delete(String id) {
        resourceMapper.deleteByPrimaryKey(id);
    }
}
