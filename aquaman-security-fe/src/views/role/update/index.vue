<template>
  <div class="app-container">
    <el-button-group style="margin-bottom:15px;">
      <el-button type="primary" @click="submitHandler">提交</el-button>
      <el-button @click="resetForm">重置</el-button>
      <el-button @click="goBack">返回</el-button>
    </el-button-group>
    <el-tabs v-model="defaultTabName" type="border-card">
      <el-tab-pane name="first">
        <span slot="label">
          角色详情
        </span>
        <el-form ref="form" :rules="rules" :model="form" label-width="80px">
          <el-collapse v-model="activeNames">
            <el-collapse-item title="基本信息" name="1">
              <el-row class="my_row">
                <el-col :xs="24" :sm="12" :md="12" :lg="8" :xl="6">
                  <el-form-item label="角色名称" prop="name">
                    <el-input v-model="form.name" />
                  </el-form-item>
                </el-col>
                <el-col :xs="24" :sm="12" :md="12" :lg="8" :xl="6">
                  <el-form-item label="角色编码" prop="code">
                    <el-input v-model="form.code" />
                  </el-form-item>
                </el-col>
                <el-col :xs="24" :sm="12" :md="12" :lg="8" :xl="6">
                  <el-form-item label="角色状态" prop="status">
                    <aq-select :business-type="'status'" :bind-value.sync="form.status" style="width: 100%" />
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row class="my_row">
                <el-col :span="24">
                  <el-form-item label="备注">
                    <el-input v-model="form.remarks" type="textarea" />
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
import { queryById } from '@/api/common'
import aqSelect from '@/components/Element/Select'
import { update } from '@/api/common'
import { resultSuccessShowMsg } from '@/utils/validate'

export default {
  components: {
    aqSelect
  },
  data() {
    return {
      defaultTabName: 'first',
      activeNames: ['1'],
      form: {
        id: '',
        name: '',
        code: '',
        status: '',
        remarks: ''
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
      })
    },
    submitHandler() {
      this.$refs['form'].validate(validate => {
        if (validate) {
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

