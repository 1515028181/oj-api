package com.xc.oj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xc.oj.model.dto.questionSubmit.QuestionSubmitAddRequest;
import com.xc.oj.model.entity.QuestionSubmit;
import com.xc.oj.model.entity.User;


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

}
