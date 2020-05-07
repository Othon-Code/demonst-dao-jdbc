package model.dao;

import db.DB;
import model.dao.impl.VendedorDaoJdbc;

public class DaoFactory {
	public static VendedorDao criaVendedorDao() {
		return new VendedorDaoJdbc(DB.getConnection());
	}

}
