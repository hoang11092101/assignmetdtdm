/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import entity.Hoadon;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.AccountDao;
import model.GioHangDao;

/**
 *
 * @author Administrator
 */
public class XemGioHang extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        request.setCharacterEncoding("UTF-8");
        try{
            String action = request.getParameter("action");
            switch(action){
                case "Xem Hóa Đơn Chưa Thanh Toán": xemHoaDonMoi(request, response, out); break;
                case "Xem Các Hóa Đơn Cũ": xemHoaDonCu(request, response, out); break;
                case "Giảm 1": giamDonHang(request, response, out); break;
                case "Tăng 1": tangDonHang(request, response, out); break;
                case "Thanh toán": thanhToan(request, response, out); break;
                case "Xóa": xoaSP(request, response, out); break;
            }
        }
        catch(Exception e){
            out.print(e);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    public void xemHoaDonMoi(HttpServletRequest request, HttpServletResponse response,PrintWriter out){
        try {
                String sdt = ""; 
             Cookie[] ck = request.getCookies();
                    if(ck.length>0){
                    for(int i=0;i<ck.length;i++)
                    {
                        String t = ck[i].getName();
                        if(t.equals("sdt")){sdt= ck[i].getValue();}
                    }
                    }
            if(sdt.equals("") || sdt.equals("none")){ 
                request.setAttribute("sdt", sdt);
                RequestDispatcher rd = null;
                rd = getServletContext().getRequestDispatcher("/login.jsp");
                rd.forward(request, response); return;
            }
            
            AccountDao dao1 = new AccountDao();
            int idUser = dao1.getIdUser(sdt);
            
            GioHangDao dao = new GioHangDao();
            if(dao.getHoaDon(idUser, "Chưa thanh toán").length>0){
                    GioHangDao dao3 = new GioHangDao();
                    int mahd = dao3.getIDHoaDon(idUser, "Chưa thanh toán");
    //                GioHangDao dao4 = new GioHangDao();
     //               Chitiethoadon[] result = dao4.getChiTietHDGroup(mahd);
                    GioHangDao dao5 = new GioHangDao();
                   Hoadon[] result = dao5.getHoaDon(idUser, "Chưa thanh toán");
                   
                   
                    request.setAttribute("INFO", result);
                    RequestDispatcher rd = request.getRequestDispatcher("giohang.jsp");
                    rd.forward(request, response);
                
             }
            else{
                
                    RequestDispatcher rd = request.getRequestDispatcher("giohang.jsp");
                    rd.forward(request, response);
            }
            
        } catch (Exception e) {
            
        }
    }
    
        public void xemHoaDonCu(HttpServletRequest request, HttpServletResponse response,PrintWriter out){
        try {
            String sdt = ""; 
             Cookie[] ck = request.getCookies();
                    if(ck.length>0){
                    for(int i=0;i<ck.length;i++)
                    {
                        String t = ck[i].getName();
                        if(t.equals("sdt")){sdt= ck[i].getValue();}
                    }
                    }
            if(sdt.equals("") || sdt.equals("none")){ 
                request.setAttribute("sdt", sdt);
                RequestDispatcher rd = null;
                rd = getServletContext().getRequestDispatcher("/login.jsp");
                rd.forward(request, response); return;
            }
            
            AccountDao dao1 = new AccountDao();
            int idUser = dao1.getIdUser(sdt);
            
            GioHangDao dao = new GioHangDao();
            if(dao.getHoaDon(idUser, "Đã thanh toán").length>0){
                    GioHangDao dao3 = new GioHangDao();
                    int mahd = dao3.getIDHoaDon(idUser, "Đã thanh toán");
//                    GioHangDao dao4 = new GioHangDao();
//                    Chitiethoadon[] result = dao4.getChiTietHD(mahd);
                    GioHangDao dao5 = new GioHangDao();
                    Hoadon[] result = dao5.getHoaDon(idUser, "Đã thanh toán");
                    request.setAttribute("INFO", result);
                    RequestDispatcher rd = request.getRequestDispatcher("giohang.jsp");
                    rd.forward(request, response);
                
             }
            else{
                
                    RequestDispatcher rd = request.getRequestDispatcher("giohang.jsp");
                    rd.forward(request, response);
            }
        } catch (Exception e) {
            
        }
    }
        
        public void giamDonHang(HttpServletRequest request, HttpServletResponse response,PrintWriter out){
        try {
            String idCthd = request.getParameter("txtIDchhd");
            String url = "/XemGioHang?action=Xem+Hóa+Đơn+Chưa+Thanh+Toán";
            GioHangDao dao = new GioHangDao();
            dao.tanggiamHoaDon(Integer.parseInt(idCthd), -1);
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }
        catch(Exception e){
            out.print(e);
        }
        }
        
        public void tangDonHang(HttpServletRequest request, HttpServletResponse response,PrintWriter out){
        try {
            String idCthd = request.getParameter("txtIDchhd");
            String url = "/XemGioHang?action=Xem+Hóa+Đơn+Chưa+Thanh+Toán";
            GioHangDao dao = new GioHangDao();
            dao.tanggiamHoaDon(Integer.parseInt(idCthd), +1);
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }
        catch(Exception e){
            out.print(e);
        }
        }
    
        
        public void thanhToan(HttpServletRequest request, HttpServletResponse response,PrintWriter out){
        try {
           int idHD = Integer.parseInt(request.getParameter("txtIDhd"));
           GioHangDao dao = new GioHangDao();
           dao.updateHoaDon(idHD,"Đã thanh toán");
           String url = "/XemGioHang?action=Xem+Hóa+Đơn+Chưa+Thanh+Toán";
           RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
        }
        catch(Exception e){
            out.print(e);
        }
        }
        
        public void xoaSP(HttpServletRequest request, HttpServletResponse response,PrintWriter out){
        try {
           String[] idSP = request.getParameterValues("cbXoa");
           
           for(int i=0; i<idSP.length;i++){
               int id = Integer.parseInt(idSP[i]);
               GioHangDao dao = new GioHangDao();
               dao.xoaChiTietHoaDon(id);
           }
           
           String url = "/XemGioHang?action=Xem+Hóa+Đơn+Chưa+Thanh+Toán";
           RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
        }
        catch(Exception e){
            out.print(e);
        }
        }
}
