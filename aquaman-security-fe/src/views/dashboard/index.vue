<template>
  <div class="dashboard-container">
    <el-alert title="首页待开发" type="warning" />
  </div>
</template>

<script>

export default {
  name: 'Dashboard'
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
.dashboard {
  &-container {
    margin: 30px;
  }
  &-text {
    font-size: 30px;
    line-height: 46px;
  }
}
</style>
