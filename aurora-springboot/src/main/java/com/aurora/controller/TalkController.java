package com.aurora.controller;

import com.aurora.dto.TalkAdminDTO;
import com.aurora.dto.TalkDTO;
import com.aurora.enums.FilePathEnum;
import com.aurora.service.TalkService;
import com.aurora.strategy.context.UploadStrategyContext;
import com.aurora.vo.ConditionVO;
import com.aurora.vo.PageResult;
import com.aurora.vo.Result;
import com.aurora.vo.TalkVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "说说模块")
@RestController
public class TalkController {

    @Autowired
    private TalkService talkService;

    @Autowired
    private UploadStrategyContext uploadStrategyContext;

    @ApiOperation(value = "查看说说列表")
    @GetMapping("/talks")
    public Result<PageResult<TalkDTO>> listTalks() {
        return Result.ok(talkService.listTalks());
    }

    @ApiOperation(value = "根据id查看说说")
    @ApiImplicitParam(name = "talkId", value = "说说id", required = true, dataType = "Integer")
    @GetMapping("/talks/{talkId}")
    public Result<TalkDTO> getTalkById(@PathVariable("talkId") Integer talkId) {
        return Result.ok(talkService.getTalkById(talkId));
    }

    @ApiOperation(value = "上传说说图片")
    @ApiImplicitParam(name = "file", value = "说说图片", required = true, dataType = "MultipartFile")
    @PostMapping("/admin/talks/images")
    public Result<String> saveTalkImages(MultipartFile file) {
        return Result.ok(uploadStrategyContext.executeUploadStrategy(file, FilePathEnum.TALK.getPath()));
    }

    @ApiOperation(value = "保存或修改说说")
    @PostMapping("/admin/talks")
    public Result<?> saveOrUpdateTalk(@Valid @RequestBody TalkVO talkVO) {
        talkService.saveOrUpdateTalk(talkVO);
        return Result.ok();
    }

    @ApiOperation(value = "删除说说")
    @DeleteMapping("/admin/talks")
    public Result<?> deleteTalks(@RequestBody List<Integer> talkIds) {
        talkService.deleteTalks(talkIds);
        return Result.ok();
    }

    @ApiOperation(value = "查看后台说说")
    @GetMapping("/admin/talks")
    public Result<PageResult<TalkAdminDTO>> listBackTalks(ConditionVO conditionVO) {
        return Result.ok(talkService.listBackTalks(conditionVO));
    }

    @ApiOperation(value = "根据id查看后台说说")
    @ApiImplicitParam(name = "talkId", value = "说说id", required = true, dataType = "Integer")
    @GetMapping("/admin/talks/{talkId}")
    public Result<TalkAdminDTO> getBackTalkById(@PathVariable("talkId") Integer talkId) {
        return Result.ok(talkService.getBackTalkById(talkId));
    }

}
