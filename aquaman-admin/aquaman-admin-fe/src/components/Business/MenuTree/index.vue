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
  mounted() {
  },
  methods: {
    fetchData() {
      queryByTree(this.query).then(response => {
        const data = response.result
        if (resultValidate(response.code)) {
          this.items = data
          this.$nextTick(function() {
            this.setCheckedKeys()
          })
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
      this.selectKeys = this.$refs.menuTree.getCheckedKeys().concat(this.$refs.menuTree.getHalfCheckedKeys())
    },
    /**
     * 设置树菜单，改设置采用递归方式，由下至上的方式进行check选中
     * @param keys
     */
    setCheckedKeys() {
      // 判断当前DOM树树形菜单是否已经加载完成，未加载完成，隔3秒再次调用该方式
      if (this.$refs.menuTree !== undefined && this.$refs.menuTree.children !== undefined &&
          this.$refs.menuTree.children.length > 0) {
        // 遍历整个菜单树
        this.$refs.menuTree.children.forEach(node => {
          // 判断菜单扩展属性非等于system,并且入参选中keys不能为空
          if (node.meta.type === 'group') {
            if (this.selectKeys.indexOf(String(node.id)) !== -1) {
              // Tree方式，接收三个参数，1. 勾选节点的 key 或者 data 2. boolean 类型，节点是否选中 3. boolean 类型，是否设置子节点 ，默认为 false
              this.$refs.menuTree.setChecked(node.id, true, false)
            }
          }
          // 递归循环子菜单，入参子菜单对象
          this.whileMenuIdsCheckedElTree(node)
        })
      } else {
        setTimeout(() => {
          this.setCheckedKeys()
        }, 1000)
      }
    },
    whileMenuIdsCheckedElTree(node) {
      if (node.children !== undefined && node.children != null) {
        node.children.forEach(cnode => {
          // 验证子菜单的ID是否与入参选中的菜单Keys是否匹配，注意：indexOf必须是字符串类型
          if (this.selectKeys.indexOf(String(cnode.id)) !== -1) {
            // Tree方式，接收三个参数，1. 勾选节点的 key 或者 data 2. boolean 类型，节点是否选中 3. boolean 类型，是否设置子节点 ，默认为 false
            this.$refs.menuTree.setChecked(cnode.id, true, false)
          }
          // 验证该菜单是否还有子菜单，如若有这自此调用该方法，并且入参为该菜单对象
          if (cnode.children !== undefined && cnode.children !== null && cnode.children.length > 0) {
            this.whileMenuIdsCheckedElTree(cnode)
          }
        })
      }
    }
  }
}
</script>

<style scoped>
</style>

