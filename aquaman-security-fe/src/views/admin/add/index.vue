<template>
  <div class="app-container">
    <el-form ref="form" :rules="rules" :model="form" label-width="80px">
      <el-card class="box-card">
        <div slot="header" class="clearfix">
          <span>基本信息</span>
          <el-button style="float: right; padding: 3px 0" type="text">操作按钮</el-button>
        </div>
        <div class="text item">
          <el-form-item label="登陆账户" prop="account">
            <el-input v-model="form.account" />
          </el-form-item>
          <el-form-item label="登陆密码" prop="password">
            <el-input v-model="form.password" />
          </el-form-item>
          <el-form-item label="用户状态" prop="status">
            <aq-select :business-type="'status'" :bind-value.sync="form.status" style="width: 100%" />
          </el-form-item>
          <el-form-item label="用户昵称">
            <el-input v-model="form.nickName" />
          </el-form-item>
          <el-form-item label="注册类型" prop="type">
            <aq-select :business-type="'register'" :bind-value.sync="form.type" style="width: 100%" />
          </el-form-item>
        </div>
      </el-card>
      <el-card class="box-card" style="margin-top: 10px;">
        <div slot="header" class="clearfix">
          <span>联系信息</span>
          <el-button style="float: right; padding: 3px 0" type="text">操作按钮</el-button>
        </div>
        <div class="text item">
          <el-form-item label="用户手机">
            <el-input v-model="form.mobile" />
          </el-form-item>
          <el-form-item label="用户邮箱">
            <el-input v-model="form.email" />
          </el-form-item>
        </div>
      </el-card>
      <el-form-item>
        <el-button type="primary" @click="submitHandler">提交</el-button>
        <el-button @click="resetForm">重置</el-button>
        <el-button @click="goBack">返回</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import aqSelect from '@/components/Element/Select'
import { save } from '@/api/common'
import { resultSuccessShowMsg } from '@/utils/validate'

export default {
  components: {
    aqSelect
  },
  data() {
    return {
      form: {
        account: '',
        password: '',
        status: '',
        type: '',
        nickName: '',
        mobile: '',
        email: ''
      },
      rules: {
        account: [
          { required: true, message: '请输入登陆账户', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入登陆密码', trigger: 'blur' }
        ],
        status: [
          { required: true, message: '请选择状态', trigger: 'change' }
        ]
      }
    }
  },
  created() {
  },
  methods: {
    submitHandler() {
      this.$refs['form'].validate(validate => {
        if (validate) {
          this.form.menuIds = this.keys.join(',')
          save('user', this.form).then(response => {
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

