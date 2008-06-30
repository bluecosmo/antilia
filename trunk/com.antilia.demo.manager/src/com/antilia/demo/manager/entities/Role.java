package com.antilia.demo.manager.entities;

// Generated Apr 23, 2008 5:11:37 PM by Hibernate Tools 3.2.1.GA

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Role generated by hbm2java
 */
@Entity
@Table(name = "role", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Role implements java.io.Serializable {

	private long id;
	private String name;
	private String shortdescription;
	private String description;
	private Set<EmployeeRole> employeeRoles = new HashSet<EmployeeRole>(0);

	public Role() {
	}

	public Role(long id, String name, String shortdescription) {
		this.id = id;
		this.name = name;
		this.shortdescription = shortdescription;
	}

	public Role(long id, String name, String shortdescription,
			String description, Set<EmployeeRole> employeeRoles) {
		this.id = id;
		this.name = name;
		this.shortdescription = shortdescription;
		this.description = description;
		this.employeeRoles = employeeRoles;
	}

	@Id
	@Column(name = "id", unique = true, nullable = false)
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "name", unique = true, nullable = false, length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "shortdescription", nullable = false, length = 300)
	public String getShortdescription() {
		return this.shortdescription;
	}

	public void setShortdescription(String shortdescription) {
		this.shortdescription = shortdescription;
	}

	@Column(name = "description", length = 2000)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "role")
	public Set<EmployeeRole> getEmployeeRoles() {
		return this.employeeRoles;
	}

	public void setEmployeeRoles(Set<EmployeeRole> employeeRoles) {
		this.employeeRoles = employeeRoles;
	}

}
