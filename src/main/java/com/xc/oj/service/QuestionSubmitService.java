package com.xc.oj.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xc.oj.model.dto.question.QuestionQueryRequest;
import com.xc.oj.model.dto.questionSubmit.QuestionSubmitAddRequest;
import com.xc.oj.model.dto.questionSubmit.QuestionSubmitQueryRequest;
import com.xc.oj.model.entity.Question;
import com.xc.oj.model.entity.QuestionSubmit;
import com.xc.oj.model.entity.User;
import com.xc.oj.model.vo.QuestionSubmitVO;
import com.xc.oj.model.vo.QuestionVO;

import javax.servlet.http.HttpServletRequest;


/**
* @author A
* @description 针对表【question_submit(题目提交)】的数据库操作Service
* @createDate 2023-12-16 16:50:56
*/
public interface QuestionSubmitService extends IService<QuestionSubmit> {


    /**
     * 点赞
     *
     * @param questionId
     * @param loginUser
     * @return
     */
    Long doQuestionSubmit(QuestionSubmitAddRequest questionSubmitAddRequest, User loginUser);


    /**
     * 获取查询条件
     *
     * @param questionQueryRequest
     * @return
     */
    QueryWrapper<QuestionSubmit> getQueryWrapper(QuestionSubmitQueryRequest questionSubmitQueryRequest);


    /**
     * 获取帖子封装
     *
     * @param question
     * @param request
     * @return
     */
    QuestionSubmitVO getQuestionSubmitVO(QuestionSubmit questionSubmit, User loginUser);

    /**
     * 分页获取帖子封装
     *
     * @param questionPage
     * @param request
     * @return
     */
    Page<QuestionSubmitVO> getQuestionSubmitVOPage(Page<QuestionSubmit> questionSubmitPage, User loginUser);

}
