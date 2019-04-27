<template>
  <div class="app-container">
    <menu-tree :keys.sync="keys" />
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
        console.log(response)
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

