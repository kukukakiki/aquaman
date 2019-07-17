<template>
  <el-select v-model="currentValue" placeholder="请选择">
    <el-option
      v-for="item in currentItem"
      :key="item.value"
      :label="item.label"
      :value="item.value" />
  </el-select>
</template>

<script>
import { options } from '@/utils/constant'

export default {
  props: {
    businessType: {
      required: true,
      type: String
    },
    bindValue: {
      required: true,
      type: String
    }
  },
  data() {
    return {
      currentValue: this.bindValue,
      currentItem: [],
      options: options()
    }
  },
  computed: {
  },
  watch: {
    bindValue(val) {
      this.currentValue = val
    },
    currentValue(val) {
      this.$emit('update:bindValue', val)
    }
  },
  created() {
    this.currentItem = this.options[this.businessType]
  },
  methods: {
  }
}
</script>

<style scoped>
</style>

