<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>请假表单</title>
    <#include "../../common.ftl">
</head>
<body class="layui-layout-body">
     <form class="layui-form">
         <div class="layui-form-item">
             <label class="layui-form-label">标题：</label>
             <div class="layui-input-block">
                 <input type="text" name="head" required lay-verify="required" value="${sysHoliday.head!''}"
                        placeholder="请输入标题" autocomplete="off" class="layui-input">
             </div>
         </div>
         <div class="layui-form-item">
             <label class="layui-form-label">类型：</label>
             <div class="layui-input-block">
                 <input type="text" name="htype" required id="htype" value="${sysHoliday.htype!''}"
                        lay-verify="required" autocomplete="off" class="layui-input">
             </div>
         </div>
         <div class="layui-form-item">
             <label class="layui-form-label">天数：</label>
             <div class="layui-input-block">
                 <input type="text" name="htime" required id="htime" value="${sysHoliday.htime!''}"
                        lay-verify="required" autocomplete="off" class="layui-input">
             </div>
         </div>
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">原因：</label>
            <div class="layui-input-block">
                <textarea name="reason" placeholder="请输入请假原因" id="reason"class="layui-textarea">${sysHoliday.reason!''}</textarea>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="formDemo">立即保存</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
     </form>

<script>
    layui.use(['layer', 'form','table'], function(){
        var layer = layui.layer
        var form = layui.form;
        //监听提交
        form.on('submit(formDemo)', function(data){
            //var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
            var arr = new Array();
            $.ajax({
                type: "post",
                url: "/com/yzx/holiday/startHoliday?proessName=myProcess_1",
                //ajax请求的参数直接用data.field获取表单里含有name属性的元素的值s
                data:data.field,
                dataType:"json",
                success:function(d){
                    if(d.flag == true){
                        layer.alert('消息信息：'+d.msg, {
                            icon: 1,
                            //2秒关闭（如果不配置，默认是3秒）
                            time: -1
                        },function () {
                            var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                            parent.layer.close(index); //再执行关闭
                        });
                    }else{
                        layer.alert('消息信息：'+d.msg, {
                            icon: 0,
                            //2秒关闭（如果不配置，默认是3秒）
                            time: -1
                        },function () {
                            var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                            parent.layer.close(index); //再执行关闭
                        });
                    }
                    parent.layui.table.reload('layHolidayListId');
                },error:function (d) {
                    layer.close(index);
                    layer.msg("发生未知错误！");
                }
            });
            //false表示不重新加载页面，true表示重新加载
            return false;
        });
    });
</script>
</body>
</html>