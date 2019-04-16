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
      options: {
        status: [{
          value: 'start',
          label: '启用'
        }, {
          value: 'stop',
          label: '停用'
        }],
        type: [{
          value: 'group',
          label: '菜单组'
        }, {
          value: 'menu',
          label: '菜单'
        }, {
          value: 'button',
          label: '按钮'
        }, {
          value: 'page',
          label: '弹出页'
        }],
        display: [{
          value: '1',
          label: '显示'
        }, {
          value: '0',
          label: '隐藏'
        }]
      }
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

