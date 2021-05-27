package com.xlblog.blog.controller;


import com.xlblog.blog.service.impl.BaseProvinceServiceImpl;
import com.xlblog.blog.utils.RespBean;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 行政区划 前端控制器
 * </p>
 *
 * @author yen
 * @since 2021-05-27
 */
@Api(tags = "Province控制層")
@RestController
@RequestMapping("/baseProvince")
public class BaseProvinceController {
    @Autowired private BaseProvinceServiceImpl baseProvinceService;

    /**
     * 查询所有分类
     * @return
     */
    @GetMapping("/getAll")
    public RespBean getAllType(){
        return baseProvinceService.getAllType();
    }
}
