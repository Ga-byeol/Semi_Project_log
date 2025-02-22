package com.car.db;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.catalina.connector.Response;

public class CarInfoDAO {
	Connection con=null;
	PreparedStatement pstmt2=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	
	public Connection getConnection() throws Exception {
		Context init=new InitialContext();
		DataSource ds=(DataSource)init.lookup("java:comp/env/jdbc/Mysql");
		con=ds.getConnection();
		return con;
	}
	
	
	public void close() {
		if(con!=null) try{con.close();}catch(SQLException ex) {}
		if(pstmt!=null) try{pstmt.close();}catch(SQLException ex) {}
		if(pstmt2!=null) try{pstmt2.close();}catch(SQLException ex) {}
		if(rs!=null) try{rs.close();}catch(SQLException ex) {}
	}

	public void insertCar(CarInfoDTO dto) {
		try {
			con = getConnection();
			
			String sql="insert into carinfo(car_num, car_place, per_hour, car_type, car_year, car_model, car_brand, car_image, car_fuel) "
					 + "value(?,?,?,?,?,?,?,?,?)";
			pstmt =con.prepareStatement(sql);
			pstmt.setString(1, dto.getCar_num());
			pstmt.setString(2, dto.getCar_place());
			pstmt.setInt(3, dto.getPer_hour());
			pstmt.setString(4, dto.getCar_type());
			pstmt.setInt(5, dto.getCar_year());
			pstmt.setString(6, dto.getCar_model());
			pstmt.setString(7, dto.getCar_brand());
			pstmt.setString(8, dto.getCar_image());
			pstmt.setString(9, dto.getCar_fuel());
			pstmt.executeUpdate();
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
	}
	
	public List<CarInfoDTO> getCarList(int startRow,int pageSize) {
		List<CarInfoDTO> carList = new ArrayList<CarInfoDTO>();
		try {
			con = getConnection();
			String sql="select * from carinfo order by car_type limit ?,? ";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, startRow-1);
			pstmt.setInt(2, pageSize);
			
			rs=pstmt.executeQuery();
			
			while (rs.next()) {
				CarInfoDTO dto=new CarInfoDTO();
				dto.setCar_num(rs.getString("car_num"));
				dto.setCar_place(rs.getString("car_place"));
				dto.setPer_hour(rs.getInt("per_hour"));
				dto.setCar_type(rs.getString("car_type"));
				dto.setCar_year(rs.getInt("car_year"));
				dto.setCar_model(rs.getString("car_model"));
				dto.setCar_brand(rs.getString("car_brand"));
				dto.setCar_image(rs.getString("car_image"));
				dto.setCar_fuel(rs.getString("car_fuel"));
				
				carList.add(dto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return carList;
	}// getBoardList
	
	public CarInfoDTO getCar(String car_num) {
		CarInfoDTO dto = null;
		try {
			con = getConnection();
			String sql="select * from carinfo where car_num=? ";
			pstmt =con.prepareStatement(sql);
			pstmt.setString(1, car_num);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto = new CarInfoDTO();
				dto.setCar_num(rs.getString("car_num"));
				dto.setCar_place(rs.getString("car_place"));
				dto.setPer_hour(rs.getInt("per_hour"));
				dto.setCar_type(rs.getString("car_type"));
				dto.setCar_year(rs.getInt("car_year"));
				dto.setCar_model(rs.getString("car_model"));
				dto.setCar_brand(rs.getString("car_brand"));
				dto.setCar_image(rs.getString("car_image"));
				dto.setCar_fuel(rs.getString("car_fuel"));
			} 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return dto;
	}

	
	public void updateCar(CarInfoDTO dto, boolean imageChange, String filepath) {
		System.out.println("updateCar = " + dto.getCar_num());
		CarInfoDTO dto2 = getCar(dto.getCar_num());
		try {
			if(imageChange) {
				String deleteImage = dto2.getCar_image();
				File deleteImageName = new File (filepath + "\\" + deleteImage);
				
				deleteImageName.delete();
			}
				con = getConnection();
				String sql="update carinfo set car_place=?, per_hour=?, car_type=?, car_year=?, car_model=?, car_brand=?, car_fuel=?, car_image=? where car_num=?";
				pstmt =con.prepareStatement(sql);
				pstmt.setString(1, dto.getCar_place());
				pstmt.setInt(2, dto.getPer_hour());
				pstmt.setString(3, dto.getCar_type());
				pstmt.setInt(4, dto.getCar_year());
				pstmt.setString(5, dto.getCar_model());
				pstmt.setString(6, dto.getCar_brand());
				pstmt.setString(7, dto.getCar_fuel());
				// ?λ‘μ΄ ??Ό?΄ ?λ‘λ?λ©? ?λ‘λ? ??ΌλͺμΌλ‘? ??  /
				// κΈ°μ‘΄ ??Ό? ?¬?©?λ©? κΈ°μ‘΄??ΌλͺμΌλ‘? ?€? ?? ₯
				if(imageChange) {
					pstmt.setString(8, dto.getCar_image());
				} else {
					pstmt.setString(8, dto2.getCar_image());
				}
				pstmt.setString(9, dto2.getCar_num());
				pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}	
		return;
	}	
	
	public void deleteCar(String car_num) {
		try {
			con = getConnection();
			String sql="delete from carinfo where car_num = ?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, car_num);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}	
		return;
	}
	
	public int getCarCount() {
		int count=0;
		try {
			con = getConnection();
			String sql="select count(*) from carinfo";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				count=rs.getInt("count(*)"); // ?΄?΄λ¦? count(*)
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return count;
	}
	
	public int carNumCheck(String car_num) {
		// μ°¨λ? λ³? ?±λ‘? ? μ°? λ²νΈ μ€λ³΅μ²΄ν¬
		int count=0;
		try {
			con=getConnection();
			String sql="select count(*) from carinfo where car_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, car_num);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				count=rs.getInt("count(*)"); // ?΄?΄λ¦? count(*)
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}	
		System.out.println("count = " +count);
		return count;
	}
	
	// ?΄ κΊ?
	public List<CarInfoDTO> getCarInfoList() {
		List<CarInfoDTO> carinfoList = new ArrayList<CarInfoDTO>();
		try {
			con = getConnection();
			String sql = "select * from carinfo";
			pstmt = con.prepareStatement(sql);
            rs=pstmt.executeQuery();
            
            while(rs.next()) {
            	CarInfoDTO dto = new CarInfoDTO();
            	dto.setCar_num(rs.getString("car_num"));
            	dto.setCar_place(rs.getString("car_place"));
            	dto.setPer_hour(rs.getInt("per_hour"));
            	dto.setCar_type(rs.getString("car_type"));
            	dto.setCar_year(rs.getInt("car_year"));
            	dto.setCar_model(rs.getString("car_model"));
            	dto.setCar_brand(rs.getString("car_brand"));
            	dto.setCar_image(rs.getString("car_image"));
            	dto.setCar_fuel(rs.getString("car_fuel"));
            	carinfoList.add(dto);
            }
		} catch (Exception e) {
            e.printStackTrace();
        } finally {
        	close();
        }
		return carinfoList;
	}
}
