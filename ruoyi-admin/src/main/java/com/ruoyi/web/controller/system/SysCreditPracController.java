package com.ruoyi.web.controller.system;

import java.util.List;

import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.SysAttachment;
import com.ruoyi.system.domain.SysCredit;
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
import com.ruoyi.system.domain.SysCreditPrac;
import com.ruoyi.system.service.ISysCreditPracService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 专业实践成绩Controller
 * 
 * @author ruoyi
 * @date 2022-03-08
 */
@Controller
@RequestMapping("/system/prac")
public class SysCreditPracController extends BaseController
{
    private String prefix = "system/prac";

    @Autowired
    private ISysCreditPracService sysCreditPracService;

    @RequiresPermissions("system:prac:view")
    @GetMapping()
    public String prac()
    {
        return "system/credit/prac";
    }

    /**
     * 查询专业实践成绩列表
     */
    @RequiresPermissions("system:prac:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysCreditPrac sysCreditPrac)
    {
        startPage();
        List<SysCreditPrac> list = sysCreditPracService.selectSysCreditPracList(sysCreditPrac);
        return getDataTable(list);
    }
    /**
     * 查询专业实践成绩列表
     */
    @RequiresPermissions("system:online:list")
    @PostMapping("/listonline")
    @ResponseBody
    public TableDataInfo listonline(SysCredit sysCredit)
    {
        startPage();
        List<SysCredit> list = sysCreditPracService.selectSysCreditPracDeptList(sysCredit);
        return getDataTable(list);
    }
    /**
     * 下载模板
     */
    @GetMapping("/importtemplate")
    @ResponseBody
    public AjaxResult importtemplate()
    {
        ExcelUtil<SysCreditPrac> util = new ExcelUtil<SysCreditPrac>(SysCreditPrac.class);
        return util.importTemplateExcel("专业实践成绩数据");
    }
    /**
     * 导入数据
     */
    @PostMapping("/importonline")
    @ResponseBody
    public AjaxResult importonline(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<SysCreditPrac> util = new ExcelUtil<SysCreditPrac>(SysCreditPrac.class);
        List<SysCreditPrac> userCreditList = util.importExcel(file.getInputStream());
        String message = importCredits(userCreditList);
        //String message="导入成功";
        return AjaxResult.success(message);
    }
    public String importCredits(List<SysCreditPrac> userCreditList)
    {
        if (StringUtils.isNull(userCreditList) || userCreditList.size() == 0)
        {
            throw new ServiceException("导入成绩数据不能为空！");
        }
        int successNum = 0;
        for(SysCreditPrac sysCreditPrac:userCreditList){
            List<SysCreditPrac> sysCreditPracList=sysCreditPracService.selectSysCreditPracList(sysCreditPrac);
            int count1=sysCreditPracList.size();
            if(count1==0){
                successNum+=sysCreditPracService.insertSysCreditPrac(sysCreditPrac);
            }else{
                SysCreditPrac updateEntity=sysCreditPracList.get(0);
                updateEntity.setUserCredit(sysCreditPrac.getUserCredit());
                successNum+=sysCreditPracService.updateSysCreditPrac(updateEntity);
            }
        }
        return StringUtils.format("成功导入{}条成绩数据",successNum);
    }
    /**
     * 导出专业实践成绩列表
     */
    @RequiresPermissions("system:prac:export")
    @Log(title = "专业实践成绩", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysCreditPrac sysCreditPrac)
    {
        List<SysCreditPrac> list = sysCreditPracService.selectSysCreditPracList(sysCreditPrac);
        ExcelUtil<SysCreditPrac> util = new ExcelUtil<SysCreditPrac>(SysCreditPrac.class);
        return util.exportExcel(list, "专业实践成绩数据");
    }
    /**
     * 导出线上教学成绩列表
     */
    @RequiresPermissions("system:online:export")
    @Log(title = "线上教学成绩", businessType = BusinessType.EXPORT)
    @PostMapping("/exportonline")
    @ResponseBody
    public AjaxResult exportonline(SysCredit sysCredit)
    {
        List<SysCredit> list = sysCreditPracService.selectSysCreditPracDeptList(sysCredit);
        ExcelUtil<SysCredit> util = new ExcelUtil<SysCredit>(SysCredit.class);
        return util.exportExcel(list, "专业实践成绩数据");
    }
    /**
     * 新增专业实践成绩
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存专业实践成绩
     */
    @RequiresPermissions("system:prac:add")
    @Log(title = "专业实践成绩", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SysCreditPrac sysCreditPrac)
    {
        return toAjax(sysCreditPracService.insertSysCreditPrac(sysCreditPrac));
    }

    /**
     * 修改专业实践成绩
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        SysCreditPrac sysCreditPrac = sysCreditPracService.selectSysCreditPracById(id);
        mmap.put("sysCreditPrac", sysCreditPrac);
        return prefix + "/edit";
    }
    /**
     * 打开专业实践附件管理
     */
    @RequiresPermissions("system:prac:import")
    @GetMapping("/attachment/{deptId}")
    public String attachment(@PathVariable("deptId") Long deptId, ModelMap mmap)
    {
        SysAttachment sysAttachment=new SysAttachment();
        sysAttachment.setDeptId(deptId);
        sysAttachment.setFileType("1");
        mmap.put("sysAttachment", sysAttachment);
        return "system/attachment/attachment1";
    }
    /**
     * 修改保存专业实践成绩
     */
    @RequiresPermissions("system:prac:edit")
    @Log(title = "专业实践成绩", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SysCreditPrac sysCreditPrac)
    {
        return toAjax(sysCreditPracService.updateSysCreditPrac(sysCreditPrac));
    }

    /**
     * 删除专业实践成绩
     */
    @RequiresPermissions("system:prac:remove")
    @Log(title = "专业实践成绩", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(sysCreditPracService.deleteSysCreditPracByIds(ids));
    }
}
