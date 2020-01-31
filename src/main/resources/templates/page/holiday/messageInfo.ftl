<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>请假流程图</title>
    <#include "../../common.ftl">
</head>
<body>
      <label>是否同意：</label>
      <select name="message">
          <option value="1">同意</option>
          <option value="2">不同意</option>
      </select>
      <br>
      <label>审核意见：</label>
      <textarea placeholder="请输入备注信息" id="messageInfo"></textarea>
      <button onclick="sendMessage()">确定</button>
</body>
<script>
    //layui 框架的基本使用
    layui.use(['layer', 'form', 'element', 'laypage', 'upload', 'tree', 'carousel', 'flow'], function(){
        var layer = layui.layer
            ,form = layui.form
            ,element = layui.element
            ,$ = layui.jquery
            ,laypage = layui.laypage
            ,upload = layui.upload
            ,tree = layui.tree
            ,carousel = layui.carousel
            ,flow = layui.flow;
    });

    function sendMessage(){
        var messageInfo = $("#messageInfo").val();
        //检查当前任务是否挂载，如果挂载，不能处理请假流程
        $.ajax({
            type: "post",
            url: "/com/yzx/holiday/jugementSate?processInstanceId="+${processInstanceId!''},
            dataType:"json",
            cache: false,
            data:{"messageInfo":messageInfo},
            success:function(d){
                if(d.flag == true){
                    $.ajax({
                        type: "post",
                        url: "/com/yzx/holiday/completeTask?proessName=myProcess_1&taskId="+${taskId!''},
                        dataType:"json",
                        cache: false,
                        data:{"messageInfo":messageInfo},
                        success:function(d){
                            if(d.flag == true){
                                layui.use('table', function() {
                                    //layUserListId 为父页面 table的 id
                                    parent.layui.table.reload('layHolidayListId');
                                })
                                var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                                parent.layer.close(index); //再执行关闭
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
    }
</script>
</html>