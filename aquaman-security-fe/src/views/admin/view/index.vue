<template>
  <div class="app-container">
    <el-form :inline="true" :model="query" class="demo-form-inline">
    </el-form>
  </div>
</template>

<script>
import { queryById } from '@/api/common'
import Pagination from '@/components/Pagination'

export default {
  components: {
    Pagination
  },
  data() {
    return {
      showButton: false, // 显示执行按钮
      loading: false, // 页面loading标示
      id: '', // 用户主键ID
      item: '' // 用户对象
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    /**
     * 获取数据
     */
    fetchData() {
      this.loading = true
      queryById('user', this.id).then(response => {
        const data = response.result
        if (response.code === '0000') {
          this.items = data.records
          this.query.total = data.total
          this.query.size = data.size
        }
        this.loading = false
      }).catch(error => {
        this.$message({
          message: error.msg,
          type: 'error',
          duration: 5 * 1000
        })
        this.loading = false
      })
    }
  }
}
</script>

<style scoped>
</style>

