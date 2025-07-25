package com.infosys.med_store.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.infosys.med_store.entity.Medicine;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

public class MedicineDao {
	EntityManager em = Persistence.createEntityManagerFactory("Myjpa").createEntityManager();
	EntityTransaction et = em.getTransaction();
	//Inserting the Medicine
	public Medicine saveMed(Medicine med) {
		try {
			et.begin();
			em.persist(med);
			et.commit();
			return med;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	//Getting Medicine by id
	public Medicine getMedById(int id) {
		Medicine med = em.find(Medicine.class, id);
		return med;
	}
	//Updating Stock of A single Medicine
	public Medicine updateMedStock(Medicine med) {
		Medicine m1 = em.find(Medicine.class, med.getId());
		if(m1 != null) {
			try {
				et.begin();
				m1.setQuantity(med.getQuantity()+m1.getQuantity());
				em.merge(m1);
				et.commit();
				return m1;
			}catch(Exception e) {
				e.printStackTrace();
				return null;
			}
		}else {
			return null;
		}
	}
	//display medicine by Name
	public Medicine getMedByName(String med) {
		Query sql = em.createQuery("SELECT m from Medicine m where m.name=:name");
		try {
			sql.setParameter("name", med);
			Medicine medi = (Medicine)sql.getSingleResult();
			return medi;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// displaying all medicine
	public void getAllMed() {
		Query sql = em.createQuery("Select m from Medicine m");
		@SuppressWarnings("unchecked")
		List<Medicine> medicines = sql.getResultList();
		
		if(!medicines.isEmpty()) {
			for(Medicine med:medicines) {
				System.out.println("Id: "+med.getId()+"\tName: "+med.getName()+"\tQuantity: "+med.getQuantity());
			}
		}else {
			System.out.println("No records");
		}
	}
	// displaying all medicine for Customer
		public void getAllMedCus() {
			Query sql = em.createQuery("Select m from Medicine m");
			@SuppressWarnings("unchecked")
			List<Medicine> medicines = sql.getResultList();
			
			if(!medicines.isEmpty()) {
				for(Medicine med:medicines) {
					System.out.println("Id: "+med.getId()+"\tName: "+med.getName());
				}
			}else {
				System.out.println("No records");
			}
		}
	// checking if medicine is under stock
	public Medicine checkmed(int id, int quant) {
		Medicine med = em.find(Medicine.class, id);
		if(med!=null) {
			if(med.getQuantity()>=quant) {
				return med;
			}else {
				return null;
			}
		}else return null;
	}
	// cheking all orderitems are in stock
	public List<Medicine> checkStock(Map<Integer, Integer> mp) {
		List<Medicine> meds = new ArrayList<>();
		for(Integer id: mp.keySet()) {
			int quant=mp.get(id);
			Medicine med = checkmed(id, quant);
			if(med!=null) {
				meds.add(med);
			}else {
				return null;
			}
		}
		return meds;
	}
	// updating medicine quantity after order is placed
	public boolean updateQuantity(List<Medicine> meds) {
		try {
			et.begin();
			for(Medicine med:meds) {
				em.merge(med);
			}
			et.commit();
			return true;
			
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	// changing medicine info
	public Medicine editMed(Medicine med) {
		try {
			et.begin();
			em.merge(med);
			et.commit();
			return med;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	//deleting medicine
	public boolean deleteMed(int id) {
		Medicine med = getMedById(id);
		if(med!=null) {
			Query sql = em.createQuery("UPDATE OrderItem oi SET oi.med=null WHERE oi.med.id= :id");
			sql.setParameter("id", id);
			et.begin();
			em.remove(med);
			et.commit();
			return true;
			
		}else {
			return false;
		}
	}
}
