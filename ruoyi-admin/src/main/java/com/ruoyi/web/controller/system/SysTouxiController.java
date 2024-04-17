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
import com.ruoyi.system.domain.SysTouxi;
import com.ruoyi.system.service.ISysUserClassService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 数据透析Controller
 * 
 * @author ruoyi
 * @date 2022-01-12
 */
@Controller
@RequestMapping("/system/touxi")
public class SysTouxiController extends BaseController
{
    private String prefix = "system/touxi";

    @Autowired
    private ISysUserClassService sysUserClassService;

    @RequiresPermissions("system:touxi:view")
    @GetMapping()
    public String touxi()
    {
        return prefix + "/touxi1";
    }

    /**
     * 查询数据透析列表
     */
    @RequiresPermissions("system:touxi:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysTouxi sysTouxi)
    {
        startPage();
        List<SysTouxi> list = sysUserClassService.selectSysUserTouxiList(sysTouxi);
        return getDataTable(list);
    }

    /**
     * 导出数据透析列表
     */
    @RequiresPermissions("system:touxi:export")
    @Log(title = "数据透析", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysTouxi sysTouxi)
    {
        List<SysTouxi> list = sysUserClassService.selectSysUserTouxiList(sysTouxi);
        ExcelUtil<SysTouxi> util = new ExcelUtil<SysTouxi>(SysTouxi.class);
        return util.exportExcel(list, "数据透析数据");
    }
}
