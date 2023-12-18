package com.xc.oj.controller;

import com.xc.oj.common.BaseResponse;
import com.xc.oj.common.ErrorCode;
import com.xc.oj.common.ResultUtils;
import com.xc.oj.exception.BusinessException;
import com.xc.oj.model.dto.questionSubmit.QuestionSubmitAddRequest;
import com.xc.oj.model.entity.User;
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
@RequestMapping("/question_submit")
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
    @PostMapping("/")
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

}
