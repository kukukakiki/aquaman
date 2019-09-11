<template>
  <div class="app-container">
    <el-button-group style="margin-bottom:15px;">
      <el-button :loading="submitLoading" type="primary" @click="submitHandler">提交</el-button>
      <el-button @click="resetForm">重置</el-button>
      <el-button @click="goBack">返回</el-button>
    </el-button-group>
    <el-tabs v-model="defaultTabName" type="border-card">
      <el-tab-pane name="first">
        <span slot="label">
          系统详情
        </span>
        <el-form ref="form" :rules="rules" :model="form" label-width="100px">
          <el-collapse v-model="activeNames">
            <el-collapse-item title="基本信息" name="1">
              <el-row class="my_row">
                <el-col :xs="24" :sm="12" :md="12" :lg="8" :xl="6">
                  <el-form-item label="系统编码" prop="code">
                    <el-input v-model="form.code" />
                  </el-form-item>
                </el-col>
                <el-col :xs="24" :sm="12" :md="12" :lg="8" :xl="6">
                  <el-form-item label="系统名称" prop="name">
                    <el-input v-model="form.name" />
                  </el-form-item>
                </el-col>
                <el-col :xs="24" :sm="12" :md="12" :lg="8" :xl="6">
                  <el-form-item label="所属协议" prop="deal">
                    <el-input v-model="form.deal" />
                  </el-form-item>
                </el-col>
                <el-col :xs="24" :sm="12" :md="12" :lg="8" :xl="6">
                  <el-form-item label="系统域名" prop="domain">
                    <el-input v-model="form.domain" />
                  </el-form-item>
                </el-col>
                <el-col :xs="24" :sm="12" :md="12" :lg="8" :xl="6">
                  <el-form-item label="系统端口" prop="port">
                    <el-input v-model="form.port" />
                  </el-form-item>
                </el-col>
                <el-col :xs="24" :sm="12" :md="12" :lg="8" :xl="6">
                  <el-form-item label="当前版本" prop="version">
                    <el-input v-model="form.version" />
                  </el-form-item>
                </el-col>
                <el-col :xs="24" :sm="12" :md="12" :lg="8" :xl="6">
                  <el-form-item label="开发语言" prop="language">
                    <el-input v-model="form.language" />
                  </el-form-item>
                </el-col>
                <el-col :xs="24" :sm="12" :md="12" :lg="8" :xl="6">
                  <el-form-item label="运行状态" prop="runStatus">
                    <el-input v-model="form.runStatus" />
                  </el-form-item>
                </el-col>
                <el-col :xs="24" :sm="12" :md="12" :lg="8" :xl="6">
                  <el-form-item label="系统状态" prop="status">
                    <aq-select :business-type="'status'" :bind-value.sync="form.status" style="width: 100%" />
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row class="my_row">
                <el-col :span="24">
                  <el-form-item label="备注" prop="remarks">
                    <el-input v-model="form.remarks" type="textarea" />
                  </el-form-item>
                </el-col>
              </el-row>
            </el-collapse-item>
            <el-collapse-item title="服务器信息" name="2">
              <el-row class="my_row">
                <el-col :xs="24" :sm="12" :md="12" :lg="8" :xl="6">
                  <el-form-item label="服务器地址" prop="serverIp">
                    <el-input v-model="form.serverIp" />
                  </el-form-item>
                </el-col>
                <el-col :xs="24" :sm="12" :md="12" :lg="8" :xl="6">
                  <el-form-item label="服务器系统" prop="serverSystem">
                    <el-input v-model="form.serverSystem" />
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
import { save } from '@/api/common'
import { resultSuccessShowMsg } from '@/utils/validate'

export default {
  components: {
    aqSelect
  },
  data() {
    return {
      submitLoading: false, // 提交按钮loading
      defaultTabName: 'first', // 默认tabs显示的name值
      activeNames: ['1', '2'], // 默认展开collapse的name值
      form: {
        code: '',
        name: '',
        domain: '',
        deal: '',
        port: '',
        serverIp: '',
        language: '',
        version: '',
        status: '',
        runStatus: '',
        serverSystem: '',
        remarks: ''
      },
      rules: {
        code: [
          { required: true, message: '请输入系统编码', trigger: 'blur' }
        ],
        name: [
          { required: true, message: '请输入系统名称', trigger: 'blur' }
        ],
        deal: [
          { required: true, message: '请输入所属协议', trigger: 'blur' }
        ],
        domain: [
          { required: true, message: '请输入系统域名', trigger: 'blur' }
        ],
        port: [
          { required: true, message: '请输入系统端口', trigger: 'blur' }
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
          save('system', this.form).then(response => {
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
      this.$router.push({ path: '/systemMessage/system' })
    }
  }
}
</script>

<style scoped>
</style>

