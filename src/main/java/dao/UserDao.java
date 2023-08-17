package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import dto.MyUser;
import dto.Task;

public class UserDao {
	EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("dev");
	EntityManager entityManager=entityManagerFactory.createEntityManager();
	EntityTransaction entityTransaction=entityManager.getTransaction();
	
	public void save(MyUser user)
	{
	entityTransaction.begin();
	entityManager.persist(user);
	entityTransaction.commit();
	}
	
	public MyUser findByEmail(String email)
	{
		List<MyUser> list=entityManager.
				createQuery("select x from MyUser x where email=?1")
				//createNativeQuery("select * from myuser where email=",myuser.class
				.setParameter(1, email).getResultList();
		if(list.isEmpty())
			return null;
		else
			return list.get(0);
	}
	
	public void save(Task task)
	{
	entityTransaction.begin();
	entityManager.persist(task);
	entityTransaction.commit();
	}
	
	public List<Task> fetchAllTask()
	{
		return entityManager.createQuery("select x from Task x").getResultList();
	}

	public void update(MyUser myUser) {
		entityTransaction.begin();
		entityManager.merge(myUser);
		entityTransaction.commit();
	}
	
	public Task fetchTask(int id) {
		return entityManager.find(Task.class, id);
	}
	
	public void update(Task task) {
		entityTransaction.begin();
		entityManager.merge(task);
		entityTransaction.commit();
	}
	
	public void remove(Task task) {
		entityTransaction.begin();
		entityManager.merge(task);
		entityTransaction.commit();
	}
}
