<template>
  <div class="dashboard-container">
    <el-upload
      :show-file-list="false"
      :on-success="handleAvatarSuccess"
      :action="getUploadFormUrl()"
      :headers="authorizationHeaders"
      list-type="picture-card">
      <el-image v-if="imageUrl" :src="imageUrl">
        <div slot="error" class="image-slot">
          <i class="el-icon-picture-outline" />
        </div>
      </el-image>
      <i v-else class="el-icon-plus avatar-uploader-icon" />
    </el-upload>
  </div>
</template>

<script>
import { getToken } from '@/utils/auth'

export default {
  name: 'Dashboard',
  data() {
    return {
      imageUrl: '',
      authorizationHeaders: { Authorization: 'Bearer ' + getToken() }
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    getUploadFormUrl() {
      return process.env.BASE_API + '/upload_file_info/upload'
    },
    fetchData() {},
    handleAvatarSuccess(res, file) {
      this.imageUrl = process.env.BASE_API + '/upload_file_info/download/' + res.result
      console.log('附件下载地址:', this.imageUrl)
    }
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
