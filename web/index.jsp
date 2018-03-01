<%-- 
    Document   : index
    Created on : Sep 28, 2016, 8:36:19 AM
    Author     : Rjk Rac Roi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%application.setAttribute("dkdn", "Đăng ký"); %>
<html>
    <head>
        <script type="text/JavaScript" src="js/JRjk.js"></script>
        <script type="text/JavaScript" src="js/JQuery/jquery-ui.min.js"></script>
        <script type="text/JavaScript" src="js/JQuery/jquery-1.12.3.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="CSS/css.css" rel="stylesheet" type="text/css"/>
        <title>JSP Page</title>
    </head>
    <body onload="checksign()">
        <div id="wrapper">
            <div id="header">
                <div class="logo"><img src="images/10150697_779467905397305_7670362946311912434_n.jpg"  width="105" height="100"/></div>
                <div class="sologan"><div class="chusologan">To Go Coffee, Coffee To Go</div></div>
                <div class="menu">
                    <nav id="nav-3">
                    <a class="link-1" href="#">Trang chủ</a>
                    <a class="link-3" href="sanpham.jsp">Sản Phẩm</a>
                    <a class="link-3" href="giohang.jsp">Giỏ Hàng</a>
                    <a class="link-3" id="sign" href="#">Đăng Nhập</a>
                    <a class="link-3" href="quanly.jsp">Quản Lý</a>
                </nav>
                </div>
            </div>
            <div id="content">
                <img width="900" src="images/1.jpg" id="hinhnen"/>
            </div>
            <div id="footer">
                <%@include file="footer.jsp" %>
            </div>
        </div>
                        
<script type="text/javascript">
var linkhinh = new Array("images/1.jpg","images/2.jpg","images/3.jpg","images/4.jpg","images/5.jpg");

var vitri=0;
$(document).ready(function(){
	setInterval(function(){ 
		vitri++;
		if(vitri==5)vitri=0;
		$("#hinhnen").fadeOut(3000,function(){ $("#hinhnen").attr("src",linkhinh[vitri]);});
		$("#hinhnen").fadeIn(3000);	
	 }, 3000);	
		
	
});
</script>
<script>
                    function checksign(){
                        var cc = getCookie("loaitk");
                    if(cc == "user"){
                        document.getElementById("sign").innerHTML = "Đăng xuất";
                        document.getElementById("sign").href ="asign.jsp";
                    }else if(cc == "admin" ){
                        document.getElementById("sign").innerHTML = "Đăng xuất";
                        document.getElementById("sign").href ="asign.jsp";
                    }
                    else{
                        document.getElementById("sign").innerHTML = "Đăng nhập";
                        document.getElementById("sign").href ="login.jsp";
                    }
                    }
                    
                    function getCookie(cname) {
                        var name = cname + "=";
                        var ca = document.cookie.split(';');
                        for(var i = 0; i < ca.length; i++) {
                        var c = ca[i];
                        while (c.charAt(0) == ' ') {
                        c = c.substring(1);
                    }
                    if (c.indexOf(name) == 0) {
                    return c.substring(name.length, c.length);
                    }
                    }
                return "";
                }
                </script>

    </body>
</html>
