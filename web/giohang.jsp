<%-- 
    Document   : index
    Created on : Sep 28, 2016, 8:36:19 AM
    Author     : Administrator
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                    <a class="link-1" href="#">Giỏ Hàng</a>
                    <a class="link-3" id="sign" href=""></a>
                    <a class="link-3" href="quanly.jsp">Quản Lý</a>
                </nav>
                </div>
            </div>
            <div id="content">
                <form action="XemGioHang">
                    <input type="submit" id="btnGioHangMoi" name="action" value="Xem Hóa Đơn Chưa Thanh Toán"/>
                    <input type="submit" id="btnGioHangCu" name="action" value="Xem Các Hóa Đơn Cũ"/>
                </form>
            <c:set var="info" value="${requestScope.INFO}"/>
            <c:if test="${not empty info}">
                <form id="formXoa" action="XemGioHang">
                <c:forEach var="hd" items="${info}">
                    <div id="txtGioHang">
                        Ngày Tạo: ${hd.ngaytao}<br/>
                    </div>
                    <table id="tbgiohang" border="1">
                        <thead>
                            <tr>
                                <th>STT</th>
                                <th>Tên</th>
                                <th>Giá</th>
                                <th>Số lượng</th>
                                <th>Thành tiền</th>
                                <c:if test="${hd.tinhtrang == "Chưa thanh toán"}">
                                    <th>Giảm/Tăng</th>
                                    <th>Delete</th>
                                </c:if>
                            </tr>
                        </thead>
                        <tbody>
                            <c:set var="count" value="0"/>
                            <c:set var="countPrice" value="0"/>
                            <c:forEach var="rows" items="${hd.chitiethoadons}">
                            <form action="XemGioHang">
                                <c:set var="count" value="${count +1}"/>
                                <c:set var="countPrice" value="${countPrice+Integer.parseInt(rows.sanpham.gia)*rows.soluong}"/>
                                <tr>
                                    <td>${count}
                                        <input type="hidden" name="txtIDsp" value="${rows.sanpham.ma}"/>
                                        
                                        <input type="hidden" name="txtIDchhd" value="${rows.id}"/>
                                    </td>
                                    <td>${rows.sanpham.ten}</td>
                                    <td>${rows.sanpham.gia}</td>
                                    <td>${rows.soluong}</td>
                                    <td>${rows.soluong*rows.sanpham.gia}</td>
                                    <c:if test="${hd.tinhtrang == "Chưa thanh toán"}">
                                    <td>
                                        <input type="submit" class="btnGioHangGiam" name="action" value="Giảm 1"/>
                                        <input type="submit" class="btnGioHangTang" name="action" value="Tăng 1"/>
                                    </td>
                                    <td><input type="checkbox" form="formXoa" name="cbXoa" value="${rows.id}"> </td>
                                    </c:if>
                                </tr>
                                
                             </form>
                            </c:forEach>
                                <tr>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td>Tổng Cộng:</td>
                                    <td>${countPrice}</td>
                                </tr>
                        </tbody>
                    </table>
                        <c:if test="${hd.tinhtrang == "Chưa thanh toán"}">
                            
                        <form action="XemGioHang">
                            <input type="hidden" name="txtIDhd" value="${hd.id}"/>
                            <input type="submit" class="btnGiohang1" name="action" value="Thanh toán"/>
                            <input type="submit" class="btnGiohang" form="formXoa" name="action" value="Xóa"/>
                        </c:if>
                        </form>
                </c:forEach>
                    </form>
            </c:if>
                
                
            </div>
            
                <%@include file="footer.jsp" %>
            
        </div>
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
    </body>
</html>
