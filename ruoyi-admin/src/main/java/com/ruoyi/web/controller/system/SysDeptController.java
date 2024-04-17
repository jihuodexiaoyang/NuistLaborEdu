package com.ruoyi.web.controller.system;

import java.util.List;

import com.ruoyi.framework.web.domain.server.Sys;
import com.ruoyi.system.domain.SysPractice;
import com.ruoyi.system.domain.SysTheory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.Ztree;
import com.ruoyi.common.core.domain.entity.SysDept;
import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.service.ISysDeptService;

/**
 * 部门信息
 * 
 * @author ruoyi
 */
@Controller
@RequestMapping("/system/dept")
public class SysDeptController extends BaseController
{
    private String prefix = "system/dept";

    @Autowired
    private ISysDeptService deptService;

    @RequiresPermissions("system:dept:view")
    @GetMapping()
    public String dept()
    {
        return prefix + "/dept";
    }

    @RequiresPermissions("system:dept:list")
    @PostMapping("/list")
    @ResponseBody
    public List<SysDept> list(SysDept dept)
    {
        List<SysDept> deptList = deptService.selectDeptList(dept);
        return deptList;
    }

    /**
     * 新增部门
     */
    @GetMapping("/add/{parentId}")
    public String add(@PathVariable("parentId") Long parentId, ModelMap mmap)
    {
        if (!getSysUser().isAdmin())
        {
            parentId = getSysUser().getDeptId();
        }
        mmap.put("dept", deptService.selectDeptById(parentId));
        return prefix + "/add";
    }

    /**
     * 新增保存部门
     */
    @Log(title = "部门管理", businessType = BusinessType.INSERT)
    @RequiresPermissions("system:dept:add")
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated SysDept dept)
    {
        if (UserConstants.DEPT_NAME_NOT_UNIQUE.equals(deptService.checkDeptNameUnique(dept)))
        {
            return error("新增部门'" + dept.getDeptName() + "'失败，部门名称已存在");
        }
        dept.setCreateBy(getLoginName());
        return toAjax(deptService.insertDept(dept));
    }
    /**
     * 初始化部门信息
     */
    @Log(title = "部门管理", businessType = BusinessType.INSERT)
    @RequiresPermissions("system:dept:add")
    @PostMapping("/init")
    @ResponseBody
    public AjaxResult addInit(SysDept sysDept)
    {
        int init_count=0;
        init_count+=initOrg2();
        init_count+=initOrg3();
        //初始化本科生 专业、年级、班级组织架构
        init_count+=initZhuanye();
        init_count+=initNianji();
        init_count+=initBanji();
        return AjaxResult.success(StringUtils.format("成功处理了{}条数据",init_count));
    }
    public int initOrg2(){
        int init_count=0;
        String create_by=getLoginName();
        List<SysDept> list2=deptService.selectSyncDeptListByParent(100000000l);
        if(list2!=null&&list2.size()>0) {
            for (SysDept dept2 : list2) {
                //循环二级部门
                SysDept qDept = new SysDept();
                qDept.setDeptName(dept2.getDeptName());
                qDept.setLeader(dept2.getLeader());
                int existDept2 = deptService.checkDeptExist(qDept);
                if (existDept2 == 0) {
                    int count1 = deptService.selectDeptCount(100l);
                    dept2.setAncestors("0,100");
                    dept2.setParentId(100l);
                    dept2.setLeader(StringUtils.format("2-{}", dept2.getLeader()));
                    dept2.setOrderNum(String.valueOf(count1 + 1));
                    dept2.setCreateBy(create_by);
                    init_count += deptService.insertDept(dept2);
                }
            }
        }
        return init_count;
    }
    public int initOrg3(){
        int init_count=0;
        String create_by=getLoginName();
        SysDept lDept=new SysDept();
        lDept.setLeader("2-");
        List<SysDept> list2=deptService.selectDeptListGte(lDept);
        if(list2!=null&&list2.size()>0) {
            for (SysDept dept2 : list2) {
                //循环二级部门
                Long pId=Long.parseLong(dept2.getLeader().split("-")[1]);
                List<SysDept> list3=deptService.selectSyncDeptListByParent(pId);
                if(list3!=null&&list3.size()>0){
                    for(SysDept dept3:list3){
                        SysDept qDept = new SysDept();
                        qDept.setDeptName(dept3.getDeptName());
                        qDept.setLeader(dept3.getLeader());
                        int existDept3 = deptService.checkDeptExist(qDept);
                        if (existDept3 == 0) {
                            int count1 = deptService.selectDeptCount(dept2.getDeptId());
                            dept3.setAncestors(StringUtils.format("{},{}",dept2.getAncestors(),dept2.getDeptId()));
                            dept3.setParentId(dept2.getDeptId());
                            dept3.setLeader(StringUtils.format("2-{}", dept3.getLeader()));
                            dept3.setOrderNum(String.valueOf(count1 + 1));
                            dept3.setCreateBy(create_by);
                            init_count += deptService.insertDept(dept3);
                        }
                    }
                }
            }
        }
        return init_count;
    }
    public int initZhuanye(){
        int init_count=0;
        String create_by=getLoginName();
        //列出所有的专业
        SysDept lDept=new SysDept();
        lDept.setLeader("2-");
        List<SysDept> deptList=deptService.selectDeptListGte(lDept);
        for(SysDept dept:deptList){
            //级联
            String ancestors1=dept.getAncestors();
            //部门编号
            Long deptId=dept.getDeptId();
            Long pId=Long.parseLong(dept.getLeader().split("-")[1]);
            //列出部门下的专业
            List<SysDept> zyDeptList=deptService.selectSyncDeptListByDep(pId);
            if(zyDeptList!=null&&zyDeptList.size()>0){
                for(SysDept zyDept:zyDeptList){
                    //查看部门下是否维护了该专业
                    SysDept qDept=new SysDept();
                    qDept.setDeptName(zyDept.getDeptName());
                    qDept.setParentId(deptId);
                    //是否存在
                    int sfcz1=deptService.checkDeptExist(qDept);
                    //没有维护则新增
                    if(sfcz1==0){
                        //计数
                        int count1=deptService.selectDeptCount(deptId);
                        //父级编号设置为部门的编号
                        zyDept.setParentId(deptId);
                        //级联设置为部门级联+部门编号
                        zyDept.setAncestors(StringUtils.format("{},{}",ancestors1,deptId));
                        //序号
                        zyDept.setOrderNum(String.valueOf(count1+1));
                        zyDept.setLeader(StringUtils.format("3-{}",zyDept.getLeader()));
                        zyDept.setCreateBy(create_by);
                        init_count+=deptService.insertDept(zyDept);
                    }
                }
            }
        }
        return init_count;
    }
    public int initNianji(){
        int init_count=0;
        String create_by=getLoginName();
        //列出所有的年级
        SysDept lDept=new SysDept();
        lDept.setLeader("3-");
        List<SysDept> deptList=deptService.selectDeptListGte(lDept);
        for(SysDept zyDept:deptList){
            Long zyDeptId=zyDept.getDeptId();
            SysDept qDept1=new SysDept();
            //根据专业名称和部门编号列出年级列表
            qDept1.setLeader(zyDept.getLeader().split("-")[1]);
            qDept1.setDeptName(zyDept.getDeptName());
            List<SysDept> njDeptList=deptService.selectSyncDeptListByZy(qDept1);
            if(njDeptList!=null&njDeptList.size()>0) {
                for (SysDept njDept : njDeptList) {
                    //判断年级是否存在
                    SysDept qDept3 = new SysDept();
                    qDept3.setDeptName(njDept.getDeptName());
                    qDept3.setParentId(zyDeptId);
                    int sfcz2 = deptService.checkDeptExist(qDept3);
                    if (sfcz2 == 0) {
                        //计数
                        int count2 = deptService.selectDeptCount(zyDeptId);
                        //设置父级编号
                        njDept.setParentId(zyDeptId);
                        njDept.setDeptName(StringUtils.format("{}-{}", zyDept.getDeptName(), njDept.getDeptName()));
                        njDept.setLeader(StringUtils.format("4-{}",njDept.getLeader()));
                        njDept.setAncestors(StringUtils.format("{},{}", zyDept.getAncestors(), zyDeptId));
                        njDept.setOrderNum(String.valueOf(count2 + 1));
                        njDept.setCreateBy(create_by);
                        init_count += deptService.insertDept(njDept);
                    }
                }
            }
        }
        return init_count;
    }
    public int initBanji(){
        int init_count=0;
        String create_by=getLoginName();
        //列出所有的年级
        SysDept lDept=new SysDept();
        lDept.setLeader("4-");
        List<SysDept> deptList=deptService.selectDeptListGte(lDept);
        for(SysDept njDept:deptList){
            Long njDeptId=njDept.getDeptId();
            SysDept qDept1=new SysDept();
            qDept1.setDeptName(njDept.getDeptName());
            qDept1.setLeader(njDept.getLeader().split("-")[1]);
            List<SysDept> bjDeptList=deptService.selectSyncDeptListByNj(qDept1);
            if(bjDeptList!=null&bjDeptList.size()>0) {
                for (SysDept bjDept : bjDeptList) {
                    //判断年级是否存在
                    SysDept qDept3 = new SysDept();
                    qDept3.setDeptName(bjDept.getDeptName());
                    qDept3.setParentId(njDeptId);
                    int sfcz2 = deptService.checkDeptExist(qDept3);
                    if (sfcz2 == 0) {
                        //计数
                        int count2 = deptService.selectDeptCount(njDeptId);
                        //设置父级编号
                        bjDept.setParentId(njDeptId);
                        bjDept.setAncestors(StringUtils.format("{},{}", njDept.getAncestors(), njDeptId));
                        bjDept.setDeptName(StringUtils.format("{}-{}", njDept.getDeptName(), bjDept.getDeptName()));
                        bjDept.setLeader(StringUtils.format("5-{}",bjDept.getLeader()));
                        bjDept.setOrderNum(String.valueOf(count2 + 1));
                        bjDept.setCreateBy(getLoginName());
                        init_count += deptService.insertDept(bjDept);
                    }
                }
            }
        }
        return init_count;
    }
    /**
     * 修改
     */
    @GetMapping("/edit/{deptId}")
    public String edit(@PathVariable("deptId") Long deptId, ModelMap mmap)
    {
        deptService.checkDeptDataScope(deptId);
        SysDept dept = deptService.selectDeptById(deptId);
        if (StringUtils.isNotNull(dept) && 100L == deptId)
        {
            dept.setParentName("无");
        }
        mmap.put("dept", dept);
        return prefix + "/edit";
    }

    /**
     * 保存
     */
    @Log(title = "部门管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:dept:edit")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated SysDept dept)
    {
        if (UserConstants.DEPT_NAME_NOT_UNIQUE.equals(deptService.checkDeptNameUnique(dept)))
        {
            return error("修改部门'" + dept.getDeptName() + "'失败，部门名称已存在");
        }
        else if (dept.getParentId().equals(dept.getDeptId()))
        {
            return error("修改部门'" + dept.getDeptName() + "'失败，上级部门不能是自己");
        }
        else if (StringUtils.equals(UserConstants.DEPT_DISABLE, dept.getStatus())
                && deptService.selectNormalChildrenDeptById(dept.getDeptId()) > 0)
        {
            return AjaxResult.error("该部门包含未停用的子部门！");
        }
        dept.setUpdateBy(getLoginName());
        return toAjax(deptService.updateDept(dept));
    }

    /**
     * 删除
     */
    @Log(title = "部门管理", businessType = BusinessType.DELETE)
    @RequiresPermissions("system:dept:remove")
    @GetMapping("/remove/{deptId}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("deptId") Long deptId)
    {
        if (deptService.selectDeptCount(deptId) > 0)
        {
            return AjaxResult.warn("存在下级部门,不允许删除");
        }
        if (deptService.checkDeptExistUser(deptId))
        {
            return AjaxResult.warn("部门存在用户,不允许删除");
        }
        return toAjax(deptService.deleteDeptById(deptId));
    }

    /**
     * 校验部门名称
     */
    @PostMapping("/checkDeptNameUnique")
    @ResponseBody
    public String checkDeptNameUnique(SysDept dept)
    {
        return deptService.checkDeptNameUnique(dept);
    }

    /**
     * 选择部门树
     * 
     * @param deptId 部门ID
     * @param excludeId 排除ID
     */
    @GetMapping(value = { "/selectDeptTree/{deptId}", "/selectDeptTree/{deptId}/{excludeId}" })
    public String selectDeptTree(@PathVariable("deptId") Long deptId,
            @PathVariable(value = "excludeId", required = false) String excludeId, ModelMap mmap)
    {
        mmap.put("dept", deptService.selectDeptById(deptId));
        mmap.put("excludeId", excludeId);
        return prefix + "/tree";
    }

    /**
     * 加载部门列表树
     */
    @GetMapping("/treeData")
    @ResponseBody
    public List<Ztree> treeData()
    {
        List<Ztree> ztrees = deptService.selectDeptTree(new SysDept());
        return ztrees;
    }
    /**
     * 加载部门列表树设置已选劳动讲座的部门
     */
    @GetMapping("/treeTheoryData")
    @ResponseBody
    public List<Ztree> treeTheoryData(SysTheory sysTheory)
    {
        List<Ztree> ztrees = deptService.theoryDeptTreeData(sysTheory);
        return ztrees;
    }
    /**
     * 加载部门列表树设置已选劳动讲座的部门
     */
    @GetMapping("/treePracticeData")
    @ResponseBody
    public List<Ztree> treePracticeData(SysPractice sysPractice)
    {
        List<Ztree> ztrees = deptService.practiceDeptTreeData(sysPractice);
        return ztrees;
    }
    /**
     * 加载部门列表树（排除下级）
     */
    @GetMapping("/treeData/{excludeId}")
    @ResponseBody
    public List<Ztree> treeDataExcludeChild(@PathVariable(value = "excludeId", required = false) Long excludeId)
    {
        SysDept dept = new SysDept();
        dept.setExcludeId(excludeId);
        List<Ztree> ztrees = deptService.selectDeptTreeExcludeChild(dept);
        return ztrees;
    }

    /**
     * 加载角色部门（数据权限）列表树
     */
    @GetMapping("/roleDeptTreeData")
    @ResponseBody
    public List<Ztree> deptTreeData(SysRole role)
    {
        List<Ztree> ztrees = deptService.roleDeptTreeData(role);
        return ztrees;
    }
}
