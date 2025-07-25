package com.infosys.med_store.dao;

import com.infosys.med_store.entity.Admin;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

public class AdminDao {
	EntityManager em = Persistence.createEntityManagerFactory("Myjpa").createEntityManager();
	EntityTransaction et = em.getTransaction();
	
	public Admin loginAdmin(Admin admin) {
		try {
			Query sql = em.createQuery("Select a from Admin a Where a.username=:username");
			sql.setParameter("username", admin.getUsername());
			Admin a1 = (Admin) sql.getSingleResult();
			
			if(a1 != null) {
				if(a1.getPass().equals(admin.getPass())) {
					return admin;
				}else {
					return null;
				}
			}else {
				return null;
			}
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
}
