package com.ruoyi.web.controller.tool;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.SysPractice;
import com.ruoyi.system.domain.SysTheory;
import com.ruoyi.system.domain.SysUserClass;
import com.ruoyi.system.service.ISysPracticeService;
import com.ruoyi.system.service.ISysTheoryService;
import com.ruoyi.system.service.ISysUserClassService;
import com.ruoyi.system.service.ISysUserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 签到接口
 * 
 * @author zyf
 */
@Api("签到相关接口")
@RestController
@RequestMapping("/api/sign")
public class ApiController extends BaseController
{
    @Autowired
    private ISysTheoryService sysTheoryService;
    @Autowired
    private ISysPracticeService sysPracticeService;
    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private ISysUserClassService sysUserClassService;

    @ApiOperation("获取理论课程详细信息")
    @ApiImplicitParam(name = "classId", value = "课程ID", required = true, dataType = "int", paramType = "path", dataTypeClass = Integer.class)
    @GetMapping("/querytheory/{classId}")
    public AjaxResult getTheory(@PathVariable Integer classId)
    {
        SysTheory sysTheory=sysTheoryService.selectSysTheoryByClassId(classId.longValue());
        if(sysTheory!=null){
            return AjaxResult.success(sysTheory);
        } else
        {
            return error("课程不存在");
        }
    }
    @ApiOperation("获取实践课程详细信息")
    @ApiImplicitParam(name = "classId", value = "课程ID", required = true, dataType = "int", paramType = "path", dataTypeClass = Integer.class)
    @GetMapping("/querypractice/{classId}")
    public AjaxResult getPractice(@PathVariable Integer classId)
    {
        SysPractice sysPractice=sysPracticeService.selectSysPracticeByClassId(classId.longValue());
        if(sysPractice!=null){
            return AjaxResult.success(sysPractice);
        } else
        {
            return error("课程不存在");
        }
    }

    @ApiOperation("理论课程签到")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "loginName", value = "学号", dataType = "String", dataTypeClass = String.class),
        @ApiImplicitParam(name = "userName", value = "姓名", dataType = "String", dataTypeClass = String.class),
        @ApiImplicitParam(name = "classId", value = "课程编号", dataType = "Long", dataTypeClass = Long.class)
    })
    @PostMapping("/signtheory")
    public AjaxResult signtheory(SignEntity signEntity)
    {
        SysUser sysUser=new SysUser();
        sysUser.setLoginName(signEntity.getLoginName());
        sysUser.setUserName(signEntity.getUserName());
        List<SysUser> sysUserList=sysUserService.selectUserList(sysUser);
        if(sysUserList!=null&&sysUserList.size()>0){
            SysUser nowUser=sysUserList.get(0);
            SysUserClass sysUserClass=new SysUserClass();
            sysUserClass.setUserId(nowUser.getUserId());
            sysUserClass.setClassId(signEntity.getClassId());
            sysUserClass.setClassType("0");
            List<SysUserClass> sysUserClasses=sysUserClassService.selectSysUserClassList(sysUserClass);
            if(sysUserClasses!=null&&sysUserClasses.size()>0){
                SysUserClass actionEntity=sysUserClasses.get(0);
                if(actionEntity.getCompleteFlag().equals("0")){
                    actionEntity.setCompleteFlag("1");
                    actionEntity.setCompleteTime(DateUtils.getNowDate());
                    int mark=sysUserClassService.updateSysUserClass(actionEntity);
                    if(mark>0){
                        return success("签到成功");
                    }else{
                        return error("签到失败");
                    }
                }else{
                    return error("已完成签到,请勿重复操作");
                }
            }else{
                return error("您未选择该门课程,无法完成签到");
            }
        }else{
            return error("请输入正确的学号和姓名");
        }
    }
    @ApiOperation("实践课程签到")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "loginName", value = "学号", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "userName", value = "姓名", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "classId", value = "课程编号", dataType = "Long", dataTypeClass = Long.class)
    })
    @PostMapping("/signpractice")
    public AjaxResult signpractice(SignEntity signEntity)
    {
        SysUser sysUser=new SysUser();
        sysUser.setLoginName(signEntity.getLoginName());
        sysUser.setUserName(signEntity.getUserName());
        List<SysUser> sysUserList=sysUserService.selectUserList(sysUser);
        if(sysUserList!=null&&sysUserList.size()>0){
            SysUser nowUser=sysUserList.get(0);
            SysUserClass sysUserClass=new SysUserClass();
            sysUserClass.setUserId(nowUser.getUserId());
            sysUserClass.setClassId(signEntity.getClassId());
            sysUserClass.setClassType("1");
            List<SysUserClass> sysUserClasses=sysUserClassService.selectSysUserClassList(sysUserClass);
            if(sysUserClasses!=null&&sysUserClasses.size()>0){
                SysUserClass actionEntity=sysUserClasses.get(0);
                if(actionEntity.getCompleteFlag().equals("0")){
                    actionEntity.setCompleteFlag("1");
                    actionEntity.setCompleteTime(DateUtils.getNowDate());
                    int mark=sysUserClassService.updateSysUserClass(actionEntity);
                    if(mark>0){
                        return success("签到成功");
                    }else{
                        return error("签到失败");
                    }
                }else{
                    return error("已完成签到,请勿重复操作");
                }
            }else{
                return error("您未选择该门课程,无法完成签到");
            }
        }else{
            return error("请输入正确的学号和姓名");
        }
    }

}

@ApiModel(value = "SignEntity", description = "签到实体")
class SignEntity {
    @ApiModelProperty("学号")
    private String loginName;

    @ApiModelProperty("姓名")
    private String userName;

    @ApiModelProperty("课程编号")
    private Long classId;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public SignEntity() {

    }

    public SignEntity(String loginName, String userName, Long classId) {
        this.loginName = loginName;
        this.userName = userName;
        this.classId = classId;
    }
}