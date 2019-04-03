<template>
  <el-pagination
    :total="total"
    :page-size="pageSize"
    :page-sizes="[5, 10, 15, 20]"
    background
    layout="total, sizes, prev, pager, next, jumper"
    @current-change="handleCurrentChange"
    @size-change="handleSizeChange" />
</template>

<script>

export default {
  props: {
    total: {
      type: Number,
      required: true
    }, pageSize: {
      type: Number,
      required: true,
      default: 1
    }
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
    handleCurrentChange(val) {
      this.$parent.query.current = val
      this.$parent.fetchData()
    },
    handleSizeChange(val) {
      this.$parent.query.size = val
      this.$parent.fetchData()
    }
  }
}
</script>

<style scoped>
</style>
