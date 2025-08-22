package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.CompanyReportsMapper;
import com.ruoyi.system.domain.CompanyReports;
import com.ruoyi.system.domain.dto.CompanyReportsDto;
import com.ruoyi.system.service.ICompanyReportsService;

/**
 * 公司报告Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-08-20
 */
@Service
public class CompanyReportsServiceImpl implements ICompanyReportsService 
{
    @Autowired
    private CompanyReportsMapper companyReportsMapper;

    /**
     * 查询公司报告
     * 
     * @param id 公司报告主键
     * @return 公司报告
     */
    @Override
    public CompanyReports selectCompanyReportsById(String id)
    {
        return companyReportsMapper.selectCompanyReportsById(id);
    }

    /**
     * 查询公司报告列表
     * 
     * @param companyReports 公司报告
     * @return 公司报告
     */
    @Override
    public List<CompanyReports> selectCompanyReportsList(CompanyReportsDto companyReports)
    {
        return companyReportsMapper.selectCompanyReportsList(companyReports);
    }

    /**
     * 新增公司报告
     * 
     * @param companyReports 公司报告
     * @return 结果
     */
    @Override
    public int insertCompanyReports(CompanyReports companyReports)
    {
        return companyReportsMapper.insertCompanyReports(companyReports);
    }

    /**
     * 修改公司报告
     * 
     * @param companyReports 公司报告
     * @return 结果
     */
    @Override
    public int updateCompanyReports(CompanyReports companyReports)
    {
        return companyReportsMapper.updateCompanyReports(companyReports);
    }

    /**
     * 批量删除公司报告
     * 
     * @param ids 需要删除的公司报告主键
     * @return 结果
     */
    @Override
    public int deleteCompanyReportsByIds(String[] ids)
    {
        return companyReportsMapper.deleteCompanyReportsByIds(ids);
    }

    /**
     * 删除公司报告信息
     * 
     * @param id 公司报告主键
     * @return 结果
     */
    @Override
    public int deleteCompanyReportsById(String id)
    {
        return companyReportsMapper.deleteCompanyReportsById(id);
    }
}
