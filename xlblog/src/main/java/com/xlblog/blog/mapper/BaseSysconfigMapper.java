package com.xlblog.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xlblog.blog.entity.BaseSysconfig;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yen
 * @since 2021-05-27
 */
@Mapper
@Repository
public interface BaseSysconfigMapper extends BaseMapper<BaseSysconfig> {

    // 增
    int insert(BaseSysconfig record);

    // 刪
    int deleteByPrimaryKey(Integer id);
    // 改
    int updateByPrimaryKeySelective(BaseSysconfig record);
    //前台查询所有博客

    List<BaseSysconfig> findAllSysConfig();


    BaseSysconfig selectByPrimaryKey(Integer id);
}
