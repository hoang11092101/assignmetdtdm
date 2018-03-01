/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import org.omg.CORBA.TCKind;

/**
 *
 * @author Administrator
 */
public class database {
    
    String sql="com.microsoft.sqlserver.jdbc.SQLServerDriver";
    String cn ="jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=tocoffee;user=sa;password=123456";
    public database(){}
    
    public ArrayList<sanpham> laysp(String tim, String loai, int min, int max){
        ArrayList<sanpham> tt = new ArrayList<>();
        try
        {
            String tim1=" ";
            String loai1=" ";
            String dk =" where ma>0 ";
            String giatri =" and gia between "+min+" and "+max;
        if(!tim.trim().equals("") || !loai.trim().equals(""))
        {
            
            if(!tim.trim().equals(""))
            {
            tim1 =" and ten like '%"+tim+"%' or ten like '%"+tim+"' or ten like '"+tim+"%' ";
            
            }
            if(!loai.trim().equals(""))
            {
                
            loai1 =" and loai = "+loai+" ";
            }
        }
            
            Class.forName(sql);
            Connection conn = DriverManager.getConnection(cn);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from sanpham"+dk +tim1+loai1+giatri);
            while(rs.next())
            {
                sanpham a = new sanpham();
                a.setMa(rs.getInt(1));
                a.setLoai(rs.getInt(2));
                a.setTen(rs.getString(3));
                a.setMota(rs.getString(4));
                a.setGia(rs.getInt(5));
                a.setHinhanh(rs.getString(6));
                tt.add(a);
            }
            rs.close();
            st.close();
            conn.close();
            return tt;
        }
        catch(Exception e){
        return tt;
        }
        
    }
    
    public boolean xoasp(String masp){
        try{
            Class.forName(sql);
            Connection con = DriverManager.getConnection(cn);
            Statement st = con.createStatement();
            st.execute("delete from sanpham where ma ="+masp);
            st.close();
            con.close();
            return true;
        }
        catch(Exception e){return false;}
        
    }
    
    public boolean themsp(String loai, String ten, String mota, String gia)
    {
        try{
            Class.forName(sql);
            Connection con = DriverManager.getConnection(cn);
            Statement st = con.createStatement();
            st.execute("insert into sanpham values ("+loai+",N'"+ten+"',N'"+mota+"',"+gia+",'')");
            st.close();
            con.close();
            return true;
        }
        catch(Exception e){return false;}
            
        
    }
    
    public boolean suasp(String ma, String loai, String ten, String mota, String gia){
        try {
            Class.forName(sql);
            Connection con = DriverManager.getConnection(cn);
            Statement st = con.createStatement();
            st.executeUpdate("update sanpham set loai ="+loai+", ten=N'"+ten+"', mota=N'"+mota+"', gia= "+gia+" where ma ="+ma);
            st.close();
            con.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
