<%-- 
    Document   : index
    Created on : Sep 28, 2016, 8:36:19 AM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<html>
    <head>
        <script type="text/JavaScript" src="js/JRjk.js"></script>
        <script type="text/JavaScript" src="js/JQuery/jquery-ui.min.js"></script>
        <script type="text/JavaScript" src="js/JQuery/jquery-1.12.3.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="CSS/sanpham.css" rel="stylesheet" type="text/css"/>
        <title>JSP Page</title>
    </head>
    <body onload="checksign();chonvitri(${vitri});">
        <div id="wrapper">
            <div id="header">
                <div class="logo"><img src="images/10150697_779467905397305_7670362946311912434_n.jpg"  width="105" height="100"/></div>
                <div class="sologan"><div class="chusologan">To Go Coffee, Coffee To Go</div></div>
                <div class="menu">
                    <nav id="nav-3">
                    <a class="link-3" href="index.jsp">Trang chủ</a>
                    <a class="link-1" href="sanpham.jsp">Sản Phẩm</a>
                    <a class="link-3" href="giohang.jsp">Giỏ Hàng</a>
                    <a class="link-3" id="sign" href="dangnhap.jsp">Đăng Nhập</a>
                    <a class="link-3" href="quanly.jsp">Quản Lý</a>
                </nav>
                </div>
            </div>
            <div id="content">
                <div class="tk">
                    <div class="txttk"> Tìm Kiếm: </div>
                    <div class="search"><jsp:include page="search.jsp"></jsp:include></div>
                </div>
                <div class="dssp">
                    
                    <table><%! int i=-1;%>
                        <c:forEach var="sanpham" items="${ds}">
                            
                            <%i++;%>
                            <%if(i==3){i=0;%><tr><%}%>
                            
                            <td>
                                <form action="ThemVaoGio">
                                <div class="showsp">
                                    <div class="hinhsp">
                                        <img src="${sanpham.hinhanh}" width="240" height="130"/>
                                    </div>
                                    <div class="tensp">${sanpham.ten}</div>
                                    <div class="motasp">Mô tả: ${sanpham.mota}</div>
                                    <div class="giasp">Giá: ${sanpham.gia} VNĐ</div>
                                    <div class="soluongsp"><div class="txtsl">Số Lượng:</div> <input class="soluongsp1" type="number" min="1" value="1" name="sl"/></div>
                                    <div class="nut">
                                            <input type="hidden" name="masp" value="${sanpham.ma}">
                                            <input type="submit" class="nutthem" name="btn" value="Thêm">
                                    </div>
                                </div>
                                </form>
                            </td>
                        <%if(i==3){i=0;%><tr><%}%>
                        </c:forEach><%i=-1;%>
                    </table>  
                    
                        
                    <div class="chuyentrang">
                                <ul id="pagination-flickr">
                                    <li class="previous-off"><div id="previouss">«Lùi</div></li>
                                <c:forEach var="sl" items="${slSP}">
                                    <li class="vitri${sl.stt}">
                                        <a href="ChuyenTrangSP?action=${sl.stt}">${sl.stt}</a>
                                    </li>
                                </c:forEach>
                                    <li class="next">
                                        <div id="nextt"><a href="">Tiếp »</a></div>
                                    </li>
                                </ul>
                    </div>
                       
                </div>
               
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
                
                function chonvitri(cho){
                        var v = "vitri"+cho.toString();
                        var btn = document.getElementsByClassName(v);
                        btn.innerHTML=1;
                        var tt = parseInt(cho)+1;
                        var ttt = parseInt(cho)-1;
                        var tttt = parseInt(cho);
                        $(document).ready(function(){
                            $(btn).removeClass(v).addClass("active");
                            $(".active").html(cho.toString());
                        });
                        if(tttt>1){
                            $(document).ready(function(){
                                $(".previous-off").removeClass("previous-off").addClass("previous").html("<a href=\"ChuyenTrangSP?action="+ttt+"\">«Lùi</a>");
                            });
                            
                        }
                        var c = document.getElementById("pagination-flickr").childNodes;
                        document.getElementById("nextt").innerHTML= "<a href=\"ChuyenTrangSP?action="+tt+"\">Tiếp »</a>";
                        
                        if(tttt===parseInt(${slmax}))
                        {
                            $(document).ready(function(){
                                $(".next").removeClass(".next").addClass("next-off").html("Tiếp »");
                            });
                        }
                    }
                </script>
 
    </body>
</html>
