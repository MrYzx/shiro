<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<a href="/yzx/com/test/main">to main page</a>
<br/>
<a href="/logout">退出</a>
<br/>
<a href="/com/yzx/yonghu">yonghu</a>
<br/>
<a href="/com/yzx/juese">juese</a>
<br/>
<br/>
<a href="/com/yzx/employment">emp</a>
<br/>
<a href="/com/yzx/addUser">addUser</a>
<br/>
<a href="/com/yzx/pdfTest">pdfPrint</a>
<br/>
<h4>显示用户list 信息</h4>
<a href="/com/yzx/showList">显示go >></a><br/>
<h4>测试缓存</h4>
<a href="/com/yzx/cache/testCache">显示cache</a><br/>
<h4>layui 页面</h4>
<a href="/com/yzx/layuiPage">layui</a><br/>
<h4>调用存储过程 信息</h4>
<a href="/com/yzx/callUser">显示go >></a><br/>

<#--    <iframe src="http://localhost:8080/statics/PDF.js/web/viewer.html?file=/statics/PDF.js/web/compressed.tracemonkey-pldi-09.pdf"  width="1024" height="700"

    </iframe>-->
<iframe src="http://localhost:8080/statics/PDF.js/web/viewer.html?file=/com/yzx/showPdf" width="1024" height="700">
</iframe>

<iframe src="/com/yzx/showPdf" width="1024" height="700">
</iframe>
<#-- <div id="_div_embed" class="x-hidden" align="center" style="vertical-align: middle;width: 1000px;height: 500px;">
             <embed id="_embed" style="vertical-align: middle;margin:10px;width: 1000px;height: 500px;"type="application/pdf">
 </div>
<#-- <script>
     document.getElementById('_embed').src = '/com/yzx/showPdf';
 </script>-->

<#if things ??>
    <#list things as thing>
        ${thing}<br/>
    </#list>
</#if>
</body>
</html>
