<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>请假历史List页面</title>
    <#include "../../common.ftl">
</head>
<body class="layui-layout-body">
    <table class="layui-hide" id="holidayListId" lay-filter="holidayListId"></table>
    <script type="text/html" id="options">
        <button type="button" class="layui-btn layui-btn-sm layui-btn-normal" lay-event="viewComment">查看</button>
    </script>
    <script>
        //layui 中基本信息的使用
        layui.use('table', function() {
            var table = layui.table;
            table.render({
                elem: '#holidayListId',
                id: 'layHolidayListId',
                url: '/com/yzx/holiday/queryHistoryHolidayList',
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
                        fixed: 'left'
                    },{
                        field: 'processInstanceId',
                        title: '流程定义Id',
                        align:"center",
                        minWidth: '120',
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
                    case 'viewComment':
                        viewComment(data.processInstanceId);
                        break;
                    case 'compHoliday':
                        compHoliday(data.executionId);
                        break;
                    default:
                        break;
                }
            });

            //头工具栏监听事件
            table.on('toolbar(holidayListId)', function(obj){
                //var checkStatus = table.checkStatus(obj.config.id); //获取选中行状态
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
            function viewComment(processId) {
                layer.open({
                    type: 2,
                    title: '请假审核意见',
                    maxmin: true,
                    offset:'60px',
                    area: ['600px', '400px'],
                    shadeClose: false, //点击遮罩关闭
                    content: '/com/yzx/holiday/getProcessComments?taskId='+processId,
                });
            };

            //请假申请页面
            function leave() {
                layer.open({
                    //设置坐标
                    offset: '100px',
                    //设置皮肤样式 ，demo-class
                    skin: 'layui-layer-molv',
                    //遮罩透明度
                    //shade: 0.8,
                    //关闭按钮是否显示 1显示0不显示
                    closeBtn: 1,
                    //类型，解析url  可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
                    type: 2,
                    title: '请假',
                    maxmin: true,
                    //设置宽高
                    area: ['600px', '400px'],
                    //点击遮罩关闭
                    shadeClose: false,
                    //遮罩透明度
                    content: '/com/yzx/holiday/holidayPage',
                });
            };

            //完成当前任务
            function compHoliday(executionId) {
                //ajax请求的参数直接用data.field获取表单里含有name属性的元素的值s
                $.ajax({
                    type: "post",
                    url: "/com/yzx/holiday/completeTask?proessName=myProcess_1",
                    dataType:"json",
                    cache: false,
                    success:function(d){
                        if(d.flag == true){
                            layer.alert('消息信息：'+d.msg, {
                                icon: 1,
                            });
                        }else {
                            layer.alert('消息信息：'+d.msg, {
                                icon: 2,
                            });
                        }
                        layui.table.reload('layHolidayListId');
                    },error:function (d) {
                        layer.msg("发生未知错误！");
                    }
                });
            };
        });
    </script>
</body>
</html>