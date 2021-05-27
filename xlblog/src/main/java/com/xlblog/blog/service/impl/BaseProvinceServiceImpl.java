package com.xlblog.blog.service.impl;

import com.xlblog.blog.entity.BaseProvince;
import com.xlblog.blog.entity.BaseSysconfig;
import com.xlblog.blog.mapper.BaseProvinceMapper;
import com.xlblog.blog.mapper.BaseSysconfigMapper;
import com.xlblog.blog.service.IBaseProvinceService;
import com.xlblog.blog.utils.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 行政区划 服务实现类
 * </p>
 *
 * @author yen
 * @since 2021-05-27
 */
@Service
public class BaseProvinceServiceImpl  implements IBaseProvinceService {

    @Autowired
    private BaseProvinceMapper baseProvinceMapper;

    @Override
    public RespBean getAllType() {
        RespBean respBean = RespBean.build();
        List<BaseProvince> tType = baseProvinceMapper.findAllProvince();
        respBean.setStatus(200);
        respBean.setObj(tType);
        return respBean;
    }
}
