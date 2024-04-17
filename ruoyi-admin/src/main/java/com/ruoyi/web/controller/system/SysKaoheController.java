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
import com.ruoyi.system.domain.SysKaohe;
import com.ruoyi.system.service.ISysUserClassService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 考核导出Controller
 * 
 * @author ruoyi
 * @date 2022-01-12
 */
@Controller
@RequestMapping("/system/kaohe")
public class SysKaoheController extends BaseController
{
    private String prefix = "system/kaohe";

    @Autowired
    private ISysUserClassService sysUserClassService;

    @RequiresPermissions("system:kaohe:view")
    @GetMapping("/kaohe")
    public String kaohe()
    {
        return "system/touxi/touxi";
    }
    @RequiresPermissions("system:kaohe:view")
    @GetMapping("/yujing")
    public String yujing()
    {
        return prefix + "/yujing";
    }

    /**
     * 查询考核导出列表
     */
    @RequiresPermissions("system:kaohe:list")
    @PostMapping("/listkaohe")
    @ResponseBody
    public TableDataInfo listkaohe(SysKaohe sysKaohe)
    {
        startPage();
        if(sysKaohe.getDeptId()==null){
            sysKaohe.setDeptId(getSysUser().getDeptId());
        }
        List<SysKaohe> list = sysUserClassService.selectSysUserKaoheList(sysKaohe);
        return getDataTable(list);
    }
    /**
     * 查询课程预警列表
     */
    @RequiresPermissions("system:kaohe:list")
    @PostMapping("/listyujing")
    @ResponseBody
    public TableDataInfo listyujing(SysKaohe sysKaohe)
    {
        startPage();
        if(sysKaohe.getDeptId()==null){
            sysKaohe.setDeptId(getSysUser().getDeptId());
        }
        List<SysKaohe> list = sysUserClassService.selectSysUserYujingList(sysKaohe);
        return getDataTable(list);
    }
    /**
     * 导出考核导出列表
     */
    @RequiresPermissions("system:kaohe:export")
    @Log(title = "考核导出", businessType = BusinessType.EXPORT)
    @PostMapping("/exportkaohe")
    @ResponseBody
    public AjaxResult exportkaohe(SysKaohe sysKaohe)
    {
        if(sysKaohe.getDeptId()==null){
            sysKaohe.setDeptId(getSysUser().getDeptId());
        }
        List<SysKaohe> list = sysUserClassService.selectSysUserKaoheList(sysKaohe);
        ExcelUtil<SysKaohe> util = new ExcelUtil<SysKaohe>(SysKaohe.class);
        return util.exportExcel(list, "考核导出数据");
    }
    /**
     * 导出考核导出列表
     */
    @RequiresPermissions("system:kaohe:export")
    @Log(title = "课程预警", businessType = BusinessType.EXPORT)
    @PostMapping("/exportyujing")
    @ResponseBody
    public AjaxResult exportyujing(SysKaohe sysKaohe)
    {
        if(sysKaohe.getDeptId()==null){
            sysKaohe.setDeptId(getSysUser().getDeptId());
        }
        List<SysKaohe> list = sysUserClassService.selectSysUserYujingList(sysKaohe);
        ExcelUtil<SysKaohe> util = new ExcelUtil<SysKaohe>(SysKaohe.class);
        return util.exportExcel(list, "课程预警数据");
    }
}
