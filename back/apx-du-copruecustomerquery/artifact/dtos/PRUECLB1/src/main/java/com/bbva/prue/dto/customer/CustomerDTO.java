package com.bbva.prue.dto.customer;

import java.io.Serializable;

/**
 * DTO de información del cliente
 */
public class CustomerDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String firstName;
	private String lastName;
	private String customerId;
	private String nationalityId;
	private String genderId;
	private String identityDocument;
	private String identityDocumentNumber;
	private String identityDocumentTypeId;

	public String getFirstName() { return firstName; }
	public void setFirstName(String firstName) { this.firstName = firstName; }

	public String getLastName() { return lastName; }
	public void setLastName(String lastName) { this.lastName = lastName; }

	public String getCustomerId() { return customerId; }
	public void setCustomerId(String customerId) { this.customerId = customerId; }

	public String getNationalityId() { return nationalityId; }
	public void setNationalityId(String nationalityId) { this.nationalityId = nationalityId; }

	public String getGenderId() { return genderId; }
	public void setGenderId(String genderId) { this.genderId = genderId; }

	public String getIdentityDocument() { return identityDocument; }
	public void setIdentityDocument(String identityDocument) { this.identityDocument = identityDocument; }

	public String getIdentityDocumentNumber() { return identityDocumentNumber; }
	public void setIdentityDocumentNumber(String identityDocumentNumber) { this.identityDocumentNumber = identityDocumentNumber; }

	public String getIdentityDocumentTypeId() { return identityDocumentTypeId; }
	public void setIdentityDocumentTypeId(String identityDocumentTypeId) { this.identityDocumentTypeId = identityDocumentTypeId; }
}