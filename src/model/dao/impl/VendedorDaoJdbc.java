package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement(
			"INSERT INTO seller "
			+"(Name, Email, BirthDate, BaseSalary, DepartmentId) "
			+"VALUES "
			+"(?, ?, ?, ?, ?)",
			Statement.RETURN_GENERATED_KEYS);
			
			pst.setString(1, obj.getNome());
			pst.setString(2, obj.getEmail());
			pst.setDate(3,new java.sql.Date(obj.getAniversario().getTime()));
			pst.setDouble(4, obj.getSalBase());
			pst.setInt(5, obj.getDepartamento().getId());
			
			int linhasAfet = pst.executeUpdate();
			
			if(linhasAfet>0) {
				ResultSet rs = pst.getGeneratedKeys();
			
				if(rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
				DB.closeResultSet(rs);
			}
			else {
				throw new DbException("Erro inesperado!! Nenhuma linha foi afetada !");
			}
					
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(pst);
			
		}
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
					"SELECT seller.*,department.Name as DepName " + "FROM seller INNER JOIN department "
							+ "ON seller.DepartmentId = department.Id WHERE seller.Id = ?");

			pst.setInt(1, id);
			rs = pst.executeQuery();

			if (rs.next()) {
				Departamento dep = instanciaDepartamento(rs);
				Vendedor vendObj = instanciaVendedor(rs, dep);
				return vendObj;
			}
			return null;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(pst);
			DB.closeResultSet(rs);

		}

	}

	private Vendedor instanciaVendedor(ResultSet rs, Departamento dep) throws SQLException {

		Vendedor vendObj = new Vendedor();
		vendObj.setId(rs.getInt("Id"));
		vendObj.setNome(rs.getString("Name"));
		vendObj.setEmail(rs.getString("Email"));
		vendObj.setSalBase(rs.getDouble("BaseSalary"));
		vendObj.setAniversario(rs.getDate("BirthDate"));
		vendObj.setDepartamento(dep);

		return vendObj;
	}

	private Departamento instanciaDepartamento(ResultSet rs) throws SQLException {
		Departamento dep = new Departamento();
		dep.setId(rs.getInt("DepartmentId"));
		dep.setNome(rs.getString("DepName"));
		return dep;
	}

	@Override
	public List<Vendedor> findAll() {

		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = conn.prepareStatement("SELECT seller.*,department.Name as DepName "
					+ "FROM seller INNER JOIN department ON seller.DepartmentId = department.Id " + "ORDER BY Name");

			rs = pst.executeQuery();

			List<Vendedor> listV = new ArrayList<>();
			Map<Integer, Departamento> map = new HashMap<>();

			while (rs.next()) {
				Departamento dept = map.get(rs.getInt("DepartmentId"));
				if (dept == null) {
					dept = instanciaDepartamento(rs);
					map.put(rs.getInt("DepartmentId"), dept);
				}
				Vendedor vendObj = instanciaVendedor(rs, dept);
				listV.add(vendObj);
			}
			return listV;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(pst);
			DB.closeResultSet(rs);

		}

	}

	@SuppressWarnings("unused")
	@Override
	public List<Vendedor> findByDepartamento(Departamento depto) {

		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = conn.prepareStatement("SELECT seller.*,department.Name as DepName "
					+ "FROM seller INNER JOIN department ON seller.DepartmentId = department.Id "
					+ "WHERE DepartmentId = ? " + "ORDER BY Name");

			pst.setInt(1, depto.getId());
			rs = pst.executeQuery();
			List<Vendedor> listV = new ArrayList<>();
			Map<Integer, Departamento> map = new HashMap<>();

			while (rs.next()) {
				Departamento dept = map.get(rs.getInt("DepartmentId"));
				if (dept == null) {
					dept = instanciaDepartamento(rs);
					map.put(rs.getInt("DepartmentId"), dept);
				}
				Vendedor vendObj = instanciaVendedor(rs, dept);
				listV.add(vendObj);
			}
			return listV;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(pst);
			DB.closeResultSet(rs);

		}

	}

}
