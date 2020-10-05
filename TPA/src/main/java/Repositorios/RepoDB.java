package Repositorios;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

public abstract class RepoDB<T>{
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
		return createQuery("");
	}
	
	protected Query createQuery(String where){
		return entityManager().createQuery("from "+ className() + " " + where);
	}
	
	protected EntityManager entityManager(){
		return PerThreadEntityManagers.getEntityManager();
	}
	
	protected abstract String className();
}
