<template>
  <div class="app-container">
    <el-row>
      <el-col :span="6">
        123
      </el-col>
      <el-col :span="18">
        <el-row>
          <el-col :span="24">
            <el-card class="box-card">
              <div slot="header" class="clearfix">
                <span>菜单信息</span>
                <el-button style="float: right; padding: 3px 0" type="text">操作按钮</el-button>
              </div>
              <div v-for="o in 4" :key="o" class="text item">
                456
              </div>
            </el-card>
          </el-col>
        </el-row>
        <el-row style="padding-top: 10px;">
          <el-col :span="24">
            <el-card class="box-card">
              <div slot="header" class="clearfix">
                <span>部门人员</span>
                <el-button style="float: right; padding: 3px 0" type="text">操作按钮</el-button>
              </div>
              <div v-for="o in 4" :key="o" class="text item">
                789
              </div>
            </el-card>
          </el-col>
        </el-row>
      </el-col>
    </el-row>
  </div>
</template>

<script>

export default {
  components: {
  },
  data() {
    return {
    }
  },
  watch: {
  },
  created() {
  },
  methods: {
  }
}
</script>

<style scoped>
</style>

