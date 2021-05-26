<template>
  <el-container class="container">
    <el-row class="row">
      <el-tabs type="border-card">
        <el-tab-pane label="基本設置">
          <el-form label-width="80px" :rules="rules" ref="form" :model="form">
            <el-col
              :span="formName[index]==='系統描述'? 24 : 12"
              v-for="(item, key, index) in form"
              :key="`${ key }-${ index }`"
            >
              <el-form-item :label="formName[index]" :prop="key">
                <el-input
                  :type="formName[index]==='系統描述' ? 'textarea' : 'text'"
                  :rows="6"
                  v-model="form[key]"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="24">
              <el-button class="save" @click="submitForm('form')" type="primary">保存</el-button>
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
      formName:['系統名稱','系統版本','公司名稱','版權信息','公司簡稱','公司地址','公司法人','公司電話','公司郵箱','系統描述'],
      form:{
        systemName : '',
        systemPatch: '',
        companyName: '',
        copyrightInformation:'',
        companyAbbreviation:'',
        companyAddress:'',
        corporate:'',
        companyTel:'',
        companyEmail:'',
        systemDescribe:'',
      },
      rules: {
        systemName: [
          { required: true, message: "請輸入系統名稱", trigger: "blur" },
        ],
        systemPatch: [
          { required: true, message: "請輸入系統版本", trigger: "blur" },
        ],
        companyName: [
          { required: true, message: "請輸入公司名稱", trigger: "blur" },
        ],
        copyrightInformation: [
          { required: true, message: "請輸入版權信息", trigger: "blur" },
        ],
        companyAbbreviation: [
          { required: true, message: "請輸入公司簡稱", trigger: "blur" },
        ],
        companyAddress: [
          { required: true, message: "請輸入公司地址", trigger: "blur" },
        ],
        corporate: [
          { required: true, message: "請輸入公司法人", trigger: "blur" },
        ],
        companyTel: [
          { required: true, message: "請輸入公司電話", trigger: "blur" },
        ],
        companyEmail: [
          { required: true, message: "請輸入公司郵箱", trigger: "blur" },
        ],
        systemDescribe: [
          { required: true, message: "請輸入系統描述", trigger: "blur" },
        ],
      },
    };
  },
  computed: {
  },
   methods: {
      submitForm(formName) {
        console.log(this.form)
        this.$refs[formName].validate((valid) => {
          if (valid) {
            alert('submit!');
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
    }
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
</style>
