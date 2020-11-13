package Repositorios;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

public abstract class RepoDB<T> implements WithGlobalEntityManager, EntityManagerOps, TransactionalOps{
	@SuppressWarnings("unchecked")
	public	List<T> getAll(){
		return createQuery().getResultList();
	}

	public void agregar(T elemento){
		entityManager().persist(elemento);
	}
	
	public void eliminarA(T elemento){
		entityManager().remove(elemento);
	}
	
	protected Query createQuery(){
		return query("");
	}

	protected Query query(String where){
		return entityManager().createQuery("from "+ className() + " " + where);
	}
	
	protected abstract String className();
}
