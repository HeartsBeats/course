package com.course.server.service;

import com.course.server.domain.Sms;
import com.course.server.domain.SmsExample;
import com.course.server.dto.SmsDto;
import com.course.server.dto.PageDto;
import com.course.server.mapper.SmsMapper;
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
public class SmsService {

    @Resource
    private SmsMapper smsMapper;

        /**
        * 列表查询
        */
        public void list(PageDto pageDto) {
            PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
            SmsExample smsExample = new SmsExample();
            List<Sms> smsList = smsMapper.selectByExample(smsExample);
            PageInfo<Sms> pageInfo = new PageInfo<>(smsList);
            pageDto.setTotal(pageInfo.getTotal());
            List
            <SmsDto> smsDtoList = CopyUtils.copyList(smsList, SmsDto.class);
                pageDto.setList(smsDtoList);
        }

        /**
        * 保存，id有值时更新，无值时新增
        */
        public void save(SmsDto smsDto) {
            Sms sms = CopyUtils.copy(smsDto, Sms.class);
            if (StringUtils.isEmpty(smsDto.getId())) {
            this.insert(sms);
            } else {
            this.update(sms);
            }
        }

        /**
        * 新增
        */
        private void insert(Sms sms) {
                            Date now = new Date();
            sms.setId(UuidUtil.getShortUuid());
            smsMapper.insert(sms);
        }

        /**
        * 更新
        */
        private void update(Sms sms) {
            smsMapper.updateByPrimaryKey(sms);
        }

        /**
        * 删除
        */
        public void delete(String id) {
            smsMapper.deleteByPrimaryKey(id);
        }
}