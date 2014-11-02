/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whynot.web.dao;

import com.whynot.web.domain.Item;
import com.whynot.web.domain.PriceList;
import com.whynot.web.domain.Shop;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p> DAO класс общения с базой, для списков цен.</p>
 * @author potapov
 */
@Repository
public class PriceRepository {
			
	@Autowired
	SessionFactory sessionFactory;

    @Transactional
	public PriceList create(PriceList entity) {
		Session session = sessionFactory.openSession();

		Long id = (Long) session.save(entity);

		if (session != null) {
			session.flush();
			session.close();
		}
		
		entity = findByID(id);
		return entity;
	}

	public PriceList findByID(long id) {
		Session session = sessionFactory.openSession();
		Criteria cr = session.createCriteria(PriceList.class);
		cr.add(Restrictions.eq("id", id));
		PriceList entity = (PriceList) cr.uniqueResult();
		try {
			Hibernate.initialize(entity);
		} catch (ObjectNotFoundException e) {
			System.out.println("PriceList object with id " + id + " not found");
			entity = null;
		} finally {
			if (session != null) {
				session.flush();
				session.close();
			}
		}

		return entity;
	}

	public List<PriceList> findAll() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Criteria cr = session.createCriteria(PriceList.class);
		List<PriceList> entitys = cr.list();
		session.getTransaction().commit();
		if (session != null) {
			session.flush();
			session.close();
		}

		return entitys;
	}

	public boolean delete(PriceList entity) {
		Session session = sessionFactory.openSession();

		session.delete(entity);

		if (session != null) {
			session.flush();
			session.close();
		}

		return findByID(entity.getId()) == null;
	}
	
        
	public List<PriceList> findPricesByItem(Item item,Shop shop) {
		Session session = sessionFactory.openSession();
		
		Criteria cr = session.createCriteria(PriceList.class);
		cr.add(Restrictions.eq("item", item));
		cr.add(Restrictions.eq("shop", shop));
		
		List<PriceList> entity = cr.list();
		try {
			Hibernate.initialize(entity);
		} catch (ObjectNotFoundException e) {
			System.out.println("Shop object with item " + item + " not found");
			entity = null;
		} finally {
			if (session != null) {
				session.flush();
				session.close();
			}
		}

		return entity;
	}
        
        public PriceList findItemPrice(Item item,Shop shop, int price) {
		Session session = sessionFactory.openSession();
		
		Criteria cr = session.createCriteria(PriceList.class);
		cr.add(Restrictions.eq("item", item));
		cr.add(Restrictions.eq("shop", shop));
                cr.add(Restrictions.eq("price", price));
		
		PriceList entity = (PriceList) cr.uniqueResult();
		try {
			Hibernate.initialize(entity);
		} catch (ObjectNotFoundException e) {
			System.out.println("Shop object with item " + item + " not found");
			entity = null;
		} finally {
			if (session != null) {
				session.flush();
				session.close();
			}
		}

		return entity;
	}

	public PriceList update(PriceList entity) {
		Session session = sessionFactory.openSession();

		session.update(entity);

		if (session != null) {
			session.flush();
			session.close();
		}

		return findByID(entity.getId());
	}
}
