package com.bbva.prue.lib.rlb1.impl;

import com.bbva.elara.domain.transaction.Context;
import com.bbva.elara.domain.transaction.ThreadContext;
import com.bbva.prue.dto.customer.CustomerDTO;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

public class PRUERLB1ImplTest {

	@InjectMocks
	private PRUERLB1Impl prueRLB1;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		ThreadContext.set(new Context());
	}

	@Test
	public void executeGetCustomerNullTypeTest() {
		CustomerDTO result = prueRLB1.executeGetCustomer(null, "1234567890");
		Assert.assertNull(result);
	}

	@Test
	public void executeGetCustomerNullNumberTest() {
		CustomerDTO result = prueRLB1.executeGetCustomer("CC", null);
		Assert.assertNull(result);
	}

	@Test
	public void executeGetCustomerEmptyTypeTest() {
		CustomerDTO result = prueRLB1.executeGetCustomer("", "1234567890");
		Assert.assertNull(result);
	}
}