<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<!-- 登录 注册 购物车... -->
<div class="container-fluid">
	<div class="col-md-4">
		<img src="/img/icon_yue.png" />
		<span style="color: #1d1d1d;font-size: 30px;text-align: center">微淘商城</span>
	</div>

	<div class="col-md-5">
		<img src="/img/header.png" />
	</div>
	<div class="col-md-3" style="padding-top:20px">
		<ol class="list-inline">
			<c:if test="${empty user.username}">
				<li><a href="/account/login.html">登录</a></li>
				<li><a href="/account/register.html">注册</a></li>
			</c:if>

			<c:if test="${!empty user.name}">
				<li><span style="color: red">hi,${user.username}</span></li>
			</c:if>
			<li><a href="/cart">购物车</a></li>
			<li><a href="order_list.jsp">我的订单</a></li>
		</ol>
	</div>
</div>

<!-- 导航条 -->
<div class="container-fluid">
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="/index">首页</a>
			</div>

			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav" id="headerNav">
					<%--<li class="active"><a href="product_list.htm">手机数码<span class="sr-only">(current)</span></a></li>--%>
					<%--<li><a href="#">电脑办公</a></li>--%>
					<%--<li><a href="#">电脑办公</a></li>--%>
					<%--<li><a href="#">电脑办公</a></li>--%>
				</ul>
				<form class="navbar-form navbar-right" role="search">
					<div class="form-group">
						<input type="text" class="form-control" placeholder="Search">
					</div>
					<button type="submit" class="btn btn-default">Submit</button>
				</form>
			</div>
		</div>
		<script type="text/javascript">
			$(function(){
			    var flag;
			    var content = "";
			   $.post(
			     "/category/list",
				   function(data){
			         flag = data.flag;
					if (flag == true){
//					    alert(data.data.length);
					    for (var i=0;i<data.data.length;i++){
							content+="<li><a href='/list?categoryid="+data.data[i].cid+"'>"+data.data[i].cname+"</a></li>"
						}
						$("#headerNav").html(content);
					}else {
					    alert("加载失败，请重新加载!")
					}
				   },
				   "json"
			   );
			});
		</script>
	</nav>
</div>