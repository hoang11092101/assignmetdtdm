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
public class searchql extends HttpServlet {

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
            String tim ="";
            String loai ="";
            int min=0;
            int max=500000;
            String[] all = request.getParameterValues("all");
            try{
                if(all.length>0){}
            }
            catch(Exception e){
                tim = request.getParameter("txttim");
                loai = request.getParameter("loai");
                min = Integer.parseInt(request.getParameter("min"));
                max = Integer.parseInt(request.getParameter("max"));}
            taocookie(tim, loai, request.getParameter("min"), request.getParameter("max"), request, response);
            database d = new database();
            ArrayList t = d.laysp(tim, loai, min, max);
            
            request.setAttribute("ds", t);
            RequestDispatcher rd = null;
            rd = getServletContext().getRequestDispatcher("/quanly.jsp");
            rd.forward(request, response); 
        }
        catch(Exception e)
        {
            
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

    public void taocookie(String tim, String loai, String min, String max, HttpServletRequest rq, HttpServletResponse rp){
            
            Cookie luutim = new Cookie("tim",tim);
            Cookie luuloai = new Cookie("loai",loai);
            Cookie luumin = new Cookie("min", min);
            Cookie luumax = new Cookie("max", max);
            luutim.setMaxAge(60*60); 
            luuloai.setMaxAge(60*60);
            luumin.setMaxAge(60*60);
            luumax.setMaxAge(60*60);
            rp.addCookie( luutim );
            rp.addCookie( luuloai );
            rp.addCookie(luumin);
            rp.addCookie(luumax);
    }
}
