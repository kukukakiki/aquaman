<template>
  <div class="dashboard-container">
    <el-upload
      :show-file-list="false"
      :on-success="handleAvatarSuccess"
      list-type="picture-card"
      action="http://aquaman.leerzoom.com:8001/file/upload">
      <img v-if="imageUrl" :src="imageUrl" class="avatar">
      <i v-else class="el-icon-plus avatar-uploader-icon" />
    </el-upload>
  </div>
</template>

<script>

export default {
  name: 'Dashboard',
  data() {
    return {
      imageUrl: ''
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {},
    handleAvatarSuccess() {}
  }
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
