/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.database;

/**
 *
 * @author Administrator
 */
public class xoasuasp extends HttpServlet {

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
            String btn = request.getParameter("btn");
            
            if(btn.equals("Xóa"))
            {
                String masp = request.getParameter("masp");
                boolean a = new database().xoasp(masp);
                if(a){
                    request.setAttribute("tb", "Xóa Thành Công");
                    database d = new database();
                    
                    String tim=" "; String loai =" "; int min=0; int max=500000;
                    Cookie[] ck = request.getCookies();
                    if(ck.length>0){
                    for(int i=0;i<ck.length;i++)
                    {
                        String t = ck[i].getName();
                        if(t.equals("tim")){tim= ck[i].getValue();}
                        if(t.equals("loai")){loai = ck[i].getValue();}
                        if(t.equals("min")){min = Integer.parseInt( ck[i].getValue());}
                        if(t.equals("max")){max = Integer.parseInt( ck[i].getValue());}
                    }
                    }
                    
                    ArrayList t = d.laysp(tim, loai, min, max);
                    request.setAttribute("ds", t);
                    
                    RequestDispatcher rd = null;
                    rd = getServletContext().getRequestDispatcher("/quanly.jsp");
                    rd.forward(request, response);
                    
                    return;
                }
                else{
                    request.setAttribute("tb", "Xóa Thất Bại");
                    response.sendRedirect("quanly.jsp");   
                }
            }
            else if(btn.equals("Thêm")){
                String ten = request.getParameter("ten");
                String loai = request.getParameter("loai");
                String mota = request.getParameter("mota");
                String gia = request.getParameter("gia");
                boolean a = new database().themsp(loai, ten, mota, gia);
                if(a){
                    request.setAttribute("tb", "Thêm Thành Công");
                    database d = new database();
                    String tim1=" "; String loai1 =" "; int min1=0; int max1=500000;
                    Cookie[] ck = request.getCookies();
                    if(ck.length>0){
                    for(int i=0;i<ck.length;i++)
                    {
                        String t = ck[i].getName();
                        if(t.equals("tim")){tim1= ck[i].getValue();}
                        if(t.equals("loai")){loai1 = ck[i].getValue();}
                        if(t.equals("min")){min1 = Integer.parseInt( ck[i].getValue());}
                        if(t.equals("max")){max1 = Integer.parseInt( ck[i].getValue());}
                    }
                    }
                    
                    ArrayList t = d.laysp(tim1, loai1, min1, max1);
                    request.setAttribute("ds", t);
                    
                    RequestDispatcher rd = null;
                    rd = getServletContext().getRequestDispatcher("/quanly.jsp");
                    rd.forward(request, response);
                    
                    return;
                }
                else{
                    request.setAttribute("tb", "Thêm Thất Bại");
                    response.sendRedirect("quanly.jsp");   
                }
            }
            else if(btn.equals("Lưu")){
                   
                    String ma = request.getParameter("masp");
                    String ten = request.getParameter("ten");
                    String loai = request.getParameter("loai");
                    String mota = request.getParameter("mota");
                    String gia = request.getParameter("gia");
                    boolean a = new database().suasp(ma, loai, ten, mota, gia);
                    if(a){
                        request.setAttribute("tb", "Sửa Thành Công");
                    database d = new database();
                    String tim1=" "; String loai1 =" "; int min1=0; int max1=500000;
                    Cookie[] ck = request.getCookies();
                    if(ck.length>0){
                    for(int i=0;i<ck.length;i++)
                    {
                        String t = ck[i].getName();
                        if(t.equals("tim")){tim1= ck[i].getValue();}
                        if(t.equals("loai")){loai1 = ck[i].getValue();}
                        if(t.equals("min")){min1 = Integer.parseInt( ck[i].getValue());}
                        if(t.equals("max")){max1 = Integer.parseInt( ck[i].getValue());}
                    }
                    }
                    
                    ArrayList t = d.laysp(tim1, loai1, min1, max1);
                    request.setAttribute("ds", t);
                    
                    RequestDispatcher rd = null;
                    rd = getServletContext().getRequestDispatcher("/quanly.jsp");
                    rd.forward(request, response);
                    
                    return;
                }
                else{
                    request.setAttribute("tb", "Sửa Thất Bại");
                    response.sendRedirect("quanly.jsp");   
                }
                    
                   out.print(ma + ten + loai + mota + gia);
                  
            }
        }
        catch(Exception e){}
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

}


