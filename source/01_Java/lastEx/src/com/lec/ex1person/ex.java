package com.lec.ex1_person;
//1:입력/ 2:직업별조회 3: 전체조회 / 그외 종료
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Perso2n {
	public static void main(String[] args) {
			String driver = "oracle.jdbc.driver.OracleDriver";
			String url    = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
			Connection        conn = null;
			PreparedStatement pstmt = null; // 1, 2번 기능
			Statement         stmt  = null; // 3번 기능
			ResultSet         rs    = null;
			Scanner           sc    = new Scanner(System.in);
			String fn, sql;
			try {
				Class.forName(driver); // (1)드라이버 로드
			} catch (ClassNotFoundException e) {
				System.out.println(e.getMessage());
			}// (1)드라이버 로드
			do {
				System.out.println("1:입력 || 2:직업별조회 || 3:전체조회 || 그외:종료");
				fn = sc.next(); // 1,2,3,a
				switch(fn) {
				case "1": // 이름, 직업명, 국, 영, 수 입력받아 insert
					// 2~7단계
					sql = "INSERT INTO PERSON " + 
							" VALUES (PERSON_NO_SQ.NEXTVAL, ?, " + 
							"(SELECT JNO FROM JOB WHERE "
							+ "JNAME=?), ?,?,?)";
					try {
						conn = DriverManager.getConnection(url, "scott", "tiger");//(2)
						pstmt = conn.prepareStatement(sql);//(3)
						System.out.print("입력할 이름은?");
						pstmt.setString(1, sc.next());
						System.out.print("직업은(배우,가수,엠씨) ?");
						pstmt.setString(2, sc.next());
						System.out.print("국어는 ?");
						pstmt.setInt(3, sc.nextInt());
						System.out.print("영어는 ?");
						pstmt.setInt(4, sc.nextInt());
						System.out.print("수학는 ?");
						pstmt.setInt(5, sc.nextInt());
						int result = pstmt.executeUpdate(); // (4)+(5)
						System.out.println(result>0 ? "입력성공":"입력실패"); // (6)
					} catch (SQLException e) {
						System.out.println(e.getMessage());
					} finally {
						try {
							if(pstmt!=null) pstmt.close();
							if(conn !=null) conn.close();
						} catch (SQLException e) {
							
						}
					}
					break;
				case "2"://직업명을 입력받아 해당 직업별  조회 출력
					// 2~7단계
					sql = "SELECT ROWNUM RANK, A.*" + 
							"    FROM (SELECT PNAME||'('||PNO||'번)' PNAME, JNAME, KOR, ENG, "+
														 "MAT, KOR+ENG+MAT SUM" + 
							"            FROM PERSON P, JOB J" + 
							"            WHERE P.JNO=J.JNO AND JNAME = ?" + 
							"            ORDER BY SUM DESC) A";
					try {
						conn = DriverManager.getConnection(url, "scott", "tiger");// (2)
						pstmt = conn.prepareStatement(sql); // (3)
						System.out.print("직업은(배우, 가수, 엠씨)?");
						pstmt.setString(1, sc.next());
						rs = pstmt.executeQuery(); // (4) + (5)
						if(rs.next()) { // 직업별 출력
							System.out.println("rank\t이름\t직업\t국\t영\t수\t총점");
							do {
								int    rank  = rs.getInt("rank");
								String pname = rs.getString("pname");
								String jname = rs.getString("jname");
								int    kor = rs.getInt("kor");
								int    eng = rs.getInt("eng");
								int    mat = rs.getInt("mat");
								int    sum = rs.getInt("sum");
								System.out.println(rank+"등\t"+pname+"\t"+jname+"\t"+kor+"\t"+
										eng+"\t"+mat+"\t"+sum);
							}while(rs.next());
						}else {
							System.out.println("해당 직업의 사람이 입력되지 않았습니다");
						}
					} catch (SQLException e) {
						System.out.println(e.getMessage());
					}finally {
						try {
							if(rs   !=null) rs.close();
							if(pstmt!=null) pstmt.close();
							if(conn !=null) conn.close();
						} catch (SQLException e) {
							
						}
					}
					
					break;
				case "3":
					// 2~7단계
					sql = "SELECT ROWNUM RANK, A.*" + 
							"    FROM (SELECT PNAME||'('||PNO||'번)' PNAME, JNAME, KOR, ENG, "+
														 "MAT, KOR+ENG+MAT SUM" + 
							"            FROM PERSON P, JOB J" + 
							"            WHERE P.JNO=J.JNO " + 
							"            ORDER BY SUM DESC) A";
					try {
						conn = DriverManager.getConnection(url, "scott", "tiger");// (2)
						//pstmt = conn.prepareStatement(sql); // (3)
						stmt = conn.createStatement(); // (3)
						//rs = pstmt.executeQuery(); // (4) + (5)
						rs = stmt.executeQuery(sql);
						if(rs.next()) { // 직업별 출력
							System.out.println("rank\t이름\t직업\t국\t영\t수\t총점");
							do {
								int    rank  = rs.getInt("rank");
								String pname = rs.getString("pname");
								String jname = rs.getString("jname");
								int    kor = rs.getInt("kor");
								int    eng = rs.getInt("eng");
								int    mat = rs.getInt("mat");
								int    sum = rs.getInt("sum");
								System.out.println(rank+"등\t"+pname+"\t"+jname+"\t"+kor+"\t"+
										eng+"\t"+mat+"\t"+sum);
							}while(rs.next());
						}else {
							System.out.println("해당 직업의 사람이 입력되지 않았습니다");
						}
					} catch (SQLException e) {
						System.out.println(e.getMessage());
					}finally {
						try {
							if(rs   !=null) rs.close();
							if(pstmt!=null) pstmt.close();
							if(conn !=null) conn.close();
						} catch (SQLException e) {
							
						}
					}
				}
			}while(fn.equals("1") || fn.equals("2") || fn.equals("3"));
			sc.close();
			System.out.println("BYE");
		}
}