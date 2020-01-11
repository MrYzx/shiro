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
                <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
                <ul class="layui-nav layui-nav-tree"  lay-filter="test">
                    <li class="layui-nav-item layui-nav-itemed">
                        <a href="javascript:;">用户管理</a>
                        <dl class="layui-nav-child">
                            <dd><a href="javascript:;">待办事项</a></dd>
                            <dd><a href="javascript:;">工作请假</a></dd>
                            <dd><a href="javascript:;">历史办结</a></dd>
                            <dd><a href="">超链接</a></dd>
                        </dl>
                    </li>
                    <li class="layui-nav-item"><a href="">云市场</a></li>
                    <li class="layui-nav-item"><a href="">发布商品</a></li>
                </ul>
            </div>
        </div>
        <#-- layui 主体模块 -->
        <div class="layui-body">
            <!-- 内容主体区域 -->
            <div style="padding: 15px;">
                <div class="layui-tab">
                    <ul class="layui-tab-title">
                        <li class="layui-this">请假</li>
                        <li>请假审批</li>
                        <li>请假历史</li>
                        <li>商品管理</li>
                        <li>订单管理</li>
                        <li>用户管理</li>
                    </ul>
                    <div class="layui-tab-content">
                        <#-- 请假模块 -->
                        <div class="layui-tab-item layui-show">
                            <blockquote class="layui-elem-quote layui-text" style="text-align:center;">
                                <i class="layui-icon layui-icon-face-smile" style="font-size: 15px; color: red; ">
                                    温馨提示：&nbsp;请假时请将基本信息填写完整，否则请假将无法完成！
                                </i>
                            </blockquote>
                            <form class="layui-form" action="">
                                <div class="layui-form-item">
                                    <div class="layui-inline">
                                        <label class="layui-form-label">多规则验证</label>
                                        <div class="layui-input-inline">
                                            <input type="text" name="number" lay-verify="required|number" autocomplete="off" class="layui-input">
                                        </div>
                                    </div>
                                    <div class="layui-inline">
                                        <label class="layui-form-label">验证日期</label>
                                        <div class="layui-input-inline">
                                            <input type="text" name="date" id="date" lay-verify="date" placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input">
                                        </div>
                                    </div>
                                </div>
                                <div class="layui-form-item">

                                    <div class="layui-input-block">
                                        <button type="button" class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
                                        <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                                    </div>
                                </div>
                            </form>
                           <a href="/com/yzx/holiday/completeTask">请假</a>
                        </div>
                        <#-- 请假记录-->
                        <div class="layui-tab-item">
                            <table class="layui-hide" id="layListId" lay-filter="layList"></table>
                            <script type="text/html" id="barDemo">
                                <button type="button" class="layui-btn layui-btn-sm layui-btn-normal" lay-event="maintainRecord">保养记录</button>
                                <button type="button" class="layui-btn layui-btn-sm layui-btn-warm" lay-event="repairRecord">维修记录</button>
                            </script>
<#--                            <script>
                                layui.use('table', function() {
                                    var table = layui.table;
                                    table.render({
                                        elem: '#layListId',
                                        id: 'layTableId',
                                        url: '/com/yzx/userList',
                                        title: '维修保养报',
                                        cellMinWidth: 100,
                                        cols: [
                                            [{
                                                type: 'checkbox',
                                                fixed: 'left'
                                            }, {
                                                type: 'numbers',
                                                fixed: 'left'
                                            }, {
                                                field: 'deviceNumber',
                                                title: '设备编号',
                                                minWidth: '120',
                                            }, {
                                                field: 'deviceName',
                                                title: '设备名称',
                                                minWidth: '120'
                                            }, {
                                                field: 'deviceType',
                                                title: '设备型号'
                                            }, {
                                                title: '操作',
                                                minWidth: '180',
                                                align: 'center',
                                                toolbar: '#barDemo'
                                            }]
                                        ],
                                        page: true
                                    });
                                    //监听工具条
                                    table.on('tool(layList)', function(obj) {
                                        var data = obj.data; //获得当前行数据
                                        switch(obj.event) {
                                            case 'maintainRecord':
                                                maintainRecord();
                                                break;
                                            case 'repairRecord':
                                                repairRecord();
                                                break;
                                            default:
                                                break;
                                        }
                                    });
                                    var $ = layui.$,
                                        active = {
                                            reload: function() {
                                                var deviceNumber = $("#deviceNumber").val();

                                                //执行重载
                                                table.reload('layTableId', {
                                                    page: {
                                                        curr: 1
                                                    },
                                                    where: {
                                                        deviceNumber: deviceNumber
                                                    }
                                                });
                                            },
                                            getCheckData: function() { //获取选中数据
                                                var checkStatus = table.checkStatus('layTableId'),
                                                    data = checkStatus.data;
                                                layer.alert(JSON.stringify(data));
                                            },
                                            getCheckLength: function() { //获取选中数目
                                                var checkStatus = table.checkStatus('layTableId'),
                                                    data = checkStatus.data;
                                                layer.msg('选中了：' + data.length + ' 个');
                                            },
                                            isAll: function() { //验证是否全选
                                                var checkStatus = table.checkStatus('layTableId');
                                                layer.msg(checkStatus.isAll ? '全选' : '未全选')
                                            }

                                        };
                                    $('.layui-btn').on('click', function() {
                                        var type = $(this).data('type');
                                        active[type] && active[type].call(this);
                                    });

                                    function maintainRecord() {
                                        layer.msg("保养记录");
                                    };

                                    function repairRecord() {
                                        layer.msg("维修记录");

                                    };
                                });
                            </script>-->
                        </div>
                        <div class="layui-tab-item">

                            <div class="layui-collapse">
                                <div class="layui-colla-item">
                                    <h2 class="layui-colla-title">杜甫</h2>
                                    <div class="layui-colla-content layui-show">内容区域</div>
                                </div>
                                <div class="layui-colla-item">
                                    <h2 class="layui-colla-title">李清照</h2>
                                    <div class="layui-colla-content ">内容区域</div>
                                </div>
                                <div class="layui-colla-item">
                                    <h2 class="layui-colla-title">鲁迅</h2>
                                    <div class="layui-colla-content ">内容区域</div>
                                </div>
                            </div>

                        </div>
                        <div class="layui-tab-item">
                            <div class="layui-carousel" id="carouse">
                                <div carousel-item>
                                    <div>条目1</div>
                                    <div>条目2</div>
                                    <div>条目3</div>
                                    <div>条目4</div>
                                    <div>条目5</div>
                                </div>
                            </div>
                            <table class="layui-table">
                                <thead>
                                <tr>
                                    <th>昵称</th>
                                    <th>加入时间</th>
                                    <th>签名</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td>贤心</td>
                                    <td>2016-11-29</td>
                                    <td>人生就像是一场修行</td>
                                </tr>
                                <tr>
                                    <td>许闲心</td>
                                    <td>2016-11-28</td>
                                    <td>于千万人之中遇见你所遇见的人，于千万年之中，时间的无涯的荒野里…</td>
                                </tr>
                                </tbody>
                            </table>

                        </div>

                        <div class="layui-tab-item">
                            <div id="test1">
                            </div>
                            <button type="button" class="layui-btn" id="uploadFile">
                                <i class="layui-icon">&#xe67c;</i>上传图片
                            </button>
                            <div id="treeTest"></div>
                        </div>
                        <#-- 用户列表模块-->
                        <div class="layui-tab-item">
                            <table class="layui-hide" id="userListId" lay-filter="userListId"></table>
                            <script type="text/html" id="options">
                                <button type="button" class="layui-btn layui-btn-sm layui-btn-normal" lay-event="viewUser">查看</button>
                                <button type="button" class="layui-btn layui-btn-sm layui-btn-warm" lay-event="removeUser">删除</button>
                            </script>
                            <script type="text/html" id="toolbarDemo">
                                <div class="layui-btn-container">
                                    <button class="layui-btn layui-btn-sm" lay-event="addUser" >
                                        <i class="layui-icon layui-icon-add-1"></i> 添加用户
                                    </button>
                                </div>
                            </script>
                            <script>
                                layui.use('table', function() {
                                    var table = layui.table;
                                    table.render({
                                        elem: '#userListId',
                                        id: 'layUserListId',
                                        url: '/com/yzx/userList2',
                                        title: '用户信息列表',
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
                                                field: 'userName',
                                                title: '用户名',
                                                align:"center",
                                                minWidth: '120',
                                            }, {
                                                field: 'tel',
                                                title: '电话',
                                                align:"center",
                                                minWidth: '120'
                                            }, {
                                                field: 'locked',
                                                //edit:true, 设置表元素是否为可编辑状态
                                                //sort:true, 设置当前列是否排序
                                                //hide:true,设置当前列是否隐藏
                                                align:"center",
                                                title: '是否锁定'
                                            }, {
                                                title: '操作',
                                                minWidth: '180',
                                                align: 'center',
                                                toolbar: '#options'
                                            }]
                                        ]
                                    });
                                    //监听工具条事件
                                    table.on('tool(userListId)', function(obj) {
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
                                    table.on('toolbar(userListId)', function(obj){
                                        //var checkStatus = table.checkStatus(obj.config.id); //获取选中行状态
                                        switch(obj.event){
                                            case 'addUser':
                                                addUser();
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
                                        layer.msg("保养记录ww");
                                    };

                                    //添加用户信息
                                    function addUser() {
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
                        </div>
                    </div>
                </div>

            </div>
        </div>
        <#-- layui 页脚模块-->
        <div class="layui-footer">
            <!-- 底部固定区域 -->
            © layui.com - yinzhixiong 学习
        </div>
    </div>
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

        //TODO  do something
        //监听提交
        form.on('submit(submitForm)', function(data){
            layer.msg(JSON.stringify(data.field));
            return false;
        });

        $('#userInfo').on('click', function () {
            // layer.open({
            //     type: 1,
            //     content: '传入任意的文本或html'
            // });
            //
            layer.open({
                type: 2,
                content: 'http://sentsin.com' //这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
            });
        });

        //触发事件
        var active = {
            tabChange: function(){
                //切换到指定Tab项
                element.tabChange('demo', '22'); //切换到：用户管理
            }
        };

        var tablePage = laypage.render({
            elem: 'test1'
            ,count: 1000
            ,limit: 100
            ,limits: [100, 300, 500]
        });

        //执行实例
        var uploadInst = upload.render({
            elem: '#uploadFile' //绑定元素
            ,url: '/upload/' //上传接口
            ,done: function(res){
                layer.alert("回调完毕");
            }
            ,error: function(){
                layer.alert("回调异常");
            }
        });

        //渲染
        var treeTest = tree.render({
            elem: '#treeTest'
            ,data: [{
                title: '成都' //一级菜单
                ,children: [{
                    title: '金牛区' //二级菜单
                    ,children: [{
                        title: '环球广场' //三级菜单
                        ,children: [{
                            title: '爱达乐' //三级菜单
                            //…… //以此类推，可无限层级
                        }]
                    }]
                }]
            },{
                title: '重庆'
            }]
            ,click: function(obj){
                console.log(obj.data); //得到当前点击的节点数据
                console.log(obj.state); //得到当前节点的展开状态：open、close、normal
                console.log(obj.elem); //得到当前节点元素
                console.log(obj.data.children); //当前节点下是否有子节点
            }
            ,oncheck: function(obj){
                console.log(obj.data); //得到当前点击的节点数据
                console.log(obj.checked); //得到当前节点的展开状态：open、close、normal
                console.log(obj.elem); //得到当前节点元素
            }
        });

        carousel.render({
            elem: '#carouse'
            ,width: '100%' //设置容器宽度
            ,arrow: 'always' //始终显示箭头
            //,anim: 'updown' //切换动画方式
        });

        /*  <ul class="flow-default" id="flowTest"></ul>*/
        flow.load({
            elem: '#flowTest' //指定列表容器
            ,scrollElem: '.menu-botR'
            ,done: function(page, next) { //到达临界点（默认滚动触发），触发下一页
                //模拟数据插入
                setTimeout(function(){
                    var lis = [];
                    for(var i = 0; i < 3; i++){
                        lis.push('<li>'+ ( (page-1)*8 + i + 1 ) +'</li>')
                    }

                    //执行下一页渲染，第二参数为：满足“加载更多”的条件，即后面仍有分页
                    //pages为Ajax返回的总页数，只有当前页小于总页数的情况下，才会继续出现加载更多
                    next(lis.join(''), page < 4 ); //假设总页数为 10
                }, 500);
            }
            ,end:'到底了！'
        });

        //按屏加载图片
        /*<div class="site-demo-flow" id="flowImageTest">
              <img lay-src="https://yqfile.alicdn.com/dbebfc5a62245eb4d7b911f73f1bb9721fa0ee1c.png">
          </div>
         */
        flow.lazyimg({
            elem: '#flowImageTest img'
        });
    });
</script>
</html>