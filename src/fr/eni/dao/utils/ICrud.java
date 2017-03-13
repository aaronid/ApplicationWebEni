package fr.eni.dao.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface ICrud<T> 
{
	boolean create(T item);
	List<T> get();
	T get(int id);
	boolean update(T item);
	boolean delete(int id);
	boolean delete(T item);
	T itemBuilder(ResultSet rs) throws SQLException;
}
