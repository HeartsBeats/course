package com.course.server.service;

import com.course.server.domain.${Domain};
import com.course.server.domain.${Domain}Example;
import com.course.server.dto.${Domain}Dto;
import com.course.server.dto.PageDto;
import com.course.server.mapper.${Domain}Mapper;
import com.course.server.util.CopyUtil;
import com.course.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ${Domain}Service {

@Resource
private ${Domain}Mapper ${domain}Mapper;

/**
* 列表查询
*/
public void list(PageDto pageDto) {
PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
${Domain}Example ${domain}Example = new ${Domain}Example();
List<${Domain}> ${domain}List = ${domain}Mapper.selectByExample(${domain}Example);
PageInfo<${Domain}> pageInfo = new PageInfo<>(${domain}List);
pageDto.setTotal(pageInfo.getTotal());
List<${Domain}Dto> ${domain}DtoList = CopyUtil.copyList(${domain}List, ${Domain}Dto.class);
    pageDto.setList(${domain}DtoList);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(${Domain}Dto ${domain}Dto) {
    ${Domain} ${domain} = CopyUtil.copy(${domain}Dto, ${Domain}.class);
    if (StringUtils.isEmpty(${domain}Dto.getId())) {
    this.insert(${domain});
    } else {
    this.update(${domain});
    }
    }

    /**
    * 新增
    */
    private void insert(${Domain} ${domain}) {
    ${domain}.setId(UuidUtil.getShortUuid());
    ${domain}Mapper.insert(${domain});
    }

    /**
    * 更新
    */
    private void update(${Domain} ${domain}) {
    ${domain}Mapper.updateByPrimaryKey(${domain});
    }

    /**
    * 删除
    */
    public void delete(String id) {
    ${domain}Mapper.deleteByPrimaryKey(id);
    }
    }
