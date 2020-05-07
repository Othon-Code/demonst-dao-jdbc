package model.dao;

import model.dao.impl.VendedorDaoJdbc;

public class DaoFactory {
	public static VendedorDao criaVendedorDao() {
		return new VendedorDaoJdbc();
	}

}
