package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 公司报告对象 company_reports
 * 
 * @author ruoyi
 * @date 2025-08-20
 */
public class CompanyReports extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private String id;

    /** 公司名称 */
    @Excel(name = "公司名称")
    private String companyName;

    /** 上市地区 */
    @Excel(name = "上市地区")
    private String listingPlace;

    /** 上市时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "上市时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date ipoDate;

    /** 报告状态 */
    @Excel(name = "报告状态")
    private String reportStatus;

    /** 报告内容 */
    @Excel(name = "报告内容")
    private String report;

    /** 生成工具源 */
    @Excel(name = "生成工具源")
    private String generatorTool;

    /** 报告提示词 */
    @Excel(name = "报告提示词")
    private String reportPrompt;

    /** 生成模型源 */
    @Excel(name = "生成模型源")
    private String generatorModel;

    /** 原始总结 */
    @Excel(name = "原始总结")
    private String originSummary;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }

    public void setCompanyName(String companyName) 
    {
        this.companyName = companyName;
    }

    public String getCompanyName() 
    {
        return companyName;
    }

    public void setListingPlace(String listingPlace) 
    {
        this.listingPlace = listingPlace;
    }

    public String getListingPlace() 
    {
        return listingPlace;
    }

    public void setIpoDate(Date ipoDate) 
    {
        this.ipoDate = ipoDate;
    }

    public Date getIpoDate() 
    {
        return ipoDate;
    }

    public void setReportStatus(String reportStatus) 
    {
        this.reportStatus = reportStatus;
    }

    public String getReportStatus() 
    {
        return reportStatus;
    }

    public void setReport(String report) 
    {
        this.report = report;
    }

    public String getReport() 
    {
        return report;
    }

    public void setGeneratorTool(String generatorTool) 
    {
        this.generatorTool = generatorTool;
    }

    public String getGeneratorTool() 
    {
        return generatorTool;
    }

    public void setReportPrompt(String reportPrompt) 
    {
        this.reportPrompt = reportPrompt;
    }

    public String getReportPrompt() 
    {
        return reportPrompt;
    }

    public void setGeneratorModel(String generatorModel) 
    {
        this.generatorModel = generatorModel;
    }

    public String getGeneratorModel() 
    {
        return generatorModel;
    }

    public void setOriginSummary(String originSummary) 
    {
        this.originSummary = originSummary;
    }

    public String getOriginSummary() 
    {
        return originSummary;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("companyName", getCompanyName())
            .append("listingPlace", getListingPlace())
            .append("ipoDate", getIpoDate())
            .append("reportStatus", getReportStatus())
            .append("report", getReport())
            .append("generatorTool", getGeneratorTool())
            .append("reportPrompt", getReportPrompt())
            .append("generatorModel", getGeneratorModel())
            .append("originSummary", getOriginSummary())
            .toString();
    }
}
