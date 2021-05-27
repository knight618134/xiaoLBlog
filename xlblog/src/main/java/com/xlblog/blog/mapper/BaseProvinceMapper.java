package com.xlblog.blog.mapper;

import com.xlblog.blog.entity.BaseProvince;
import com.xlblog.blog.entity.BaseSysconfig;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 行政区划 Mapper 接口
 * </p>
 *
 * @author yen
 * @since 2021-05-27
 */
@Mapper
@Repository
public interface BaseProvinceMapper extends BaseMapper<BaseProvince> {

    List<BaseProvince> findAllProvince();
}
