/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import Util.HibernateUtil;
import entity.Hoadon;
import entity.Sanpham;
import entity.Taikhoan;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Administrator
 */
public class SanPhamDao {
    private Session ss;
    public SanPhamDao(){ss = HibernateUtil.getSessionFactory().openSession();}
    
    public void insertImg(int id, String url){
        Transaction trans = null;
        try {
            trans = ss.getTransaction();
            trans.begin();
            entity.Sanpham sp = (entity.Sanpham) ss.get(entity.Sanpham.class, id);
            sp.setHinhanh("images/Products/"+url);
            sp.setHinhanh("images/Products/"+url);
            ss.save(sp);
            ss.flush();
            trans.commit();
        } catch (Exception e) {
            if(trans.isActive()){
                trans.rollback();
            }
            e.printStackTrace();
        }
    }
    
    public Sanpham[] getSanPham(String tim, String loai, int min, int max, int soTrang){
         Transaction trans = null;
            String tim1=" ";
            String loai1=" ";
            String dk =" where s.ma>0 ";
            String giatri =" and s.gia between "+min+" and "+max;
        if(!tim.trim().equals("") || !loai.trim().equals(""))
        {
            
            if(!tim.trim().equals(""))
            {
            tim1 =" and s.ten like '%"+tim+"%' or s.ten like '%"+tim+"' or s.ten like '"+tim+"%' ";
            
            }
            if(!loai.trim().equals(""))
            {
                
            loai1 =" and s.loaisanpham.ma = "+loai+" ";
            }
        }
        
        int soTrangMin=0;
        int soTrangMax=0;
        if(soTrang==1){soTrangMin=0; soTrangMax=9;}
        else{
            soTrangMin=(soTrang*9)-9;
            soTrangMax= 9;
        }
        
        try {
            trans = ss.getTransaction();
            trans.begin();
            String sql = "SELECT s FROM Sanpham s "+dk+tim1+loai1+giatri;
            Query query = ss.createQuery(sql);
            query.setFirstResult(soTrangMin);
            query.setMaxResults(soTrangMax);
            List list = query.list();
            Sanpham[] result = new Sanpham[list.size()];
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
}
