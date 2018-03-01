<%-- 
    Document   : index
    Created on : Sep 28, 2016, 8:36:19 AM
    Author     : Administrator
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
                    <a class="link-3" href="index.jsp">Trang chủ</a>
                    <a class="link-3" href="sanpham.jsp">Sản Phẩm</a>
                    <a class="link-3" href="giohang.jsp">Giỏ Hàng</a>
                    <a class="link-3" id="sign" href="#">Đăng Nhập</a>
                    <a class="link-1" href="quanly.jsp">Quản Lý</a>
                </nav>
                </div>
            </div>
            <div id="content">
                <div id="txtSignUp">Đăng Nhập</div>
                <form action="CtlAccount">
                    <table id="tbSignUp">
                        <tr>
                            <td>SĐT:</td>
                            <td><input type="text" name="txtsdt"/></td>
                        </tr>
                        <tr>
                            <td>Mật Khẩu: </td>
                            <td><input type="password" name="txtmk"/></td>
                        </tr>
                        <tr>
                            <td>Tên: </td>
                            <td><input type="text" name="txtten"/></td>
                        </tr>
                        <tr>
                            <td>Địa chỉ: </td>
                            <td><input type="text" name="txtdiachi"/></td>
                        </tr>
                        <tr>
                            <td>Email: </td>
                            <td><input type="text" name="txtemail"/></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td>
                                <input type="submit" name="action" value="Đăng ký"/>
                                <a href="login.jsp">Hủy</a>
                            </td>
                        </tr>
                    </table>
                </form>
                
            </div>
            <div id="footer">
                <%@include file="footer.jsp" %>
            </div>
        </div>
        <script>
                    function checksign(){
                        var cc = getCookie("loaitk");
                    if(cc == "loaitk = user"){
                        document.getElementById("sign").innerHTML = "Đăng xuất";
                        document.getElementById("sign").href ="asign.jsp";
                        window.location="index.jsp";
                    }else if(cc == "loaitk = admin" ){
                        document.getElementById("sign").innerHTML = "Đăng xuất";
                        document.getElementById("sign").href ="asign.jsp";
                        window.location="index.jsp";
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
