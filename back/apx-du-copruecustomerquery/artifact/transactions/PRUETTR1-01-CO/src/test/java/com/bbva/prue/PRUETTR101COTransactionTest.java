package com.bbva.prue;

import com.bbva.elara.configuration.manager.application.ApplicationConfigurationService;
import com.bbva.elara.domain.transaction.Context;
import com.bbva.elara.domain.transaction.request.header.CommonRequestHeader;
import com.bbva.prue.dto.customer.CustomerDTO;
import com.bbva.prue.lib.rlb2.PRUERLB2;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class PRUETTR101COTransactionTest {

	private Map<String, Object> parameters;
	private Map<Class<?>, Object> serviceLibraries;

	@Mock
	private ApplicationConfigurationService applicationConfigurationService;

	@Mock
	private CommonRequestHeader commonRequestHeader;

	@Mock
	private PRUERLB2 prueRLB2;

	private final PRUETTR101COTransaction transaction = new PRUETTR101COTransaction() {
		@Override
		protected void addParameter(String field, Object obj) {
			if (parameters != null) {
				parameters.put(field, obj);
			}
		}

		@Override
		protected Object getParameter(String field) {
			return parameters.get(field);
		}

		@Override
		protected <T> T getServiceLibrary(Class<T> serviceInterface) {
			return (T) serviceLibraries.get(serviceInterface);
		}

		@Override
		public String getProperty(String keyProperty) {
			return applicationConfigurationService.getProperty(keyProperty);
		}

		@Override
		protected CommonRequestHeader getRequestHeader() {
			return commonRequestHeader;
		}
	};

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		initializeTransaction();
		setServiceLibrary(PRUERLB2.class, prueRLB2);
	}

	private void initializeTransaction() {
		transaction.setContext(new Context());
		parameters = new HashMap<>();
		serviceLibraries = new HashMap<>();
	}

	private void setServiceLibrary(Class<?> clasz, Object mockObject) {
		serviceLibraries.put(clasz, mockObject);
	}

	private void setParameterToTransaction(String parameter, Object value) {
		parameters.put(parameter, value);
	}

	private Object getParameterFromTransaction(String parameter) {
		return parameters.get(parameter);
	}

	@Test
	public void executeTestClienteNoEncontrado() {
		setParameterToTransaction("identityDocumentTypeId", "CC");
		setParameterToTransaction("identityDocumentNumber", "1234567890");
		Mockito.when(prueRLB2.executeGetCustomer("CC", "1234567890")).thenReturn(null);
		transaction.execute();
		Assert.assertEquals(0, transaction.getAdviceList().size());
	}

	@Test
	public void executeTestClienteEncontrado() {
		CustomerDTO mockCustomer = new CustomerDTO();
		mockCustomer.setFirstName("Alejandro");
		mockCustomer.setLastName("Rojas");
		mockCustomer.setCustomerId("C001");
		mockCustomer.setNationalityId("CO");
		mockCustomer.setGenderId("M");
		mockCustomer.setIdentityDocument("Cédula de Ciudadanía");
		mockCustomer.setIdentityDocumentNumber("1234567890");
		mockCustomer.setIdentityDocumentTypeId("CC");

		setParameterToTransaction("identityDocumentTypeId", "CC");
		setParameterToTransaction("identityDocumentNumber", "1234567890");
		Mockito.when(prueRLB2.executeGetCustomer("CC", "1234567890")).thenReturn(mockCustomer);

		transaction.execute();

		Assert.assertEquals("Alejandro", getParameterFromTransaction("firstName"));
		Assert.assertEquals("Rojas", getParameterFromTransaction("lastName"));
		Assert.assertEquals(0, transaction.getAdviceList().size());
	}
}