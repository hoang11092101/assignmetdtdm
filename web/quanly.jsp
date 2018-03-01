<%-- 
    Document   : index
    Created on : Sep 28, 2016, 8:36:19 AM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<%application.setAttribute("showhide", "an()"); %>
<html>
    <head>
        <script type="text/JavaScript" src="js/JRjk.js"></script>
        <script type="text/JavaScript" src="js/jquery-ui.min.js"></script>
        <script type="text/JavaScript" src="js/jquery-1.12.3.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="CSS/quanly.css" rel="stylesheet" type="text/css"/>
        <title>JSP Page</title>
    </head>
    <body onload="${showhide};checksign();andmk();" >
        <div id="editbox">
            <jsp:include page="edit.jsp"></jsp:include>
        </div>
        <div id="dmkdiv">
                        <%@include file="doimatkhau.jsp"%>
        </div>
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
                <div id="quanlyuser">
                <%@include file="quanlyuser.jsp" %>
                        
                </div>   
                <div id="quanlyadmin">
                    <a href="#">Quản lý sản phẩm</a>
                    <form action="QuanLyAcount">
                        <c:url var="quanlyaccount" value="QuanLyAccount">
                            <c:param name="action" value="Quản lý Account"/>
                        </c:url>
                        <a href="${quanlyaccount}">Quản lý Account</a>
                        ${message}
                    </form>
                    
                <div class="tk">
                    <div class="txttk"> Tìm Kiếm: </div>
                    <div class="search"><jsp:include page="searchql.jsp"></jsp:include></div>
                </div>
                <div class="dssp">
                    <table><%! int i=0;int w=0;%>
                        <td>
                                <div class="showsp">
                                    <form  action="xoasuasp">
                                    <div class="hinhsp"><img src=""/></div>
                                    <div class="tensp">
                                        Tên:<input type="text" name="ten"/><br/>
                                        <label>Loại Nước:
                                            <select name="loai" class="loainuoc"> 
                                                <option value="1">Cà Phê</option>
                                                <option value="2">Soda</option>
                                                <option value="5">Yogurt</option>
                                                <option value="3">Chocolate</option>
                                                <option value="4">Trà</option>
                                            </select>
                                        </label>
                                    </div>
                                    <div class="motasp">Mô tả:<input type="text" name="mota"/></div>
                                    <div class="giasp">Giá: <input type="number" name="gia"/></div>
                                    <div class="nut">
                                        
                                            <input type="submit" class="nutthem" name="btn" value="Thêm">
                                    
                                    </div></form>
                                </div>
                                </td>
                        <c:forEach var="sanpham" items="${ds}">
                            <%if(w==0){i=0;%>
                                
                            <%}%>
                            <%i++;w++;%>
                            <%if(i==3){i=0;%><tr><%}%>
                            
                            <td>
                                <div class="showsp">
                                    <div class="hinhsp"><img src="${sanpham.hinhanh}" width="240" height="130"/></div>
                                    <div class="tensp">${sanpham.ten}</div>
                                    <div class="motasp">Mô tả: ${sanpham.mota}</div>
                                    <div class="giasp">Giá: ${sanpham.gia} VNĐ</div>
                                    <form  action="xoasuasp">
                                        <div class="nut">
                                            <input type="hidden" name="masp" value="${sanpham.ma}">
                                            <input type="hidden" name="loaisp" value="${sanpham.loai}">
                                            <input type="hidden" name="tensp" value="${sanpham.ten}">
                                            <input type="hidden" name="motasp" value="${sanpham.mota}">
                                            <input type="hidden" name="giasp" value="${sanpham.gia}">
                                            <input type="submit" class="nutxoa" onclick="thongbao()" name="btn" value="Xóa">
                                            <input type="button" onclick="hien(this);" class="nutsua" name="btn" value="Sửa">
                                        
                                        </div>
                                    </form>
                                </div>
                            </td>
                        <%if(i==3){i=0;%><tr><%}%>
                        </c:forEach><%i=-1; w=0;%>
                    </table>  
                </div>
               
            </div>
                      
                    
            </div>
                    
            <div id="footer">
                               <%@include file="footer.jsp" %>

            </div>
        </div>
                    ${thongbao}
<script language="javascript">

   
   function hien(vitri){
        $(document).ready(function(){
	
            $("#editbox").show();
	});
        
        var ma = vitri.parentNode.firstChild.nextSibling.value;
        document.getElementById("masp1").value = ma;
        document.getElementById("maSPhinh").value = ma;
        var loai = vitri.parentNode.firstChild.nextSibling.nextSibling.nextSibling.value;
        document.getElementById("loainuoc1").options[loai-1].selected = "selected";
        var ten = vitri.parentNode.firstChild.nextSibling.nextSibling.nextSibling.nextSibling.nextSibling.value;
        document.getElementById("tened").value = ten;
        var mota = vitri.parentNode.firstChild.nextSibling.nextSibling.nextSibling.nextSibling.nextSibling.nextSibling.nextSibling.value;
        var gia = vitri.parentNode.firstChild.nextSibling.nextSibling.nextSibling.nextSibling.nextSibling.nextSibling.nextSibling.nextSibling.nextSibling.value;
        document.getElementById("motaed").value = mota;
        document.getElementById("giaed").value = gia;
   }
   
   function an(){
      $(document).ready(function(){
	
        $("#editbox").hide();
		});
   }
   

            function andmk(){
                $(document).ready(function(){
                             $("#dmkdiv").hide();
                            });
            }
   
            function hiendmk(){
                $(document).ready(function(){
                             $("#dmkdiv").show();
                            });
            }
   

</script>
<script>
                    function checksign(){
                        var cc = getCookie("loaitk");
                    if(cc == "user"){
                        document.getElementById("sign").innerHTML = "Đăng xuất";
                        document.getElementById("sign").href ="asign.jsp";
                        $(document).ready(function(){
                             $("#quanlyadmin").hide();
                             $("#quanlyuser").show();
                            });
                        
                        
                    }else if(cc == "admin" ){
                        document.getElementById("sign").innerHTML = "Đăng xuất";
                        document.getElementById("sign").href ="asign.jsp";
                        $(document).ready(function(){
                             $("#quanlyuser").hide();
                            });
                    }
                    else{
                        document.getElementById("sign").innerHTML = "Đăng nhập";
                        document.getElementById("sign").href ="login.jsp";
                        $(document).ready(function(){
                             $("#quanlyadmin").hide();
                            });
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
