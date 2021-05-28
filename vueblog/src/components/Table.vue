<template>
  <div class="table">
    <el-button class="addBtn" type="primary" @click="openAddDialog"
      >+ 新建</el-button
    >
    <el-table
      :data="
        tableData.filter((item) => {
          return item.name.match(this.searchWord);
        })
      "
      style="width: 100%; margin-bottom: 20px"
      row-key="id"
      border
      :tree-props="{ children: 'childrens', hasChildren: 'hasChildren' }"
    >
      <el-table-column type="index" width="50" label="序號"> </el-table-column>
      <el-table-column
        v-for="{ prop, label, width } in colConfigs"
        :key="prop"
        :prop="prop"
        :label="label"
        :width="width"
      ></el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button
            size="mini"
            @click="editTableData(scope.$index, scope.row)"
            type="text"
            >編輯</el-button
          >
          <el-button
            size="mini"
            @click="deleteTableData(scope.$index, scope.row)"
            type="text"
            >刪除</el-button
          >
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import _ from "lodash";
export default {
  data() {
    return {};
  },
  mounted() {},
  computed: {},
  watch: {
    closeDialog(newVal) {
      console.log(newVal, "closeDialog");
      if (newVal) {
        this.dialogVisible = false;
      }
    },
  },
  methods: {
    editTableData(idx, row) {
      this.$emit("isAdd", false);
      this.$emit("isDialogOpen", true);
      this.$emit("editData", row);
    },
    deleteTableData(idx, row) {
      this.$emit("deleteData", row);
    },
    openAddDialog() {
      this.$emit("isAdd", true);
      this.$emit("isDialogOpen", true);
    },
  },
  props: {
    searchWord: {
      type: String,
      default: "",
    },
    colConfigs: {
      type: Array,
      default: [],
    },
    tableData: {
      type: Array,
      default: [],
    },
  },
};
</script>

<style scoped>
.addBtn {
  margin: 20px 0;
}
</style>