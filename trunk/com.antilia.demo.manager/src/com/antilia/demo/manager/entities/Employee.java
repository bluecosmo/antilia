package com.antilia.demo.manager.entities;

// Generated Apr 23, 2008 5:11:37 PM by Hibernate Tools 3.2.1.GA

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Employee generated by hbm2java
 */
@Entity
@Table(name = "employee")
public class Employee implements java.io.Serializable {

	private long id;
	private Address address;
	private String name;
	private String lastname1;
	private String lastname2;
	private Date hired;
	private Date fired;
	private Set<EmployeeRole> employeeRoles = new HashSet<EmployeeRole>(0);
	private Set<Asignment> asignments = new HashSet<Asignment>(0);

	public Employee() {
	}

	public Employee(long id, Address address, String name, String lastname1,
			Date hired) {
		this.id = id;
		this.address = address;
		this.name = name;
		this.lastname1 = lastname1;
		this.hired = hired;
	}

	public Employee(long id, Address address, String name, String lastname1,
			String lastname2, Date hired, Date fired,
			Set<EmployeeRole> employeeRoles, Set<Asignment> asignments) {
		this.id = id;
		this.address = address;
		this.name = name;
		this.lastname1 = lastname1;
		this.lastname2 = lastname2;
		this.hired = hired;
		this.fired = fired;
		this.employeeRoles = employeeRoles;
		this.asignments = asignments;
	}

	@Id
	@Column(name = "id", unique = true, nullable = false)
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "homeaddress", nullable = false)
	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Column(name = "name", nullable = false, length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "lastname1", nullable = false, length = 100)
	public String getLastname1() {
		return this.lastname1;
	}

	public void setLastname1(String lastname1) {
		this.lastname1 = lastname1;
	}

	@Column(name = "lastname2", length = 100)
	public String getLastname2() {
		return this.lastname2;
	}

	public void setLastname2(String lastname2) {
		this.lastname2 = lastname2;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "hired", nullable = false, length = 13)
	public Date getHired() {
		return this.hired;
	}

	public void setHired(Date hired) {
		this.hired = hired;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fired", length = 13)
	public Date getFired() {
		return this.fired;
	}

	public void setFired(Date fired) {
		this.fired = fired;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "employee")
	public Set<EmployeeRole> getEmployeeRoles() {
		return this.employeeRoles;
	}

	public void setEmployeeRoles(Set<EmployeeRole> employeeRoles) {
		this.employeeRoles = employeeRoles;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "employee")
	public Set<Asignment> getAsignments() {
		return this.asignments;
	}

	public void setAsignments(Set<Asignment> asignments) {
		this.asignments = asignments;
	}

}