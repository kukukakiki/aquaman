<template>
  <div class="app-container">
    <menu-tree ref="menuTree" :keys.sync="keys" />
  </div>
</template>

<script>
import { queryById } from '@/api/common'
import menuTree from '@/components/Business/MenuTree'

export default {
  components: {
    menuTree
  },
  data() {
    return {
      keys: [],
      form: {
        id: ''
      }
    }
  },
  created() {
    this.form.id = this.$route.query.id
    if (this.form.id) {
      queryById('role', this.form.id).then(response => {
        this.keys = response.result.menuIds.split(',')
      })
    }
  },
  methods: {
  }
}
</script>

<style scoped>
</style>

