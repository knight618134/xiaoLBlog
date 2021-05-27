package com.xlblog.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xlblog.blog.entity.BaseSysconfig;
import com.xlblog.blog.mapper.BaseSysconfigMapper;
import com.xlblog.blog.service.IBaseSysconfigService;
import com.xlblog.blog.utils.RespBean;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yen
 * @since 2021-05-27
 */
@Service
public class BaseSysconfigServiceImpl implements IBaseSysconfigService {

    //調用 mapper 層
    @Autowired
    private BaseSysconfigMapper baseSysconfigMapper;

    // 增
    @Override
    public int insert(BaseSysconfig record) {
        return baseSysconfigMapper.insert(record);
    }

    // 刪
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return baseSysconfigMapper.deleteByPrimaryKey(id);
    }

    // 改
    @Override
    public int updateByPrimaryKeySelective(BaseSysconfig record) {
        return baseSysconfigMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public RespBean getAllType() {
        RespBean respBean = RespBean.build();
        List<BaseSysconfig> tType = baseSysconfigMapper.findAllSysConfig();
        respBean.setStatus(200);
        respBean.setObj(tType);
        return respBean;
    }

    //依 id 查
    @Override
    public BaseSysconfig selectByPrimaryKey(Integer id) {
        return baseSysconfigMapper.selectByPrimaryKey(id);
    }
}
