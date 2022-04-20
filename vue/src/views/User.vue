<template>
  <div style="padding: 10px;">

   <div style="margin: 10px 0">
<!--     新增按钮-->
     <div style="float: left">
       <el-button @click="add">新增</el-button>
     </div>
<!--     查询按钮及输入框-->
     <div style="margin: 10px 0;">
       <el-input v-model="search" placeholder="请输入用户名" style="width: 20%; margin-left: 825px"></el-input>
       <el-button style="margin-left: 5px" @click="load()">查询</el-button>
     </div>
   </div>

<!--    用户信息表格-->
    <el-table
        :data="tableData"
        border
        stripe
        style="width: 100%">
      <el-table-column
          prop="id"
          label="编号"
          sortable
      >
      </el-table-column>
      <el-table-column
          prop="username"
          label="用户名">
      </el-table-column>
      <el-table-column
          prop="phone"
          label="电话号码">
      </el-table-column>
      <el-table-column
          prop="age"
          label="年龄">
      </el-table-column>
      <el-table-column
          prop="sex"
          label="性别">
      </el-table-column>
      <el-table-column
          prop="address"
          label="地址">
      </el-table-column>
      <el-table-column
          prop="edit"
          label="操作">
        <template #default="scope">
          <el-button @click="handleEdit(scope.row)" type="text" size="small">编辑</el-button>
          <el-popconfirm
              title="确定删除吗？" @Confirm="handleDelete(scope.row.id)">
            <template #reference>
             <el-button type="text" size="small">删除</el-button>
            </template>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <div style="margin: 10px 0">
<!--      表格功能等设置-->
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="currentPage"
          :page-sizes="[5, 10, 20]"
          :page-size="pageSize"
          layout="total, prev, pager, next, jumper"
          :total="total"
      >
      </el-pagination>

<!--      新增弹窗-->
      <el-dialog
          title="提示"
          v-model="dialogVisible"
          width="30%">
        <el-form :model="form" label-width="120px">
          <el-form-item label="用户名称">
            <el-input v-model="form.username" style="width: 50%"></el-input>
          </el-form-item>
          <el-form-item label="电话号码">
            <el-input v-model="form.phone" style="width: 50%"></el-input>
          </el-form-item>
          <el-form-item label="年龄">
            <el-input v-model="form.age" style="width: 50%"></el-input>
          </el-form-item>
          <el-form-item label="性别">
            <el-radio v-model="form.sex" label="男">男</el-radio>
            <el-radio v-model="form.sex" label="女">女</el-radio>
            <el-radio v-model="form.sex" label="未知">未知</el-radio>
          </el-form-item>
          <el-form-item label="地址">
            <el-input type="textarea" v-model="form.address" style="width: 50%"></el-input>
          </el-form-item>
        </el-form>
        <template #footer>
        <span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="save">确 定</el-button>
        </span>
        </template>
      </el-dialog>

    </div>
  </div>
</template>

<script>


import request from "@/utils/request";

export default {
  name: 'User',
  components: {

  },
  data() {
    return {
      form: {},
      dialogVisible: false,
      search: '',
      currentPage: 1,
      pageSize: 5,
      total: 0,
      tableData: []
    }
  },
  created() {
    this.load()
  },
  methods: {
    // 刷新数据
    load() {
      request.get("/user",{
        params: {
          pageNum: this.currentPage,
          pageSize:this.pageSize,
          search: this.search
        }
      }).then(res =>{
        console.log(res)
        this.tableData = res.data.records
        this.total = res.data.total
      })
    },
    // 增加用户
    add() {
      this.dialogVisible = true
      this.form={}
    },
    // 保存信息
    save() {
      if(this.form.id) {
        request.put("/user", this.form).then(res => {
          console.log(res)
          if(res.code === '0') {
            this.$message({
              type: "success",
              message: "更新成功"
            })
          } else {
            this.$message({
              type: "error",
              message: res.msg
            })
          }
          this.load()
          this.dialogVisible = false
        })
      } else {
        request.post("/user", this.form).then(res => {
          console.log(res)
          if(res.code === '0') {
            this.$message({
              type: "success",
              message: "添加成功"
            })
          } else {
            this.$message({
              type: "error",
              message: res.msg
            })
          }
          this.load()
          this.dialogVisible = false
        })
      }

    },
    // 修改信息
    handleEdit(row) {
      this.form = JSON.parse(JSON.stringify(row))
      this.dialogVisible = true
    },
    // 删除信息
    handleDelete(id) {
      console.log(id)
      request.delete("/user/" + id).then(res => {
        if(res.code == '0') {
          this.$message({
            type: "success",
            message: "删除成功"
          })
        } else {
          this.$message({
            type: "error",
            message: res.msg
          })
        }
        this.load()
      })
    },
    // 修改页面信息数
    handleSizeChange(pageSize) {
      this.pageSize = pageSize
      this.load()
    },
    // 跳转页面
    handleCurrentChange(pageNum) {
      this.currentPage = pageNum
      this.load()
    }
  }
}
</script>
