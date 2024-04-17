package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SysClassDept;
import com.ruoyi.system.domain.SysRoleMenu;

/**
 * 课程部门对应Mapper接口
 * 
 * @author ruoyi
 * @date 2022-03-06
 */
public interface SysClassDeptMapper 
{
    /**
     * 查询课程部门对应
     * 
     * @param classId 课程部门对应主键
     * @return 课程部门对应
     */
    public SysClassDept selectSysClassDeptByClassId(Long classId);

    /**
     * 查询课程部门对应列表
     * 
     * @param sysClassDept 课程部门对应
     * @return 课程部门对应集合
     */
    public List<SysClassDept> selectSysClassDeptList(SysClassDept sysClassDept);

    /**
     * 新增课程部门对应
     * 
     * @param sysClassDept 课程部门对应
     * @return 结果
     */
    public int insertSysClassDept(SysClassDept sysClassDept);

    /**
     * 修改课程部门对应
     * 
     * @param sysClassDept 课程部门对应
     * @return 结果
     */
    public int updateSysClassDept(SysClassDept sysClassDept);

    /**
     * 删除课程部门对应
     * 
     * @param classId 课程部门对应主键
     * @return 结果
     */
    public int deleteSysClassDeptByClassId(Long classId);
    public int deleteSysClassDeptByClassId1(Long classId);
    public int deleteSysClassDeptByClassId2(Long classId);

    /**
     * 批量删除课程部门对应
     * 
     * @param classIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysClassDeptByClassIds(String[] classIds);
    /**
     * 批量新增课程部门信息
     *
     * @param classDeptList 课程部门列表
     * @return 结果
     */
    public int batchClassDept(List<SysClassDept> classDeptList);
}
