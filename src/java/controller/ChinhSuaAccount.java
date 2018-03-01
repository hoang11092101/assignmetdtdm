/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Taikhoan;
import java.io.IOException;
import java.io.PrintWriter;
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
public class ChinhSuaAccount extends HttpServlet {

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
               case "Lưu": suaTK(request, response, out); break;
               case "XemTK": showTK(request, response, out); break;
               case "Lưu chỉnh sửa": suaTKUser(request, response, out); break;
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
        private void suaTK(HttpServletRequest request, HttpServletResponse response,PrintWriter out){
            try {
                int id = Integer.parseInt(request.getParameter("txtIDTKCS"));
                String sdt = request.getParameter("txtsdt1");
                String ten = request.getParameter("txtten1");
                String diachi = request.getParameter("txtdiachi1");
                String email = request.getParameter("txtemail1");
                AccountDao dao = new AccountDao();
                dao.updateTK(id, sdt, ten, diachi, email);
                RequestDispatcher rd = request.getRequestDispatcher("/QuanLyAccount?action=Quản+lý+Account");
                rd.forward(request, response);
            } catch (Exception e) {
                out.print(e);
            }
        }
        
         private void suaTKUser(HttpServletRequest request, HttpServletResponse response,PrintWriter out){
            try {
                int id = Integer.parseInt(request.getParameter("txtIDTKCS"));
                String sdt = request.getParameter("txtsdt1");
                String ten = request.getParameter("txtten1");
                String diachi = request.getParameter("txtdiachi1");
                String email = request.getParameter("txtemail1");
                AccountDao dao = new AccountDao();
                dao.updateTK(id, sdt, ten, diachi, email);
                RequestDispatcher rd = request.getRequestDispatcher("quanly.jsp");
                rd.forward(request, response);
            } catch (Exception e) {
                out.print(e);
            }
            
            
        }
        public void showTK(HttpServletRequest request, HttpServletResponse response,PrintWriter out){
            try {
                    String sdt = request.getParameter("sdt"); 
                    AccountDao dao1 = new AccountDao();
                    int idUser = dao1.getIdUser(sdt);
                    AccountDao dao2 = new AccountDao();
                    Taikhoan tk = dao2.getTK(idUser);
                    request.setAttribute("INFO", tk); 
                    request.getRequestDispatcher("/quanlyuser.jsp").forward(request, response);
                } catch (Exception e) {
            }
        }
}
