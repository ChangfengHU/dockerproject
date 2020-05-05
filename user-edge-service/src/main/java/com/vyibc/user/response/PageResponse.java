package com.vyibc.user.response;


import groovy.transform.ToString;
import lombok.Data;

import java.util.List;

/**
 * Created by 25131 on 2019/8/24.
 */
@Data
@ToString
public class PageResponse<T extends List<?>>  extends Response<T>{
    private static final long serialVersionUID = -2410124673604109647L;
    /**
     * 当前页码
     */
    private int currentPage;

    /**
     * 总页数
     */
    private int totalPage;
    /**
     * 总共项数
     */
    private long total;
    /**

     * 每页项数目
     */
    private int pageSize;
}
