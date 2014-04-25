/*
 * devjav [http://devjav.com]
 * Copyright (C) 2014-2014 Pham Thai Thinh
 * Contact:phamthaithinh@gmail.com
 * 
 */
package com.devjav.tutorial.hibernate.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * Utils for manage Hibernate sessionFactory and session
 * 
 * @author Pham Thai Thinh
 * 
 */
public class HibernateUtil {
	private static SessionFactory sf;

	private synchronized static void init() {
		if (sf == null) {
			try {
				Configuration cfg = new Configuration().configure();
				StandardServiceRegistryBuilder serviceRegistry = new StandardServiceRegistryBuilder()
						.applySettings(cfg.getProperties());
				sf = cfg.buildSessionFactory(serviceRegistry.build());
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	public static Session openSession() {
		init();
		return sf.openSession();
	}

	public static void release() {
		sf.close();
	}

}
