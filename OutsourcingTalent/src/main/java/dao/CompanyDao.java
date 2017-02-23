package dao;

import java.util.List;

import entity.CompanyEntity;

public interface CompanyDao {

	public void addCompany(CompanyEntity entity);
	public void deleteCompany(CompanyEntity entity);
	public void updateCompany(CompanyEntity entity);
	public CompanyEntity findCompanyById(int Id);
	public List<?> findComaniesByName(String name);
}
