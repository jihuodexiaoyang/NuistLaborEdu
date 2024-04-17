package com.ruoyi.web.controller.system;

import java.util.List;

import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.file.FileUtils;
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
import com.ruoyi.system.domain.SysAttachment;
import com.ruoyi.system.service.ISysAttachmentService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 成绩附件Controller
 * 
 * @author ruoyi
 * @date 2022-03-14
 */
@Controller
@RequestMapping("/system/attachment")
public class SysAttachmentController extends BaseController
{
    private String prefix = "system/attachment";

    @Autowired
    private ISysAttachmentService sysAttachmentService;

    @RequiresPermissions("system:attachment:view")
    @GetMapping()
    public String attachment()
    {
        return prefix + "/attachment";
    }

    /**
     * 查询成绩附件列表
     */
    @RequiresPermissions("system:attachment:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysAttachment sysAttachment)
    {
        startPage();
        List<SysAttachment> list = sysAttachmentService.selectSysAttachmentList(sysAttachment);
        return getDataTable(list);
    }

    /**
     * 导出成绩附件列表
     */
    @RequiresPermissions("system:attachment:export")
    @Log(title = "成绩附件", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysAttachment sysAttachment)
    {
        List<SysAttachment> list = sysAttachmentService.selectSysAttachmentList(sysAttachment);
        ExcelUtil<SysAttachment> util = new ExcelUtil<SysAttachment>(SysAttachment.class);
        return util.exportExcel(list, "成绩附件数据");
    }

    /**
     * 新增线上理论成绩附件
     */
    @GetMapping("/add/{deptId}")
    public String add(@PathVariable("deptId") Long deptId,ModelMap mmap)
    {
        SysAttachment sysAttachment=new SysAttachment();
        sysAttachment.setFileType("1");
        sysAttachment.setDeptId(deptId);
        mmap.put("sysAttachment", sysAttachment);
        return prefix + "/add";
    }
    /**
     * 新增专业实践成绩附件
     */
    @GetMapping("/add1/{deptId}")
    public String add1(@PathVariable("deptId") Long deptId,ModelMap mmap)
    {
        SysAttachment sysAttachment=new SysAttachment();
        sysAttachment.setFileType("2");
        sysAttachment.setDeptId(deptId);
        mmap.put("sysAttachment", sysAttachment);
        return prefix + "/add";
    }

    /**
     * 新增保存成绩附件
     */
    /*
    @RequiresPermissions("system:attachment:add")
    @Log(title = "成绩附件", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SysAttachment sysAttachment)
    {
        return toAjax(sysAttachmentService.insertSysAttachment(sysAttachment));
    }*/
    /**
     * 新增保存文件上传
     */
    @Log(title = "文件上传", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SysAttachment sysAttachment, MultipartFile uploadfile)
    {
        try
        {
            // 上传文件路径
            String filePath = RuoYiConfig.getUploadPath();
            // 上传并返回新文件名称
            String dealPath = FileUploadUtils.upload(filePath, uploadfile);
            AjaxResult ajax = AjaxResult.success();
            //保存上传记录
            sysAttachment.setFileName(uploadfile.getOriginalFilename());
            sysAttachment.setFilePath(dealPath);
            sysAttachment.setUserId(getUserId());
            sysAttachmentService.insertSysAttachment(sysAttachment);
            return ajax;
        }
        catch (Exception e)
        {
            return AjaxResult.error(e.getMessage());
        }
        //return toAjax(bizFileUploadService.insertBizFileUpload(bizFileUpload));
    }

    /**
     * 修改成绩附件
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        SysAttachment sysAttachment = sysAttachmentService.selectSysAttachmentById(id);
        mmap.put("sysAttachment", sysAttachment);
        return prefix + "/edit";
    }

    /**
     * 修改保存成绩附件
     */
    @RequiresPermissions("system:attachment:edit")
    @Log(title = "成绩附件", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SysAttachment sysAttachment)
    {
        return toAjax(sysAttachmentService.updateSysAttachment(sysAttachment));
    }

    /**
     * 删除成绩附件
     */
    @RequiresPermissions("system:attachment:remove")
    @Log(title = "成绩附件", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(sysAttachmentService.deleteSysAttachmentByIds(ids));
    }
}
