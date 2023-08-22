package controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.Carros;

public class CarrosDAO {
	
	public ArrayList<Carros> listar(){
		
		Conexao c = Conexao.getInstancia();
		Connection con = c.conectar();
		
		ArrayList<Carros> carros = new ArrayList();
		
		String query = "SELECT * FROM carros";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			
			 ResultSet rs = ps.executeQuery();
			 while(rs.next()) {
				 int idCarro = rs.getInt("id_pessoa");
				 String modelo =rs.getString("modelo");
				 
				 Carros p = new Carros();
				 p.setIdCarro(idCarro);
				 p.setModelo(modelo);
				 
				 carros.add(p);
				 
			 }
			
		}catch (SQLException e) {
				e.printStackTrace();
			}
		c.fecharConexao();
		return carros;
		
	}
	
	public boolean inerir(Carros ca) {
		
		Conexao c = Conexao.getInstancia();
		
		Connection con = c.conectar();
		
		String query = "INSERT INTO Carros (idCarro, Modelo) VALUES (?, ?)";
		
		try {
			PreparedStatement ps = con.prepareStatement(query);
			
			// seta os parametros
			ps.setInt(1, ca.getIdCarro());
			ps.setString(2, ca.getModelo());
			
			// consolidar a execução do comando no banco
			ps.executeUpdate();
			
			// fecha a conexão
			c.fecharConexao();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return true;
	}
	public boolean excluir(Carros p) {
		Conexao c = Conexao.getInstancia();
		Connection con = c.conectar();
		
		String query = "DELETE FROM carro WHERE id_pessoa = ?";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, p.getIdCarro());
			ps.executeUpdate();
			c.fecharConexao();
			return false;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	public boolean atualizar(Carros p) {
		Conexao c = Conexao.getInstancia();
		Connection con = c.conectar();
		
		String query = "UPDATE carro SET modelo_carro" + "nome_carro = ?, + WHERE id_carro";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, p.getModelo());
			ps.setInt(2, p.getIdCarro());
			
			ps.executeUpdate();
			c.fecharConexao();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
}
}
