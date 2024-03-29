/**
 * 
 */
package com.antilia.ibatis.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.antilia.common.dao.IQuerableDao;
import com.antilia.common.query.IQuery;
import com.antilia.ibatis.dialect.IBatisDialect;
import com.antilia.ibatis.query.IBatisQuery;
import com.ibatis.sqlmap.client.SqlMapExecutor;

/**
 * A querable DAO that uses Spring IBatis support.
 *  
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
@Transactional(propagation = Propagation.SUPPORTS)
public class SpringIBatisQuerableDao<E extends Serializable> extends SqlMapClientDaoSupport implements IQuerableDao<E>  {

	private static final long serialVersionUID = 1L;
	
	private IBatisDialect dialect;
	
	public SpringIBatisQuerableDao() {
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.SUPPORTS, readOnly=true)
	public List<E> findAll(final IQuery<E> query) {
		return (List<E>)(getSqlMapClientTemplate().execute(new IBatisQueryCallback<E>(dialect,query) {			
			
			@Override
			public Object doInIBatis(SqlMapExecutor executor, IBatisQuery<E> iBatisQuery) throws SQLException {				
				String name = iBatisQuery.getBeanClass().getSimpleName();
				String id = "select" + name + "s";				
				//TODO:Find out why this is so inefficient for SQL server 2000!
				if(iBatisQuery.getMaxResults()>0 && !iBatisQuery.isUseNativePagination()) {
					return executor.queryForList(id, iBatisQuery, iBatisQuery.getFirstResult(), iBatisQuery.getMaxResults());
				}				
				return executor.queryForList(id, iBatisQuery);
			}									
		}));
		
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.SUPPORTS, readOnly=true)
	public List<E> findAll(final Class<E> beanClass) {
		return (List<E>)(getSqlMapClientTemplate().execute(new IBatisQueryCallback<E>(dialect,beanClass) {			
			
			@Override
			public Object doInIBatis(SqlMapExecutor executor, IBatisQuery<E> iBatisQuery) throws SQLException {
				String name = iBatisQuery.getBeanClass().getSimpleName();
				String id = "select" + name + "s";
				//TODO: Find out why this is so inefficient for SQL server 2000!
				if(iBatisQuery.getMaxResults()>0 && !iBatisQuery.isUseNativePagination()) {
					return executor.queryForList(id, iBatisQuery, iBatisQuery.getFirstResult(), iBatisQuery.getMaxResults());
				}				
				return executor.queryForList(id, iBatisQuery);
			}									
		}));		
	}
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly=true)
	public Long count(final IQuery<E> query) {
		return (Long)(getSqlMapClientTemplate().execute(new IBatisQueryCallback<E>(dialect,query, false) {			
			
			@Override
			public Object doInIBatis(SqlMapExecutor executor, IBatisQuery<E> iBatisQuery) throws SQLException {
				String name = iBatisQuery.getBeanClass().getSimpleName();
				String id = "count" + name + "s";
				return executor.queryForObject(id, iBatisQuery);
			}									
		}));
		
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.SUPPORTS, readOnly=true)
	public E findById(final Class<E> beanClass, final Serializable key) {
		return (E)(getSqlMapClientTemplate().execute(new IBatisQueryCallback<E>(dialect,beanClass, false) {			
			
			@Override
			public Object doInIBatis(SqlMapExecutor executor, IBatisQuery<E> batisQuery) throws SQLException {
				// TODO Auto-generated method stub
				return null;
			}									
		}));						
	}
	
	

	/**
	 * @return the dialect
	 */
	public IBatisDialect getDialect() {
		return dialect;
	}

	/**
	 * @param dialect the dialect to set
	 */
	public void setDialect(IBatisDialect dialect) {
		this.dialect = dialect;
	}

	public List<E> findByExample(E bean) {
		// TODO Auto-generated method stub
		return null;
	}	
}
