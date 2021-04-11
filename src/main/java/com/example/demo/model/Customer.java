package com.example.demo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "_Customer")
@Data
public class Customer implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idcust")
	private long idCust;
//	@OneToOne
//	@JoinTable(name = "FullName")
//	private FullName fullname;
//	@OneToOne
//	@JoinTable(name = "Address")
//	private Address address;
	@OneToOne
	@JoinTable(name = "Account")
	private Account account;
}
