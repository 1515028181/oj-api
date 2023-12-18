package com.xc.oj.model.dto.questionSubmit;


import com.xc.oj.model.dto.question.JudgeCase;
import com.xc.oj.model.dto.question.JudgeConfig;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 创建请求
 *
 * @author xc
 * @from oj
 */
@Data
public class QuestionSubmitAddRequest implements Serializable {
    /**
     * 编程语言
     */
    private String language;

    /**
     * 用户代码
     */
    private String code;


    /**
     * 题目 id
     */
    private Long questionId;




    private static final long serialVersionUID = 1L;
}