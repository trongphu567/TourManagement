package DAO;

import java.util.ArrayList;

public interface DAOInterface<T> {
	public int insert (T t);
	public int update (T t);
	public int delete (T t);
	public int delete_all();
	public ArrayList<T> selectAll();
	public ArrayList<T> selectById();
	public ArrayList<T> selectByCondition(String condition,String detail);
	public int check(T t);
}
