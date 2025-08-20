import request from '@/utils/request'

// 查询公司报告列表
export function listCompanyReport(query) {
  return request({
    url: '/system/companyReport/list',
    method: 'get',
    params: query
  })
}

// 查询公司报告详细
export function getCompanyReport(id) {
  return request({
    url: '/system/companyReport/' + id,
    method: 'get'
  })
}

// 新增公司报告
export function addCompanyReport(data) {
  return request({
    url: '/system/companyReport',
    method: 'post',
    data: data
  })
}

// 修改公司报告
export function updateCompanyReport(data) {
  return request({
    url: '/system/companyReport',
    method: 'put',
    data: data
  })
}

// 删除公司报告
export function delCompanyReport(id) {
  return request({
    url: '/system/companyReport/' + id,
    method: 'delete'
  })
}
