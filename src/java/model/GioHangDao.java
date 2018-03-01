/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import Util.HibernateUtil;
import entity.Chitiethoadon;
import entity.Hoadon;
import entity.Taikhoan;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Administrator
 */
public class GioHangDao {
     private Session ss;
    public GioHangDao(){ss = HibernateUtil.getSessionFactory().openSession();}
    
    public Hoadon[] getHoaDon(int idUser, String tinhtrang){
         Transaction trans = null;
        try {
            trans = ss.getTransaction();
            trans.begin();
            String sql = "select h From Hoadon h where h.taikhoan.id = ? and h.tinhtrang like ?";
            Query query = ss.createQuery(sql);
            query.setInteger(0, idUser);
            query.setString(1, tinhtrang);
            List list = query.list();
            Hoadon[] result = new Hoadon[list.size()];
            list.toArray(result);
            trans.commit();
            return result;
        } catch (Exception e) {
            if(trans.isActive()){
                trans.rollback();
            }
            e.printStackTrace();
            return null;
        }
     }
    
        public boolean taoHoaDon(int idUser, String tinhtrang){
         Transaction trans = null;
        try {
            trans = ss.getTransaction();
            trans.begin();
            Taikhoan tk = (Taikhoan) ss.get(Taikhoan.class,idUser);
            Hoadon hd = new Hoadon(tk, new Date(),"Chưa thanh toán");
            ss.save(hd);
           // ss.flush();
            trans.commit();
            return true;
        } catch (Exception e) {
            if(trans.isActive()){
                trans.rollback();
            }
            e.printStackTrace();
            return false;
        }
     }
    
     public int getIDHoaDon(int idUser, String tinhtrang){
         Transaction trans = null;
        try {
            trans = ss.getTransaction();
            trans.begin();
            String sql = "From Hoadon where mauser = ? and tinhtrang like ?";
            Query query = ss.createQuery(sql);
            query.setInteger(0, idUser);
            query.setString(1, tinhtrang);
            List list = query.list();
            Hoadon[] result = new Hoadon[list.size()];
            list.toArray(result);
            trans.commit();
            return result[0].getId();
        } catch (Exception e) {
            if(trans.isActive()){
                trans.rollback();
            }
            e.printStackTrace();
            return -1;
        }
     }
     
     public boolean themMonCTHoaDon(int idSP, int idHD,int sl){
         Transaction trans = null;
        try {
            trans = ss.getTransaction();
            trans.begin();
            entity.Sanpham sp = (entity.Sanpham) ss.get(entity.Sanpham.class,idSP);
            Hoadon hd = (Hoadon) ss.get(Hoadon.class, idHD);
            Chitiethoadon cthd = new Chitiethoadon(hd,sp,sl);
            ss.save(cthd);
           // ss.flush();
            trans.commit();
            return true;
        } catch (Exception e) {
            if(trans.isActive()){
                trans.rollback();
            }
            e.printStackTrace();
            return false;
        }
     }
     
     public boolean themSlCTHoaDon(int idSP, int idHD,int sl){
         Transaction trans = null;
        try {
            trans = ss.getTransaction();
            trans.begin();
            String sql = "From Chitiethoadon c where mahoadon = ? and masanpham = ?";
            Query query = ss.createQuery(sql);
            query.setInteger(0, idHD);
            query.setInteger(1, idSP);
            List list = query.list();
            Chitiethoadon[] result = new Chitiethoadon[list.size()];
            list.toArray(result);
            
            if(result.length>0){
                int maCTHD= result[0].getId();
                Chitiethoadon cthd = (Chitiethoadon) ss.get(Chitiethoadon.class, maCTHD);
                cthd.setSoluong(cthd.getSoluong()+sl);
                ss.save(cthd);
                ss.flush();
                trans.commit();
                return true;
            }
            else{
                entity.Sanpham sp = (entity.Sanpham) ss.get(entity.Sanpham.class,idSP);
                Hoadon hd = (Hoadon) ss.get(Hoadon.class, idHD);
                Chitiethoadon cthd = new Chitiethoadon(hd,sp,1);
                cthd.setSoluong(sl);
                ss.save(cthd);
                // ss.flush();
                trans.commit();
            return true;
            }
            
        } catch (Exception e) {
            if(trans.isActive()){
                trans.rollback();
            }
            e.printStackTrace();
            return false;
        }
     }
     

     
     public Chitiethoadon[] getChiTietHD(int mahd){
        Transaction trans = null;
        try {
            trans = ss.getTransaction();
            trans.begin();
            String sql = "From Chitiethoadon c where mahoadon = ?";
            Query query = ss.createQuery(sql);
            query.setInteger(0, mahd);
            List list = query.list();
            Chitiethoadon[] result = new Chitiethoadon[list.size()];
            list.toArray(result);
            trans.commit();
            return  result;
        } catch (Exception e) {
            if(trans.isActive()){
                trans.rollback();
            }
            e.printStackTrace();
        }
        return null;
    }
     
     public Chitiethoadon[] getChiTietHDGroup(int mahd){
        Transaction trans = null;
        try {
            trans = ss.getTransaction();
            trans.begin();
            String sql = "SELECT c from Chitiethoadon c inner join hoadon h on c.mahoadon = h.id where h.id =?";
            Query query = ss.createQuery(sql);
            query.setInteger(0, mahd);
            List list = query.list();
            Chitiethoadon[] result = new Chitiethoadon[list.size()];
            list.toArray(result);
            trans.commit();
            return  result;
        } catch (Exception e) {
            if(trans.isActive()){
                trans.rollback();
            }
            e.printStackTrace();
        }
        return null;
    }
     
     public void xoaChiTietHoaDon(int maCthd)
     {
         Transaction trans = null;
        try {
            trans = ss.getTransaction();
            trans.begin();
            Chitiethoadon dc = (Chitiethoadon) ss.get(Chitiethoadon.class, maCthd);
            ss.delete(dc);
            ss.flush();
            trans.commit();
        } catch (Exception e) {
            if(trans.isActive()){
                trans.rollback();
            }
            e.printStackTrace();
        }
     }
     
     public void updateHoaDon (int idHD,String tinhtrang){
        Transaction trans = null;
        try {
            trans = ss.getTransaction();
            trans.begin();
            Hoadon hd = (Hoadon) ss.get(Hoadon.class, idHD);
            hd.setTinhtrang(tinhtrang);
            ss.save(hd);
            ss.flush();
            trans.commit();
        } catch (Exception e) {
            if(trans.isActive()){
                trans.rollback();
            }
            e.printStackTrace();
        }
    }
    
     public void tanggiamHoaDon (int idCtdh,int sl){
        Transaction trans = null;
        try {
            trans = ss.getTransaction();
            trans.begin();
            Chitiethoadon cthd = (Chitiethoadon) ss.get(Chitiethoadon.class, idCtdh);
            cthd.setSoluong(cthd.getSoluong()+sl);
            
            ss.save(cthd);
            ss.flush();
            trans.commit();
            if(cthd.getSoluong()<=0)
            {
                xoaChiTietHoaDon(idCtdh);
            }
        } catch (Exception e) {
            if(trans.isActive()){
                trans.rollback();
            }
            e.printStackTrace();
        }
    }
}
