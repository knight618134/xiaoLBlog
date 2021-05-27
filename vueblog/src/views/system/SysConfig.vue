<template>
  <el-container class="container">
    <el-row class="row">
      <el-tabs type="border-card">
        <el-tab-pane label="基本設置">
          <el-form label-width="80px" :rules="rules" ref="form" :model="form">
            <el-col
              :span="formName[index] === '系統描述' ? 24 : 12"
              v-for="(item, key, index) in form"
              :key="`${key}-${index}`"
            >
              <el-form-item :label="formName[index]" :prop="key">
                <el-input
                  :type="formName[index] === '系統描述' ? 'textarea' : 'text'"
                  :rows="6"
                  v-model="form[key]"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="24">
              <el-button
                v-if="!isGetForm"
                class="save"
                @click="submitForm('form')"
                type="primary"
                >保存</el-button
              >
              <el-button
                v-if="isGetForm"
                class="update"
                @click="updateForm('form')"
                type="primary"
                >更新</el-button
              >
              <el-button
                v-if="isGetForm"
                class="delete"
                @click="deleteForm"
                type="danger"
                >刪除</el-button
              >
            </el-col>
          </el-form>
        </el-tab-pane>
        <el-tab-pane label="安全設置">安全設置</el-tab-pane>
        <el-tab-pane label="郵箱設置">郵箱設置</el-tab-pane>
        <el-tab-pane label="短信設置">短信設置</el-tab-pane>
      </el-tabs>
    </el-row>
  </el-container>
</template>

<script>
export default {
  data() {
    return {
      isGetForm: false,
      formId: "",
      formName: [
        "系統名稱",
        "系統版本",
        "公司名稱",
        "版權信息",
        "公司簡稱",
        "公司地址",
        "公司法人",
        "公司電話",
        "公司郵箱",
        "系統描述",
      ],
      form: {
        systemname: "",
        systempatch: "",
        companyname: "",
        copyrightinformation: "",
        companyabbreviation: "",
        companyaddress: "",
        corporate: "",
        companytel: "",
        companyemail: "",
        systemdescribe: "",
      },
      rules: {
        systemname: [
          {
            required: true,
            message: "請輸入系統名稱",
            trigger: "blur",
          },
        ],
        systempatch: [
          {
            required: true,
            message: "請輸入系統版本",
            trigger: "blur",
          },
        ],
        companyname: [
          {
            required: true,
            message: "請輸入公司名稱",
            trigger: "blur",
          },
        ],
        copyrightinformation: [
          {
            required: true,
            message: "請輸入版權信息",
            trigger: "blur",
          },
        ],
        companyabbreviation: [
          {
            required: true,
            message: "請輸入公司簡稱",
            trigger: "blur",
          },
        ],
        companyaddress: [
          {
            required: true,
            message: "請輸入公司地址",
            trigger: "blur",
          },
        ],
        corporate: [
          {
            required: true,
            message: "請輸入公司法人",
            trigger: "blur",
          },
        ],
        companytel: [
          {
            required: true,
            message: "請輸入公司電話",
            trigger: "blur",
          },
        ],
        companyemail: [
          {
            required: true,
            message: "請輸入公司郵箱",
            trigger: "blur",
          },
        ],
        systemdescribe: [
          {
            required: true,
            message: "請輸入系統描述",
            trigger: "blur",
          },
        ],
      },
    };
  },
  computed: {
    isFormHasValue() {
      return Object.values(this.form).join("") !== "";
    },
  },
  mounted() {
    this.getInitSetting();
  },
  methods: {
    getInitSetting() {
      this.getRequest("/baseSysconfig/getAll").then((resp) => {
        if (resp.obj.length !== 0) {
          this.isGetForm = true;
          const { id, ...args } = resp.obj[0];
          this.formId = id;
          this.form = args;
        } else {
          this.isGetForm = false;
          Object.keys(this.form).forEach((v) => (this.form[v] = ""));
        }
      });
    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          const payload = { ...this.form, id: 1 };
          const encodePayload = this.queryParameters(payload);
          this.postRequest("/baseSysconfig/create?" + encodePayload).then(
            (res) => {
              this.$message({
                type: "info",
                message: "成功新增",
              });
              this.getInitSetting();
            }
          );
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    queryParameters(payload) {
      return Object.entries(payload)
        .map(([key, val]) => `${key}=${val}`)
        .join("&");
    },
    updateForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          const encodePayload = this.queryParameters(this.form);
          this.putRequest(
            `/baseSysconfig/update?id=${this.formId}&${encodePayload}`
          ).then((res) => {
            this.$message({
              type: "info",
              message: "成功變更",
            });
            this.getInitSetting();
          });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    deleteForm() {
      if (this.isFormHasValue) {
        this.deleteRequest("/baseSysconfig/delete?id=" + this.formId).then(
          () => {
            this.$message({
              type: "info",
              message: "成功刪除",
            });
            this.getInitSetting();
          }
        );
      } else {
        return;
      }
    },
  },
};
</script>

<style scoped>
.container {
  margin: 20px 0 0 0;
}

.row {
  width: 100%;
}

.save {
  margin: 0 0 0 80px;
  padding: 10px 40px;
}

.update {
  padding: 10px 40px;
}

.delete {
  padding: 10px 40px;
}
</style>
