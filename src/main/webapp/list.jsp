<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%--%>
    <%--String path = request.getContextPath();--%>
    <%--String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";--%>
<%--%>--%>
<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head>
    <%--<base href="<%=basePath%>"/>--%>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <!-- 使用Edge最新的浏览器的渲染方式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
    width: 默认宽度与设备的宽度相同
    initial-scale: 初始的缩放比，为1:1 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>用户信息管理系统</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
    <style type="text/css">
        td, th {
            text-align: center;
        }
    </style>
</head>
<body>
<div class="container">
    <h3 style="text-align: center">用户信息列表</h3>
    <table border="1" class="table table-bordered table-hover">
        <tr class="success">
            <th>编号</th>
            <th>姓名</th>
            <th>性别</th>
            <th>年龄</th>
            <th>籍贯</th>
            <th>QQ</th>
            <th>邮箱</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${pageBean.userList}" var="user" varStatus="status">
            <tr>
                <td>${status.index+1}</td>
                <td>${user.name}</td>
                <td>${user.sex}</td>
                <td>${user.age}</td>
                <td>${user.address}</td>
                <td>${user.qq}</td>
                <td>${user.email}</td>
                <td><a class="btn btn-default btn-sm" href="QueryByIdServlet?userId=${user.id}">修改</a>&nbsp;<a
                        class="btn btn-default btn-sm" href="DeleteServlet?userId=${user.id}">删除</a></td>
            </tr>
        </c:forEach>
        <tr>
            <td colspan="8" align="center">
                <!--  每页显示的数量-->
                <select name="pageSize" id="" onchange="findUserByPage(${pageBean.currentPage},this.value)">
                    <option value="5" ${pageBean.pageSize==5?"selected='selected'":""}>5/页</option>
                    <option value="10" ${pageBean.pageSize==10?"selected='selected'":""}>10/页</option>
                    <option value="15" ${pageBean.pageSize==15?"selected='selected'":""}>15页</option>
                </select>
            </td>
        </tr>
        <tr>
            <td colspan="8" align="center">
                <nav aria-label="Page navigation">
                    <ul class="pagination">
                        <!-- 上一页的值必须大于0才能显示上一页 -->
                        <c:if test="${requestScope.pageBean.prePage>0}">
                            <li>
                                <a href="FindUserByPageServlet?currentPage=${pageBean.prePage}&pageSize=${pageBean.pageSize}"
                                   aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                        </c:if>
                        <c:forEach begin="1" end="${pageBean.totalPage}" var="i">
                            <li ${pageBean.currentPage==i?"class='active'":""}>
                                <a href="FindUserByPageServlet?currentPage=${i}&pageSize=${pageBean.pageSize}">${i}</a>
                            </li>
                        </c:forEach>
                        <c:if test="${requestScope.pageBean.nextPage<=pageBean.totalPage}">
                            <li>
                                <a href="FindUserByPageServlet?currentPage=${pageBean.nextPage}&pageSize=${pageBean.pageSize}"
                                   aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                        </c:if>
                    </ul>
                </nav>
            </td>
        </tr>
        <tr>
            <td colspan="8" align="center"><a class="btn btn-primary" href="add.jsp">添加联系人</a></td>
        </tr>
    </table>
</div>
</body>
<script>
    function findUserByPage(currentPage, pageSize) {
        location.href = "FindUserByPageServlet?currentPage="
            + currentPage + "&pageSize=" + pageSize;
    }
</script>
</html>
