<template>
  <div class="app-container">
    <el-button-group style="margin-bottom:15px;">
      <el-button :loading="submitLoading" type="primary" @click="submitHandler">提交</el-button>
      <el-button :loading="submitLoading" @click="resetForm">重置</el-button>
      <el-button :loading="submitLoading" @click="goBack">返回</el-button>
    </el-button-group>
    <el-tabs v-model="defaultTabName" type="border-card">
      <el-tab-pane name="first">
        <span slot="label">
          管理员详情
        </span>
        <el-form ref="form" :rules="rules" :model="form" label-width="120px">
          <el-collapse v-model="activeNames">
            <el-collapse-item title="账户信息" name="1">
              <el-row class="my_row">
                <el-col :xs="24" :sm="12" :md="12" :lg="8" :xl="6">
                  <el-form-item label="登陆账户" prop="account">
                    <el-input v-model="form.account" />
                  </el-form-item>
                </el-col>
                <el-col :xs="24" :sm="12" :md="12" :lg="8" :xl="6">
                  <el-form-item label="登陆密码" prop="password">
                    <el-input v-model="form.password" type="password" />
                  </el-form-item>
                </el-col>
                <el-col :xs="24" :sm="12" :md="12" :lg="8" :xl="6">
                  <el-form-item label="用户状态" prop="status">
                    <aq-select :business-type="'status'" :bind-value.sync="form.status" style="width: 100%" />
                  </el-form-item>
                </el-col>
                <el-col :xs="24" :sm="12" :md="12" :lg="8" :xl="6">
                  <el-form-item label="所属部门" prop="deptId">
                    <radio-dept-tree ref="radioDeptTree" :_id.sync="form.deptId" />
                  </el-form-item>
                </el-col>
                <el-col :xs="24" :sm="12" :md="12" :lg="8" :xl="6">
                  <el-form-item label="用户姓名" prop="name">
                    <el-input v-model="form.name" />
                  </el-form-item>
                </el-col>
                <el-col :xs="24" :sm="12" :md="12" :lg="8" :xl="6">
                  <el-form-item label="用户昵称" prop="nickName">
                    <el-input v-model="form.nickName" />
                  </el-form-item>
                </el-col>
                <el-col :xs="24" :sm="12" :md="12" :lg="8" :xl="6">
                  <el-form-item label="注册类型" prop="type">
                    <aq-select :business-type="'register'" :bind-value.sync="form.type" style="width: 100%" />
                  </el-form-item>
                </el-col>
              </el-row>
            </el-collapse-item>
            <el-collapse-item title="联系信息" name="2">
              <el-row class="my_row">
                <el-col :xs="24" :sm="12" :md="12" :lg="8" :xl="6">
                  <el-form-item label="用户手机" prop="mobile">
                    <el-input v-model="form.mobile" />
                  </el-form-item>
                </el-col>
                <el-col :xs="24" :sm="12" :md="12" :lg="8" :xl="6">
                  <el-form-item label="用户邮箱" prop="email">
                    <el-input v-model="form.email" />
                  </el-form-item>
                </el-col>
              </el-row>
            </el-collapse-item>
          </el-collapse>
        </el-form>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
import aqSelect from '@/components/Element/Select'
import radioDeptTree from '@/components/Business/Dept/RadioTree'
import { save } from '@/api/common'
import { resultSuccessShowMsg } from '@/utils/validate'

export default {
  components: {
    aqSelect,
    radioDeptTree
  },
  data() {
    return {
      defaultTabName: 'first',
      activeNames: ['1', '2'],
      submitLoading: false,
      form: {
        account: '',
        password: '',
        name: '',
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
          this.submitLoading = true
          save('user', this.form).then(response => {
            this.submitLoading = false
            resultSuccessShowMsg(response)
          }).catch(error => {
            this.submitLoading = false
            console.log(error)
          })
        }
      })
    },
    resetForm() {
      this.$refs['form'].resetFields()
    },
    goBack() {
      this.$router.push({ path: '/authorizeManager/admin' })
    }
  }
}
</script>

<style scoped>
</style>

