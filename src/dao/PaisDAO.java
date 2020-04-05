package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Pais;

public class PaisDAO {

	public int incluir(Pais p) {
		String sqlInsert = "INSERT INTO Pais (nome,populaçao,area) VALUES(?,?,?)";
		try(Connection conn = ConnectionFactory.obtemConexao();
						PreparedStatement stm = conn.prepareStatement(sqlInsert);){
			stm.setString(1,p.getNome());
			stm.setLong(2,p.getPopulacao());
			stm.setDouble(3,p.getArea());
			stm.execute();
			String sqlQuery = "SELECT LAST_INSERT_ID()";
			try (PreparedStatement stm2 = conn.prepareStatement(sqlQuery);
				   ResultSet rs = stm2.executeQuery();){
					   
		        if(rs.next()) {
		           p.setId(rs.getInt(1));
		        }
		        
		    } catch(SQLException e) {
		    	e.printStackTrace();
		    	
		    }
				   
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
					
		return p.getId();
	}

	public void atualizar(Pais p) {
		String sqlUpdate = "UPDATE Pais SET nome=?, populaçao=?,area=? WHERE id=?";
		try(Connection conn = ConnectionFactory.obtemConexao();
						PreparedStatement stm = conn.prepareStatement(sqlUpdate);){
			stm.setString(1,p.getNome());
			stm.setLong(2,p.getPopulacao());
			stm.setDouble(3,p.getArea());
			stm.setInt(4,p.getId());
			stm.execute();
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
					
		
	}

	public void excluir(Pais p) {
		String sqlDelete = "DELETE FROM Pais WHERE id=?";
		try(Connection conn = ConnectionFactory.obtemConexao();
						PreparedStatement stm = conn.prepareStatement(sqlDelete);){
			stm.setInt(1,p.getId());
			stm.execute();
		}catch(SQLException e) {
			e.printStackTrace();
		}
					
		
	}
	public Pais carregar(Pais p) {
		String sqlSelect = "SELECT nome,populaçao,area FROM Pais WHERE Pais.id=?";
		try(Connection conn = ConnectionFactory.obtemConexao();
						PreparedStatement stm = conn.prepareStatement(sqlSelect);){
			stm.setInt(1,p.getId());
			try(ResultSet rs = stm.executeQuery();){
					if(rs.next()) {
						p.setNome(rs.getString("nome"));
						p.setPopulacao(rs.getLong("populaçao"));
						p.setArea(rs.getDouble("area"));			
					}
			} catch(SQLException e) {
				   e.printStackTrace();
			}
		}catch (SQLException e1) {
			System.out.println(e1.getStackTrace());
		}	
		return p;
	}
	
}
