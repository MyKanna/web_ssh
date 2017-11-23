<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta name="description" content="">
    <meta name="author" content="">
	
    <title>Mobile Shop</title>
	
    <!-- Bootstrap Core CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css"  type="text/css">
	
	<!-- Custom CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
	
	
	<!-- Custom Fonts -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/font-awesome/css/font-awesome.min.css"  type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/fonts/font-slider.css" type="text/css">
	
	<!-- jQuery and Modernizr-->
	<script src="${pageContext.request.contextPath}/js/jquery-2.1.1.js"></script>
	
	<!-- Core JavaScript Files -->  	 
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	
	<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="js/html5shiv.js"></script>
        <script src="js/respond.min.js"></script>
    <![endif]-->
    
    
<script >
	function checkForm(){
	//校验用户名
	var username = document.getElementById("username").value;
	if(username==null ||username ==''){
		alert("用户名不能为空");
		return false;
	}

	//校验密码
	var password = document.getElementById("password").value;
	if(password==null ||password ==''){
		alert("密码不能为空");
		return false;
	}
	//检验确定密码
	var repassword = document.getElementById("repassword").value;
	if(repassword != password){
	alert("两次密码不一致");
	return false;
	 }
	 
	 var checkbox = document.getElementById("agree");
	if(!checkbox.checked){
	alert("请同意协议");
	return false;
	}
	}

	function checkUsernama(){
		var username = document.getElementById("username").value;
		//创建异步交互对象
		 var xhr = createAjax();
		 var url = "${pageContext.request.contextPath}/user_findByUsername.action?time"+new Date().getTime()+"&username="+username;
		//设置监听
		xhr.onreadystatechange = function(){
			if(xhr.readyState==4){
				if(xhr.status==200){
					document.getElementById("usernameSpan").innerHTML = xhr.responseText;
				}
			}
		}
		//打开链接
		xhr.open("GET",url,true);
		//发送
		xhr.send(null);
	}
	
	function createAjax(){

        var xmlhttp;
        if (window.XMLHttpRequest){// code for IE7+, Firefox, Chrome, Opera, Safari
          xmlhttp=new XMLHttpRequest();
        }
        else{// code for IE6, IE5
          xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
        }
        return xmlhttp;
    }
	
	function _change() {
	/*
	1. 得到img元素
	2. 修改其src为/day11_3/VerifyCodeServlet
	*/
	var imgEle = document.getElementById("img");
	imgEle.src = "${pageContext.request.contextPath}/user_VerifyCode?a=" + new Date().getTime();
}


</script>

</head>

<body>
	<%@include file="menu.jsp" %>
	<!--//////////////////////////////////////////////////-->
	<!--///////////////////Account Page///////////////////-->
	<!--//////////////////////////////////////////////////-->
	<div id="page-content" class="single-page">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<ul class="breadcrumb">
						<li><a href="index.html">Home</a></li>
						<li><a href="account.html">账号</a></li>
					</ul>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="heading"><h2>登入</h2><s:actionerror/></div>
					<form name="form1" id="ff1" method="post" action="${pageContext.request.contextPath }/user_login.action">
						<div class="form-group">
							<input type="text" class="form-control" placeholder="用户名" name="username" id="lusername" required>
						</div>
						<div class="form-group">
							<input type="password" class="form-control" placeholder="密&nbsp;&nbsp;&nbsp;码" name="password" id="lpassword" required>
						</div>
						<div class="form-group">
						<input type="text" class="form-control" placeholder="验证码" name="verifyCode" id="verifyCode" maxlength="4" required>
						<a href="javascript:_change()" title="点击换一张"><img id="img" src="${pageContext.request.contextPath}/user_VerifyCode"/>
						</a>
						</div>
						<button type="submit" class="btn btn-1" name="login" id="login">登入</button>
						<a href="#">&nbsp;&nbsp;&nbsp;忘记密码？</a>
					</form>
				</div>
				<div class="col-md-6">
					<div class="heading"><h2>没有账户？ 创建一个新账户！</h2></div>
					<form name="form2" id="ff2" method="post" action="${pageContext.request.contextPath }/user_regist.action" onsubmit="return checkForm();">
						<div class="form-group">
							<input type="text" class="form-control" placeholder="用户名*" name="username" id="username"  onblur="checkUsernama()"  required >
							<span id="usernameSpan"></span>
						</div>
						<div class="form-group">
							<input type="text" class="form-control" placeholder="名&nbsp;&nbsp;&nbsp;字" name="name" id="name"  required >
						</div>
						<div class="form-group">
							<input type="text" class="form-control" placeholder="电&nbsp;&nbsp;&nbsp;话" name="phone" id="phone" maxlength="11" required >
						</div>
						<div class="form-group">
							<input type="text" class="form-control" placeholder="地&nbsp;&nbsp;&nbsp;址" name="address" id="address"  required >
						</div>
						<div class="form-group">
							<input type="email" class="form-control" placeholder="邮&nbsp;&nbsp;&nbsp;箱*" name="email" id="email" required>
						</div>
						<div class="form-group">
							<input type="password" class="form-control" placeholder="密&nbsp;&nbsp;&nbsp;码*" name="password" id="password" maxlength="22" required>
						</div>
						<div class="form-group">
							<input type="password" class="form-control" placeholder="重复密码*" name="repassword" id="repassword" maxlength="22" required>
						</div>
						<div class="form-group">
							<input name="agree" id="agree" type="checkbox" value="agree" >同意网站协议
						</div>
						<button type="submit" class="btn btn-1" >创建</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	<footer>
		<div class="container">
			<div class="wrap-footer">
				<div class="row">
					<div class="col-md-3 col-footer footer-1">
						<img src="images/logofooter.png" />
						<p>Lorem Ipsum is simply dummy text of the printing and typesetting industry.Lorem Ipsum is simply dummy text of the printing and typesetting industry.</p>
					</div>
					<div class="col-md-3 col-footer footer-2">
						<div class="heading"><h4>Customer Service</h4></div>
						<ul>
							<li><a href="#">About Us</a></li>
							<li><a href="#">Delivery Information</a></li>
							<li><a href="#">Privacy Policy</a></li>
							<li><a href="#">Terms & Conditions</a></li>
							<li><a href="#">Contact Us</a></li>
						</ul>
					</div>
					<div class="col-md-3 col-footer footer-3">
						<div class="heading"><h4>My Account</h4></div>
						<ul>
							<li><a href="#">My Account</a></li>
							<li><a href="#">Brands</a></li>
							<li><a href="#">Gift Vouchers</a></li>
							<li><a href="#">Specials</a></li>
							<li><a href="#">Site Map</a></li>
						</ul>
					</div>
					<div class="col-md-3 col-footer footer-4">
						<div class="heading"><h4>Contact Us</h4></div>
						<ul>
							<li><span class="glyphicon glyphicon-home"></span>California, United States 3000009</li>
							<li><span class="glyphicon glyphicon-earphone"></span>+91 8866888111</li>
							<li><span class="glyphicon glyphicon-envelope"></span>infor@yoursite.com</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<div class="copyright">
			<div class="container">
				<div class="row">
					<div class="col-md-6">
						Copyright &copy; 2015.Company name All rights reserved.<a target="_blank" href="http://www.cssmoban.com/">&#x7F51;&#x9875;&#x6A21;&#x677F;</a>
					</div>
					<div class="col-md-6">
						<div class="pull-right">
							<ul>
								<li><img src="images/visa-curved-32px.png" /></li>
								<li><img src="images/paypal-curved-32px.png" /></li>
								<li><img src="images/discover-curved-32px.png" /></li>
								<li><img src="images/maestro-curved-32px.png" /></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</footer>
</body>
</html>