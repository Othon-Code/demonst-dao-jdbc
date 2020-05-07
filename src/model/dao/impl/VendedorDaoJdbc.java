package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.VendedorDao;
import model.entities.Departamento;
import model.entities.Vendedor;

public class VendedorDaoJdbc implements VendedorDao {
	private Connection conn;

	public VendedorDaoJdbc(Connection conn) {
		this.conn = conn;

	}

	@Override
	public void inserir(Vendedor obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updade(Vendedor obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deletePorId(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Vendedor findById(Integer id) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = conn.prepareStatement(
			"SELECT seller.*,department.Name as DepName "
			+"FROM seller INNER JOIN department "
			+"ON seller.DepartmentId = department.Id WHERE seller.Id = ?");
			
			
		pst.setInt(1, id);
		rs = pst.executeQuery();
		
		if(rs.next()) {
			Departamento dep = new Departamento();
			dep.setId(rs.getInt("DepartmentId"));
			dep.setNome(rs.getString("DepName"));
			Vendedor vendObj = new Vendedor();
			vendObj.setId(rs.getInt("Id"));
			vendObj.setNome(rs.getString("Name"));
			vendObj.setEmail(rs.getString("Email"));
			vendObj.setSalBase(rs.getDouble("BaseSalary"));
			vendObj.setAniversario(rs.getDate("BirthDate"));
			vendObj.setDepartamento(dep);
			return vendObj;
		}
		return null;
		
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(pst);
			DB.closeResultSet(rs);
			
		}

	}

	@Override
	public List<Vendedor> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
