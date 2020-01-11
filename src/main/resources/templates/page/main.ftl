<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
     ${user} 来到主页！

     <@shiro.guest>
         您当前是游客，<a href="javascript:void(0);">登录</a>
     </@shiro.guest>
</body>
</html>