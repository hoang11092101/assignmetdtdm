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
import model.PageEnity;
import model.SanPhamDao;
import model.database;

/**
 *
 * @author Administrator
 */
public class ChuyenTrangSP extends HttpServlet {

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
        try{
           int action = Integer.parseInt(request.getParameter("action"));
            SanPhamDao dao = new SanPhamDao();
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
            database d = new database();     
            ArrayList tt = d.laysp(tim, loai, min, max);
            ArrayList<PageEnity> page = new ArrayList<PageEnity>();
            int tr = 0;
            if(tt.size()%9==0){
                for(int i =0; i<tt.size()/9;i++){
                tr+=1;
                PageEnity p = new PageEnity();
                p.setStt(tr);
                page.add(p);
                }   
            }
            else{
                for(int i =0; i<tt.size()/9;i++){
                tr+=1;
                PageEnity p = new PageEnity();
                p.setStt(tr);
                page.add(p);
                }   
                tr+=1;
                PageEnity p = new PageEnity();
                p.setStt(tr);
                page.add(p);
            } 
           request.setAttribute("slSP", page);
           request.setAttribute("ds", dao.getSanPham(tim, loai, min, max, action));
           request.setAttribute("vitri", action);
           request.setAttribute("slmax", tr);
           RequestDispatcher rd = null;
           rd = getServletContext().getRequestDispatcher("/sanpham.jsp");
           rd.forward(request, response);
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

}
