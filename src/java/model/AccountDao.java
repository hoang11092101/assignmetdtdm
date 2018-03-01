/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import Util.HibernateUtil;
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
public class AccountDao {
     private Session ss;
    public AccountDao(){ss = HibernateUtil.getSessionFactory().openSession();}
    
    
    public String signUpUser(String sdt, String matkhau, String ten, String diachi, String email){
         Transaction trans = null;
        try {
            trans = ss.getTransaction();
            trans.begin();
            Taikhoan nv = new Taikhoan(sdt,matkhau,ten,diachi,email,new Date(),"user");
            ss.save(nv);
           // ss.flush();
            trans.commit();
            return "OK";
        } catch (Exception e) {
            if(trans.isActive()){
                trans.rollback();
            }
            e.printStackTrace();
            return e+"";
        }
     }
    
     public int signIn(String sdt, String matkhau){
         Transaction trans = null;
        try {
           trans = ss.getTransaction();
            trans.begin();
            String sql = "From Taikhoan where sdt like ? and matkhau like ?";
            Query query = ss.createQuery(sql);
            query.setString(0, "%"+sdt+"%");
            query.setString(1, "%"+matkhau+"%");
            List list = query.list();
            ss.close();
            return list.size();
        } catch (Exception e) {
            if(trans.isActive()){
                trans.rollback();
            }
            e.printStackTrace();
            return 0;
        }
     }
     
     public String search(String sdt){
        Transaction trans = null;
        try {
            trans = ss.getTransaction();
            trans.begin();
            String sql = "From Taikhoan where sdt like ?";
            Query query = ss.createQuery(sql);
            query.setString(0, "%"+sdt+"%");
            List list = query.list();
            Taikhoan[] result = new Taikhoan[list.size()];
            list.toArray(result);
            trans.commit();
            return  result[0].getLoaitk();
        } catch (Exception e) {
            if(trans.isActive()){
                trans.rollback();
            }
            e.printStackTrace();
            return e+"0";
        }
        
    }
     
     public int getIdUser(String sdt){
        Transaction trans = null;
        try {
            trans = ss.getTransaction();
            trans.begin();
            String sql = "From Taikhoan where sdt like ?";
            Query query = ss.createQuery(sql);
            query.setString(0, "%"+sdt+"%");
            List list = query.list();
            Taikhoan[] result = new Taikhoan[list.size()];
            list.toArray(result);
            trans.commit();
            return  result[0].getId();
        } catch (Exception e) {
            if(trans.isActive()){
                trans.rollback();
            }
            e.printStackTrace();
            return -1;
        }
        
    }
     
     public Taikhoan getTK(int id){
        Transaction trans = null;
        try {
            trans = ss.getTransaction();
            trans.begin();
            String sql = "From Taikhoan where id = ?";
            Query query = ss.createQuery(sql);
            query.setInteger(0, id);
            List list = query.list();
            Taikhoan[] result = new Taikhoan[list.size()];
            list.toArray(result);
            trans.commit();
            return  result[0];
        } catch (Exception e) {
            if(trans.isActive()){
                trans.rollback();
            }
            e.printStackTrace();
            return null;
        }
        
    }
     
     public Taikhoan[] getTKList(){
        Transaction trans = null;
        try {
            trans = ss.getTransaction();
            trans.begin();
            String sql = "From Taikhoan";
            Query query = ss.createQuery(sql);
            List list = query.list();
            Taikhoan[] result = new Taikhoan[list.size()];
            list.toArray(result);
            trans.commit();
            return  result;
        } catch (Exception e) {
            if(trans.isActive()){
                trans.rollback();
            }
            e.printStackTrace();
            return null;
        }}
     
        public void updateTK (int id,String sdt, String ten, String diachi, String email){
        Transaction trans = null;
        try {
            trans = ss.getTransaction();
            trans.begin();
            Taikhoan tk = (Taikhoan) ss.get(Taikhoan.class, id);
            tk.setSdt(sdt);
            tk.setTen(ten);
            tk.setDiachi(diachi);
            tk.setEmail(email);
            ss.save(tk);
            ss.flush();
            trans.commit();
        } catch (Exception e) {
            if(trans.isActive()){
                trans.rollback();
            }
            e.printStackTrace();
        }
    }
       
    public void resetTKMK (int id){
        Transaction trans = null;
        try {
            trans = ss.getTransaction();
            trans.begin();
            Taikhoan tk = (Taikhoan) ss.get(Taikhoan.class, id);
            tk.setMatkhau("e10adc3949ba59abbe56e057f20f883e");
            ss.save(tk);
            ss.flush();
            trans.commit();
        } catch (Exception e) {
            if(trans.isActive()){
                trans.rollback();
            }
            e.printStackTrace();
        }
    }
        
    public void deleteTK (int id){
        Transaction trans = null;
        try {
            trans = ss.getTransaction();
            trans.begin();
            Taikhoan tk = (Taikhoan) ss.get(Taikhoan.class, id);
            ss.delete(tk);
            ss.flush();
            trans.commit();
        } catch (Exception e) {
            if(trans.isActive()){
                trans.rollback();
            }
            e.printStackTrace();
        }
    }
    
    public void insertImg(int id, String url){
        Transaction trans = null;
        try {
            trans = ss.getTransaction();
            trans.begin();
            Taikhoan tk = (Taikhoan) ss.get(Taikhoan.class, id);
            tk.setHinhanh("images/Users/"+url);
            tk.setHinhanh("images/Users/"+url);
            ss.save(tk);
            ss.flush();
            trans.commit();
        } catch (Exception e) {
            if(trans.isActive()){
                trans.rollback();
            }
            e.printStackTrace();
        }
    }
    
     public String getMK(int idTK){
         Transaction trans = null;
        try {
            trans = ss.getTransaction();
            trans.begin();
            Taikhoan sp = (Taikhoan) ss.get(Taikhoan.class,idTK);
            trans.commit();
            return sp.getMatkhau();
        } catch (Exception e) {
            if(trans.isActive()){
                trans.rollback();
            }
            e.printStackTrace();
            return null;
        }
     }
     
     public boolean setMK(int idTK,String mkmoi){
         Transaction trans = null;
        try {
            trans = ss.getTransaction();
            trans.begin();
            Taikhoan sp = (Taikhoan) ss.get(Taikhoan.class,idTK);
            sp.setMatkhau(mkmoi);
            ss.save(sp);
            ss.flush();
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
}
