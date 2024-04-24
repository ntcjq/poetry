package com.sea.bean;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @description:
 * @author: jiaqi.cui
 * @date: 2023/1/12
 */
@Data
@Entity
public class Poetry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JSONField(deserialize = false)
    private Long id;
    private String author;//作者
    private String title;//标题
    private String paragraphs;//内容
    @JSONField(deserialize = false)
    private String tags;//标签
}
