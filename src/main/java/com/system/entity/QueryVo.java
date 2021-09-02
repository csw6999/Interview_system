package com.system.entity;

import lombok.Data;

@Data
public class QueryVo {

    private int page;

    private int rows;

    private String keyword;
}
