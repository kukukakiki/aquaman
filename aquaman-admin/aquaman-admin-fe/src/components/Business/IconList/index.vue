<template>
  <div>
    <el-input v-model="selectIcon" placeholder="请输入内容" class="input-with-select">
      <el-button slot="append" icon="el-icon-search" @click="showDialog" />
    </el-input>
    <el-dialog
      :visible.sync="dialogVisible"
      center
      title="菜单图标"
      width="20%">
      <el-radio-group v-model="selectIcon" @change="selectHandler">
        <el-radio-button v-for="icon in icons" :key="icon" :label="icon">
          <svg-icon :icon-class="icon" />
        </el-radio-button>
      </el-radio-group>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitHandler">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  components: {
  },
  props: {
    iconName: {
      required: false,
      type: String,
      default: null
    }
  },
  data() {
    return {
      dialogVisible: false,
      selectIcon: '',
      radio1: '',
      icons: [
        'example',
        'form',
        'password',
        'eye',
        'eye-open',
        'link',
        'nested',
        'table',
        'tree',
        'user',
        'authorize',
        'calendar',
        'system',
        'systemManager',
        'sort',
        'logger',
        'dictionary',
        'uploadFile'
      ]
    }
  },
  watch: {
    iconName(val) {
      this.selectIcon = val
    },
    selectIcon(val) {
      this.$emit('update:iconName', val)
    }
  },
  created() {
    this.fetchData()
  },
  mounted() {
  },
  methods: {
    fetchData() {
      // 遍历icons/svg目录下的所有图标
    },
    showDialog() {
      this.dialogVisible = true
    },
    selectHandler(value) {
      console.log(value)
      this.selectIcon = value
    },
    submitHandler() {
      this.dialogVisible = false
    }
  }
}
</script>

<style scoped>
</style>
