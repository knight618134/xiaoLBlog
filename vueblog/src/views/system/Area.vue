<template>
  <div>
    <el-container class="container">
      <el-row class="row">
        <el-button class="addBtn" type="primary">+ 新建</el-button>
        <el-table
          v-loading="loading"
          element-loading-text="拼命加载中"
          element-loading-spinner="el-icon-loading"
          element-loading-background="rgba(0, 0, 0, 0.8)"
          :data="tableData"
          style="width: 100%; margin-bottom: 20px"
          row-key="id"
          border
          lazy
          :load="load"
          :tree-props="{ children: 'childrens', hasChildren: 'hasChildren' }"
        >
          <!-- <el-table-column prop="id" label="序號"> </el-table-column> -->
          <el-table-column type="index" width="50" label="序號">
          </el-table-column>
          <el-table-column
            prop="areaName"
            label="區域名稱"
            width="450"
          ></el-table-column>
          <el-table-column prop="areaCode" width="450" label="區域編碼">
          </el-table-column>
          <el-table-column prop="areaOrder" width="80" label="排序">
          </el-table-column>
          <el-table-column label="操作">
            <template slot-scope="scope">
              <el-button
                size="mini"
                @click="handleEdit(scope.$index, scope.row)"
                type="text"
                >編輯</el-button
              >
              <el-button
                size="mini"
                @click="handleDelete(scope.$index, scope.row)"
                type="text"
                >刪除</el-button
              >
            </template>
          </el-table-column>
        </el-table>
      </el-row>
    </el-container>
    <el-dialog
      title="編輯區域"
      :visible.sync="dialogVisible"
      width="30%"
      :before-close="handleClose"
    >
      <el-form
        label-width="80px"
        :model="formLabelAlign"
      >
        <el-form-item label="區域名稱">
          <el-input v-model="formLabelAlign.fFullname"></el-input>
        </el-form-item>
        <el-form-item label="區域編碼">
          <el-input v-model="formLabelAlign.fEncode"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="dialogVisible = false"
          >确 定</el-button
        >
      </span>
    </el-dialog>
  </div>
</template>

<script>
import _ from "lodash";
export default {
  data() {
    return {
      loading: true,
      isStatus: true,
      tableData: [],
      resolveTree: [],
      allFirstAreaOrderIdx: [],
      addProvinceData: [],
      dialogVisible: false,
      formLabelAlign: {
        fId: "",
        fEncode: "",
        fFullname: "",
      },
    };
  },
  mounted() {
    this.initGetAreaData();
  },
  methods: {
    handleClose(done) {
      this.$confirm("确认关闭？")
        .then((_) => {
          done();
        })
        .catch((_) => {});
    },
    initGetAreaData() {
      this.getRequest("/baseProvince/getAll").then((res) => {

        const [...args] = res.obj;
        const filterProvinceData = args.map((item, idx) => {
          return {
            areaCode: item.fencode,
            areaName: item.ffullname,
            areaOrder: item.ftype,
            id: idx + 1,
            fid: item.fid,
          };
        });
        this.addProvinceData = filterProvinceData;
        filterProvinceData.forEach((element, index) => {
          if (element.areaOrder === "1") {
            this.allFirstAreaOrderIdx.push(index);
          }
        });
        const handleProvinceData = [];
        this.allFirstAreaOrderIdx.forEach((element, idx, arr) => {
          handleProvinceData.push(
            JSON.stringify(filterProvinceData.slice(arr[idx], arr[idx + 1]))
          );
        });
        const orderAllFirstAreaOrderIdx = this.allFirstAreaOrderIdx.map(
          (item) => item + 1
        );
        const orderByProvinceData = handleProvinceData.map((item) =>
          JSON.parse(item)
        );
        orderByProvinceData.forEach((element) => {
          const findSpecialNumArr = element.filter(function (item) {
            return orderAllFirstAreaOrderIdx.indexOf(item.id) !== -1;
          });
          const convertObject = Object.assign({}, ...findSpecialNumArr);
          convertObject.hasChildren = true;
          this.tableData.push(convertObject);
        });
        this.loading = false;
      });
    },
    handleEdit(index, row) {
      this.dialogVisible = true;
      this.formLabelAlign.fId = row.fid;
      this.formLabelAlign.fEncode = row.areaCode;
      this.formLabelAlign.fFullname = row.areaName;
      console.log(this.formLabelAlign)
    },
    handleDelete(index, row) {
      console.log(index, row);
    },
    load(tree, treeNode, resolve) {
      const findTreeNodeIndex = this.allFirstAreaOrderIdx.findIndex(
        (item) => item === tree.id - 1
      );
      const startFilter = this.allFirstAreaOrderIdx[findTreeNodeIndex];
      const endFilter = this.allFirstAreaOrderIdx[findTreeNodeIndex + 1];
      const resolveChild = this.addProvinceData.slice(
        startFilter + 1,
        endFilter
      );
      setTimeout(() => {
        resolve(resolveChild);
      }, 1000);
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

.addBtn {
  margin: 20px 0;
}
</style>
