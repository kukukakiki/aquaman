<template>
  <div class="app-container">
    <el-row>
      <el-col :span="6">
        <div style="padding-bottom: 5px;">
          <el-button-group>
            <el-button v-show_button="'menuAdd'" type="primary" @click.stop="addHandler">新增</el-button>
            <el-button v-show_button="'menuUpdate'" :disabled="!showButton" type="primary" @click.stop="updateHandler">修改</el-button>
            <el-button :disabled="!showButton" type="primary" @click.stop="deleteHandler">删除</el-button>
          </el-button-group>
        </div>
        <el-input v-model="filterText" placeholder="输入关键字进行过滤" />
        <el-tree ref="menuTree" :data="items" :props="defaultTreeProps" :filter-node-method="filterNode" @node-click="handleNodeClick" />
      </el-col>
      <el-col :span="18">
        <el-form ref="form" :rules="rules" :model="form" label-width="100px">
          <el-form-item label="上级菜单" prop="parentId">
            <el-select v-model="form.parentId" placeholder="请选择" style="width: 100%" >
              <el-option v-for="item in parentList" :key="item.id" :label="item.name" :value="item.id" />
            </el-select>
          </el-form-item>
          <el-form-item label="菜单名称" prop="name">
            <el-input v-model="form.name" />
          </el-form-item>
          <el-form-item label="菜单编码" prop="code">
            <el-input v-model="form.code" />
          </el-form-item>
          <el-form-item label="菜单类型" prop="type">
            <aq-select :business-type="'type'" :bind-value.sync="form.type" style="width: 100%" />
          </el-form-item>
          <el-form-item label="路由地址" prop="router">
            <el-input v-model="form.router" />
          </el-form-item>
          <el-form-item label="接口地址" prop="url">
            <el-input v-model="form.url" />
          </el-form-item>
          <el-form-item label="菜单序号" prop="sort">
            <el-input v-model="form.sort" />
          </el-form-item>
          <el-form-item label="菜单图标" prop="iconType">
            <el-input v-model="form.iconType" />
          </el-form-item>
          <el-form-item label="是否显示" prop="display">
            <aq-select :business-type="'display'" :bind-value.sync="form.display" style="width: 100%" />
          </el-form-item>
          <el-form-item label="菜单状态" prop="status">
            <aq-select :business-type="'status'" :bind-value.sync="form.status" style="width: 100%" />
          </el-form-item>
          <el-form-item>
            <el-button-group>
              <el-button v-if="showOperatorButton" type="primary" @click="submitHandler">{{ operatorButtonName }}</el-button>
              <el-button v-if="showOperatorButton" @click="resetForm">重置</el-button>
            </el-button-group>
          </el-form-item>
        </el-form>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import { queryByTree, queryById } from '@/api/menu'
import { queryNotConditions, save, update } from '@/api/common'
import aqSelect from '@/components/Element/Select'
import { isNotEmpty, resultValidate, resultSuccessShowMsg } from '@/utils/validate'
// import aqPrompt from '@/components/Element/Prompt'

export default {
  components: {
    aqSelect
  },
  data() {
    var validateType = (rule, value, callback) => {
      const checkLevel = this.calculationMenuTypeLevel(value)
      if (checkLevel < this.parentLeve) {
        callback(new Error('当前级别不可高于上级节点'))
      }
      // 修改菜单类型需要验证能够调低级别
      callback()
    }
    return {
      showButton: false, // 显示执行按钮
      showOperatorButton: false, // 显示操作按钮
      operatorButtonName: '创建', // 操作按钮名称
      parentLeve: 1, // 父节点菜单类型级别
      filterText: '',
      items: [], // 列表集合
      /**
       * 列表查询对象
       */
      query: {
        parentId: -1 // 从根节点查询菜单树
      },
      /**
       * 默认树对应关系
       */
      defaultTreeProps: {
        children: 'children', // 子菜单集合名称
        label: 'name' // 菜单显示名称
      },
      /**
       * 菜单Form属性
       */
      form: {
        id: '', // 主键ID
        systemId: '', // 所属系统ID
        code: '', // 菜单编码
        name: '', // 菜单名称
        url: '', // 菜单接口API地址
        router: '', // 前端路由地址
        iconType: '', // 前端菜单图标
        type: '', // 菜单类型
        display: '', // 前端菜单是否显示
        remark: '', // 菜单备注
        sort: '', // 菜单排序
        status: 'start', // 菜单状态
        parentId: -1 // 菜单父节点ID
      },
      /**
       * 父菜单集合
       */
      parentList: [
        {
          id: -1,
          name: '根节点'
        }
      ],
      /**
       * 菜单类型规则
       */
      menuTypeRules: {},
      rules: {
        name: [
          { required: true, message: '请输入名称', trigger: 'blur' }
        ],
        code: [
          { required: true, message: '请输入编码', trigger: 'blur' }
        ],
        type: [
          { required: true, message: '请选择菜单类型', trigger: 'change' },
          { validator: validateType, trigger: 'change' }
        ],
        router: [
          { required: true, message: '请输入路由地址', trigger: 'blur' }
        ],
        parentId: [
          { required: true, message: '请输选择上级菜单', trigger: 'change' }
        ]
      }
    }
  },
  watch: {
    /**
     * 监听菜单树搜索框输入事件
     */
    filterText(val) {
      this.$refs.menuTree.filter(val)
    }
  },
  created() {
    this.fetchData()
    queryNotConditions('menu/keyLevelToMap').then(response => {
      const data = response.result
      if (resultValidate(response.code)) {
        this.menuTypeRules = data
      }
    })
  },
  methods: {
    /**
     * 获取数据
     */
    fetchData() {
      queryByTree(this.query).then(response => {
        const data = response.result
        if (resultValidate(response.code)) {
          this.items = data
        }
      })
    },
    /**
     * 获取选中树数据
     */
    handleNodeClick(data) {
      queryById(data.id).then(response => {
        const data = response.result
        if (resultValidate(response.code)) {
          this.form = data.menu
          this.parentLeve = this.calculationMenuTypeLevel(this.form.parentLeve)
          if (this.form.parentId !== -1) {
            this.parentList = data.parentList
          } else {
            this.parentList = this.resetParentList()
          }
          this.showButton = true
        }
      })
    },
    /**
     * 表单提交
     */
    submitHandler() {
      this.$refs['form'].validate((valid) => {
        if (valid) {
          if (isNotEmpty(this.form.id)) {
            update('menu', this.form).then(response => {
              resultSuccessShowMsg(response)
            })
          } else {
            save('menu', this.form).then(response => {
              resultSuccessShowMsg(response)
            })
          }
          this.fetchData()
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    /**
     * 过滤菜单树查询
     */
    filterNode(value, data) {
      if (!value) return true
      return data.name.indexOf(value) !== -1
    },
    /**
     * 新增处理器
     */
    addHandler() {
      this.showOperatorButtonHandler()
      this.operatorButtonName = '创建'
      // 屏蔽修改，删除按钮
      this.showButton = false
      if (isNotEmpty(this.form.id)) {
        const parentId = this.form.id
        const name = this.form.name
        this.resetForm()
        this.form.parentId = parentId
        this.parentList = [{ id: parentId, name: name }]
      }
    },
    /**
     * 修改处理器
     */
    updateHandler() {
      this.showOperatorButtonHandler()
      this.operatorButtonName = '更新'
    },
    /**
     * 删除处理器
     */
    deleteHandler() {},
    /**
     * 显示操作按钮处理器
     */
    showOperatorButtonHandler() {
      this.showOperatorButton = true
    },
    /**
     * 隐藏才做按钮处理器
     */
    hiddenOperatorButtonHandler() {
      this.showOperatorButton = false
    },
    /**
     * 重置表单属性
     */
    resetForm() {
      this.$refs['form'].resetFields()
      this.form.id = ''
      this.resetParentList()
    },
    /**
     * 重置上级菜单
     */
    resetParentList() {
      this.form.parentId = -1
      this.parentLeve = 1
      this.parentList = [{ id: -1, name: '根节点' }]
    },
    /**
     * 计算菜单级别
     */
    calculationMenuTypeLevel(type) {
      if (this.menuTypeRules !== undefined && this.menuTypeRules !== null) {
        return this.menuTypeRules[this.form.type]
      }
    }
  }
}
</script>

<style scoped>
</style>

