package com.bluefly.gonzalo.dao;

import javax.sql.DataSource;

import org.dbunit.database.DatabaseDataSourceConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;

import com.bluefly.gonzalo.configuration.HibernateTestConfiguration;

/**
 * Configuration class for DAO tests. It utilizes DBUint for database-related unit testing
 * @author gdiaz
 *
 */
@ContextConfiguration(classes = { HibernateTestConfiguration.class })
public abstract class EntityDaoImplTest extends AbstractTransactionalTestNGSpringContextTests {

	@Autowired
	DataSource dataSource;

	@BeforeMethod
	public void setUp() throws Exception {
		IDatabaseConnection dbConn = new DatabaseDataSourceConnection(
				dataSource);
		DatabaseOperation.CLEAN_INSERT.execute(dbConn, getDataSet());
	}
	
	protected abstract IDataSet getDataSet() throws Exception;

}