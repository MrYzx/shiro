<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <#-- freemaker 引入公共页面-->
    <#include "../common.ftl">
</head>
<style type="text/css">
    .table {
        margin-top: 5px;
    }

    .button {
        margin-top: 8px;
        margin-left: 10px;
    }
</style>
<script type="text/javascript">
    $(function () {
        //编辑表格
        $('#reportTable').bootstrapTable({
            //数据来源的网址
            url: '/index.xhtml',
            method: 'post',
            editable: true,//开启编辑模式
            clickToSelect: true,
            showPaginationSwitch: true, //显示分页切换按钮
            search: true,  //显示检索框
            showRefresh: true,  //显示刷新按钮
            showToggle: true, //显示切换按钮来切换列表/卡片视图
            pagination: true,
            pageList: [5, 25],
            pageSize: 5,
            pageNumber: 1,
            columns: [[
                {field: "id", edit: false, title: "编号", align: "center"},
                {
                    field: "user_company", edit: {
                        type: 'select',//下拉框
                        //数据来源地址
                        //url:'user/getUser.htm',
                        data: [{id: 1, text: 'lzx'}, {id: 2, text: 'lsl'}],
                        valueField: 'id',
                        textField: 'text',
                        onSelect: function (val, rec) {
                            console.log(val, rec);
                        }
                    }, title: "下拉框", align: "center", width: "200px"
                },
                {
                    field: "time", edit: {
                        type: 'date',//日期
                        required: true,
                        click: function () {
                        }
                    }, title: "时间", align: "center", editor: true
                },
                {field: "name", title: "名字", align: "center"},
                {field: "age", title: "年龄", align: "center"},
                {
                    field: "gender",
                    title: "性别",
                    align: "center",
                    width: "200px",
                    formatter: function (value, row, rowIndex) {
                        if (value == 1) {
                            return '男';
                        } else if (value == 2) {
                            return '女';
                        }
                    },
                    edit: {
                        type: 'select',//下拉框
                        //数据来源地址
                        data: [{id: 1, text: '男'}, {id: 2, text: '女'}],
                        valueField: 'id',
                        textField: 'text',
                        onSelect: function (val, rec) {
                            console.log(val, rec);
                        }
                    }
                }
                // {field:"userstatus_end_time",title:"操作",align:"center",formatter:function(value,row,rowIndex){
                //  var strHtml ='<a href="javascript:void(0);" onclick="removeRow('+row+')">删除</a>';
                //  return strHtml;
                // },edit:false}
            ]]
        });
        $('#addRowbtn').click(function () {
            var data = {};
            $('#reportTable').bootstrapTable('append', data);
        });

        $('sava').onClickCell(function () {

        });
    });

    function removeRow(row) {
        console.log(row);
    }

    function update() {
        var row = $('#reportTable').bootstrapTable('getSelections')
        console.log(row)
        location.href = "delete.action?uid=" + row.uid
        var row = $('#dg').datagrid('reload');
    }

    function sava() {
        var row = $('#reportTable').bootstrapTable('getSelections');
        if (row.length == 1) {
            console.log(a[0].id);
        } else {
            console.log(row.name);
            alert("请选中一行")
        }
    }
</script>
<body>
<div id="myTabContent" class="tab-content" style="">
    <!--可编辑表格-->
    <div class="tab-pane fade in active button" id="tab2">
        <button type="button" class="btn btn-success dropdown-toggle" id="addRowbtn">
            <span class="glyphicon glyphicon  glyphicon-plus" aria-hidden="true"></span>增加
        </button>
        <button type="button" class="btn btn-warning" onclick="javascript:update()">
            <span class="glyphicon glyphicon-edit" aria-hidden="true"></span>修改
        </button>
        <button type="button" class="btn btn-info" onclick="javascript:sava()" id="sava">
            <span class=" glyphicon glyphicon-floppy-save" aria-hidden="true"></span>保存
        </button>
    </div>
</div>
<div>
    <table class="table table-striped table-hover" id="reportTable"></table>
</div>
</body>
</html>