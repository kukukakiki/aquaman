<template>
  <div>
    <el-input v-model="filterText" placeholder="输入关键字进行权限筛选" />
    <el-tree ref="menuTree" :data="items" :props="defaultTreeProps" :filter-node-method="filterNode" show-checkbox node-key="id" @check-change="handleCheckChange" />
  </div>
</template>

<script>
import { queryByTree } from '@/api/menu'
import { resultValidate } from '@/utils/validate'

export default {
  components: {
  },
  props: {
    keys: {
      required: true,
      type: Array
    }
  },
  data() {
    return {
      filterText: '',
      selectKeys: this.keys, // 选中菜单ID集合
      items: [], // 列表集合
      query: {
        parentId: -1 // 从根节点查询菜单树
      },
      defaultTreeProps: {
        children: 'children', // 子菜单集合名称
        label: 'name' // 菜单显示名称
      }
    }
  },
  watch: {
    /**
     * 监听菜单树搜索框输入事件
     */
    filterText(val) {
      this.$refs.menuTree.filter(val)
    },
    keys(val) {
      this.selectKeys = val
    },
    selectKeys(val) {
      this.$emit('update:keys', val)
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      queryByTree(this.query).then(response => {
        const data = response.result
        if (resultValidate(response.code)) {
          this.items = data
        }
      })
    },
    filterNode(value, data) {
      if (!value) return true
      return data.name.indexOf(value) !== -1
    },
    getCheckedKeys() {
      console.log(this.$refs.menuTree.getCheckedKeys())
    },
    handleCheckChange(data, checked, indeterminate) {
      this.selectKeys = this.$refs.menuTree.getCheckedKeys()
    }
  }
}
</script>

<style scoped>
</style>

