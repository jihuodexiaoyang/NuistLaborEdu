package com.ruoyi.web.controller.system;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.utils.file.ImageUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
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
import com.ruoyi.system.domain.SysCourseCover;
import com.ruoyi.system.service.ISysCourseCoverService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

import javax.servlet.http.HttpServletResponse;

/**
 * 封面图片Controller
 *
 * @author ruoyi
 * @date 2021-12-26
 */
@Controller
@RequestMapping("/system/cover")
public class SysCourseCoverController extends BaseController
{
    private String prefix = "system/cover";

    @Autowired
    private ISysCourseCoverService sysCourseCoverService;

    @RequiresPermissions("system:elearning:view")
    @GetMapping()
    public String cover()
    {
        return prefix + "/cover";
    }
    /**
     * 预览图片
     */
    @GetMapping("/preview/{fileUuid}")
    public void preview(HttpServletResponse response,@PathVariable("fileUuid") String fileUuid) throws IOException
    {
        try{
            SysCourseCover sysCourseCover=sysCourseCoverService.selectSysCourseCoverByFileUuid(fileUuid);
            File imageFile = new File(sysCourseCover.getFilePath());
            FileInputStream fis;
            fis = new FileInputStream(imageFile);
            long size = imageFile.length();
            byte[] data = new byte[(int) size];
            fis.read(data, 0, (int) size);
            fis.close();
            response.setContentType("image/png");
            OutputStream out = response.getOutputStream();
            out.write(data);
            out.flush();
            out.close();
        }catch (Exception ex){
            logger.error("查看封面图片错误");
        }
    }

    /**
     * 查询封面图片列表
     */
    @RequiresPermissions("system:elearning:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysCourseCover sysCourseCover)
    {
        //initCourseCover();
        startPage();
        List<SysCourseCover> list = sysCourseCoverService.selectSysCourseCoverList(sysCourseCover);
        return getDataTable(list);
    }
    public int initCourseCover(){
        //先清除数据库记录 再读取文件内容
        sysCourseCoverService.truncateTable();
        try {
            String coverPath= RuoYiConfig.getCoverPath();
            File coverDir=new File(coverPath);
            if(coverDir.exists()){
                coverDir.mkdirs();
            }
            String[] filelist = coverDir.list();
            List<SysCourseCover> coverList=new ArrayList<SysCourseCover>();
            for (int i = 0; i < filelist.length; i++) {
                String fileName=filelist[i];
                File readfile = new File(coverPath + File.separator + fileName);
                if (!readfile.isDirectory()) {
                    SysCourseCover sysCourseCover=new SysCourseCover();
                    sysCourseCover.setFileName(fileName);
                    sysCourseCover.setFileUuid(IdUtils.fastSimpleUUID());
                    sysCourseCover.setFilePath(readfile.getAbsolutePath());
                    sysCourseCover.setFileSize(new BigDecimal(readfile.length()/1024.0/1024.0));
                    sysCourseCover.setFileExt(fileName.substring(fileName.lastIndexOf(".") + 1));
                    coverList.add(sysCourseCover);
                }
            }
            return sysCourseCoverService.insertBatch(coverList);
        }catch (Exception ex){
            logger.error("读取目录失败", ex);
            return -1;
        }
    }
    /**
     * 导出封面图片列表
     */
    @RequiresPermissions("system:elearning:export")
    @Log(title = "封面图片", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysCourseCover sysCourseCover)
    {
        List<SysCourseCover> list = sysCourseCoverService.selectSysCourseCoverList(sysCourseCover);
        ExcelUtil<SysCourseCover> util = new ExcelUtil<SysCourseCover>(SysCourseCover.class);
        return util.exportExcel(list, "封面图片数据");
    }

    /**
     * 新增封面图片
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存封面图片
     */
    @RequiresPermissions("system:elearning:add")
    @Log(title = "封面图片", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SysCourseCover sysCourseCover)
    {
        return toAjax(sysCourseCoverService.insertSysCourseCover(sysCourseCover));
    }

    /**
     * 修改封面图片
     */
    @GetMapping("/edit/{fileUuid}")
    public String edit(@PathVariable("fileUuid") String fileUuid, ModelMap mmap)
    {
        SysCourseCover sysCourseCover = sysCourseCoverService.selectSysCourseCoverByFileUuid(fileUuid);
        mmap.put("sysCourseCover", sysCourseCover);
        return prefix + "/edit";
    }

    /**
     * 修改保存封面图片
     */
    @RequiresPermissions("system:elearning:edit")
    @Log(title = "封面图片", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SysCourseCover sysCourseCover)
    {
        return toAjax(sysCourseCoverService.updateSysCourseCover(sysCourseCover));
    }

    /**
     * 删除封面图片
     */
    @RequiresPermissions("system:elearning:remove")
    @Log(title = "封面图片", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(sysCourseCoverService.deleteSysCourseCoverByFileUuids(ids));
    }
}
