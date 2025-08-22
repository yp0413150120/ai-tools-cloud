package com.ruoyi.system.domain.dto;

import java.util.Date;

import com.ruoyi.system.domain.CompanyReports;
import lombok.Data;

/**
 * CompanyReportsDto类
 * 继承自CompanyReports类，用于封装公司报告相关的数据传输对象
 * 使用@Data注解自动生成getter、setter等方法
 */
@Data
public class CompanyReportsDto extends CompanyReports {
  // 上市起始时间
  private Date ipoDateStart;
  
  // 上市结束时间
  private Date ipoDateEnd;
}
