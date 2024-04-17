package com.ruoyi.web.controller.system;

import java.util.List;

import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.system.domain.SysUserClass;
import com.ruoyi.system.service.ISysCourseService;
import com.ruoyi.system.service.ISysUserClassService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.SysElearning;
import com.ruoyi.system.service.ISysElearningService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 在线学习Controller
 *
 * @author ruoyi
 * @date 2021-12-19
 */
@Controller
@RequestMapping("/system/elearning")
public class SysElearningController extends BaseController
{
    private String prefix = "system/elearning";

    @Autowired
    private ISysElearningService sysElearningService;
    @Autowired
    private ISysUserClassService sysUserClassService;
    @Autowired
    private ISysCourseService sysCourseService;

    @RequiresPermissions("system:elearning:view")
    @GetMapping()
    public String elearning()
    {
        return prefix + "/elearning";
    }
    /**
     * 学生选课界面
     */
    @RequiresPermissions("system:elearning:select")
    @GetMapping("/select")
    public String select()
    {
        return prefix + "/select";
    }
    /**
     * 学生已选课程界面
     */
    @RequiresPermissions("system:elearning:select")
    @GetMapping("/selected")
    public String selected()
    {
        return prefix + "/selected";
    }

    /**
     * 查询在线学习列表
     */
    @RequiresPermissions("system:elearning:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysElearning sysElearning)
    {
        startPage();
        sysElearning.setDelFlag("0");
        List<SysElearning> list = sysElearningService.selectSysElearningList(sysElearning);
        return getDataTable(list);
    }
    /**
     * 查询在线学习列表（选课）
     */
    @RequiresPermissions("system:elearning:select")
    @PostMapping("/listsel")
    @ResponseBody
    public TableDataInfo listsel(SysElearning sysElearning)
    {
        startPage();
        sysElearning.setDelFlag("0");
        sysElearning.setClassId(getUserId());
        List<SysElearning> list = sysElearningService.selectSysElearningListSelect(sysElearning);
        return getDataTable(list);
    }
    /**
     * 查询已选课程列表
     */
    @RequiresPermissions("system:elearning:select")
    @PostMapping("/listselected")
    @ResponseBody
    public TableDataInfo listselected(SysElearning sysElearning)
    {
        startPage();
        sysElearning.setDelFlag("0");
        sysElearning.setClassId(getUserId());
        List<SysElearning> list = sysElearningService.selectSysElearningListSelected(sysElearning);
        return getDataTable(list);
    }
    /**
     * 导出在线学习列表
     */
    @RequiresPermissions("system:elearning:export")
    @Log(title = "在线学习", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysElearning sysElearning)
    {
        List<SysElearning> list = sysElearningService.selectSysElearningList(sysElearning);
        ExcelUtil<SysElearning> util = new ExcelUtil<SysElearning>(SysElearning.class);
        return util.exportExcel(list, "在线学习数据");
    }
    /**
     * 导出在线学习列表
     */
    @RequiresPermissions("system:elearning:select")
    @Log(title = "在线学习", businessType = BusinessType.EXPORT)
    @PostMapping("/exportselected")
    @ResponseBody
    public AjaxResult exportselected(SysElearning sysElearning)
    {
        sysElearning.setDelFlag("0");
        sysElearning.setClassId(getUserId());
        List<SysElearning> list = sysElearningService.selectSysElearningListSelected(sysElearning);
        ExcelUtil<SysElearning> util = new ExcelUtil<SysElearning>(SysElearning.class);
        return util.exportExcel(list, "在线学习选课数据");
    }
    /**
     * 新增在线学习
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存在线学习
     */
    @RequiresPermissions("system:elearning:add")
    @Log(title = "在线学习", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SysElearning sysElearning)
    {
        sysElearning.setCreateBy(getLoginName());
        return toAjax(sysElearningService.insertSysElearning(sysElearning));
    }

    /**
     * 修改在线学习
     */
    @GetMapping("/edit/{classId}")
    public String edit(@PathVariable("classId") Long classId, ModelMap mmap)
    {
        SysElearning sysElearning = sysElearningService.selectSysElearningByClassId(classId);
        mmap.put("sysElearning", sysElearning);
        return prefix + "/edit";
    }

    /**
     * 修改保存在线学习
     */
    @RequiresPermissions("system:elearning:edit")
    @Log(title = "在线学习", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SysElearning sysElearning)
    {
        sysElearning.setUpdateBy(getLoginName());
        return toAjax(sysElearningService.updateSysElearning(sysElearning));
    }
    /**
     * 在线学习选课
     */
    @RequiresPermissions("system:elearning:select")
    @Log(title = "在线学习选课", businessType = BusinessType.SELECT)
    @PostMapping("/select")
    @ResponseBody
    @Transactional
    public AjaxResult selectSave(SysElearning sysElearning)
    {
        SysElearning validEntity=sysElearningService.selectSysElearningByClassId(sysElearning.getClassId());
        if(validEntity.getStatus().equals("0")){
            //判断学时是否已选课
            SysUserClass sysUserClass=new SysUserClass();
            sysUserClass.setUserId(getUserId());
            sysUserClass.setClassId(validEntity.getClassId());
            //0理论学习 1实践学习 2在线学习
            sysUserClass.setClassType("2");
            int selectCount=sysUserClassService.selectSysUserClassList(sysUserClass).size();
            if(selectCount==0){
                //不用判断选课人数 将选课信息保存至数据库
                sysUserClassService.insertSysUserClass(sysUserClass);
                sysElearningService.updateSelected(validEntity);
                return AjaxResult.success("选课成功");
            }else {
                return AjaxResult.error("已选课,请勿重复操作");
            }
        }else{
            return AjaxResult.error("未到选课时间,不能完成选课操作");
        }
    }
    /**
     * 在线学习取消选课
     */
    @RequiresPermissions("system:elearning:select")
    @Log(title = "在线学习取消选课", businessType = BusinessType.DESELECT)
    @PostMapping("/deselect")
    @ResponseBody
    @Transactional
    public AjaxResult deSelect(SysElearning sysElearning)
    {
        SysElearning validEntity=sysElearningService.selectSysElearningByClassId(sysElearning.getClassId());
        //判断学时是否已选课
        SysUserClass sysUserClass=new SysUserClass();
        sysUserClass.setUserId(getUserId());
        sysUserClass.setClassId(validEntity.getClassId());
        //0理论学习 1实践学习 2在线学习
        sysUserClass.setClassType("2");
        int deSelectMark=sysUserClassService.deSelectClass(sysUserClass);
        if(deSelectMark>0){
            //不用判断选课人数 将取消选课信息保存至数据库
            int markDeSelected=sysElearningService.updateDeSelected(validEntity);
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
     * 删除在线学习
     */
    @RequiresPermissions("system:elearning:remove")
    @Log(title = "在线学习", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(sysElearningService.deleteSysElearningByClassIds(ids));
    }
    /**
     * 查询课程详细
     */
    @RequiresPermissions("system:elearning:list")
    @GetMapping("/detail/{classId}")
    public String detail(@PathVariable("classId") Long classId, ModelMap mmap)
    {
        mmap.put("sysElearning", sysElearningService.selectSysElearningByClassId(classId));
        return "system/course/course";
    }
    /**
     * 在线学习
     */
    @RequiresPermissions("system:elearning:learn")
    @GetMapping("/learn/{classId}")
    public String learn(@PathVariable("classId") Long classId, ModelMap mmap)
    {
        SysElearning sysElearning=sysElearningService.selectSysElearningByClassId(classId);
        mmap.put("sysElearning", sysElearning);
        return "system/elearning/learn";
    }
    /**
     * 在线播放视频
     */
    @RequiresPermissions("system:elearning:learn")
    @GetMapping("/video/{courseId}")
    public String video(@PathVariable("courseId") Long courseId, ModelMap mmap)
    {
        mmap.put("sysCourse", sysCourseService.selectSysCourseByCourseId(courseId));
        mmap.put("appId", RuoYiConfig.getAppId());
        return "system/elearning/video";
    }
    /**
     * 课程状态修改
     */
    @Log(title = "课程管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:elearning:edit")
    @PostMapping("/changeStatus")
    @ResponseBody
    public AjaxResult changeStatus(SysElearning sysElearning)
    {
        return toAjax(sysElearningService.changeStatus(sysElearning));
    }
}
