package com.xc.oj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xc.oj.common.ErrorCode;
import com.xc.oj.exception.BusinessException;
import com.xc.oj.model.dto.questionSubmit.QuestionSubmitAddRequest;
import com.xc.oj.model.entity.Question;
import com.xc.oj.model.entity.QuestionSubmit;
import com.xc.oj.model.entity.User;
import com.xc.oj.model.enums.JudgeInfoMessageEnum;
import com.xc.oj.model.enums.QuestionSubmitEnum;
import com.xc.oj.model.enums.QuestionSubmitLanguageEnum;
import com.xc.oj.service.QuestionService;
import com.xc.oj.service.QuestionSubmitService;
import com.xc.oj.mapper.QuestionSubmitMapper;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
* @author A
* @description 针对表【question_submit(题目提交)】的数据库操作Service实现
* @createDate 2023-12-16 16:50:56
*/
@Service
public class QuestionSubmitServiceImpl extends ServiceImpl<QuestionSubmitMapper, QuestionSubmit>
    implements QuestionSubmitService {


    @Resource
    private QuestionService questionService;

    /**
     * 点赞
     *
     * @param questionSubmitAddRequest
     * @param loginUser
     * @return
     */
    @Override
    public Long doQuestionSubmit(QuestionSubmitAddRequest questionSubmitAddRequest, User loginUser) {
        //校验用户语言是否合法
        String language = questionSubmitAddRequest.getLanguage();
        QuestionSubmitLanguageEnum languageEnum = QuestionSubmitLanguageEnum.getEnumByValue(language);

        long questionId = questionSubmitAddRequest.getQuestionId();
        String code = questionSubmitAddRequest.getCode();
        // 判断实体是否存在，根据类别获取实体
        Question question = questionService.getById(questionId);
        if (question == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        long userId = loginUser.getId();
        QuestionSubmit questionSubmit = new QuestionSubmit();
        questionSubmit.setLanguage(language);
        questionSubmit.setCode(code);
        questionSubmit.setUserId(userId);
        //设置判题状态
        questionSubmit.setStatus(QuestionSubmitEnum.WAITING.getValue());
        questionSubmit.setJudgeInfo("{}");
        boolean save = this.save(questionSubmit);
        if (!save){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"插入失败");
        }
        return questionSubmit.getId();
    }

}




