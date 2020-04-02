<%--
  Created by IntelliJ IDEA.
  User: 马梓翔
  Date: 2020/3/30
  Time: 0:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>上传文件</title>
</head>
<body>
<form action="fileToAnother" method="post" enctype="multipart/form-data">
    <input type="file" value="上传文件" name="file"><br/>
    <input type="submit" value="提交">
</form>
</body>
</html>
