package com.course.server.service;

import com.course.server.domain.File;
import com.course.server.domain.FileExample;
import com.course.server.dto.FileDto;
import com.course.server.dto.PageDto;
import com.course.server.mapper.FileMapper;
import com.course.server.util.CopyUtils;
import com.course.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Date;

@Service
public class FileService {

    @Resource
    private FileMapper fileMapper;

        /**
        * 列表查询
        */
        public void list(PageDto pageDto) {
            PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
            FileExample fileExample = new FileExample();
            List<File> fileList = fileMapper.selectByExample(fileExample);
            PageInfo<File> pageInfo = new PageInfo<>(fileList);
            pageDto.setTotal(pageInfo.getTotal());
            List
            <FileDto> fileDtoList = CopyUtils.copyList(fileList, FileDto.class);
                pageDto.setList(fileDtoList);
        }

        /**
        * 保存，id有值时更新，无值时新增
        */
        public void save(FileDto fileDto) {
            File file = CopyUtils.copy(fileDto, File.class);
            if (StringUtils.isEmpty(fileDto.getId())) {
            this.insert(file);
            } else {
            this.update(file);
            }
        }

        /**
        * 新增
        */
        private void insert(File file) {
                            Date now = new Date();
                    file.setCreatedAt(now);
                    file.setUpdatedAt(now);
            file.setId(UuidUtil.getShortUuid());
            fileMapper.insert(file);
        }

        /**
        * 更新
        */
        private void update(File file) {
                    file.setUpdatedAt(new Date());
            fileMapper.updateByPrimaryKey(file);
        }

        /**
        * 删除
        */
        public void delete(String id) {
            fileMapper.deleteByPrimaryKey(id);
        }
}