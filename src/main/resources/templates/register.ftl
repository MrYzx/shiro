<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Layui HTML</title>
    <link rel="stylesheet" href="/statics/layui/css/layui.css">
    <script src="/webjars/jquery/3.1.1/jquery.min.js"></script>
    <script src="/statics/layui/layui.js"></script>
</head>
<body class="layui-layout-body">
<blockquote class="layui-elem-quote layui-text">
    鉴于小伙伴的普遍反馈，先温馨提醒两个常见“问题”
</blockquote>

<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>用户信息的注册</legend>
</fieldset>

<form class="layui-form" id="userRegister">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">用户名称</label>
            <div class="layui-input-inline">
                <input type="text" name="userName" value="${sysUser.userName!''}" autocomplete="off"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">用户密码</label>
            <div class="layui-input-inline">
                <input type="text" name="password" lay-verify="password" value="${sysUser.password!''}"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">手机号码</label>
            <div class="layui-input-inline">
                <input type="tel" name="tel" autocomplete="off" value="${sysUser.tel!''}" class="layui-input">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <#-- option 不为空时的操作 -->
            <#if option ??><#else>
                <button type="button" class="layui-btn" onclick="register()" lay-filter="demo1">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </#if>
        </div>
    </div>
</form>
</body>
<script>
    //layui 框架的基本使用
    layui.use(['layer', 'form', 'element', 'laypage', 'upload', 'tree', 'carousel', 'flow'], function () {
        var layer = layui.layer
            , form = layui.form
            , element = layui.element
            , $ = layui.jquery
            , laypage = layui.laypage
            , upload = layui.upload
            , tree = layui.tree
            , carousel = layui.carousel
            , flow = layui.flow;
    });

    //注册用户信息
    function register() {
        var formData = $("#userRegister").serialize();
        $.ajax({
            type: 'post',
            url: "/com/yzx/addUser",
            cache: false,
            data: formData,
            dataType: 'json',
            success: function (data) {
                var a = data;
                if (a.flag == true) {
                    layui.use(['layer', 'form'], function () {
                        var layer = layui.layer;
                        layer.msg('注册消息：' + a.msg);
                    });

                    //layui 中关闭父页面的弹出框
                    var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                    parent.layer.close(index); //再执行关闭

                    //layui 刷新父页面的 table 【list】
                    layui.use('table', function () {
                        //layUserListId 为父页面 table的 id
                        parent.layui.table.reload('layUserListId');
                    })
                } else {
                    layui.use(['layer', 'form'], function () {
                        var layer = layui.layer;
                        layer.msg('错误信息：' + a.msg);
                    });
                }
            },
            error: function () {
                layui.use(['layer', 'form'], function () {
                    var layer = layui.layer;
                    layer.msg('网络信息异常，请稍后再试！');
                });
            }
        })
    }
</script>
</html>