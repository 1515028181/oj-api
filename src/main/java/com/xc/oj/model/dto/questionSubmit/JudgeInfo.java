package com.xc.oj.model.dto.questionSubmit;

import lombok.Data;

/**
 * 题目配置
 */
@Data
public class JudgeInfo {
    /**
     * 程序执行信息
     */
    private String  massage;
    /**
     * 消耗时间
     */
    private Long time;
    /**
     * 消耗内存
     */
    private Long memory;
}
