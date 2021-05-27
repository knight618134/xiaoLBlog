package com.xlblog.blog.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author yen
 * @since 2021-05-27
 */
@ApiModel("BaseSysconfig对象")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseSysconfig  {

    @ApiModelProperty("id主鍵 (自增)")
    private Integer id;

    @ApiModelProperty("systemname")
    private String systemname;

    @ApiModelProperty("systempatch")
    private String systempatch;

    @ApiModelProperty("companyname")
    private String companyname;

    @ApiModelProperty("copyrightinformation")
    private String copyrightinformation;

    @ApiModelProperty("companyabbreviation")
    private String companyabbreviation;

    @ApiModelProperty("companyaddress")
    private String companyaddress;

    @ApiModelProperty("corporate")
    private String corporate;

    @ApiModelProperty("companytel")
    private String companytel;

    @ApiModelProperty("companyemail")
    private String companyemail;

    @ApiModelProperty("systemdescribe")
    private String systemdescribe;
}
