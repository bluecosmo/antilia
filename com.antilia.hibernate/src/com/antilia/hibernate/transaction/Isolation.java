package com.antilia.hibernate.transaction;

import java.sql.Connection;

/**
 * Enumeration that represents transaction isolation levels
 * 
 */
public enum Isolation {
	
	/**
	 * Use the default isolation level of the underlying datastore.
	 * All other levels correspond to the JDBC isolation levels.
	 * @see java.sql.Connection
	 */
	DEFAULT(-1),

	/**
	 * A constant indicating no isolation is used between concurrent transactions.
	 * If two transaction change the same row, and the second transation rolls back,
	 * the changes commited by teh first one will be lost. 
	 * @see java.sql.Connection#TRANSACTION_NONE
	 */
	NONE(Connection.TRANSACTION_NONE),

	/**
	 * A constant indicating that dirty reads, non-repeatable reads and phantom reads
	 * can occur. This level allows a row changed by one transaction to be read by
	 * another transaction before any changes in that row have been committed
	 * (a "dirty read"). If any of the changes are rolled back, the second
	 * transaction will have retrieved an invalid row.
	 * @see java.sql.Connection#TRANSACTION_READ_UNCOMMITTED
	 */
	READ_UNCOMMITTED(Connection.TRANSACTION_READ_UNCOMMITTED),

	/**
	 * A constant indicating that dirty reads are prevented; non-repeatable reads
	 * and phantom reads can occur. This level only prohibits a transaction
	 * from reading a row with uncommitted changes in it.
	 * @see java.sql.Connection#TRANSACTION_READ_COMMITTED
	 */
	READ_COMMITTED(Connection.TRANSACTION_READ_COMMITTED),

	/**
	 * A constant indicating that dirty reads and non-repeatable reads are
	 * prevented; phantom reads can occur. This level prohibits a transaction
	 * from reading a row with uncommitted changes in it, and it also prohibits
	 * the situation where one transaction reads a row, a second transaction
	 * alters the row, and the first transaction rereads the row, getting
	 * different values the second time (a "non-repeatable read").
	 * @see java.sql.Connection#TRANSACTION_REPEATABLE_READ
	 */
	REPEATABLE_READ(Connection.TRANSACTION_REPEATABLE_READ),

	/**
	 * A constant indicating that dirty reads, non-repeatable reads and phantom
	 * reads are prevented. This level includes the prohibitions in
	 * <code>ISOLATION_REPEATABLE_READ</code> and further prohibits the situation
	 * where one transaction reads all rows that satisfy a <code>WHERE</code>
	 * condition, a second transaction inserts a row that satisfies that
	 * <code>WHERE</code> condition, and the first transaction rereads for the
	 * same condition, retrieving the additional "phantom" row in the second read.
	 * @see java.sql.Connection#TRANSACTION_SERIALIZABLE
	 */
	SERIALIZABLE(Connection.TRANSACTION_SERIALIZABLE);

	private final int value;

	Isolation(int value) {
		this.value = value;
	}
	
	public int value() {
		return value;
	}
}