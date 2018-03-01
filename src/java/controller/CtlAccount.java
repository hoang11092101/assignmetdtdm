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
public class CtlAccount extends HttpServlet {

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
                case "Đăng ký": signUp(request, response, out);break;
                case "Đăng Nhập": signIn(request, response, out);break;
                case "Có": signOut(request, response, out);break;
            }
            
        }
        catch(Exception e){out.print(e);}
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

    
    public void signUp(HttpServletRequest request, HttpServletResponse response,PrintWriter out ){
        try{
            String sdt = request.getParameter("txtsdt");
            String matkhau = mahoaMD5(request.getParameter("txtmk"));
            String  ten = request.getParameter("txtten");
            String diachi = request.getParameter("txtdiachi");
            String email = request.getParameter("txtemail");
            AccountDao dao = new AccountDao();
            String kq = dao.signUpUser(sdt, matkhau, ten, diachi, email);
            out.print(kq);
        }
        catch(Exception e){
            out.print(e);
        }
    }
    
    public void signIn(HttpServletRequest request, HttpServletResponse response,PrintWriter out ){
        try{
            String sdt = request.getParameter("txtsdt");
            String matkhau = mahoaMD5(request.getParameter("txtmk"));
            AccountDao dao = new AccountDao();
            
            int kq = dao.signIn(sdt, matkhau);
            if(kq!=0){
                AccountDao dao1 = new AccountDao();
                String loai = dao1.search(sdt);
                Cookie luuloai = new Cookie("loaitk", loai);
                luuloai.setMaxAge(60*60); 
                response.addCookie(luuloai);
                Cookie luusdt = new Cookie("sdt", sdt);
                luusdt.setMaxAge(60*60); 
                response.addCookie(luusdt);
                request.setAttribute("tbsign", "Đăng nhập thành công");
                RequestDispatcher rd = null;
                rd = getServletContext().getRequestDispatcher("/infosign.jsp");
                rd.forward(request, response); 
            }
            else{
                Cookie luuloai = new Cookie("loaitk", "none");
                luuloai.setMaxAge(60*60); 
                response.addCookie(luuloai);
                Cookie luusdt = new Cookie("sdt", "none");
                luusdt.setMaxAge(60*60); 
                response.addCookie(luusdt);
                request.setAttribute("tbsign", "Đăng nhập thất bại");
                RequestDispatcher rd = null;
                rd = getServletContext().getRequestDispatcher("/infosign.jsp");
                rd.forward(request, response); 
            }
            
        }
        catch(Exception e){
            out.print(e);
        }
    }
    
    public void signOut(HttpServletRequest request, HttpServletResponse response,PrintWriter out ){
        try{
                for (Cookie cookie : request.getCookies()) {
                        if(cookie.equals("loaitk") || cookie.equals("sdt")){
                            cookie.setValue("none");
                            response.addCookie(cookie);
                        }
                        }
                request.setAttribute("tbsign", "Đăng xuất thành công");
                RequestDispatcher rd = null;
                rd = getServletContext().getRequestDispatcher("/infosign.jsp");
                rd.forward(request, response); 
        }
        catch(Exception e){
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
