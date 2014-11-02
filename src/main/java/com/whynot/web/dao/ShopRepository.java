/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whynot.web.dao;

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
 * <p> DAO класс общения с базой, для магазинов.</p>
 * @author potapov
 */
@Repository
@Transactional(readOnly = true)
public class ShopRepository {
		
	@Autowired
	SessionFactory sessionFactory;

	@Transactional
	public Shop create(Shop entity) {
		Session session = sessionFactory.openSession();

		Long id = (Long) session.save(entity);

		if (session != null) {
			session.flush();
			session.close();
		}
		
		entity = findByID(id);
		return entity;
	}

	public Shop findByID(long id) {
		Session session = sessionFactory.openSession();
		Criteria cr = session.createCriteria(Shop.class);
		cr.add(Restrictions.eq("id", id));
		Shop entity = (Shop) cr.uniqueResult();
		try {
			Hibernate.initialize(entity);
		} catch (ObjectNotFoundException e) {
			System.out.println("Shop object with id " + id + " not found");
			entity = null;
		} finally {
			if (session != null) {
				session.flush();
				session.close();
			}
		}

		return entity;
	}

	public List<Shop> findAll() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Criteria cr = session.createCriteria(Shop.class);
		List<Shop> entitys = cr.list();
		session.getTransaction().commit();
		if (session != null) {
			session.flush();
			session.close();
		}

		return entitys;
	}

	public boolean delete(Shop entity) {
		Session session = sessionFactory.openSession();

		session.delete(entity);

		if (session != null) {
			session.flush();
			session.close();
		}

		return findByID(entity.getId()) == null;
	}

	public Shop update(Shop entity) {
		Session session = sessionFactory.openSession();

		session.update(entity);

		if (session != null) {
			session.flush();
			session.close();
		}

		return findByID(entity.getId());
	}

	public Shop findByShop(Shop shop) {
		Session session = sessionFactory.openSession();
		
		Criteria cr = session.createCriteria(Shop.class);
		cr.add(Restrictions.eq("name", shop.getName()));
		cr.add(Restrictions.eq("address", shop.getAddress()));
		cr.add(Restrictions.eq("city", shop.getCity()));
		
		Shop entity = (Shop) cr.uniqueResult();
		try {
			Hibernate.initialize(entity);
		} catch (ObjectNotFoundException e) {
			System.out.println("Shop object with shop " + shop + " not found");
			entity = null;
		} finally {
			if (session != null) {
				session.flush();
				session.close();
			}
		}

		return entity;
	}
}
