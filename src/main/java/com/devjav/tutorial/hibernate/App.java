/*
 * devjav [http://devjav.com]
 * Copyright (C) 2014-2014 Pham Thai Thinh
 * Contact:phamthaithinh@gmail.com
 * 
 */
package com.devjav.tutorial.hibernate;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.devjav.tutorial.hibernate.entity.Profile;
import com.devjav.tutorial.hibernate.entity.Role;
import com.devjav.tutorial.hibernate.entity.User;
import com.devjav.tutorial.hibernate.util.HibernateUtil;

/**
 * 
 * @author Pham Thai Thinh
 * 
 */
public class App {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Session session = null;
		Transaction txn = null;
		try {
			session = HibernateUtil.openSession();
			User user = new User();
			user.setCrtDate(new Date());
			user.setUsername("thinhpt");
			user.setPassword("123456");
			Profile profile= new Profile();
			profile.setCity("Hanoi");
			profile.setCountry("Vietnam");
			profile.setMobile("9934934343");
			profile.setStreet1("dsadsa");
			profile.setStreet2("dsadsadsad");
			profile.setUser(user);
			profile.setZipcode("10000");
			user.setProfile(profile);
			Role role = new Role();
			role.setName("Administrator");
			role.setDescription("Adminstrator of application");
			role.setStatus("Active");
			role.getUsers().add(user);
			user.getRoles().add(role);
			txn = session.beginTransaction();
			session.persist(user);
			txn.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			if (txn != null) {
				txn.rollback();
				txn = null;
			}
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
			HibernateUtil.release();
		}

	}

}
