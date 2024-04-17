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
import com.ruoyi.system.domain.SysCreditOnline;
import com.ruoyi.system.service.ISysCreditOnlineService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 线上教学成绩Controller
 * 
 * @author ruoyi
 * @date 2022-03-08
 */
@Controller
@RequestMapping("/system/online")
public class SysCreditOnlineController extends BaseController
{
    private String prefix = "system/online";

    @Autowired
    private ISysCreditOnlineService sysCreditOnlineService;

    @RequiresPermissions("system:online:view")
    @GetMapping()
    public String online()
    {
        return "system/credit/online";
    }

    /**
     * 查询线上教学成绩列表
     */
    @RequiresPermissions("system:online:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysCreditOnline sysCreditOnline)
    {
        startPage();
        List<SysCreditOnline> list = sysCreditOnlineService.selectSysCreditOnlineList(sysCreditOnline);
        return getDataTable(list);
    }
    /**
     * 查询线上教学成绩列表
     */
    @RequiresPermissions("system:online:list")
    @PostMapping("/listonline")
    @ResponseBody
    public TableDataInfo listonline(SysCredit sysCredit)
    {
        startPage();
        List<SysCredit> list = sysCreditOnlineService.selectSysCreditOnlineDeptList(sysCredit);
        return getDataTable(list);
    }
    /**
     * 下载模板
     */
    @GetMapping("/importtemplate")
    @ResponseBody
    public AjaxResult importtemplate()
    {
        ExcelUtil<SysCreditOnline> util = new ExcelUtil<SysCreditOnline>(SysCreditOnline.class);
        return util.importTemplateExcel("线上成绩数据");
    }
    /**
     * 导入数据
     */
    @PostMapping("/importonline")
    @ResponseBody
    public AjaxResult importonline(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<SysCreditOnline> util = new ExcelUtil<SysCreditOnline>(SysCreditOnline.class);
        List<SysCreditOnline> userCreditList = util.importExcel(file.getInputStream());
        String message = importCredits(userCreditList);
        //String message="导入成功";
        return AjaxResult.success(message);
    }
    public String importCredits(List<SysCreditOnline> userCreditList)
    {
        if (StringUtils.isNull(userCreditList) || userCreditList.size() == 0)
        {
            throw new ServiceException("导入成绩数据不能为空！");
        }
        int successNum = 0;
        for(SysCreditOnline sysCreditOnline:userCreditList){
            List<SysCreditOnline> sysCreditOnlineList=sysCreditOnlineService.selectSysCreditOnlineList(sysCreditOnline);
            int count1=sysCreditOnlineList.size();
            if(count1==0){
                successNum+=sysCreditOnlineService.insertSysCreditOnline(sysCreditOnline);
            }else{
                SysCreditOnline updateEntity=sysCreditOnlineList.get(0);
                updateEntity.setUserCredit(sysCreditOnline.getUserCredit());
                successNum+=sysCreditOnlineService.updateSysCreditOnline(updateEntity);
            }
        }
        return StringUtils.format("成功导入{}条成绩数据",successNum);
    }
    /**
     * 导出线上教学成绩列表
     */
    @RequiresPermissions("system:online:export")
    @Log(title = "线上教学成绩", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysCreditOnline sysCreditOnline)
    {
        List<SysCreditOnline> list = sysCreditOnlineService.selectSysCreditOnlineList(sysCreditOnline);
        ExcelUtil<SysCreditOnline> util = new ExcelUtil<SysCreditOnline>(SysCreditOnline.class);
        return util.exportExcel(list, "线上教学成绩数据");
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
        List<SysCredit> list = sysCreditOnlineService.selectSysCreditOnlineDeptList(sysCredit);
        ExcelUtil<SysCredit> util = new ExcelUtil<SysCredit>(SysCredit.class);
        return util.exportExcel(list, "线上教学成绩数据");
    }
    /**
     * 新增线上教学成绩
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存线上教学成绩
     */
    @RequiresPermissions("system:online:add")
    @Log(title = "线上教学成绩", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SysCreditOnline sysCreditOnline)
    {
        return toAjax(sysCreditOnlineService.insertSysCreditOnline(sysCreditOnline));
    }

    /**
     * 修改线上教学成绩
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        SysCreditOnline sysCreditOnline = sysCreditOnlineService.selectSysCreditOnlineById(id);
        mmap.put("sysCreditOnline", sysCreditOnline);
        return prefix + "/edit";
    }
    /**
     * 打开线上理论附件管理
     */
    @RequiresPermissions("system:online:import")
    @GetMapping("/attachment/{deptId}")
    public String attachment(@PathVariable("deptId") Long deptId, ModelMap mmap)
    {
        SysAttachment sysAttachment=new SysAttachment();
        sysAttachment.setDeptId(deptId);
        sysAttachment.setFileType("1");
        mmap.put("sysAttachment", sysAttachment);
        return "system/attachment/attachment";
    }
    /**
     * 修改保存线上教学成绩
     */
    @RequiresPermissions("system:online:edit")
    @Log(title = "线上教学成绩", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SysCreditOnline sysCreditOnline)
    {
        return toAjax(sysCreditOnlineService.updateSysCreditOnline(sysCreditOnline));
    }

    /**
     * 删除线上教学成绩
     */
    @RequiresPermissions("system:online:remove")
    @Log(title = "线上教学成绩", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(sysCreditOnlineService.deleteSysCreditOnlineByIds(ids));
    }
}
