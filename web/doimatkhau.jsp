<%-- 
    Document   : edit
    Created on : Oct 1, 2016, 10:31:37 AM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/JavaScript" src="js/JRjk.js"></script>
        <script type="text/JavaScript" src="js/JQuery/jquery-ui.min.js"></script>
        <script type="text/JavaScript" src="js/JQuery/jquery-1.12.3.js"></script>
        <title>JSP Page</title>
        <link href="CSS/edit.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="showdmk">
            <form action="QuanLyAccount">
            <table>
                <tr>
                    <td>Mật khẩu cũ: </td>
                    <td><input type="password" name="txtmkcu"/><br/></td>
                <tr>
                    <td>Mật khẩu mới: </td>
                    <td><input type="password" name="txtmkmoi"/><br/></td>
                </tr>
                <tr>
                    <td>Nhập lại mật khẩu mới:</td>
                    <td><input type="password" name="txtmknhaplai"/><br/></td>
                    
                </tr>
                <tr>
                    <td></td>
                    <td>
                    <input type="submit" name="action" value="Lưu"/>
                    <input type="button" onclick="andmk()" id="btndong" value="Hủy"/>
                    </td>
                </tr>
            
            </table>
            </form>
        </div>
    </body>
</html>
