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
          表单新增/修改示例
        </span>
        <el-form ref="form" :rules="rules" :model="form" label-width="80px">
          <el-collapse v-model="activeNames">
            <el-collapse-item title="展示示例1" name="1">
              <el-row class="my_row">
                <el-col :xs="24" :sm="12" :md="12" :lg="8" :xl="6">
                  增加form-item属性1
                </el-col>
                <el-col :xs="24" :sm="12" :md="12" :lg="8" :xl="6">
                  增加form-item属性1
                </el-col>
                <el-col :xs="24" :sm="12" :md="12" :lg="8" :xl="6">
                  增加form-item属性1
                </el-col>
                <el-col :xs="24" :sm="12" :md="12" :lg="8" :xl="6">
                  增加form-item属性1
                </el-col>
                <el-col :xs="24" :sm="12" :md="12" :lg="8" :xl="6">
                  增加form-item属性1
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

export default {
  components: {
  },
  data() {
    return {
      submitLoading: 'false', // 提交按钮loading
      defaultTabName: 'first', // 默认tabs显示的name值
      activeNames: ['1'] // 默认展开collapse的name值
    }
  },
  created() {
  },
  methods: {
    submitHandler() {},
    resetForm() {},
    goBack() {}
  }
}
</script>

<style scoped>
</style>

