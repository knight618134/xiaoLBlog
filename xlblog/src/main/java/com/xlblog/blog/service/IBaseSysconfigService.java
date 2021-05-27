package com.xlblog.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xlblog.blog.entity.BaseSysconfig;
import com.xlblog.blog.utils.RespBean;
import io.swagger.models.auth.In;

import java.util.HashMap;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yen
 * @since 2021-05-27
 */
public interface IBaseSysconfigService {

    // 增
    int insert(BaseSysconfig record);

    //delete
    int deleteByPrimaryKey(Integer id);
    // 改
    int updateByPrimaryKeySelective(BaseSysconfig record);

    RespBean getAllType();

    BaseSysconfig selectByPrimaryKey(Integer id);
}
