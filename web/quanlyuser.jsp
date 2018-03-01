<%-- 
    Document   : index
    Created on : Sep 28, 2016, 8:36:19 AM
    Author     : Administrator
--%>

<%@page import="controller.XemGioHang"%>
<%@page import="entity.Taikhoan"%>
<%@page import="model.AccountDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<html>
    <head>
        <script type="text/JavaScript" src="js/JRjk.js"></script>
        <script type="text/JavaScript" src="js/JQuery/jquery-ui.min.js"></script>
        <script type="text/JavaScript" src="js/JQuery/jquery-1.12.3.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="CSS/quanly.css" rel="stylesheet" type="text/css"/>
        <title>JSP Page</title>
    </head>
    <body onload="checksign();" >
        
        <%
                    String sdt="";
                    Cookie[] ck = request.getCookies();
                    if(ck.length>0){
                    for(int i=0;i<ck.length;i++)
                    {
                        String t = ck[i].getName();
                        if(t.equals("sdt")){sdt= ck[i].getValue();}
                    }
                    }
                    AccountDao dao1 = new AccountDao();
                    int idUser = dao1.getIdUser(sdt);
                    AccountDao dao2 = new AccountDao();
                    Taikhoan tk1 = dao2.getTK(idUser);
        %>

                <div id="showtt"></div>
                <div id="quanlyuser">
                    
                    <c:set var="info" value="<%=tk1%>"/>
                    <c:if test="${not empty info}">
                        <div id="txtQuanlyuser">
                            Quản lý thông tin
                        </div>
                    <table id="tbQuanlyuser">
                        <tr>
                            <td>Hình ảnh:</td>
                            <td><img src="${info.hinhanh}" width="150" height="150"/>
                            </td>
                        </tr>
                        <tr>
                            <td></td>
                            <td>
                                <form action="UpLoadImgUser" enctype="multipart/form-data" method="POST" >
                                    <input type="hidden" name="txtidUser" value="${info.id}"/>
                                    <input type="submit" name="btn" value="Lưu hình"/>
                                    <input type="file" name="file"/>
                                    
                                </form>
                            </td>
                        </tr>
                        
                        <tr>
                            <td>Số điện thoại:<form action="ChinhSuaAccount" id="chinhsuauser"/></td>
                            <td>${info.sdt}</td>
                        </tr>
                        <tr>
                            <td>Tên</td>
                            <td>${info.ten}</td>
                        </tr>
                        <tr>
                            <td>Địa chỉ:</td>
                            <td>${info.diachi}</td>
                        </tr>
                        <tr>
                            <td>Email:</td>
                            <td>${info.email}</td>
                        </tr>
                        <tr>
                            <td>Ngày đăng ký:</td>
                            <td>${info.ngaytao}</td>
                        </tr>
                        <tr>
                            <td></td>
                            <td>
                                <input type="hidden" form="chinhsuauser" name="txtIDTKCS" value="${info.id}"/>
                                <input type="button" onclick="change(this)" id="btnsua" name="action" value="Chỉnh sửa"/>
                                
                                <input type="submit" name="action" form="chinhsuauser" id="btnluu1" value="Lưu chỉnh sửa" style="display: none;"/>
                                <input type="button" onclick="hiendmk();" name="action" value="Đổi mật khẩu"/>
                            </td>
                            
                        </tr>
                       
                    </table>
                    </c:if>
                    
                </div>
                    
         <script>
             
                
            
            
                    function change(btn){
                    
                     btn.parentNode.parentNode.parentNode.parentNode.childNodes[1].childNodes[4].childNodes[3].innerHTML= "<input type=\"text\" form=\"chinhsuauser\" name=\"txtsdt1\" style=\"width: 80px;\" value=\""+btn.parentNode.parentNode.parentNode.parentNode.childNodes[1].childNodes[4].childNodes[3].innerHTML+"\"/>";
                     btn.parentNode.parentNode.parentNode.parentNode.childNodes[1].childNodes[6].childNodes[3].innerHTML= "<input type=\"text\" form=\"chinhsuauser\" name=\"txtten1\" style=\"width: 120px;\" value=\""+btn.parentNode.parentNode.parentNode.parentNode.childNodes[1].childNodes[6].childNodes[3].innerHTML+"\"/>";
                     btn.parentNode.parentNode.parentNode.parentNode.childNodes[1].childNodes[8].childNodes[3].innerHTML= "<input type=\"text\" form=\"chinhsuauser\" name=\"txtdiachi1\" value=\""+btn.parentNode.parentNode.parentNode.parentNode.childNodes[1].childNodes[8].childNodes[3].innerHTML+"\"/>";
                     btn.parentNode.parentNode.parentNode.parentNode.childNodes[1].childNodes[10].childNodes[3].innerHTML= "<input type=\"text\" form=\"chinhsuauser\" name=\"txtemail1\" value=\""+btn.parentNode.parentNode.parentNode.parentNode.childNodes[1].childNodes[10].childNodes[3].innerHTML+"\"/>";
                     
                    $(document).ready(function(){
                             $(btn).hide();
                             $("#btnluu1").show();
                            });
                    
                 }
                 
            
            
            
                 
         </script>
<script>
                    function checksign(){
                        var cc = getCookie("loaitk");
                        var sdt = getCookie("sdt");
                    if(cc == "user"){
                        document.getElementById("sign").innerHTML = "Đăng xuất";
                        document.getElementById("sign").href ="asign.jsp";
                        $(document).ready(function(){
                             $("#quanlyadmin").hide();
                            });
                        
                    }else if(cc == "admin" ){
                        document.getElementById("sign").innerHTML = "Đăng xuất";
                        document.getElementById("sign").href ="asign.jsp";
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
