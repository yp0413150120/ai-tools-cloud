package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.CompanyReports;

/**
 * 公司报告Mapper接口
 * 
 * @author ruoyi
 * @date 2025-08-20
 */
public interface CompanyReportsMapper 
{
    /**
     * 查询公司报告
     * 
     * @param id 公司报告主键
     * @return 公司报告
     */
    public CompanyReports selectCompanyReportsById(String id);

    /**
     * 查询公司报告列表
     * 
     * @param companyReports 公司报告
     * @return 公司报告集合
     */
    public List<CompanyReports> selectCompanyReportsList(CompanyReports companyReports);

    /**
     * 新增公司报告
     * 
     * @param companyReports 公司报告
     * @return 结果
     */
    public int insertCompanyReports(CompanyReports companyReports);

    /**
     * 修改公司报告
     * 
     * @param companyReports 公司报告
     * @return 结果
     */
    public int updateCompanyReports(CompanyReports companyReports);

    /**
     * 删除公司报告
     * 
     * @param id 公司报告主键
     * @return 结果
     */
    public int deleteCompanyReportsById(String id);

    /**
     * 批量删除公司报告
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCompanyReportsByIds(String[] ids);
}
