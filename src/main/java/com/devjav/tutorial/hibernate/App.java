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

import com.devjav.tutorial.hibernate.entity.LoginHistory;
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
			LoginHistory history= new LoginHistory();
			history.setIp("127.0.0.1");
			history.setUser(user);
			history.setStatus("success");
			history.setLoginDate(new Date());
			txn = session.beginTransaction();
			session.persist(history);
			txn.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			if (txn != null) {
				txn.rollback();
				txn=null;
			}
		} finally {
			if (session != null) {
				session.close();
				session=null;
			}
			HibernateUtil.release();
		}

	}

}
