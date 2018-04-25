<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8"/>
    <title>Title</title>
    <meta content="text/html;charset=UTF-8"/>
    <meta name="viewport" content="width=device-width,initial-scale=1"/>
    <link href="../../static/js/bootstrap/css/bootstrap.css" rel="stylesheet"/>
</head>
<body>
<table class="table table-bordered">
<#list users as user >
<tr>
    <td>${user_index}</td>
    <td>${user.id!}</td>
    <td>${user.email!}</td>
    <td>${user.name!}</td>
</tr>
</#list>
</table>
</body>
</html>