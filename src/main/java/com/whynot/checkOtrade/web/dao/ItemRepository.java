/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whynot.checkOtrade.web.dao;

import com.whynot.checkOtrade.web.domain.Item;
import com.whynot.checkOtrade.web.domain.PriceList;
import com.whynot.checkOtrade.web.domain.Shop;
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
 * <p> DAO класс общения с базой, для товаров.</p>
 * @author potapov
 */
@Repository
@Transactional(readOnly = true)
public class ItemRepository {
	
	@Autowired
	SessionFactory sessionFactory;

	@Transactional
	public Item create(Item entity) {
		Session session = sessionFactory.openSession();

		Long id = (Long) session.save(entity);

		if (session != null) {
			session.flush();
			session.close();
		}
		
		entity = findByID(id);
		return entity;
	}

	public Item findByID(long id) {
		Session session = sessionFactory.openSession();
		Criteria cr = session.createCriteria(Item.class);
		cr.add(Restrictions.eq("id", id));
		Item entity = (Item) cr.uniqueResult();
		try {
			Hibernate.initialize(entity);
		} catch (ObjectNotFoundException e) {
			System.out.println("Item object with id " + id + " not found");
			entity = null;
		} finally {
			if (session != null) {
				session.flush();
				session.close();
			}
		}

		return entity;
	}
        
        public Item findByName(String name) {
		Session session = sessionFactory.openSession();
		Criteria cr = session.createCriteria(Item.class);
		cr.add(Restrictions.eq("name", name));
		Item entity = (Item) cr.uniqueResult();
		try {
			Hibernate.initialize(entity);
		} catch (ObjectNotFoundException e) {
			System.out.println("Item object with id " + name + " not found");
			entity = null;
		} finally {
			if (session != null) {
				session.flush();
				session.close();
			}
		}

		return entity;
	}

	public List<Item> findAll() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Criteria cr = session.createCriteria(Item.class);
		List<Item> entitys = cr.list();
		session.getTransaction().commit();
		if (session != null) {
			session.flush();
			session.close();
		}

		return entitys;
	}

	public boolean delete(Item entity) {
		Session session = sessionFactory.openSession();

		session.delete(entity);

		if (session != null) {
			session.flush();
			session.close();
		}

		return findByID(entity.getId()) == null;
	}

	public Item update(Item entity) {
		Session session = sessionFactory.openSession();

		session.update(entity);

		if (session != null) {
			session.flush();
			session.close();
		}

		return findByID(entity.getId());
	}

	
}
