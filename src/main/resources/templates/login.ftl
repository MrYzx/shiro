<!DOCTYPE html>
<html lang="zh_CN">
<head>
    <meta charset="UTF-8">
    <title>login</title>
    <#-- freemaker 引入公共页面-->
    <#include "common.ftl">
    <style>
        .form {
            background: rgba(255, 255, 255, 0.2);
            width: 400px;
            margin: 120px auto;
        }

        .fa {
            display: inline-block;
            top: 27px;
            left: 6px;
            position: relative;
            color: #ccc;
        }

        input[type="text"], input[type="password"] {
            padding-left: 26px;
        }

        .checkbox {
            padding-left: 21px;
        }

        .forma {
            color: rebeccapurple;
        }

        .forma:hover {
            color: whitesmoke;
        }
    </style>
</head>
<body style="background: url('/statics/images/login/2.png'); background-size: cover;">
<#-- 用户登录页面表单-->
<div class="container">
    <form id="form_login">
        <div class="form row">
            <div class="form-horizontal col-md-offset-3" id="login_form">
                <h3 class="form-title">LOGIN</h3>
                <div class="col-md-9">
                    <div class="form-group">
                        <i class="fa fa-user fa-lg"></i>
                        <input class="form-control required" type="text" placeholder="Username" id="username"
                               name="username" autofocus="autofocus" maxlength="20"/>
                    </div>
                    <div class="form-group">
                        <i class="fa fa-lock fa-lg"></i>
                        <input class="form-control required" type="password" placeholder="Password" id="password"
                               name="password" maxlength="8"/>
                    </div>
                    <div class="form-group">
                        <i class="fa fa-safari fa-lg"></i>
                        <input class="form-control required" type="text" placeholder="请输入验证码" id="verifyCode"
                               name="verifyCode" maxlength="8"/>
                    </div>
                    <div class="form-group">
                        <img src="/yzx/common/captcha" id="imgCode" width="100px" height="32px"/>
                        <a href="#" onclick="refrash()">
                            <i class="fa fa-refresh" aria-hidden="true" style="top:auto;color: yellow;">&nbsp;&nbsp;看不清，换一个</i>
                        </a>
                    </div>
                    <div class="form-group">
                        <label class="checkbox">
                            <input type="checkbox" name="rememberMe" value="1"/><span style="color: white">记住我</span>
                        </label>
                    </div>
                    <div class="form-group col-md-offset-9" style="margin-bottom: 20px;">
                        <a class="forma" onclick="toRegister()" style="text-decoration: none;"
                           href="javascript:void(0);">注册 &nbsp;</a>|
                        <a class="forma" style="text-decoration: none;"> &nbsp;忘记密码</a>
                        <button type="button" class="btn btn-success pull-right" name="submit" onclick="login()">登录
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
</body>
<script>
    //刷新验证码图片
    function refrash() {
        document.getElementById("imgCode").setAttribute("src", "/yzx/common/captcha?" + Math.random());
    }

    //用户的登录
    function login() {
        //异步验证用户的登录
        var data = $("#form_login").serialize();
        $.ajax({
            type: 'post',
            url: "/com/yzx/login",
            cache: false,
            data: data,
            dataType: 'json',
            success: function (data) {
                var a = data;
                if (a.flag == true) {
                    //跳转到主页面
                    window.location.href = "/com/yzx/main";
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
                    layer.msg('网络异常，请稍后再试！');
                });
            }
        })
    }

    //登录时基本信息的验证
    function validate() {

    }

    //用户名的校验，看是否重复
    function validateUserName() {

    }

    //到 index 页面
    function toIndexPage() {

    }

    //到注册页面
    function toRegister() {
        window.location.href = "/com/yzx/register";
    }

    //到忘记密码页面
    function toForget() {

    }
</script>
</html>