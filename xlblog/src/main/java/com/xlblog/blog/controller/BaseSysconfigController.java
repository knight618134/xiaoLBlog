package com.xlblog.blog.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.xlblog.blog.entity.BaseSysconfig;
import com.xlblog.blog.entity.TLinks;
import com.xlblog.blog.entity.TType;
import com.xlblog.blog.form.BlogForm;
import com.xlblog.blog.mapper.BaseSysconfigMapper;
import com.xlblog.blog.service.IBaseSysconfigService;
import com.xlblog.blog.service.TBlogService;
import com.xlblog.blog.service.impl.BaseSysconfigServiceImpl;
import com.xlblog.blog.utils.RespBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yen
 * @since 2021-05-27
 */
@Api(tags = "SysConfig控制層")
@RestController
@RequestMapping("/baseSysconfig")
public class BaseSysconfigController {
    @Autowired
    private BaseSysconfigServiceImpl baseSysconfigService;


    // 增
    @ApiOperation("新增")
    @ResponseBody
    @PostMapping("/create")
    public void create(
                       @RequestParam Integer id,
                       @RequestParam String systemname,
                       @RequestParam String systempatch,
                       @RequestParam String companyname,
                       @RequestParam String copyrightinformation,
                       @RequestParam String companyabbreviation,
                       @RequestParam String companyaddress,
                       @RequestParam String corporate,
                       @RequestParam String companytel,
                       @RequestParam String companyemail,
                       @RequestParam String systemdescribe) {
        BaseSysconfig member = new BaseSysconfig(id, systemname, systempatch, companyname, copyrightinformation, companyabbreviation, companyaddress, corporate, companytel, companyemail, systemdescribe);
        baseSysconfigService.insert(member);
    }

    // 改
    @ApiOperation("修改")
    @PutMapping("/update")
    public void update(@RequestParam Integer id,
                       @RequestParam(required = false) String systemname,
                       @RequestParam(required = false) String systempatch,
                       @RequestParam(required = false)String companyname,
                       @RequestParam(required = false) String copyrightinformation,
                       @RequestParam(required = false) String companyabbreviation,
                       @RequestParam(required = false) String companyaddress,
                       @RequestParam(required = false) String corporate,
                       @RequestParam(required = false) String companytel,
                       @RequestParam(required = false) String companyemail,
                       @RequestParam(required = false) String systemdescribe){

        BaseSysconfig member = baseSysconfigService.selectByPrimaryKey(id);
        if (systemname!=null && systemname.trim().length()>0) member.setSystemname(systemname);
        if (systempatch!=null && systempatch.trim().length()>0) member.setSystempatch(systempatch);
        if (companyname!=null && companyname.trim().length()>0) member.setCompanyname(companyname);
        if (copyrightinformation!=null && copyrightinformation.trim().length()>0) member.setCopyrightinformation(copyrightinformation);
        if (companyabbreviation!=null && companyabbreviation.trim().length()>0) member.setCompanyabbreviation(companyabbreviation);
        if (companyaddress!=null && companyaddress.trim().length()>0) member.setCompanyaddress(companyaddress);
        if (corporate!=null && corporate.trim().length()>0) member.setCorporate(corporate);
        if (companytel!=null && companytel.trim().length()>0) member.setCompanytel(companytel);
        if (systemname!=null && systemname.trim().length()>0) member.setSystemname(systemname);
        if (companyemail!=null && companyemail.trim().length()>0) member.setCompanyemail(companyemail);
        if (systemdescribe!=null && systemdescribe.trim().length()>0) member.setSystemdescribe(systemdescribe);

        baseSysconfigService.updateByPrimaryKeySelective(member);
    }

    // 刪
    @ApiOperation("刪除用戶")
    @DeleteMapping("/delete")
    public void delete(@RequestParam(value = "id", required = true)Integer id){
        baseSysconfigService.deleteByPrimaryKey(id);
    }

    /**
     * 查询所有分类
     * @return
     */
    @GetMapping("/getAll")
    public RespBean getAllType(){
        return baseSysconfigService.getAllType();
    }

}
