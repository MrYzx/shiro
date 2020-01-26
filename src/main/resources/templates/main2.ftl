<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Layui HTML</title>
    <#-- freemaker 引入公共页面-->
    <#include "common.ftl">
</head>
<body class="layui-layout-body">
    <div class="layui-layout layui-layout-admin">
        <#-- layui 头部模块 -->
        <div class="layui-header">
            <div class="layui-logo"><i class="layui-icon layui-icon-release" style="font-size: 30px; color: #fff1fd;"></i>
                运营后台管理系统
            </div>
            <!-- 头部区域（可配合layui已有的水平导航） -->
            <ul class="layui-nav layui-layout-right">
                <li class="layui-nav-item" style="width: 120px;">
                    <a href="javascript:;">
                        个人中心
                    </a>
                    <dl class="layui-nav-child">
                        <dd><a id="userInfo">基本资料<span class="layui-badge-dot layui-bg-orange" /></a></dd>
                        <dd><a href="/logout">退出</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item" style="width: 120px;">
                    <a href="/com/yzx/index">大厅首页</a>
                </li>
                <li class="layui-nav-item">
                    <@shiro.user>
                        欢迎【<@shiro.principal property="userName"/>】
                    </@shiro.user>
                </li>
            </ul>
        </div>

        <#-- layui 侧边栏模块 -->
        <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧垂直导航区域-->
            <ul class="layui-nav layui-nav-tree" lay-filter="test">
                <li class="layui-nav-item">
                    <a class="" href="javascript:;">管理员管理</a>
                    <dl class="layui-nav-child">
                        <dd>
                            <a href="javascript:;" data-id="1" data-title="用户管理" data-url="/com/yzx/userList"
                               class="site-demo-active" data-type="tabAdd">用户管理</a></dd>
                        <dd><a href="javascript:;" data-id="2" data-title="请假管理" data-url="/com/yzx/holiday/ListPage"
                               class="site-demo-active" data-type="tabAdd">请假管理</a></dd>
                        <dd><a href="javascript:;" data-id="3" data-title="请假流程图" data-url="/com/yzx/holiday/viewHolidayImg"
                               class="site-demo-active" data-type="tabAdd">请假流程图</a></dd>
                        <dd><a href="javascript:;" data-id="4" data-title="请假记录" data-url="/com/yzx/holiday/queryHistoryHolidayList"
                               class="site-demo-active" data-type="tabAdd">请假记录</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">交易管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" data-id="3" data-title="存款列表"
                               　data-url="index.php?&a=adminList" class="site-demo-active" data-type="tabAdd">存款列表</a>
                        </dd>
                        <dd><a href="javascript:;">代付列表</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">系统管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" data-id="2" data-title="接口管理" data-url="/swagger-ui.html"
                               class="site-demo-active" data-type="tabAdd">接口管理</a>
                        </dd>
                        <dd><a href="javascript:;" data-id="3" data-title="支付API设置" class="site-demo-active" data-url="index.php?&a=adminList" data-type="tabAdd">支付API设置</a></dd>
                        <dd><a href="javascript:;">公告设置</a></dd>
                        <dd><a href="javascript:;">控制台</a></dd>
                </li>
            </ul>
        </div>
    </div>

        <!--tab标签-->
        <div class="layui-tab" lay-filter="demo" lay-allowclose="true" style="margin-left: 200px;">
            <ul class="layui-tab-title">
                <li class="layui-this">首页</li>
            </ul>
            <div class="layui-tab-content">
                <div class="layui-tab-item layui-show">首页内容...</div>
            </div>
        </div>

        <#-- layui 页脚模块-->
        <div class="layui-footer">
            <!-- 底部固定区域 -->
            © layui.com - yinzhixiong 学习
        </div>
    </div>
<script>
    layui.use(['element', 'layer', 'jquery'], function () {
        var element = layui.element;
        // var layer = layui.layer;
        var $ = layui.$;
        // 配置tab实践在下面无法获取到菜单元素
        $('.site-demo-active').on('click', function () {
            var dataid = $(this);
            //这时会判断右侧.layui-tab-title属性下的有lay-id属性的li的数目，即已经打开的tab项数目
            if ($(".layui-tab-title li[lay-id]").length <= 0) {
                //如果比零小，则直接打开新的tab项
                active.tabAdd(dataid.attr("data-url"), dataid.attr("data-id"), dataid.attr("data-title"));
            } else {
                //否则判断该tab项是否以及存在
                var isData = false; //初始化一个标志，为false说明未打开该tab项 为true则说明已有
                $.each($(".layui-tab-title li[lay-id]"), function () {
                    //如果点击左侧菜单栏所传入的id 在右侧tab项中的lay-id属性可以找到，则说明该tab项已经打开
                    if ($(this).attr("lay-id") == dataid.attr("data-id")) {
                        isData = true;
                    }
                })
                if (isData == false) {
                    //标志为false 新增一个tab项
                    active.tabAdd(dataid.attr("data-url"), dataid.attr("data-id"), dataid.attr("data-title"));
                }
            }
            //最后不管是否新增tab，最后都转到要打开的选项页面上
            active.tabChange(dataid.attr("data-id"));
        });

        var active = {
            //在这里给active绑定几项事件，后面可通过active调用这些事件
            tabAdd: function (url, id, name) {
                //新增一个Tab项 传入三个参数，分别对应其标题，tab页面的地址，还有一个规定的id，是标签中data-id的属性值
                //关于tabAdd的方法所传入的参数可看layui的开发文档中基础方法部分
                element.tabAdd('demo', {
                    title: name,
                    content: '<iframe data-frameid="' + id + '" scrolling="auto" frameborder="0" src="' + url + '" style="width:100%;height:99%;"></iframe>',
                    id: id //规定好的id
                })
                FrameWH();  //计算ifram层的大小
            },
            tabChange: function (id) {
                //切换到指定Tab项
                element.tabChange('demo', id); //根据传入的id传入到指定的tab项
            },
            tabDelete: function (id) {
                element.tabDelete("demo", id);//删除
            }
        };

        function FrameWH() {
            var h = $(window).height();
            $("iframe").css("height",h+"px");
        }
    });

    //下载文件
    function downLoad() {
        alert("aaa");
        window.open("/com/yzx/holiday/download");
    }
</script>
</body>
</html>