<template>
  <span slot="label">
    {{ labelName }}
    <el-tooltip placement="top">
      <div slot="content">
        <p>温馨提示：</p>
        <p v-for="(item, index) in promptArray" :key="index">{{ $index }}、{{ item }}</p>
      </div>
      <i class="el-icon-question" />
    </el-tooltip>
  </span>
</template>

<script>

export default {
  props: {
    labelName: {
      required: true,
      type: String
    },
    promptArray: {
      required: true,
      type: Array
    }
  },
  data() {
    return {
    }
  },
  computed: {
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

