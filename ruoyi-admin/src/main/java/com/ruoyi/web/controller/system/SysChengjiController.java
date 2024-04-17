package com.ruoyi.web.controller.system;

import java.util.List;
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
import com.ruoyi.system.domain.SysChengji;
import com.ruoyi.system.service.ISysChengjiService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 成绩统计Controller
 * 
 * @author ruoyi
 * @date 2022-03-15
 */
@Controller
@RequestMapping("/system/chengji")
public class SysChengjiController extends BaseController
{
    private String prefix = "system/chengji";

    @Autowired
    private ISysChengjiService sysChengjiService;

    @RequiresPermissions("system:chengji:view")
    @GetMapping()
    public String chengji()
    {
        return prefix + "/chengji";
    }

    /**
     * 查询成绩统计列表
     */
    @RequiresPermissions("system:chengji:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysChengji sysChengji)
    {
        startPage();
        List<SysChengji> list = sysChengjiService.selectSysChengjiList(sysChengji);
        return getDataTable(list);
    }

    /**
     * 导出成绩统计列表
     */
    @RequiresPermissions("system:chengji:export")
    @Log(title = "成绩统计", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysChengji sysChengji)
    {
        List<SysChengji> list = sysChengjiService.selectSysChengjiList(sysChengji);
        ExcelUtil<SysChengji> util = new ExcelUtil<SysChengji>(SysChengji.class);
        return util.exportExcel(list, "成绩统计数据");
    }

    /**
     * 新增成绩统计
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存成绩统计
     */
    @RequiresPermissions("system:chengji:add")
    @Log(title = "成绩统计", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SysChengji sysChengji)
    {
        return toAjax(sysChengjiService.insertSysChengji(sysChengji));
    }

    /**
     * 修改成绩统计
     */
    @GetMapping("/edit/{userId}")
    public String edit(@PathVariable("userId") Long userId, ModelMap mmap)
    {
        SysChengji sysChengji = sysChengjiService.selectSysChengjiByUserId(userId);
        mmap.put("sysChengji", sysChengji);
        return prefix + "/edit";
    }

    /**
     * 修改保存成绩统计
     */
    @RequiresPermissions("system:chengji:edit")
    @Log(title = "成绩统计", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SysChengji sysChengji)
    {
        return toAjax(sysChengjiService.updateSysChengji(sysChengji));
    }

    /**
     * 删除成绩统计
     */
    @RequiresPermissions("system:chengji:remove")
    @Log(title = "成绩统计", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(sysChengjiService.deleteSysChengjiByUserIds(ids));
    }
}
