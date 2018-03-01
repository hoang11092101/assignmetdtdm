/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import entity.Hoadon;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.AccountDao;
import model.GioHangDao;
import model.database;

/**
 *
 * @author Administrator
 */
public class ThemVaoGio extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        try{
           String action = request.getParameter("btn");
           switch(action){
               case "Thêm": themgiohang(request, response, out);break;
           }
        }
        catch(Exception e){
            
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

    
        private void themgiohang(HttpServletRequest request, HttpServletResponse response,PrintWriter out ){
        try{
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
            if(dao.getHoaDon(idUser, "Chưa thanh toán").length<=0){
                GioHangDao dao2 = new GioHangDao();
                if(dao2.taoHoaDon(idUser, "Chưa thanh toán"))
                {
                    GioHangDao dao3 = new GioHangDao();
                    int mahd = dao3.getIDHoaDon(idUser, "Chưa thanh toán");
                    int sl = Integer.parseInt(request.getParameter("sl"));
                    GioHangDao dao4 = new GioHangDao();
                    dao4.themSlCTHoaDon(Integer.parseInt(request.getParameter("masp")), mahd,sl);
                }
            }
            else{
                    GioHangDao dao3 = new GioHangDao();
                    int mahd = dao3.getIDHoaDon(idUser, "Chưa thanh toán");
                    int sl = Integer.parseInt(request.getParameter("sl"));
                    GioHangDao dao4 = new GioHangDao();
                    dao4.themSlCTHoaDon(Integer.parseInt(request.getParameter("masp")), mahd,sl);
                    
                    
            }
                 String tim=" "; String loai =" "; int min=0; int max=500000;
                    Cookie[] cc = request.getCookies();
                    if(cc.length>0){
                    for(int i=0;i<cc.length;i++)
                    {
                        String t = cc[i].getName();
                        if(t.equals("tim")){tim= cc[i].getValue();}
                        if(t.equals("loai")){loai = cc[i].getValue();}
                        if(t.equals("min")){min = Integer.parseInt( cc[i].getValue());}
                        if(t.equals("max")){max = Integer.parseInt( cc[i].getValue());}
                    }
                    }
                    database d = new database();
                    ArrayList t = d.laysp(tim, loai, min, max);
                    request.setAttribute("ds", t);
                    
                    RequestDispatcher rd = null;
                    rd = getServletContext().getRequestDispatcher("/sanpham.jsp");
                    rd.forward(request, response);
           
        }
        catch(Exception e){
            out.print(e);
        }
    }
}
