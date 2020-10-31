package com.course.server.service;

import com.course.server.domain.Section;
import com.course.server.domain.SectionExample;
import com.course.server.dto.SectionDto;
import com.course.server.dto.PageDto;
import com.course.server.mapper.SectionMapper;
import com.course.server.util.CopyUtils;
import com.course.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SectionService {

@Resource
private SectionMapper sectionMapper;

/**
* 列表查询
*/
public void list(PageDto pageDto) {
PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
SectionExample sectionExample = new SectionExample();
List<Section> sectionList = sectionMapper.selectByExample(sectionExample);
PageInfo<Section> pageInfo = new PageInfo<>(sectionList);
pageDto.setTotal(pageInfo.getTotal());
List<SectionDto> sectionDtoList = CopyUtils.copyList(sectionList, SectionDto.class);
    pageDto.setList(sectionDtoList);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(SectionDto sectionDto) {
    Section section = CopyUtils.copy(sectionDto, Section.class);
    if (StringUtils.isEmpty(sectionDto.getId())) {
    this.insert(section);
    } else {
    this.update(section);
    }
    }

    /**
    * 新增
    */
    private void insert(Section section) {
    section.setId(UuidUtil.getShortUuid());
    sectionMapper.insert(section);
    }

    /**
    * 更新
    */
    private void update(Section section) {
    sectionMapper.updateByPrimaryKey(section);
    }

    /**
    * 删除
    */
    public void delete(String id) {
    sectionMapper.deleteByPrimaryKey(id);
    }
    }
