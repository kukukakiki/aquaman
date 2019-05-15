<template>
  <div class="app-container">
    <el-form ref="form" :rules="rules" :model="form" label-width="80px">
      <el-form-item label="角色名称" prop="name">
        <el-input v-model="form.name" />
      </el-form-item>
      <el-form-item label="角色编码" prop="code">
        <el-input v-model="form.code" />
      </el-form-item>
      <el-form-item label="角色状态" prop="status">
        <aq-select :business-type="'status'" :bind-value.sync="form.status" style="width: 100%" />
      </el-form-item>
      <el-form-item label="分配权限">
        <menu-tree ref="menuTree" :keys.sync="keys" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitHandler">提交</el-button>
        <el-button @click="resetForm">重置</el-button>
        <el-button @click="goBack">返回</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { queryById } from '@/api/common'
import aqSelect from '@/components/Element/Select'
import menuTree from '@/components/Business/MenuTree'
import { update } from '@/api/common'
import { resultSuccessShowMsg } from '@/utils/validate'

export default {
  components: {
    aqSelect,
    menuTree
  },
  data() {
    return {
      keys: [],
      form: {
        id: '',
        name: '',
        code: '',
        status: '',
        menuIds: ''
      },
      rules: {
        name: [
          { required: true, message: '请输入名称', trigger: 'blur' }
        ],
        code: [
          { required: true, message: '请输入编码', trigger: 'blur' }
        ],
        status: [
          { required: true, message: '请选择状态', trigger: 'change' }
        ]
      }
    }
  },
  created() {
    this.form.id = this.$route.query.id
    if (this.form.id) {
      this.fetchData()
    }
  },
  methods: {
    fetchData() {
      queryById('role', this.form.id).then(response => {
        this.form = response.result
        this.keys = response.result.menuIds.split(',')
      })
    },
    submitHandler() {
      this.$refs['form'].validate(validate => {
        if (validate) {
          this.form.menuIds = this.keys.join(',')
          update('role', this.form).then(response => {
            resultSuccessShowMsg(response)
          })
        }
      })
    },
    resetForm() {
      this.$refs['form'].resetFields()
    },
    goBack() {}
  }
}
</script>

<style scoped>
</style>

