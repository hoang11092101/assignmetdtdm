/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Taikhoan;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.AccountDao;

/**
 *
 * @author Administrator
 */
public class QuanLyAccount extends HttpServlet {

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
           switch (action){
               case "Quản lý Account": showQuanLyAccount(request, response, out); break;
               case "Reset": resetTK(request, response, out); break;
               case "Xóa": xoaTK(request, response, out); break;
               case "Lưu": luuDmK(request, response, out); break;
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

        private void showQuanLyAccount(HttpServletRequest request, HttpServletResponse response,PrintWriter out){
            try {
                Taikhoan[] tk = new AccountDao().getTKList();
                request.setAttribute("INFO", tk);
                RequestDispatcher rd = request.getRequestDispatcher("quanlyaccount.jsp");
                rd.forward(request, response);
            } catch (Exception e) {
                out.print(e);
            }
            
            
        }
        
        private void resetTK(HttpServletRequest request, HttpServletResponse response,PrintWriter out){
            try {
                    int id = Integer.parseInt(request.getParameter("txtIDtk"));
                    
                    AccountDao dao = new AccountDao();
                    dao.resetTKMK(id);
                    RequestDispatcher rd = request.getRequestDispatcher("/QuanLyAccount?action=Quản+lý+Account");
                    rd.forward(request, response);
            } catch (Exception e) {
                out.print(e);
            }
        }
        
        private void xoaTK(HttpServletRequest request, HttpServletResponse response,PrintWriter out){
            try {
                int id = Integer.parseInt(request.getParameter("txtIDtk"));
                AccountDao dao = new AccountDao();
                dao.deleteTK(id);
                RequestDispatcher rd = request.getRequestDispatcher("/QuanLyAccount?action=Quản+lý+Account");
                rd.forward(request, response);
            } catch (Exception e) {
                out.print(e);
            }
        }
        
        private void luuDmK(HttpServletRequest request, HttpServletResponse response,PrintWriter out){
            try {
                String mkcu = mahoaMD5(request.getParameter("txtmkcu"));
                String mkmoi = mahoaMD5(request.getParameter("txtmkmoi"));
                String mknhaplai = mahoaMD5(request.getParameter("txtmknhaplai"));
                String sdt ="";
                for (Cookie cookie : request.getCookies()) {
                        if(cookie.equals("sdt")){
                            sdt = cookie.getValue();
                        }
                        }
                
                int id= new AccountDao().getIdUser(sdt);
                String mk = new AccountDao().getMK(id);
                if(mkcu.equals(mk)){
                    if(mkmoi.equals(mknhaplai)){
                        if(new AccountDao().setMK(id, mkmoi)){
                            request.setAttribute("thongbao", "<script> alert(\"Đổi mật khẩu thành công!!!\");</script>");
                        }
                    }
                    else{
                        request.setAttribute("thongbao", "<script> alert(\"Mật khẩu nhập lại không đúng!!!\");</script>");
                    }
                }
                else{
                    request.setAttribute("thongbao", "<script> alert(\"Mật khẩu cũ không đúng!!!\");</script>");
                }
                
                
                RequestDispatcher rd = request.getRequestDispatcher("/quanly.jsp");
                rd.forward(request, response);
            } catch (Exception e) {
                out.print(e);
            }
            
        }
        
        public static String mahoaMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
