<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>请假List页面</title>
    <link rel="stylesheet" href="/statics/layui/css/layui.css">
    <script src="/webjars/jquery/3.1.1/jquery.min.js"></script>
    <script src="/statics/layui/layui.js"></script>
</head>
<body class="layui-layout-body">
    <table class="layui-hide" id="holidayListId" lay-filter="holidayListId"></table>
    <script type="text/html" id="options">
        <button type="button" class="layui-btn layui-btn-sm layui-btn-normal" lay-event="viewUser">查看</button>
        <button type="button" class="layui-btn layui-btn-sm layui-btn-warm" lay-event="removeUser">删除</button>
    </script>
    <script type="text/html" id="toolbarDemo">
        <div class="layui-btn-container">
            <button class="layui-btn layui-btn-sm" lay-event="leave" >
                <i class="layui-icon layui-icon-add-1"></i> 请假
            </button>
        </div>
    </script>
    <script>
        layui.use('table', function() {
            var table = layui.table;
            table.render({
                elem: '#holidayListId',
                id: 'layHolidayListId',
                url: '/com/yzx/holiday/queryHolidayList',
                title: '请假信息列表',
                toolbar: '#toolbarDemo',
                cellMinWidth: 300,
                page: true, //开启分页
                limit:5,//要传向后台的每页显示条数
                page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
                    layout: ['count', 'prev', 'page', 'next', 'limit', 'refresh', 'skip']//自定义分页布局
                    ,limits:[5,10,15]
                    ,first: false //不显示首页
                    ,last: false //不显示尾页
                },
                cols: [
                    [{
                        type:'radio',      //设置单选框
                        //type: 'checkbox', //设置复选框
                        fixed: 'left'
                    },{
                        field: 'executionId',
                        title: '执行ID',
                        align:"center",
                        minWidth: '120',
                    }, {
                        field: 'name',
                        title: '处理名称',
                        align:"center",
                        minWidth: '120'
                    }, {
                        field: 'assignee',
                        //edit:true, 设置表元素是否为可编辑状态
                        //sort:true, 设置当前列是否排序
                        //hide:true,设置当前列是否隐藏
                        align:"center",
                        title: '操作人员'
                    }, {
                        title: '操作',
                        minWidth: '180',
                        align: 'center',
                        toolbar: '#options'
                    }]
                ]
            });
            //监听工具条事件
            table.on('tool(holidayListId)', function(obj) {
                var data = obj.data; //获得当前行数据
                switch(obj.event) {
                    case 'viewUser':
                        viewUser();
                        break;
                    case 'removeUser':
                        removeUser(data.userId);
                        break;
                    default:
                        break;
                }
            });

            //头工具栏监听事件
            table.on('toolbar(holidayListId)', function(obj){
                //var checkStatus = table.checkStatus(obj.config.id); //获取选中行状态
                debugger;
                switch(obj.event){
                    case 'leave':
                        leave();
                        break;
                    default:
                        break;
                };
            });


            var $ = layui.$,
                active = {
                    reload: function() {
                        var deviceNumber = $("#userName").val();
                        //执行重载
                        table.reload('layUserListId', {
                            page: {
                                curr: 1
                            },
                            where: {
                                deviceNumber:deviceNumber
                            }
                        });
                    },
                    getCheckData: function() { //获取选中数据
                        var checkStatus = table.checkStatus('layUserListId'),
                            data = checkStatus.data;
                        layer.alert(JSON.stringify(data));
                    },
                    getCheckLength: function() { //获取选中数目
                        var checkStatus = table.checkStatus('layUserListId'),
                            data = checkStatus.data;
                        layer.msg('选中了：' + data.length + ' 个');
                    },
                    isAll: function() { //验证是否全选
                        var checkStatus = table.checkStatus('layUserListId');
                        layer.msg(checkStatus.isAll ? '全选' : '未全选')
                    }
                };
            $('.layui-btn').on('click', function() {
                var type = $(this).data('type');
                active[type] && active[type].call(this);
            });

            //查看用户信息
            function viewUser() {

            };

            //添加用户信息
            function leave() {
                layer.open({
                    type: 2,
                    title: '用户信息添加',
                    maxmin: true,
                    area: ['600px', '600px'],
                    shadeClose: false, //点击遮罩关闭
                    content: '/com/yzx/register',
                });
            };

            //删除用户信息
            function removeUser(userId) {
                layer.confirm('确定要删除么?', function(index){
                    $.ajax({
                        type:'post',
                        url:"/com/yzx/deleteUser?userId="+userId,
                        cache: false,
                        dataType:'json',
                        success:function(data){
                            var a = data;
                            if(a.flag == true){
                                layer.close(index);
                                layui.use(['layer', 'form'], function(){
                                    var layer = layui.layer;
                                    layer.msg('删除信息：'+a.msg);
                                });
                                layui.table.reload('layUserListId');
                            }else{
                                layui.use(['layer', 'form'], function(){
                                    var layer = layui.layer;
                                    layer.msg('错误信息：'+a.msg);
                                });
                            }
                        }
                    })
                });
            };
        });
    </script>
</body>
</html>