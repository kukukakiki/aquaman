<template>
  <div class="app-container">
    <el-button-group style="margin-bottom:15px;">
      <el-button @click="goBack">返回</el-button>
    </el-button-group>
    <el-tabs v-model="defaultTabName" type="border-card">
      <el-tab-pane name="first">
        <span slot="label">
          角色详情
        </span>
        <el-form ref="form" :model="form" label-width="120px">
          <el-collapse v-model="activeNames">
            <el-collapse-item title="基本信息" name="1">
              <el-row class="my_row">
                <el-col :xs="24" :sm="12" :md="12" :lg="8" :xl="6">
                  <el-form-item label="角色名称：" prop="name">
                    {{ form.name }}
                  </el-form-item>
                </el-col>
                <el-col :xs="24" :sm="12" :md="12" :lg="8" :xl="6">
                  <el-form-item label="角色编码：" prop="code">
                    {{ form.code }}
                  </el-form-item>
                </el-col>
                <el-col :xs="24" :sm="12" :md="12" :lg="8" :xl="6">
                  <el-form-item label="角色状态：" prop="status">
                    {{ getLabel('status', form.status) }}
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row class="my_row">
                <el-col :span="24">
                  <el-form-item label="备注：" prop="remarks">
                    {{ form.remarks }}
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
import { getLabel } from '@/utils/constant'

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
    getLabel,
    fetchData() {
      queryById('role', this.form.id).then(response => {
        this.form = response.result
      })
    },
    goBack() {
      this.$router.push({ path: '/authorizeManager/role' })
    }
  }
}
</script>

<style scoped>
</style>

