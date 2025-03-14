package com.test.java.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.test.util.DBUtil;

public class MapDAO {

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	private DBUtil util;
	
	public MapDAO() {
		try {
			util = new DBUtil();
			conn = util.open();
			stat = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<MapDTO> list() {
		
		try {
			
			String sql = "select * from tblMarker";
			rs = stat.executeQuery(sql);
			
			ArrayList<MapDTO> list = new ArrayList<MapDTO>();
			
			while (rs.next()) {
				//레코드 1줄 > DTO 1개
				MapDTO dto = new MapDTO();
				dto.setSeq(rs.getString("seq"));
				dto.setLat(rs.getString("lat"));
				dto.setLng(rs.getString("lng"));
				list.add(dto);
			}
			
			return list;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

    public void add(MapDTO dto) {
        
        try {
            String sql = "insert into tblMarker(seq, lat, lng) values (seqMarker.nextVal, ?, ?)";
            pstat = conn.prepareStatement(sql);
            pstat.setString(1, dto.getLat());
            pstat.setString(2, dto.getLng());
            
            pstat.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
	
}










