<template>
    <el-table
            :data="CommentData"
            style="width: 100%">
        <el-table-column
                label="Avatar"
                width="300px">
            <template v-slot:default="scope" >
                <div style="display: flex">
                    <div style="width: 120px; height: 60px">hellowwwww</div>
                    <div style="width: 60px; height: 60px">{{ scope.row.date }}</div>
                    <div style="width: 120px; height: 60px"></div>
                </div>
            </template>
        </el-table-column>
        <el-table-column
                label="Content"
                width="800">
            <template v-slot:default="scope" >
                <div style="display: flex; flex-direction: column">
                    <div style="display: flex">
                        <div style="min-width: 40px; height: 30px">{{ scope.row.authorName }}</div>
                        <div style="min-width: 120px; height: 30px; justify-content: center; display: flex; font-weight: bold; color: #00008B;"> Reply To: </div>
                        <div style="min-width: 40px; height: 30px">{{ scope.row.toUserName }}</div>
                    </div>
                    <div style="white-space: pre-wrap; height: 80px">{{ scope.row.content }}</div>
                </div>

            </template>
        </el-table-column>
        <el-table-column
                label="Stars"
                prop="address">
            <template v-slot:default="scope" >
                <div style="width: 80px; display: flex; flex-direction: column">
                    <i class="el-icon-star-on" style="width: 60px;"></i>
                    <div>{{ scope.row.stars }}</div>
                </div>

            </template>
        </el-table-column>
    </el-table>
</template>

<script>
    export default {
        props: {
            CommentData: {
                type: Array,
                default: () => ([]),
            },
        },
        data() {
            return {
                tableData: [{
                    date: '2016-05-02',
                    name: '王小虎',
                    address: '上海市普陀区金沙江路 1518 弄'
                }, {
                    date: '2016-05-04',
                    name: '王小虎',
                    address: '上海市普陀区金沙江路 1517 弄'
                }, {
                    date: '2016-05-01',
                    name: '王小虎',
                    address: '上海市普陀区金沙江路 1519 弄'
                }, {
                    date: '2016-05-03',
                    name: '王小虎',
                    address: '上海市普陀区金沙江路 1516 弄'
                }]
            }
        }
    }
</script>