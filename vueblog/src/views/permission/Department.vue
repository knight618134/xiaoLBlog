<template>
  <el-container class="container">
    <el-aside class="aside" width="300px">
      <span>組織架構</span>
      <el-divider></el-divider>
      <el-tag type="info">标签三</el-tag>
    </el-aside>
    <el-container>
      <el-header class="row">
        <search-form
          @startSearch="startSearch"
          @resetSearch="searchWord = ''"
        ></search-form>
      </el-header>
      <el-main class="row">
        <Table
          :colConfigs="colConfigs"
          :tableData="tableData"
          @isAdd="getIsAddData"
          @isDialogOpen="getIsDialogOpenData"
          @editData="getEditData"
          :searchWord="searchWord"
          @deleteData="getDeleteData"
        ></Table>
        <el-dialog
          :title="dialogTitle"
          :visible.sync="dialogVisible"
          width="80%"
        >
          <el-form label-width="80px" :model="form" ref="form" :rules="rules">
            <el-col
              :span="12"
              v-for="(item, key, index) in form"
              :key="`${key}-${index}`"
            >
              <el-form-item :label="formName[index]" :prop="key">
                <el-input
                  :type="formName[index] === '排序' ? 'number' : 'text'"
                  :rows="6"
                  v-model="form[key]"
                ></el-input>
              </el-form-item>
            </el-col>
          </el-form>
          <span slot="footer" class="dialog-footer">
            <el-button @click="dialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="saveData">确 定</el-button>
          </span>
        </el-dialog>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import Table from "@/components/Table";
import moment from "moment";
import SearchForm from "@/components/SearchForm";
export default {
  components: {
    Table,
    SearchForm,
  },
  data() {
    return {
      editDataId: "",
      isAdd: false,
      dialogVisible: false,
      tableData: [],
      colConfigs: [
        { prop: "name", label: "名稱", width: 200 },
        { prop: "encode", label: "編碼", width: 200 },
        { prop: "description", label: "備註", width: 200 },
        { prop: "createTime", label: "創建時間", width: 200 },
        { prop: "order", label: "排序", width: 200 },
      ],
      form: {
        name: "",
        encode: "",
        description: "",
        order: "",
      },
      rules: {
        name: [
          {
            required: true,
            message: "請輸入名稱",
            trigger: "blur",
          },
        ],
        encode: [
          {
            required: true,
            message: "請輸入編碼",
            trigger: "blur",
          },
        ],
        description: [
          {
            required: true,
            message: "請輸入描述",
            trigger: "blur",
          },
        ],
        order: [
          {
            required: true,
            message: "請輸入排序",
            trigger: "blur",
          },
        ],
      },
      formName: ["名稱", "編碼", "備註", "排序"],
      tableTitles: [
        {
          tilte: "名稱",
          name: "name",
          width: "200",
        },

        {
          tilte: "編碼",
          name: "encode",
          width: "200",
        },
        {
          tilte: "備註",
          name: "description",
          width: "200",
        },
        {
          tilte: "創建時間",
          name: "createTime",
          width: "100",
        },

        {
          tilte: "排序",
          name: "order",
          width: "50",
        },
      ],
      searchWord: "",
    };
  },
  computed: {
    dialogTitle() {
      return this.isAdd ? "新增" : "編輯";
    },
  },
  mounted() {
    this.getInitOrganizeData();
  },
  methods: {
    startSearch(word) {
      this.searchWord = word;
    },
    getIsAddData(status) {
      this.isAdd = status;
    },
    getIsDialogOpenData() {
      this.dialogVisible = true;
    },
    getEditData(data) {
      const { createTime, id, ...args } = data;
      this.form = args;
      this.editDataId = id;
    },
    getDeleteData(data) {
      const { id } = data;
      this.deleteRequest(`http://localhost:3000/organizeData/${id}`)
        .then((res) => {
          this.getInitOrganizeData();
          this.$message({
            type: "info",
            message: "刪除成功",
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "刪除失敗",
          });
        });
    },
    getInitOrganizeData() {
      this.getRequest("http://localhost:3000/organizeData").then((res) => {
        const [id, ...args] = res;
        this.tableData = args;
      });
    },
    resetFormData() {
      this.form = {
        name: "",
        encode: "",
        description: "",
        order: "",
      };
    },
    saveData(editData) {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          this.form.createTime = moment().format("YYYY-MM-DD HH:mm:ss");
          if (this.isAdd) {
            this.postRequest("http://localhost:3000/organizeData", this.form)
              .then(() => {
                this.dialogVisible = false;
                this.resetFormData();
                this.$message({
                  type: "info",
                  message: "新增成功",
                });
                this.getInitOrganizeData();
              })
              .catch(() => {
                this.$message({
                  type: "info",
                  message: "新增失敗",
                });
              });
          } else {
            const { createTime, ...args } = this.form;
            console.log(args, "args");
            this.putRequest(
              `http://localhost:3000/organizeData/${this.editDataId}`,
              args
            )
              .then(() => {
                this.dialogVisible = false;
                this.resetFormData();
                this.$message({
                  type: "info",
                  message: "更新成功",
                });
                this.getInitOrganizeData();
              })
              .catch(() => {
                this.$message({
                  type: "info",
                  message: "更新失敗",
                });
              });
          }
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    onSubmit() {
      console.log("submit!");
    },
    getFormIsAdd(status) {
      console.log(status, "t");
      this.isAdd = status;
    },
  },
};
</script>

<style scoped>
.aside {
  border: 1px solid gray;
}

.container {
  margin: 20px 0 0 0;
}

.row {
  width: 100%;
}
</style>