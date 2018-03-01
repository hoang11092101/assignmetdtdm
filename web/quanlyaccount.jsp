<%-- 
    Document   : index
    Created on : Sep 28, 2016, 8:36:19 AM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
                    <a class="link-3" id="sign" href="dangnhap.jsp">Đăng Nhập</a>
                    <a class="link-1" href="quanly.jsp">Quản Lý</a>
                </nav>
                </div>
            </div>
            <div id="content">
               <c:set var="info" value="${requestScope.INFO}"/>
                <c:if test="${not empty info}">
                    <form id="chinhsua" action="ChinhSuaAccount">
                    <table border="1">
                        <thead>
                            <tr>
                                <th>No</th>
                                <th>SĐT</th>
                                <th>Tên</th>
                                <th>Địa chỉ</th>
                                <th>Email</th>
                                <th>Ngày Tạo</th>
                                <th>Sửa thông tin</th>
                                <th>Reset Password</th>
                                <th>Xóa</th>
                            </tr>
                        </thead>
                        
                        <tbody>
                            <c:set var="count" value="0"/>
                            <c:forEach var="rows" items="${info}">
                            <form id="formquanly" action="QuanLyAccount">
                                <c:set var="count" value="${count +1}"/>
                                <tr>
                                        
                                        <td>${count}
                                            <input type="hidden" form="formquanly" name="txtIDtk" value="${rows.id}"/>
                                        </td>
                                        <td>${rows.sdt}</td>
                                        <td>${rows.ten}</td>
                                        <td>${rows.diachi}</td>
                                        <td>${rows.email}</td>
                                        <td>${rows.ngaytao}</td>
                                        <td>
                                            <input type="button" class="btnsua" onclick="change(this)" name="action" value="Sửa"/>
                                            <input type="submit" form="chinhsua" class="btnluu" name="action" value="Lưu" style="display: none;"/>
                                        </td>
                                        <td>
                                            <c:url var="reset" value="QuanLyAccount">
                                                <c:param name="action" value="Reset"/>
                                                <c:param name="txtIDtk" value="${rows.id}"/>
                                            </c:url>
                                            <a href="${reset}">Reset</a>(123456)
                                        </td>
                                        <td><c:url var="xoa" value="QuanLyAccount">
                                                <c:param name="action" value="Xóa"/>
                                                <c:param name="txtIDtk" value="${rows.id}"/>
                                            </c:url>
                                            <a href="${xoa}">Xóa</a>
                                        </td>
                                        
                                </tr>
                                </form>
                            </c:forEach>
                        </tbody>
                    </table>
                     </form>
                       
            </c:if>
                    
            </div>
            <div id="footer">
                
            </div>
        </div>
        <script>
                    function checksign(){
                        var cc = getCookie("loaitk");
                    if(cc == "user"){
                        document.getElementById("sign").innerHTML = "Đăng xuất";
                        document.getElementById("sign").href ="asign.jsp";
                        window.location="index.jsp";
                    }else if(cc == "admin" ){
                        document.getElementById("sign").innerHTML = "Đăng xuất";
                        document.getElementById("sign").href ="asign.jsp";
                    }
                    else{
                        document.getElementById("sign").innerHTML = "Đăng nhập";
                        document.getElementById("sign").href ="login.jsp";
                        window.location="login.jsp";
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
                <script>
                    function change(btn){
                 if(btn.value=="Sửa"){
                     
                     btn.parentNode.parentNode.childNodes[3].innerHTML= "<input type=\"text\" name=\"txtsdt1\" style=\"width: 80px;\" value=\""+btn.parentNode.parentNode.childNodes[3].innerHTML+"\"/>";
                     btn.parentNode.parentNode.childNodes[5].innerHTML= "<input type=\"text\" name=\"txtten1\" style=\"width: 120px;\" value=\""+btn.parentNode.parentNode.childNodes[5].innerHTML+"\"/>";
                     btn.parentNode.parentNode.childNodes[7].innerHTML= "<input type=\"text\" name=\"txtdiachi1\" value=\""+btn.parentNode.parentNode.childNodes[7].innerHTML+"\"/>";
                     btn.parentNode.parentNode.childNodes[9].innerHTML= "<input type=\"text\" name=\"txtemail1\" value=\""+btn.parentNode.parentNode.childNodes[9].innerHTML+"\"/>"+"<input type=\"hidden\" name=\"txtIDTKCS\" value=\""+btn.parentNode.parentNode.childNodes[1].childNodes[1].value+"\"/>";
                        an(btn);
                 }
                 }
                 
                function an(btn){
                    var f = document.getElementById("form1");
                    
                    $(document).ready(function(){
                             $(".btnsua").hide();
                             $(".btnxoa").hide();
                             $(".btnreset").hide();
                             $(btn).next().show();
                            });
                    }
         </script>
    </body>
</html>
