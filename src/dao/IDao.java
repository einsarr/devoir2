package dao;

import java.util.List;

public interface IDao<T> {
   int create(T objet);
   int update(T objet);
   List<T> findAll();
   T findById(int id);
}
