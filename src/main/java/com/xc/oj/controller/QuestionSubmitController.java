package com.xc.oj.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xc.oj.common.BaseResponse;
import com.xc.oj.common.ErrorCode;
import com.xc.oj.common.ResultUtils;
import com.xc.oj.exception.BusinessException;
import com.xc.oj.exception.ThrowUtils;
import com.xc.oj.model.dto.question.QuestionQueryRequest;
import com.xc.oj.model.dto.questionSubmit.QuestionSubmitAddRequest;
import com.xc.oj.model.dto.questionSubmit.QuestionSubmitQueryRequest;
import com.xc.oj.model.entity.Question;
import com.xc.oj.model.entity.QuestionSubmit;
import com.xc.oj.model.entity.User;
import com.xc.oj.model.vo.QuestionSubmitVO;
import com.xc.oj.model.vo.QuestionVO;
import com.xc.oj.service.QuestionSubmitService;
import com.xc.oj.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 题目提交接口
 *
 * @author xc
 * @from oj
 */
@RestController
@RequestMapping("/question/question_submit")
@Slf4j
public class QuestionSubmitController {

    @Resource
    private QuestionSubmitService questionSubmitService;

    @Resource
    private UserService userService;

    /**
     * 题目提交 / 取消题目提交
     *
     * @param questionSubmitAddRequest
     * @param request
     * @return resultNum 本次题目提交变化数
     */
    @PostMapping("/do")
    public BaseResponse<Long> doThumb(@RequestBody QuestionSubmitAddRequest questionSubmitAddRequest,
            HttpServletRequest request) {
        if (questionSubmitAddRequest == null || questionSubmitAddRequest.getQuestionId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 登录才能题目提交
        final User loginUser = userService.getLoginUser(request);
        Long result = questionSubmitService.doQuestionSubmit(questionSubmitAddRequest, loginUser);
        return ResultUtils.success(result);
    }


    /**
     * 分页获取题目提交列表
     *
     * @param questionQueryRequest
     * @param request
     * @return
     */
    @PostMapping("/list/page")
    public BaseResponse<Page<QuestionSubmitVO>> listQuestionSubmitByPage(@RequestBody QuestionSubmitQueryRequest questionSubmitQueryRequest, HttpServletRequest request) {
        long current = questionSubmitQueryRequest.getCurrent();
        long size = questionSubmitQueryRequest.getPageSize();
        // 限制爬虫
        ThrowUtils.throwIf(size > 20, ErrorCode.PARAMS_ERROR);
        //从数据库中查询原始提交的分页信息
        Page<QuestionSubmit> questionPage = questionSubmitService.page(new Page<>(current, size), questionSubmitService.getQueryWrapper(questionSubmitQueryRequest));
        //返回脱敏信息
        User loginUser = userService.getLoginUser(request);
        return ResultUtils.success(questionSubmitService.getQuestionSubmitVOPage(questionPage, loginUser));
    }

}
