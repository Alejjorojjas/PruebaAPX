package com.bbva.prue.lib.rlb2.impl;

import com.bbva.elara.domain.transaction.Context;
import com.bbva.elara.domain.transaction.ThreadContext;
import com.bbva.prue.dto.customer.CustomerDTO;
import com.bbva.prue.lib.rlb1.PRUERLB1;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class PRUERLB2ImplTest {

	@Mock
	private PRUERLB1 prueRLB1;

	@InjectMocks
	private PRUERLB2Impl prueRLB2;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		ThreadContext.set(new Context());
	}

	@Test
	public void executeGetCustomerNullTypeTest() {
		CustomerDTO result = prueRLB2.executeGetCustomer(null, "1234567890");
		Assert.assertNull(result);
	}

	@Test
	public void executeGetCustomerNullNumberTest() {
		CustomerDTO result = prueRLB2.executeGetCustomer("CC", null);
		Assert.assertNull(result);
	}

	@Test
	public void executeGetCustomerNotFoundTest() {
		Mockito.when(prueRLB1.executeGetCustomer("CC", "1234567890")).thenReturn(null);
		CustomerDTO result = prueRLB2.executeGetCustomer("CC", "1234567890");
		Assert.assertNull(result);
	}

	@Test
	public void executeGetCustomerSuccessTest() {
		CustomerDTO mockCustomer = new CustomerDTO();
		mockCustomer.setFirstName("Alejandro");
		mockCustomer.setLastName("Rojas");
		Mockito.when(prueRLB1.executeGetCustomer("CC", "1234567890")).thenReturn(mockCustomer);
		CustomerDTO result = prueRLB2.executeGetCustomer("CC", "1234567890");
		Assert.assertNotNull(result);
		Assert.assertEquals("Alejandro", result.getFirstName());
	}
}