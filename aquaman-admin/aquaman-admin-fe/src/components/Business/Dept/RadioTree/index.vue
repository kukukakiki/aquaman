<template>
  <div>
    <el-input v-model="selectName" placeholder="请输入内容" class="input-with-select">
      <el-button slot="append" icon="el-icon-search" @click="showDialog" />
    </el-input>
    <el-dialog
      :visible.sync="dialogVisible"
      title="选择部门"
      width="50%">
      <el-input v-model="filterText" placeholder="输入关键字进行权限筛选" />
      <el-tree ref="deptTree" :filter-node-method="filterNode" :data="items" :props="defaultProps" class="filter-tree" default-expand-all @node-click="handleNodeClick" />
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitHandler">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import { queryAllByTree, queryById } from '@/api/dept'
import { resultValidate } from '@/utils/validate'

export default {
  components: {
  },
  props: {
    _id: {
      required: false,
      type: Number,
      default: null
    }
  },
  data() {
    return {
      filterText: '',
      selectName: '', // 选中部门名称
      selectKey: this._id, // 选中部门ID
      selectItem: {},
      dialogVisible: false,
      items: [], // 列表集合
      defaultProps: { // 部门树显示配置
        children: 'children',
        label: 'name'
      }
    }
  },
  watch: {
    /**
     * 监听菜单树搜索框输入事件
     */
    filterText(val) {
      this.$refs.deptTree.filter(val)
    },
    _id(val) {
      this.selectKeys = val
      console.log(val)
      if (val) {
        queryById(val).then(response => {
          const data = response.result
          if (resultValidate(response.code)) {
            this.selectName = data.dept.name
          }
        })
      }
    },
    selectKey(val) {
      this.$emit('update:_id', val)
    }
  },
  created() {
    this.fetchData()
  },
  mounted() {
  },
  methods: {
    fetchData() {
      queryAllByTree().then(response => {
        this.items = response.result
      })
    },
    filterNode(value, data) {
      if (!value) return true
      return data.name.indexOf(value) !== -1
    },
    handleNodeClick(data) {
      this.selectItem = data
    },
    submitHandler() {
      this.selectName = this.selectItem.name
      this.selectKey = this.selectItem.id
      this.dialogVisible = false
    },
    showDialog() {
      this.dialogVisible = true
    }
  }
}
</script>

<style scoped>
</style>

