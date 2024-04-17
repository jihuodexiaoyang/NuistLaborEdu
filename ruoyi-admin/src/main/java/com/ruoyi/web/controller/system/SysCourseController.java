package com.ruoyi.web.controller.system;

import java.util.List;

import com.ruoyi.common.config.VodResponse;
import com.ruoyi.system.domain.SysElearning;
import com.ruoyi.system.service.ISysElearningService;
import com.ruoyi.web.core.config.TencentVodUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.SysCourse;
import com.ruoyi.system.service.ISysCourseService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 在线学习课程Controller
 * 
 * @author ruoyi
 * @date 2021-12-25
 */
@Controller
@RequestMapping("/system/course")
public class SysCourseController extends BaseController
{
    private String prefix = "system/course";
    private String media_prefix = "system/media";
    private String cover_prefix = "system/cover";

    @Autowired
    private ISysCourseService sysCourseService;
    @Autowired
    private ISysElearningService sysElearningService;

    @RequiresPermissions("system:elearning:view")
    @GetMapping()
    public String course()
    {
        return prefix + "/course";
    }


    /**
     * 查询在线学习课程列表
     */
    @RequiresPermissions("system:elearning:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysCourse sysCourse)
    {
        startPage();
        List<SysCourse> list = sysCourseService.selectSysCourseList(sysCourse);
        return getDataTable(list);
    }
    /**
     * 查询在线学习课程列表
     */
    @RequiresPermissions("system:elearning:learn")
    @PostMapping("/listlearn")
    @ResponseBody
    public TableDataInfo listlearn(SysCourse sysCourse)
    {
        startPage();
        List<SysCourse> list = sysCourseService.selectSysCourseList(sysCourse);
        return getDataTable(list);
    }
    /**
     * 导出在线学习课程列表
     */
    @RequiresPermissions("system:elearning:export")
    @Log(title = "在线学习课程", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysCourse sysCourse)
    {
        List<SysCourse> list = sysCourseService.selectSysCourseList(sysCourse);
        ExcelUtil<SysCourse> util = new ExcelUtil<SysCourse>(SysCourse.class);
        return util.exportExcel(list, "在线学习课程数据");
    }

    /**
     * 新增在线学习课程
     */
    @GetMapping("/add/{classId}")
    public String add(@PathVariable("classId") Long classId,ModelMap mmap)
    {
        mmap.put("sysElearning", sysElearningService.selectSysElearningByClassId(classId));
        return prefix + "/add";
    }
    /**
     * 打开视频选择页面
     */
    @GetMapping("/media")
    public String media(ModelMap mmap)
    {
        return media_prefix + "/media";
    }
    /**
     * 打开封面选择页面
     */
    @GetMapping("/cover")
    public String cover(ModelMap mmap)
    {
        return cover_prefix + "/cover";
    }
    /**
     * 新增保存在线学习课程
     */
    @RequiresPermissions("system:elearning:add")
    @Log(title = "在线学习课程", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SysCourse sysCourse)
    {
        VodResponse vodResponse= TencentVodUtils.uploadMedia(sysCourse.getCoursePath(),sysCourse.getCourseCoverpath());
        if(vodResponse!=null){
            sysCourse.setCreateBy(getLoginName());
            sysCourse.setCourseUrl(vodResponse.getMediaUrl());
            sysCourse.setCourseFileid(vodResponse.getFileId());
            sysCourse.setCourseCoverurl(vodResponse.getCoverUrl());
            return toAjax(sysCourseService.insertSysCourse(sysCourse));
        }else{
            return AjaxResult.error("上传失败");
        }
    }

    /**
     * 修改在线学习课程
     */
    @GetMapping("/edit/{courseId}")
    public String edit(@PathVariable("courseId") Long courseId, ModelMap mmap)
    {
        SysCourse sysCourse = sysCourseService.selectSysCourseByCourseId(courseId);
        SysElearning sysElearning=sysElearningService.selectSysElearningByClassId(sysCourse.getClassId());
        mmap.put("sysCourse", sysCourse);
        mmap.put("classTitle", sysElearning.getClassTitle());
        return prefix + "/edit";
    }

    /**
     * 修改保存在线学习课程
     */
    @RequiresPermissions("system:elearning:edit")
    @Log(title = "在线学习课程", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SysCourse sysCourse)
    {
        VodResponse vodResponse= TencentVodUtils.uploadMedia(sysCourse.getCoursePath(),sysCourse.getCourseCoverpath());
        if(vodResponse!=null){
            sysCourse.setCreateBy(getLoginName());
            sysCourse.setCourseUrl(vodResponse.getMediaUrl());
            sysCourse.setCourseFileid(vodResponse.getFileId());
            sysCourse.setCourseCoverurl(vodResponse.getCoverUrl());
            sysCourse.setUpdateBy(getLoginName());
            return toAjax(sysCourseService.updateSysCourse(sysCourse));
        }else{
            return AjaxResult.error("上传失败");
        }
    }

    /**
     * 删除在线学习课程
     */
    @RequiresPermissions("system:elearning:remove")
    @Log(title = "在线学习课程", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(sysCourseService.deleteSysCourseByCourseIds(ids));
    }
    /**
     * 课时状态修改
     */
    @Log(title = "课时管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:elearning:edit")
    @PostMapping("/changeStatus")
    @ResponseBody
    public AjaxResult changeStatus(SysCourse sysCourse)
    {
        return toAjax(sysCourseService.changeStatus(sysCourse));
    }
}
