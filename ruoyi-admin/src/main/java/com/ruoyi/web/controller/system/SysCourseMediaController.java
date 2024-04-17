package com.ruoyi.web.controller.system;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.ruoyi.common.config.RuoYiConfig;
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
import com.ruoyi.system.domain.SysCourseMedia;
import com.ruoyi.system.service.ISysCourseMediaService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 视频文件Controller
 *
 * @author ruoyi
 * @date 2021-12-26
 */
@Controller
@RequestMapping("/system/media")
public class SysCourseMediaController extends BaseController
{
    private String prefix = "system/media";

    @Autowired
    private ISysCourseMediaService sysCourseMediaService;

    @RequiresPermissions("system:elearning:view")
    @GetMapping()
    public String media()
    {
        return prefix + "/media";
    }

    /**
     * 查询视频文件列表
     */
    @RequiresPermissions("system:elearning:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysCourseMedia sysCourseMedia)
    {
        //initCourseMedia();
        startPage();
        List<SysCourseMedia> list = sysCourseMediaService.selectSysCourseMediaList(sysCourseMedia);
        return getDataTable(list);
    }
    public int initCourseMedia(){
        //先清除数据库记录 再读取文件内容
        sysCourseMediaService.truncateTable();
        try {
            String mediaPath= RuoYiConfig.getMediaPath();
            File mediaDir=new File(mediaPath);
            if(mediaDir.exists()){
                mediaDir.mkdirs();
            }
            String[] filelist = mediaDir.list();
            List<SysCourseMedia> mediaList=new ArrayList<SysCourseMedia>();
            for (int i = 0; i < filelist.length; i++) {
                String fileName=filelist[i];
                File readfile = new File(mediaPath + File.separator + fileName);
                if (!readfile.isDirectory()) {
                    SysCourseMedia sysCourseMedia=new SysCourseMedia();
                    sysCourseMedia.setFileName(fileName);
                    sysCourseMedia.setFileUuid(IdUtils.fastSimpleUUID());
                    sysCourseMedia.setFilePath(readfile.getAbsolutePath());
                    sysCourseMedia.setFileSize(new BigDecimal(readfile.length()/1024.0/1024.0));
                    sysCourseMedia.setFileExt(fileName.substring(fileName.lastIndexOf(".") + 1));
                    mediaList.add(sysCourseMedia);
                }
            }
            return sysCourseMediaService.insertBatch(mediaList);
        }catch (Exception ex){
            logger.error("读取目录失败", ex);
            return -1;
        }
    }
    /**
     * 导出视频文件列表
     */
    @RequiresPermissions("system:elearning:export")
    @Log(title = "视频文件", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysCourseMedia sysCourseMedia)
    {
        List<SysCourseMedia> list = sysCourseMediaService.selectSysCourseMediaList(sysCourseMedia);
        ExcelUtil<SysCourseMedia> util = new ExcelUtil<SysCourseMedia>(SysCourseMedia.class);
        return util.exportExcel(list, "视频文件数据");
    }

    /**
     * 新增视频文件
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存视频文件
     */
    @RequiresPermissions("system:elearning:add")
    @Log(title = "视频文件", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SysCourseMedia sysCourseMedia)
    {
        return toAjax(sysCourseMediaService.insertSysCourseMedia(sysCourseMedia));
    }

    /**
     * 修改视频文件
     */
    @GetMapping("/edit/{fileUuid}")
    public String edit(@PathVariable("fileUuid") String fileUuid, ModelMap mmap)
    {
        SysCourseMedia sysCourseMedia = sysCourseMediaService.selectSysCourseMediaByFileUuid(fileUuid);
        mmap.put("sysCourseMedia", sysCourseMedia);
        return prefix + "/edit";
    }

    /**
     * 修改保存视频文件
     */
    @RequiresPermissions("system:elearning:edit")
    @Log(title = "视频文件", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SysCourseMedia sysCourseMedia)
    {
        return toAjax(sysCourseMediaService.updateSysCourseMedia(sysCourseMedia));
    }

    /**
     * 删除视频文件
     */
    @RequiresPermissions("system:elearning:remove")
    @Log(title = "视频文件", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(sysCourseMediaService.deleteSysCourseMediaByFileUuids(ids));
    }
}
