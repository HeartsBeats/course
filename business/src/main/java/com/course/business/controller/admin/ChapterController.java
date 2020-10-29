package com.course.business.controller.admin;


import com.course.server.dto.ChapterDto;
import com.course.server.dto.PageDto;
import com.course.server.dto.ResponseDto;
import com.course.server.service.ChapterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;


@RestController
@RequestMapping("/admin/chapter")
public class ChapterController {
    @Resource
    private ChapterService chapterService;

    private static final Logger LOG = LoggerFactory.getLogger(ChapterController.class);

    @RequestMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        LOG.info("pageDto: {}", pageDto);
        chapterService.list(pageDto);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setContent(pageDto);
        return responseDto;
    }
    @RequestMapping("/save")
    public ResponseDto save(@RequestBody ChapterDto chapterDto) {
        LOG.info("chapterDto: {}", chapterDto);
        chapterService.save(chapterDto);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setContent(chapterDto);
        return responseDto;
    }


}
