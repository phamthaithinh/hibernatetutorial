/*
 * devjav [http://devjav.com]
 * Copyright (C) 2014-2014 Pham Thai Thinh
 * Contact:phamthaithinh@gmail.com
 * 
 */
package com.devjav.hibernate.test;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.devjav.tutorial.hibernate.entity.User;
import com.devjav.tutorial.hibernate.util.HibernateUtil;

/**
 * 
 * @author Pham Thai Thinh
 * 
 */

public class HibernateTest {
	private Session session;

	@Before
	public void initTest() {
		session = HibernateUtil.openSession();
	}

	@Test
	public void testInsert() {
		User user = new User();
		user.setCrtDate(new Date());
		user.setUsername("thinhpt");
		user.setPassword("123456");
		Transaction txn = session.beginTransaction();
		session.save(user);
		txn.commit();
	}

	@After
	public void endTest() {
		session.close();
		HibernateUtil.release();
	}

}
