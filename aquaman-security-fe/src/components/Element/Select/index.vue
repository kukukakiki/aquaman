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
          value: 'START',
          label: '启用'
        }, {
          value: 'STOP',
          label: '停用'
        }],
        type: [{
          value: 'GROUP',
          label: '菜单组'
        }, {
          value: 'MENU',
          label: '菜单'
        }, {
          value: 'BUTTON',
          label: '按钮'
        }, {
          value: 'PAGE',
          label: '弹出页'
        }],
        display: [{
          value: 'SHOW',
          label: '显示'
        }, {
          value: 'HIDE',
          label: '隐藏'
        }]
      }
    }
  },
  computed: {
  },
  watch: {
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

