<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>请假评论信息</title>
    <#include "../../common.ftl">
</head>
<body>
<#if comments?size gt 0>
    <#list comments as comment>
        <div style="margin-top: 10px;"></div>
        <div style="background-color: #e81919;color: white;">
            审核人ID：${comment.id}<br>
            审核人：${comment.userId}<br>
            审核意见：${comment.fullMessage}<br>
        </div>
    </#list>
<#else>
    <div style="text-align: center;margin-top: 30px;">
        暂无审核信息
    </div>
</#if>
</body>
</html>