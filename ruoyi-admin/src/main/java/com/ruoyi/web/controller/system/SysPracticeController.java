package com.ruoyi.web.controller.system;

import java.io.IOException;
import java.util.List;

import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.core.domain.entity.SysDept;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.SysTheory;
import com.ruoyi.system.domain.SysUserClass;
import com.ruoyi.system.service.ISysDeptService;
import com.ruoyi.system.service.ISysUserClassService;
import com.ruoyi.system.service.ISysUserService;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;
import org.apache.commons.io.IOUtils;
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
import com.ruoyi.system.domain.SysPractice;
import com.ruoyi.system.service.ISysPracticeService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

import javax.servlet.http.HttpServletResponse;

/**
 * 实践学习Controller
 *
 * @author ruoyi
 * @date 2021-12-19
 */
@Controller
@RequestMapping("/system/practice")
public class SysPracticeController extends BaseController  {
    private String prefix = "system/practice";

    @Autowired
    private ISysPracticeService sysPracticeService;
    @Autowired
    private ISysUserClassService sysUserClassService;
    @Autowired
    private ISysDeptService sysDeptService;
    @Autowired
    private ISysUserService userService;

    @RequiresPermissions("system:practice:view")
    @GetMapping()
    public String practice()
    {
        return prefix + "/practice";
    }

    /**
     * 学生选课界面
     */
    @RequiresPermissions("system:practice:select")
    @GetMapping("/select")
    public String select()
    {
        return prefix + "/select";
    }

    /**
     * 学生已选选课界面
     */
    @RequiresPermissions("system:practice:select")
    @GetMapping("/selected")
    public String selected()
    {
        return prefix + "/selected";
    }

    /**
     * 查询实践学习列表
     */
    @RequiresPermissions("system:practice:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysPractice sysPractice)
    {
        startPage();
        sysPractice.setDelFlag("0");
        SysUser sysUser=getSysUser();
        //非管理员 只查询本部门数据
        if(!sysUser.isAdmin()){
            sysPractice.setClassDept(sysUser.getDeptId());
        }
        List<SysPractice> list = sysPracticeService.selectSysPracticeList(sysPractice);
        return getDataTable(list);
    }
    /**
     * 分配负责人页面
     */
    @RequiresPermissions("system:practice:list")
    @GetMapping("/selectfzr")
    public String selectfzr()
    {
        return prefix + "/selectfzr";
    }
    /**
     * 查询负责人列表
     */
    @RequiresPermissions("system:practice:list")
    @PostMapping("/fzrlist")
    @ResponseBody
    public TableDataInfo fzrlist(SysUser user)
    {
        startPage();
        List<SysUser> list = userService.selectUserList(user);
        return getDataTable(list);
    }
    /**
     * 查询实践学习列表(选课)
     */
    @RequiresPermissions("system:practice:select")
    @PostMapping("/listsel")
    @ResponseBody
    public TableDataInfo listsel(SysPractice sysPractice)
    {
        startPage();
        //非删除状态的
        sysPractice.setDelFlag("0");
        SysUser sysUser=getSysUser();
        /*
        //非管理员 只查询本部门数据
        if(!sysUser.isAdmin()){
            SysDept sysDept=sysDeptService.selectDeptById(sysUser.getDeptId());
            String[] ans=sysDept.getAncestors().split(",");
            sysPractice.setClassDept(Long.parseLong(ans[2]));
        }*/
        //利用classId存储用户id
        sysPractice.setClassId(sysUser.getUserId());
        //利用classDept存储用户所在部门编号
        sysPractice.setClassDept(sysUser.getDeptId());
        List<SysPractice> list = sysPracticeService.selectSysPracticeListSelect(sysPractice);
        return getDataTable(list);
    }
    /**
     * 查询已选实践学习列表
     */
    @RequiresPermissions("system:practice:select")
    @PostMapping("/listselected")
    @ResponseBody
    public TableDataInfo listselected(SysPractice sysPractice)
    {
        startPage();
        sysPractice.setDelFlag("0");
        sysPractice.setClassId(getUserId());
        List<SysPractice> list = sysPracticeService.selectSysPracticeListSelected(sysPractice);
        return getDataTable(list);
    }
    /**
     * 导出实践学习列表
     */
    @RequiresPermissions("system:practice:export")
    @Log(title = "实践学习", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysPractice sysPractice)
    {
        List<SysPractice> list = sysPracticeService.selectSysPracticeList(sysPractice);
        ExcelUtil<SysPractice> util = new ExcelUtil<SysPractice>(SysPractice.class);
        return util.exportExcel(list, "实践学习数据");
    }
    /**
     * 导出实践学习列表
     */
    @RequiresPermissions("system:practice:select")
    @Log(title = "实践学习", businessType = BusinessType.EXPORT)
    @PostMapping("/exportselected")
    @ResponseBody
    public AjaxResult exportselected(SysPractice sysPractice)
    {
        sysPractice.setDelFlag("0");
        sysPractice.setClassId(getUserId());
        List<SysPractice> list = sysPracticeService.selectSysPracticeListSelected(sysPractice);
        ExcelUtil<SysPractice> util = new ExcelUtil<SysPractice>(SysPractice.class);
        return util.exportExcel(list, "实践学习选课数据");
    }
    /**
     * 新增实践学习
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存实践学习
     */
    @RequiresPermissions("system:practice:add")
    @Log(title = "实践学习", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SysPractice sysPractice)
    {
        sysPractice.setCreateBy(getLoginName());
        sysPractice.setClassDept(getSysUser().getDeptId());
        return toAjax(sysPracticeService.insertSysPractice(sysPractice));
    }

    /**
     * 修改实践学习
     */
    @GetMapping("/edit/{classId}")
    public String edit(@PathVariable("classId") Long classId, ModelMap mmap)
    {
        SysPractice sysPractice = sysPracticeService.selectSysPracticeByClassId(classId);
        mmap.put("sysPractice", sysPractice);
        return prefix + "/edit";
    }

    /**
     * 修改保存实践学习
     */
    @RequiresPermissions("system:practice:edit")
    @Log(title = "实践学习", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SysPractice sysPractice)
    {
        sysPractice.setUpdateBy(getLoginName());
        return toAjax(sysPracticeService.updateSysPractice(sysPractice));
    }
    /**
     * 实践学习选课
     */
    @RequiresPermissions("system:practice:select")
    @Log(title = "实践学习选课", businessType = BusinessType.SELECT)
    @PostMapping("/select")
    @ResponseBody
    @Transactional
    public AjaxResult selectSave(SysPractice sysPractice)
    {
        SysPractice validEntity=sysPracticeService.selectSysPracticeByClassId(sysPractice.getClassId());
        if(validEntity.getStatus().equals("0")){
            //判断学时是否已选课
            SysUserClass sysUserClass=new SysUserClass();
            sysUserClass.setUserId(getUserId());
            sysUserClass.setClassId(validEntity.getClassId());
            //0理论学习 1实践学习 2在线学习
            sysUserClass.setClassType("1");
            int selectCount=sysUserClassService.selectSysUserClassList(sysUserClass).size();
            if(selectCount==0){
                //判断选课人数是否超限
                SysUserClass checkSelect=new SysUserClass();
                checkSelect.setClassId(validEntity.getClassId());
                if(validEntity.getClassSubject().equals("0")){
                    //校园劳动
                    checkSelect.setClassType("1");
                }else {
                    //社会服务
                    checkSelect.setClassType("2");
                }
                int checkCount=sysUserClassService.selectSysUserClassList(checkSelect).size();
                if(checkCount<validEntity.getClassLimit()){
                    //将选课信息保存至数据库
                    sysUserClassService.insertSysUserClass(sysUserClass);
                    sysPracticeService.updateSelected(validEntity);
                    return AjaxResult.success("选课成功");
                }else{
                    return AjaxResult.error("该课程人数已选满");
                }

            }else {
                return AjaxResult.error("已选课,请勿重复操作");
            }
        }else{
            return AjaxResult.error("未到选课时间,不能完成选课操作");
        }
    }
    /**
     * 实践学习取消选课
     */
    @RequiresPermissions("system:practice:select")
    @Log(title = "实践学习取消选课", businessType = BusinessType.DESELECT)
    @PostMapping("/deselect")
    @ResponseBody
    @Transactional
    public AjaxResult deSelect(SysPractice sysPractice)
    {
        SysPractice validEntity=sysPracticeService.selectSysPracticeByClassId(sysPractice.getClassId());
        //判断学时是否已选课
        SysUserClass sysUserClass=new SysUserClass();
        sysUserClass.setUserId(getUserId());
        sysUserClass.setClassId(validEntity.getClassId());
        //0理论学习 1实践学习 2在线学习
        sysUserClass.setClassType("1");
        int deSelectMark=sysUserClassService.deSelectClass(sysUserClass);
        if(deSelectMark>0){
            //不用判断选课人数 将取消选课信息保存至数据库
            int markDeSelected=sysPracticeService.updateDeSelected(validEntity);
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
     * 删除实践学习
     */
    @RequiresPermissions("system:practice:remove")
    @Log(title = "实践学习", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(sysPracticeService.deleteSysPracticeByClassIds(ids));
    }
    /**
     * 生成二维码（下载方式）
     */
    @RequiresPermissions("system:practice:generate")
    @Log(title = "二维码生成", businessType = BusinessType.QRCODE)
    @GetMapping("/genqrcode/{classId}")
    public void genqrcode(HttpServletResponse response, @PathVariable("classId") Long classId) throws IOException
    {
        try{
            //SysTheory sysTheory=sysTheoryService.selectSysTheoryByClassId(classId);
            //String downloadName= StringUtils.format("{}_{}.gif",sysTheory.getClassOrg(),sysTheory.getClassTitle());
            String signUrl=StringUtils.format("{}sign/index.html?classid={}&classtype=1", RuoYiConfig.getSignAddress(),classId);
            byte[] data= QRCode.from(signUrl).to(ImageType.GIF).withSize(300, 300).stream().toByteArray();
            genQrcodeImage(response,data);
        } catch (Exception e)
        {
            logger.error("下载二维码失败", e);
        }
    }
    /**
     * 生成下载图片
     */
    private void genQrcodeImage(HttpServletResponse response, byte[] data) throws IOException
    {
        response.reset();
        response.setHeader("Content-Disposition", StringUtils.format("attachment; filename=\"{}.gif\"", DateUtils.dateTimeNow()));
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");
        IOUtils.write(data, response.getOutputStream());
    }
    /**
     * 课程状态修改
     */
    @Log(title = "课程管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:practice:edit")
    @PostMapping("/changeStatus")
    @ResponseBody
    public AjaxResult changeStatus(SysPractice sysPractice)
    {
        return toAjax(sysPracticeService.changeStatus(sysPractice));
    }
}
