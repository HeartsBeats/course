package com.course.business.controller.admin;


import com.course.server.dto.ChapterDto;
import com.course.server.dto.ChapterPageDto;
import com.course.server.dto.ResponseDto;
import com.course.server.service.ChapterService;
import com.course.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
@RequestMapping("/admin/chapter")
public class ChapterController {
    @Resource
    private ChapterService chapterService;

    public static final String BUSINESS_NAME = "大章";

    private static final Logger LOG = LoggerFactory.getLogger(ChapterController.class);

    /*
     *列表查询
     * @Author: YJQ
     * @Date: 2020-10-30 20:18
     */

    @PostMapping("/list")
    public ResponseDto list(@RequestBody ChapterPageDto chapterPageDto) {
        LOG.info("pageDto: {}", chapterPageDto);
        ValidatorUtil.require(chapterPageDto.getCourseId(),"课程ID");
        chapterService.list(chapterPageDto);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setContent(chapterPageDto);
        return responseDto;
    }
    /*
     *  数据更新和存储
     * @Author: YJQ
     * @Date: 2020-10-30 20:18
     */

    @PostMapping("/save")
    public ResponseDto save(@RequestBody ChapterDto chapterDto) {
        LOG.info("chapterDto: {}", chapterDto);
        // 保存校验
        ValidatorUtil.require(chapterDto.getName(), "名称");
        ValidatorUtil.require(chapterDto.getCourseId(), "课程ID");
        ValidatorUtil.length(chapterDto.getCourseId(), "课程ID", 1, 8);
        chapterService.save(chapterDto);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setContent(chapterDto);
        return responseDto;
    }
    /*
     *  根据id删除数据
     * @Author: YJQ
     * @Date: 2020-10-30 20:19
     */

    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        LOG.info("chapterDto: {}", id);
        ResponseDto responseDto = new ResponseDto();
        chapterService.delete(id);
        return responseDto;
    }

}
