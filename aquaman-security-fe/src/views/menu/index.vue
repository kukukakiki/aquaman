<template>
  <div class="app-container">
    <el-row>
      <el-col :span="6">
        <el-input v-model="filterText" placeholder="输入关键字进行过滤" />
        <el-tree ref="menuTree" :data="items" :props="defaultTreeProps" :filter-node-method="filterNode" @node-click="handleNodeClick" />
      </el-col>
      <el-col :span="18">
        <el-form ref="form" :rules="rules" :model="form" label-width="100px">
          <el-form-item label="上级菜单">
            <el-input v-model="form.parentName" />
          </el-form-item>
          <el-form-item label="菜单名称" prop="name">
            <el-input v-model="form.name" />
          </el-form-item>
          <el-form-item label="菜单编码" prop="code">
            <el-input v-model="form.code" />
          </el-form-item>
          <el-form-item>
            <span slot="label">
              接口地址
              <el-tooltip placement="top">
                <div slot="content">
                  <p>温馨提示：</p>
                  1、访问后台服务端地址
                </div>
                <i class="el-icon-question" />
              </el-tooltip>
            </span>
            <el-input v-model="form.url" />
          </el-form-item>
          <el-form-item>
            <span slot="label">
              路由地址
              <el-tooltip placement="top">
                <div slot="content">
                  <p>温馨提示：</p>
                  1、前端页面跳转地址
                </div>
                <i class="el-icon-question" />
              </el-tooltip>
            </span>
            <el-input v-model="form.router" />
          </el-form-item>
          <el-form-item label="菜单类型" prop="type">
            <aq-select :business-type="'type'" :bind-value.sync="form.type" />
          </el-form-item>
          <el-form-item label="菜单状态">
            <aq-select :business-type="'status'" :bind-value.sync="form.status" />
          </el-form-item>
          <el-form-item label="菜单序号">
            <el-input v-model="form.sort" />
          </el-form-item>
          <el-form-item label="菜单图标">
            <el-input v-model="form.iconType" />
          </el-form-item>
          <el-form-item label="是否显示">
            <aq-select :business-type="'display'" :bind-value.sync="form.display" />
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="onSubmit">立即创建</el-button>
            <el-button>取消</el-button>
          </el-form-item>
        </el-form>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import { queryByTree, queryById } from '@/api/menu'
import aqSelect from '@/components/Element/Select'

export default {
  components: {
    aqSelect
  },
  data() {
    return {
      filterText: '',
      loading: false, // 页面loading标示
      query: { // 列表查询对象
        parentId: -1 // 从根节点查询菜单树
      },
      items: [], // 列表集合
      defaultTreeProps: { // 默认树对应关系
        children: 'children', // 子菜单集合名称
        label: 'name' // 菜单显示名称
      },
      form: { // 菜单属性
        id: '',
        systemId: '',
        code: '',
        name: '',
        url: '',
        router: '',
        iconType: '',
        type: '',
        display: '',
        status: 'START',
        parentId: -1,
        remark: '',
        sort: ''
      },
      rules: {
        name: [
          { required: true, message: '请输入名称', trigger: 'blur' }
        ],
        code: [
          { required: true, message: '请输入编码', trigger: 'blur' }
        ],
        type: [
          { required: true, message: '请选择菜单类型', trigger: 'change' }
        ]
      }
    }
  },
  watch: {
    filterText(val) {
      this.$refs.menuTree.filter(val)
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    /**
     * 获取数据
     */
    fetchData() {
      this.loading = true
      queryByTree(this.query).then(response => {
        const data = response.result
        if (response.code === '0000') {
          this.items = data
        }
        this.loading = false
      }).catch(error => {
        this.$message({
          message: error.msg,
          type: 'error',
          duration: 5 * 1000
        })
        this.loading = false
      })
    },
    /**
     * 获取选中树数据
     */
    handleNodeClick(data) {
      queryById(data.id).then(response => {
        const data = response.result
        this.form = data
        console.log(this.form)
      }).catch(error => {
        this.$message({
          message: error.msg,
          type: 'error',
          duration: 5 * 1000
        })
        this.loading = false
      })
    },
    /**
     * 表单提交
     */
    onSubmit() {
      this.$refs['form'].validate((valid) => {
        if (valid) {
          console.log(this.form)
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    filterNode(value, data) {
      if (!value) return true
      return data.name.indexOf(value) !== -1
    }
  }
}
</script>

<style scoped>
</style>

