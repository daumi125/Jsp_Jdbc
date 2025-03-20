package com.test.java.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.test.util.DBUtil;

public class AjaxDAO {
    
    private Connection conn;
    private Statement stat;
    private PreparedStatement pstat;
    private ResultSet rs;
    private DBUtil util;
    
    public AjaxDAO() {
        try {
            util = new DBUtil();
            conn = util.open();
            stat = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<SurveyDTO> listSurvey() {
        
        try {
            
            List<SurveyDTO> list = new ArrayList<SurveyDTO>();
            
            String sql = "select * from tblSurvey where seq = 1";
            
            rs = stat.executeQuery(sql);
            
            while (rs.next()) {
                //레코드 1줄 > DTO 1개
                SurveyDTO dto = new SurveyDTO();
                dto.setSeq(rs.getString("seq"));
                dto.setQuestion(rs.getString("question"));
                dto.setItem1(rs.getString("item1"));
                dto.setItem2(rs.getString("item2"));
                dto.setItem3(rs.getString("item3"));
                dto.setItem4(rs.getString("item4"));
                dto.setCnt1(rs.getString("cnt1"));
                dto.setCnt2(rs.getString("cnt2"));
                dto.setCnt3(rs.getString("cnt3"));
                dto.setCnt4(rs.getString("cnt4"));
                
                list.add(dto);
            }
            
            return list;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }

    public String getJikwi(String txt1) {
        
        try {
            
            String sql = "select jikwi from tblInsa where name = ?";
            
            pstat = conn.prepareStatement(sql);
            pstat.setString(1, txt1);
            
            rs = pstat.executeQuery();
            
            if (rs.next()) {
                return rs.getString("jikwi");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }

    public void updateBasicpay(String txt2) {
        
        try {
            
            String sql = "update tblInsa set basicpay = basicpay + 1000000 where name = ?";
            
            pstat = conn.prepareStatement(sql);
            pstat.setString(1, txt2);
            
            pstat.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    public int checkId(String id) {
        
        try {
            
            String sql = "select count(*) as cnt from tblUser where id = ?";
            
            pstat = conn.prepareStatement(sql);
            pstat.setString(1, id);
            
            rs = pstat.executeQuery();
            
            if (rs.next()) {
                return rs.getInt("cnt");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return 0;
    }

    public ArrayList<UserDTO> listUser() {
        
        try {
            
            String sql = "select * from tblUser";
            
            rs = stat.executeQuery(sql);
            
            ArrayList<UserDTO> list = new ArrayList<UserDTO>();
            
            while (rs.next()) {
                
                UserDTO dto = new UserDTO();
                
                dto.setId(rs.getString("id"));
                dto.setPw(rs.getString("pw"));
                dto.setName(rs.getString("name"));
                dto.setLv(rs.getString("lv"));
                
                list.add(dto);
            }
            
            return list;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }

    public ArrayList<ZipcodeDTO> listZipCode(String dong) {
        
        try {
            
            String sql = "select * from zipcode"
                            + " where dong like '%' || ? || '%'";
                            
            pstat = conn.prepareStatement(sql);
            pstat.setString(1, dong);
            
            rs = pstat.executeQuery();
            
            ArrayList<ZipcodeDTO> list = new ArrayList<ZipcodeDTO>();
            
            while (rs.next()) {
                //레코드 1줄 > DTO 1개
                ZipcodeDTO dto = new ZipcodeDTO();
                
                dto.setSeq(rs.getString("seq"));
                dto.setSido(rs.getString("sido"));
                dto.setGugun(rs.getString("gugun"));
                dto.setDong(rs.getString("dong"));
                dto.setBunji(rs.getString("bunji"));
                dto.setZipcode(rs.getString("zipcode"));
                
                list.add(dto);                      
            }
            
            return list;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }

    public int add(CatDTO dto) {
        
        try {
            
            String sql = "insert into tblCat (catid, src, x, y) values (?, ?, 0, 0)";
            pstat = conn.prepareStatement(sql);
            pstat.setString(1, dto.getCatid());
            pstat.setString(2, dto.getSrc());
            
            return pstat.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return 0;
    }

    public int getMaxCatId() {
        
        try {
            
            String sql = "select max(to_number(catid)) as catid from tblCat";
            
            rs = stat.executeQuery(sql);
            
            if (rs.next()) {
                return rs.getInt("catid");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return 0;
    }

    public int edit(CatDTO dto) {
        
        try {
            
            String sql = "update tblCat set x = ?, y = ? where catid = ?";
            
            pstat = conn.prepareStatement(sql);
            pstat.setString(1, dto.getX());
            pstat.setString(2, dto.getY());
            pstat.setString(3, dto.getCatid());
            
            return pstat.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return 0;
    }

    public ArrayList<CatDTO> listCat() {
        
        try {
            
            String sql = "select * from tblCat order by to_number(catid) asc";
            
            rs = stat.executeQuery(sql);
            
            ArrayList<CatDTO> list = new ArrayList<CatDTO>();
            
            while (rs.next()) {
                CatDTO dto = new CatDTO();
                dto.setCatid(rs.getString("catid"));
                dto.setSrc(rs.getString("src"));
                dto.setX(rs.getString("x"));
                dto.setY(rs.getString("y"));
                list.add(dto);
            }
            
            return list;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }

    public int addAddress(AddressDTO dto) {
        try {
            
            String sql = "insert into tblAddress (seq, name, age, gender, tel, address, regdate) values (seqAddress.nextVal, ?,?,?,?,?,default)";
            pstat = conn.prepareStatement(sql);
            pstat.setString(1, dto.getName());
            pstat.setString(2, dto.getAge());
            pstat.setString(3, dto.getGender());
            pstat.setString(4, dto.getTel());
            pstat.setString(5, dto.getAddress());
            
            return pstat.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return 0;
    }

    public ArrayList<AddressDTO> listAddress() {
        try {
            String sql = "select * from tblAddress order by seq desc";
            
            rs = stat.executeQuery(sql);
            
            ArrayList<AddressDTO> list = new ArrayList<>();
            
            while (rs.next()) {
                AddressDTO dto = new AddressDTO();
                dto.setSeq(rs.getString("seq"));
                dto.setAddress(rs.getString("address"));
                dto.setAge(rs.getString("age"));
                dto.setGender(rs.getString("gender"));
                dto.setName(rs.getString("name"));
                dto.setRegdate(rs.getString("regdate"));
                dto.setTel(rs.getString("tel"));
                
                list.add(dto);
            }
            
            return list;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }

}























