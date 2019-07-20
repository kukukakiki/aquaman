<template>
  <div class="app-container">
    <el-button-group style="margin-bottom:15px;">
      <el-button @click="goBack">返回</el-button>
    </el-button-group>
    <el-tabs v-model="defaultTabName" type="border-card">
      <el-tab-pane name="first">
        <span slot="label">
          系统详情
        </span>
        <el-form ref="form" :model="form" label-width="100px">
          <el-collapse v-model="activeNames">
            <el-collapse-item title="基本信息" name="1">
              <el-row class="my_row">
                <el-col :xs="24" :sm="12" :md="12" :lg="8" :xl="6">
                  <el-form-item label="系统编码" prop="code">
                    {{ form.code }}
                  </el-form-item>
                </el-col>
                <el-col :xs="24" :sm="12" :md="12" :lg="8" :xl="6">
                  <el-form-item label="系统名称" prop="name">
                    {{ form.name }}
                  </el-form-item>
                </el-col>
                <el-col :xs="24" :sm="12" :md="12" :lg="8" :xl="6">
                  <el-form-item label="所属协议" prop="deal">
                    {{ form.deal }}
                  </el-form-item>
                </el-col>
                <el-col :xs="24" :sm="12" :md="12" :lg="8" :xl="6">
                  <el-form-item label="系统域名" prop="domain">
                    {{ form.domain }}
                  </el-form-item>
                </el-col>
                <el-col :xs="24" :sm="12" :md="12" :lg="8" :xl="6">
                  <el-form-item label="系统端口" prop="port">
                    {{ form.port }}
                  </el-form-item>
                </el-col>
                <el-col :xs="24" :sm="12" :md="12" :lg="8" :xl="6">
                  <el-form-item label="当前版本" prop="version">
                    {{ form.version }}
                  </el-form-item>
                </el-col>
                <el-col :xs="24" :sm="12" :md="12" :lg="8" :xl="6">
                  <el-form-item label="开发语言" prop="language">
                    {{ form.language }}
                  </el-form-item>
                </el-col>
                <el-col :xs="24" :sm="12" :md="12" :lg="8" :xl="6">
                  <el-form-item label="运行状态" prop="runStatus">
                    {{ form.runStatus }}
                  </el-form-item>
                </el-col>
                <el-col :xs="24" :sm="12" :md="12" :lg="8" :xl="6">
                  <el-form-item label="系统状态" prop="status">
                    {{ getLabel('status', form.status) }}
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row class="my_row">
                <el-col :span="24">
                  <el-form-item label="备注" prop="remarks">
                    {{ form.remarks }}
                  </el-form-item>
                </el-col>
              </el-row>
            </el-collapse-item>
            <el-collapse-item title="服务器信息" name="2">
              <el-row class="my_row">
                <el-col :xs="24" :sm="12" :md="12" :lg="8" :xl="6">
                  <el-form-item label="服务器地址" prop="serverIp">
                    {{ form.serverIp }}
                  </el-form-item>
                </el-col>
                <el-col :xs="24" :sm="12" :md="12" :lg="8" :xl="6">
                  <el-form-item label="服务器系统" prop="serverSystem">
                    {{ form.serverSystem }}
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
import { getLabel } from '@/utils/constant'

export default {
  components: {
  },
  data() {
    return {
      defaultTabName: 'first', // 默认tabs显示的name值
      activeNames: ['1', '2'], // 默认展开collapse的name值
      form: {
        id: '',
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
      }
    }
  },
  created() {
    this.fetchData(this.$route.query.id)
  },
  methods: {
    getLabel,
    fetchData(id) {
      queryById('system', id).then(response => {
        console.log(response)
        this.form = response.result
      }).catch(error => {
        console.log(error)
      })
    },
    goBack() {
      this.$router.push({ path: '/systemMessage/system' })
    }
  }
}
</script>

<style scoped>
</style>

