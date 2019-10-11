<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
欢迎:${name}
<hr/>
自我介绍:${content}
<hr/>
水果列表
<ul>
    <#list names as n>
        <li>${n}</li>
    </#list>
</ul>
</body>
</html>