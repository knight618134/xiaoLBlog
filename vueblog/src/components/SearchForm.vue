<template>
  <el-form :inline="true" class="demo-form-inline">
    <el-form-item label="關鍵詞">
      <el-input v-model="searchInput" placeholder="請輸入關鍵詞查詢"></el-input>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="startSearch">查询</el-button>
      <el-button @click="resetSearchInput">重置</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
export default {
  data() {
    return {
      searchInput: ''
    }
  },
  methods: {
    startSearch() {
      this.$emit('startSearch', this.searchInput)
    },
    resetSearchInput() {
      this.searchInput = ''
      this.$emit('resetSearch')
    }
  }
};
</script>

<style>
</style>