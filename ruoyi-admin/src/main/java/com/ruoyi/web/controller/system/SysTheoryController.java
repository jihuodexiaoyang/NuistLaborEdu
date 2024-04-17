package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysDept;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.SysTheory;
import com.ruoyi.system.domain.SysUserClass;
import com.ruoyi.system.service.ISysDeptService;
import com.ruoyi.system.service.ISysTheoryService;
import com.ruoyi.system.service.ISysUserClassService;
import com.ruoyi.system.service.ISysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 理论学习Controller
 *
 * @author ruoyi
 * @date 2021-12-19
 */
@Controller
@RequestMapping("/system/theory")
public class SysTheoryController extends BaseController
{
    private String prefix = "system/theory";

    @Autowired
    private ISysTheoryService sysTheoryService;
    @Autowired
    private ISysUserClassService sysUserClassService;
    @Autowired
    private ISysDeptService sysDeptService;
    @Autowired
    private ISysUserService userService;

    @RequiresPermissions("system:theory:view")
    @GetMapping()
    public String theory()
    {
        return prefix + "/theory";
    }
    /**
     * 学生选课界面
     */
    @RequiresPermissions("system:theory:select")
    @GetMapping("/select")
    public String select()
    {
        return prefix + "/select";
    }
    /**
     * 学生已选选课界面
     */
    @RequiresPermissions("system:theory:select")
    @GetMapping("/selected")
    public String selected()
    {
        return prefix + "/selected";
    }
    /**
     * 查询理论学习列表
     */
    @RequiresPermissions("system:theory:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysTheory sysTheory)
    {
        startPage();
        sysTheory.setDelFlag("0");
        //非管理员只查询本部门数据
        SysUser sysUser=getSysUser();
        if(!sysUser.isAdmin()){
            sysTheory.setClassDept(sysUser.getDeptId());
        }
        List<SysTheory> list = sysTheoryService.selectSysTheoryList(sysTheory);
        return getDataTable(list);
    }
    /**
     * 分配负责人页面
     */
    @RequiresPermissions("system:theory:list")
    @GetMapping("/selectfzr")
    public String selectfzr()
    {
        return prefix + "/selectfzr";
    }
    /**
     * 查询负责人列表
     */
    @RequiresPermissions("system:theory:list")
    @PostMapping("/fzrlist")
    @ResponseBody
    public TableDataInfo fzrlist(SysUser user)
    {
        startPage();
        List<SysUser> list = userService.selectUserList(user);
        return getDataTable(list);
    }
    /**
     * 查询理论学习列表(选课)
     */
    @RequiresPermissions("system:theory:select")
    @PostMapping("/listsel")
    @ResponseBody
    public TableDataInfo listsel(SysTheory sysTheory)
    {
        startPage();
        sysTheory.setDelFlag("0");
        //非管理员只查询本部门数据
        SysUser sysUser=getSysUser();

        /*if(!sysUser.isAdmin()){
            SysDept sysDept=sysDeptService.selectDeptById(sysUser.getDeptId());
            String[] ans=sysDept.getAncestors().split(",");
            sysTheory.setClassDept(Long.parseLong(ans[2]));
        }*/
        //利用classId存储用户id
        sysTheory.setClassId(sysUser.getUserId());
        //利用classDept存储用户所在部门编号
        sysTheory.setClassDept(sysUser.getDeptId());
        List<SysTheory> list = sysTheoryService.selectSysTheoryListSelect(sysTheory);
        return getDataTable(list);
    }
    /**
     * 查询理论学习已选列表
     */
    @RequiresPermissions("system:theory:select")
    @PostMapping("/listselected")
    @ResponseBody
    public TableDataInfo listselected(SysTheory sysTheory)
    {
        startPage();
        sysTheory.setDelFlag("0");
        sysTheory.setClassId(getUserId());
        List<SysTheory> list = sysTheoryService.selectSysTheoryListSelected(sysTheory);
        return getDataTable(list);
    }
    /**
     * 导出理论学习列表
     */
    @RequiresPermissions("system:theory:export")
    @Log(title = "理论学习", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysTheory sysTheory)
    {
        List<SysTheory> list = sysTheoryService.selectSysTheoryList(sysTheory);
        ExcelUtil<SysTheory> util = new ExcelUtil<SysTheory>(SysTheory.class);
        return util.exportExcel(list, "理论学习数据");
    }
    /**
     * 导出理论学习列表
     */
    @RequiresPermissions("system:theory:select")
    @Log(title = "理论学习", businessType = BusinessType.EXPORT)
    @PostMapping("/exportselected")
    @ResponseBody
    public AjaxResult exportselected(SysTheory sysTheory)
    {
        sysTheory.setDelFlag("0");
        sysTheory.setClassId(getUserId());
        List<SysTheory> list = sysTheoryService.selectSysTheoryListSelected(sysTheory);
        ExcelUtil<SysTheory> util = new ExcelUtil<SysTheory>(SysTheory.class);
        return util.exportExcel(list, "理论学习选课数据");
    }
    /**
     * 新增理论学习
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存理论学习
     */
    @RequiresPermissions("system:theory:add")
    @Log(title = "理论学习", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SysTheory sysTheory)
    {
        sysTheory.setCreateBy(getLoginName());
        sysTheory.setClassDept(getSysUser().getDeptId());
        return toAjax(sysTheoryService.insertSysTheory(sysTheory));
    }

    /**
     * 修改理论学习
     */
    @GetMapping("/edit/{classId}")
    public String edit(@PathVariable("classId") Long classId, ModelMap mmap)
    {
        SysTheory sysTheory = sysTheoryService.selectSysTheoryByClassId(classId);
        mmap.put("sysTheory", sysTheory);
        return prefix + "/edit";
    }

    /**
     * 修改保存理论学习
     */
    @RequiresPermissions("system:theory:edit")
    @Log(title = "理论学习", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SysTheory sysTheory)
    {
        sysTheory.setUpdateBy(getLoginName());
        return toAjax(sysTheoryService.updateSysTheory(sysTheory));
    }
    /**
     * 理论学习选课
     */
    @RequiresPermissions("system:theory:select")
    @Log(title = "理论学习选课", businessType = BusinessType.SELECT)
    @PostMapping("/select")
    @ResponseBody
    @Transactional
    public AjaxResult selectSave(SysTheory sysTheory)
    {
        SysTheory validEntity=sysTheoryService.selectSysTheoryByClassId(sysTheory.getClassId());
        //判断是否为可选课状态
        if(validEntity.getStatus().equals("0")){
            //判断学生是否已选课
            SysUserClass sysUserClass=new SysUserClass();
            sysUserClass.setUserId(getUserId());
            sysUserClass.setClassId(validEntity.getClassId());
            //0理论学习 1实践学习 2在线学习
            sysUserClass.setClassType("0");
            int selectCount=sysUserClassService.selectSysUserClassList(sysUserClass).size();
            if(selectCount==0){
                //判断选课人数是否超限
                SysUserClass checkSelect=new SysUserClass();
                checkSelect.setClassId(validEntity.getClassId());
                checkSelect.setClassType("0");
                int checkCount=sysUserClassService.selectSysUserClassList(checkSelect).size();
                if(checkCount<validEntity.getClassLimit()){
                    //将选课信息保存至数据库
                    sysUserClassService.insertSysUserClass(sysUserClass);
                    sysTheoryService.updateSelected(validEntity);
                    return AjaxResult.success("选课成功");
                }else{
                    return AjaxResult.error("该课程人数已选满");
                }
            }else {
                return AjaxResult.error("已选课,请勿重复操作");
            }
        }else{
            return AjaxResult.error("未到选课时间,不能完成选课操作");
        }
    }
    /**
     * 理论学习取消选课
     */
    @RequiresPermissions("system:theory:select")
    @Log(title = "理论学习取消选课", businessType = BusinessType.DESELECT)
    @PostMapping("/deselect")
    @ResponseBody
    @Transactional
    public AjaxResult deSelect(SysTheory sysTheory)
    {
        SysTheory validEntity=sysTheoryService.selectSysTheoryByClassId(sysTheory.getClassId());
        //判断学时是否已选课
        SysUserClass sysUserClass=new SysUserClass();
        sysUserClass.setUserId(getUserId());
        sysUserClass.setClassId(validEntity.getClassId());
        //0理论学习 1实践学习 2在线学习
        sysUserClass.setClassType("0");
        int deSelectMark=sysUserClassService.deSelectClass(sysUserClass);
        if(deSelectMark>0){
            //不用判断选课人数 将取消选课信息保存至数据库
            int markDeSelected=sysTheoryService.updateDeSelected(validEntity);
            //更新已选课人数
            if(markDeSelected>0){
                return AjaxResult.success("取消选课成功");
            }else{
                return AjaxResult.error("取消选课失败,系统错误");
            }
        }else {
            return AjaxResult.error("已取消选课,请勿重复操作");
        }
    }
    /**
     * 删除理论学习
     */
    @RequiresPermissions("system:theory:remove")
    @Log(title = "理论学习", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(sysTheoryService.deleteSysTheoryByClassIds(ids));
    }

    /**
     * 生成二维码（下载方式）
     */
    @RequiresPermissions("system:theory:generate")
    @Log(title = "二维码生成", businessType = BusinessType.QRCODE)
    @GetMapping("/genqrcode/{classId}")
    public void genqrcode(HttpServletResponse response, @PathVariable("classId") Long classId) throws IOException
    {
        try{
            //SysTheory sysTheory=sysTheoryService.selectSysTheoryByClassId(classId);
            //String downloadName= StringUtils.format("{}_{}.gif",sysTheory.getClassOrg(),sysTheory.getClassTitle());
            String signUrl=StringUtils.format("{}sign/index.html?classid={}&classtype=0",RuoYiConfig.getSignAddress(),classId);
            byte[] data=QRCode.from(signUrl).to(ImageType.GIF).withSize(300, 300).stream().toByteArray();
            genQrcodeImage(response,data);
        } catch (Exception e)
        {
            logger.error("下载二维码失败", e);
        }
    }
    /**
     * 生成下载图片
     */
    private void genQrcodeImage(HttpServletResponse response, byte[] data) throws IOException
    {
        response.reset();
        response.setHeader("Content-Disposition", StringUtils.format("attachment; filename=\"{}.gif\"",DateUtils.dateTimeNow()));
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");
        IOUtils.write(data, response.getOutputStream());
    }
    /**
     * 课程状态修改
     */
    @Log(title = "课程管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:theory:edit")
    @PostMapping("/changeStatus")
    @ResponseBody
    public AjaxResult changeStatus(SysTheory sysTheory)
    {
        return toAjax(sysTheoryService.changeStatus(sysTheory));
    }
}
