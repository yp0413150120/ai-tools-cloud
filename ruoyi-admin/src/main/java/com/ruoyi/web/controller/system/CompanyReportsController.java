package com.ruoyi.system.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.CompanyReports;
import com.ruoyi.system.service.ICompanyReportsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 公司报告Controller
 * 
 * @author ruoyi
 * @date 2025-08-20
 */
@RestController
@RequestMapping("/system/companyReport")
public class CompanyReportsController extends BaseController
{
    @Autowired
    private ICompanyReportsService companyReportsService;

    /**
     * 查询公司报告列表
     */
    @PreAuthorize("@ss.hasPermi('system:companyReport:list')")
    @GetMapping("/list")
    public TableDataInfo list(CompanyReports companyReports)
    {
        startPage();
        List<CompanyReports> list = companyReportsService.selectCompanyReportsList(companyReports);
        return getDataTable(list);
    }

    /**
     * 导出公司报告列表
     */
    @PreAuthorize("@ss.hasPermi('system:companyReport:export')")
    @Log(title = "公司报告", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CompanyReports companyReports)
    {
        List<CompanyReports> list = companyReportsService.selectCompanyReportsList(companyReports);
        ExcelUtil<CompanyReports> util = new ExcelUtil<CompanyReports>(CompanyReports.class);
        util.exportExcel(response, list, "公司报告数据");
    }

    /**
     * 获取公司报告详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:companyReport:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(companyReportsService.selectCompanyReportsById(id));
    }

    /**
     * 新增公司报告
     */
    @PreAuthorize("@ss.hasPermi('system:companyReport:add')")
    @Log(title = "公司报告", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CompanyReports companyReports)
    {
        return toAjax(companyReportsService.insertCompanyReports(companyReports));
    }

    /**
     * 修改公司报告
     */
    @PreAuthorize("@ss.hasPermi('system:companyReport:edit')")
    @Log(title = "公司报告", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CompanyReports companyReports)
    {
        return toAjax(companyReportsService.updateCompanyReports(companyReports));
    }

    /**
     * 删除公司报告
     */
    @PreAuthorize("@ss.hasPermi('system:companyReport:remove')")
    @Log(title = "公司报告", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(companyReportsService.deleteCompanyReportsByIds(ids));
    }
}
