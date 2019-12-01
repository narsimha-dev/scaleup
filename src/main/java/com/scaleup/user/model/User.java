package com.scaleup.user.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.NonNull;

@Entity
@Table(name="User")
@NamedQuery(name = "User.findByFirstNameAndLastNameAndEmail",
query = "SELECT u FROM User u WHERE u.firstName LIKE :firstName OR u.lastName LIKE :lastName OR u.email LIKE :email")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NonNull
    @NotEmpty
    @Column(name="first")
    private String firstName;
    
    @NonNull
    @NotEmpty
    @Column(name="last")
    private String lastName;
    
    @Email
    @NonNull
    @NotEmpty
    @Column(name="email", nullable=false, length=200)
    private String email;
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

    @Override
    public String toString() {
        return "UserEntity [id=" + id + ", firstName=" + firstName + 
                ", lastName=" + lastName + ", email=" + email   + "]";
    }
}