package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.CompanyReports;
import com.ruoyi.system.domain.dto.CompanyReportsDto;

/**
 * 公司报告Service接口
 * 
 * @author ruoyi
 * @date 2025-08-20
 */
public interface ICompanyReportsService 
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
    public List<CompanyReports> selectCompanyReportsList(CompanyReportsDto companyReports);

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
     * 批量删除公司报告
     * 
     * @param ids 需要删除的公司报告主键集合
     * @return 结果
     */
    public int deleteCompanyReportsByIds(String[] ids);

    /**
     * 删除公司报告信息
     * 
     * @param id 公司报告主键
     * @return 结果
     */
    public int deleteCompanyReportsById(String id);
}
