<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!--Top-->
	<nav id="top">
		<div class="container">
			<div class="row">
				<div class="col-xs-6">
					<select class="language">
						<option value="English" selected>Chinese</option>
					</select>
					<select class="currency">
						<option value="USD" selected>RMB</option>
					</select>
				</div>
				<div class="col-xs-6">
					<ul class="top-link">
						<s:if test="#session.existUser == null">
						<li><a href="${pageContext.request.contextPath }/user_loginPage.action"><span class="glyphicon glyphicon-user"></span>登入 &amp; 注册</a></li>
						</s:if>
						<s:else>
						<li><span class="glyphicon glyphicon-user"></span>欢迎：<s:property value="#session.existUser.name"/><a href="${pageContext.request.contextPath }/user_loginOut.action">&nbsp;&nbsp;退出</a> </li>
						</s:else>
						<li><a href="contact.html">我的订单</a></li>
						<li><a href="contact.html"><span class="glyphicon glyphicon-shopping-cart"></span> 购物车</a></li>
					</ul>
				</div>
			</div>
		</div>
	</nav>
	<!--Header-->
	<header class="container">
		<div class="row">
			<div class="col-md-4">
				<div id="logo"><img src="${pageContext.request.contextPath}/images/logo.png" /></div>
			</div>
			<div class="col-md-8">
				<form class="form-search">  
					<input type="text" class="input-medium search-query">  
					<button type="submit" class="btn"><span class="glyphicon glyphicon-search"></span></button>  
				</form>
			</div>
			
		</div>
	</header>
	<!--Navigation-->
    <nav id="menu" class="navbar">
		<div class="container">
			<div class="navbar-header"><span id="heading" class="visible-xs">Categories</span>
			  <button type="button" class="btn btn-navbar navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse"><i class="fa fa-bars"></i></button>
			</div>
			<div class="collapse navbar-collapse navbar-ex1-collapse">
				<ul class="nav navbar-nav">
					<li><a href="${pageContext.request.contextPath }/index.action">首页</a></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">笔记本电脑</a>
						<div class="dropdown-menu" >
							<div class="dropdown-inner">
								<ul class="list-unstyled">
									<li><a href="category.html">Window</a></li>
									<li><a href="category.html">MacBook</a></li>
								</ul>
							</div>
						</div>
                    </li>
					<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">手机</a>
						<div class="dropdown-menu" style="margin-left: -25.625px;">
							<div class="dropdown-inner">
								<ul class="list-unstyled">
									<li><a href="category.html">Iphone</a></li>
									<li><a href="category.html">Samsung</a></li>
									<li><a href="category.html">XiaoMi</a></li>
									<li><a href="category.html">Huawei</a></li>
									<li><a href="category.html">Meizu</a></li>
								</ul>
							</div>
						</div>
					</li>
					<s:iterator var="category" value="#session.cList">
						<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"><s:property value="#category.cname"/></a>
						<div class="dropdown-menu" >
							<div class="dropdown-inner">
								<ul class="list-unstyled">
									
								</ul>
							</div>
						</div>
                    </li>
					</s:iterator>
					<li><a href="category.html">ALL</a></li>
				</ul>
			</div>
		</div>
	</nav>