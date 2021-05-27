<template>
  <div>
    <el-container class="container">
      <el-row class="row">
        <el-button class="addBtn" type="primary">+ 新建</el-button>
        <el-table
          :data="tableData"
          style="width: 100%; margin-bottom: 20px"
          row-key="id"
          border
          default-expand-all
          :tree-props="{ children: 'children', hasChildren: 'hasChildren' }"
        >
          <el-table-column prop="tableNum" label="序號"> </el-table-column>
          <el-table-column prop="areaName" label="區域名稱" width="180">
          </el-table-column>
          <el-table-column prop="areaCode" label="區域編碼"> </el-table-column>
          <el-table-column prop="areaOrder" label="排序"> </el-table-column>
          <!-- <el-table-column label="狀態">
          <el-switch active-color="blue" inactive-color="#fff" :change="isStatus=false" v-model="isStatus">
          </el-switch>
        </el-table-column> -->

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
  </div>
</template>

<script>
import _ from "lodash";
export default {
  data() {
    return {
      isStatus: true,
      tableData: [
        // {
        //   id: 1,
        //   tableNum: 0,
        //   name: "王小虎",
        //   address: "上海市普陀区金沙江路 1518 弄",
        // },
        // {
        //   id: 2,
        //   date: "2016-05-04",
        //   name: "王小虎",
        //   address: "上海市普陀区金沙江路 1517 弄",
        // },
        // {
        //   id: 3,
        //   date: "2016-05-01",
        //   name: "王小虎",
        //   address: "上海市普陀区金沙江路 1519 弄",
        //   children: [{
        //       id: 31,
        //       date: "2016-05-01",
        //       name: "王小虎",
        //       address: "上海市普陀区金沙江路 1519 弄",
        //     },
        //     {
        //       id: 32,
        //       date: "2016-05-01",
        //       name: "王小虎",
        //       address: "上海市普陀区金沙江路 1519 弄",
        //     },
        //   ],
        // },
        // {
        //   id: 4,
        //   date: "2016-05-03",
        //   name: "王小虎",
        //   address: "上海市普陀区金沙江路 1516 弄",
        // },
      ],
    };
  },
  mounted() {
    this.initGetAreaData();
  },
  methods: {
    initGetAreaData() {
      this.getRequest("/baseProvince/getAll").then((res) => {
        const [fid, ...args] = res.obj;
        const filterProvinceData = args.map((item) => {
          return {
            areaCode: item.fencode,
            areaName: item.ffullname,
            areaOrder: item.ftype,
          };
        });
        const allFirstAreaOrderIdx = [];
        filterProvinceData.findIndex((item) => item.areaOrder === "1");
        filterProvinceData.forEach((element, index) => {
          if (element.areaOrder === "1") {
            allFirstAreaOrderIdx.push(index);
          }
        });
     
          // [0, 337, 634, 3100, 4667, 5820, 7524, 8529, 10514, 10780, 12568, 14206, 16040, 17331, 19244, 21352, 23987, 25544, 28205, 30129, 31528, 31895, 32973, 37872, 39523, 41070, 41844, 43743, 45253, 45721, 46015]
        allFirstAreaOrderIdx.forEach((element, idx, arr) => {
          
          const arr2 = filterProvinceData.slice(arr[idx], arr[idx + 1])
          console.log(arr2)
        });

     
      });
    },
    handleEdit(index, row) {
      console.log(index, row);
    },
    handleDelete(index, row) {
      console.log(index, row);
    },
    load(tree, treeNode, resolve) {
      setTimeout(() => {
        resolve([
          {
            id: 31,
            date: "2016-05-01",
            name: "王小虎",
            address: "上海市普陀区金沙江路 1519 弄",
          },
          {
            id: 32,
            date: "2016-05-01",
            name: "王小虎",
            address: "上海市普陀区金沙江路 1519 弄",
          },
        ]);
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
