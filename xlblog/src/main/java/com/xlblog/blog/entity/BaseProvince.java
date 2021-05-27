package com.xlblog.blog.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>
 * 行政区划
 * </p>
 *
 * @author yen
 * @since 2021-05-27
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value="BaseProvince对象", description="行政区划")
public class BaseProvince implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "自然主键")
    private String fId;

    @ApiModelProperty(value = "区域编号")
    private String fEncode;

    @ApiModelProperty(value = "区域名称")
    private String fFullname;

    @ApiModelProperty(value = "区域类型")
    private String fType;


}
