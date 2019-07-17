<template>
  <div class="app-container">
    <el-button-group style="margin-bottom:15px;">
      <el-button @click="goBack">返回</el-button>
    </el-button-group>
    <el-tabs v-model="defaultTabName" type="border-card">
      <el-tab-pane name="first">
        <span slot="label">
          管理员详情
        </span>
        <el-form ref="form" :model="form" label-width="120px">
          <el-collapse v-model="activeNames">
            <el-collapse-item title="账户信息" name="1">
              <el-row class="my_row">
                <el-col :xs="24" :sm="12" :md="12" :lg="8" :xl="6">
                  <el-form-item label="登陆账户：">
                    {{ form.account }}
                  </el-form-item>
                </el-col>
                <el-col :xs="24" :sm="12" :md="12" :lg="8" :xl="6">
                  <el-form-item label="用户姓名：">
                    {{ form.name }}
                  </el-form-item>
                </el-col>
                <el-col :xs="24" :sm="12" :md="12" :lg="8" :xl="6">
                  <el-form-item label="用户状态：" prop="status">
                    {{ getLabel('status', form.status) }}
                  </el-form-item>
                </el-col>
                <el-col :xs="24" :sm="12" :md="12" :lg="8" :xl="6">
                  <el-form-item label="用户昵称：">
                    {{ form.nickName }}
                  </el-form-item>
                </el-col>
                <el-col :xs="24" :sm="12" :md="12" :lg="8" :xl="6">
                  <el-form-item label="注册类型：">
                    {{ getLabel('register', form.type) }}
                  </el-form-item>
                </el-col>
              </el-row>
            </el-collapse-item>
            <el-collapse-item title="联系信息" name="2">
              <el-row class="my_row">
                <el-col :xs="24" :sm="12" :md="12" :lg="8" :xl="6">
                  <el-form-item label="用户手机：" prop="mobile">
                    {{ form.mobile }}
                  </el-form-item>
                </el-col>
                <el-col :xs="24" :sm="12" :md="12" :lg="8" :xl="6">
                  <el-form-item label="用户邮箱：" prop="emial">
                    {{ form.email }}
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
import Pagination from '@/components/Pagination'
import { getLabel } from '@/utils/constant'

export default {
  components: {
    Pagination
  },
  data() {
    return {
      defaultTabName: 'first',
      activeNames: ['1', '2'],
      showButton: false, // 显示执行按钮
      loading: false, // 页面loading标示
      id: '', // 用户主键ID
      form: {
        id: '',
        account: '',
        password: '',
        status: '',
        type: '',
        nickName: '',
        mobile: '',
        email: ''
      } // 用户对象
    }
  },
  created() {
    this.form.id = this.$route.query.id
    if (this.form.id) {
      this.fetchData()
    }
  },
  methods: {
    getLabel,
    /**
     * 获取数据
     */
    fetchData() {
      queryById('user', this.form.id).then(response => {
        console.log(response.result)
        if (response.code === '0000') {
          this.form = response.result
        }
      }).catch(error => {
        this.$message({
          message: error.msg,
          type: 'error',
          duration: 5 * 1000
        })
        this.loading = false
      })
    },
    goBack() {}
  }
}
</script>

<style scoped>
</style>

