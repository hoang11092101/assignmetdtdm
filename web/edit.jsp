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
        <title>JSP Page</title>
        <link href="CSS/edit.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div id="bocEdit">
        <div class="showsp1">
            
            <div class="hinhsp1"><img src=""/></div>
            <div class="doihinhsp1">
                <form action="UpLoadImgSanPham" enctype="multipart/form-data" method="POST" >
                    <input type="hidden" name="maSPhinh1" id="maSPhinh" value=""/>
                    <input type="file" name="file"/>
                    <input type="submit" name="action" value="Lưu hình"/>
                </form>
            </div>
            <form  action="xoasuasp">
            <div class="tensp1">
                Tên:<input type="text" id="tened" name="ten" value="${ten}"/>
                <br/>
                <label>Loại Nước:
                    <select name="loai" id="loainuoc1"> 
                        <option value="1">Cà Phê</option>
                        <option value="2">Soda</option>
                        <option value="5">Yogurt</option>
                        <option value="3">Chocolate</option>
                        <option value="4">Trà</option>
                    </select>
                </label>
            </div>
            <div class="motasp1">Mô tả: <input type="text" id="motaed" name="mota" value="${mota}"/></div>
            <div class="giasp1">Giá: <input type="text" id="giaed" name="gia" value="${gia}"/></div>
            <div class="nut1">
                
                    <input type="hidden" name="masp" id="masp1" value=""/>
                    <input type="submit" class="nutluu1" name="btn" value="Lưu">
                    <input type="button" class="nuthuy1" onclick="an()" name="btn" value="Hủy">
                
            </div>
            </form>
        </div>
        </div>
    </body>
</html>
