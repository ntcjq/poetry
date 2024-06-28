package com.sea.bean;

import lombok.Data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * @description:
 * @author: jiaqi.cui
 * @date: 2023/1/12
 */
@Data
@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;//作者
    private String profile;//简介
    private String tags;//标签
}
