<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      size="small"
      :inline="true"
      v-show="showSearch"
      label-width="68px"
    >
      <el-form-item label="公司名称" prop="companyName">
        <el-input
          v-model="queryParams.companyName"
          placeholder="请输入公司名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="上市地区" prop="listingPlace">
        <el-input
          v-model="queryParams.listingPlace"
          placeholder="请输入上市地区"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="上市时间" prop="ipoDate">
        <el-date-picker
          clearable
          v-model="queryParams.ipoDate"
          type="daterange"
          value-format="yyyy-MM-dd"
          start-placeholder="起始上市时间"
          end-placeholder="结束上市时间"
        >
        </el-date-picker>
      </el-form-item>
      <el-form-item label="生成工具源" prop="generatorTool">
        <el-input
          v-model="queryParams.generatorTool"
          placeholder="请输入生成工具源"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="生成模型源" prop="generatorModel">
        <el-input
          v-model="queryParams.generatorModel"
          placeholder="请输入生成模型源"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button
          type="primary"
          icon="el-icon-search"
          size="mini"
          @click="handleQuery"
          >搜索</el-button
        >
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery"
          >重置</el-button
        >
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:companyReport:add']"
          >新增</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:companyReport:edit']"
          >修改</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:companyReport:remove']"
          >删除</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:companyReport:export']"
          >导出</el-button
        >
      </el-col>
      <right-toolbar
        :showSearch.sync="showSearch"
        @queryTable="getList"
      ></right-toolbar>
    </el-row>

    <el-table
      v-loading="loading"
      :data="companyReportList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="id" />
      <el-table-column label="公司名称" align="center" prop="companyName" />
      <el-table-column label="上市地区" align="center" prop="listingPlace" />
      <el-table-column
        label="上市时间"
        align="center"
        prop="ipoDate"
        width="180"
      >
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.ipoDate, "{y}-{m}-{d}") }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="报告状态"
        align="center"
        prop="reportStatus"
        show-overflow-tooltip
      />
      <el-table-column
        label="报告内容"
        align="center"
        prop="report"
        show-overflow-tooltip
      />
      <el-table-column
        label="生成工具源"
        align="center"
        prop="generatorTool"
        show-overflow-tooltip
      />
      <el-table-column
        label="报告提示词"
        align="center"
        prop="reportPrompt"
        show-overflow-tooltip
      />
      <el-table-column
        label="生成模型源"
        align="center"
        prop="generatorModel"
        show-overflow-tooltip
      />
      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:companyReport:edit']"
            >修改</el-button
          >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:companyReport:remove']"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改公司报告对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="公司名称" prop="companyName">
          <el-input v-model="form.companyName" placeholder="请输入公司名称" />
        </el-form-item>
        <el-form-item label="上市地区" prop="listingPlace">
          <el-input v-model="form.listingPlace" placeholder="请输入上市地区" />
        </el-form-item>
        <el-form-item label="上市时间" prop="ipoDate">
          <el-date-picker
            clearable
            v-model="form.ipoDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择上市时间"
          >
          </el-date-picker>
        </el-form-item>
        <el-form-item label="报告内容" prop="report">
          <el-input
            v-model="form.report"
            type="textarea"
            placeholder="请输入内容"
          />
        </el-form-item>
        <el-form-item label="生成工具源" prop="generatorTool">
          <el-input
            v-model="form.generatorTool"
            placeholder="请输入生成工具源"
          />
        </el-form-item>
        <el-form-item label="报告提示词" prop="reportPrompt">
          <el-input
            v-model="form.reportPrompt"
            type="textarea"
            placeholder="请输入内容"
          />
        </el-form-item>
        <el-form-item label="生成模型源" prop="generatorModel">
          <el-input
            v-model="form.generatorModel"
            placeholder="请输入生成模型源"
          />
        </el-form-item>
        <el-form-item label="原始总结" prop="originSummary">
          <el-input
            v-model="form.originSummary"
            type="textarea"
            placeholder="请输入内容"
          />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  listCompanyReport,
  getCompanyReport,
  delCompanyReport,
  addCompanyReport,
  updateCompanyReport,
} from "@/api/system/companyReport";

export default {
  name: "CompanyReport",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 公司报告表格数据
      companyReportList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        companyName: null,
        listingPlace: null,
        ipoDate: null,
        reportStatus: null,
        report: null,
        generatorTool: null,
        reportPrompt: null,
        generatorModel: null,
        originSummary: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        companyName: [
          { required: true, message: "公司名称不能为空", trigger: "blur" },
        ],
        listingPlace: [
          { required: true, message: "上市地区不能为空", trigger: "blur" },
        ],
        ipoDate: [
          { required: true, message: "上市时间不能为空", trigger: "blur" },
        ],
        reportStatus: [
          { required: true, message: "报告状态不能为空", trigger: "change" },
        ],
      },
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询公司报告列表 */
    getList() {
      this.loading = true;
      listCompanyReport({
        ...this.queryParams,
        ipoDate: undefined,
        ipoDateStart: this.queryParams.ipoDate?.[0],
        ipoDateEnd: this.queryParams.ipoDate?.[1],
      }).then((response) => {
        this.companyReportList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        companyName: null,
        listingPlace: null,
        ipoDate: null,
        reportStatus: null,
        report: null,
        generatorTool: null,
        reportPrompt: null,
        generatorModel: null,
        originSummary: null,
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map((item) => item.id);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加公司报告";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids;
      getCompanyReport(id).then((response) => {
        this.form = response.data;
        this.open = true;
        this.title = "修改公司报告";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.form.id != null) {
            updateCompanyReport(this.form).then((response) => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addCompanyReport(this.form).then((response) => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal
        .confirm('是否确认删除公司报告编号为"' + ids + '"的数据项？')
        .then(function () {
          return delCompanyReport(ids);
        })
        .then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        })
        .catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download(
        "system/companyReport/export",
        {
          ...this.queryParams,
        },
        `companyReport_${new Date().getTime()}.xlsx`
      );
    },
  },
};
</script>
