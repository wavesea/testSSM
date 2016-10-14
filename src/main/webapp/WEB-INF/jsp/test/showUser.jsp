<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="common/tag.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<title>查看用户列表</title>
	<%@include file="common/head.jsp"%>
  </head>
  
  <body>
    <div class="container">
		<div class="panel panel-default">
			<div class="panel-heading text-center">
				<h2>用户列表</h2>
			</div>
			<div class="panel-body">
				<table class="table table-hover">
					<thead>
						<tr>
							<td>用户ID</td>
							<td>用户名</td>
							<td>年龄</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="user" items="${userList}">
							<tr>
								<td>${user.id}</td>
								<td>${user.userName}</td>
								<td>${user.age}</td>
								<%-- <td><fmt:formatDate value="${user.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td> --%>
							</tr>
						</c:forEach>
					</tbody>
				</table>

			</div>
		</div>
	</div>
  </body>
</html>
