package com.bbva.prue;

import com.bbva.prue.dto.customer.CustomerDTO;
import com.bbva.prue.lib.rlb2.PRUERLB2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Transacción de consulta de información del cliente
 */
public class PRUETTR101COTransaction extends AbstractPRUETTR101COTransaction {

	private static final Logger LOGGER = LoggerFactory.getLogger(PRUETTR101COTransaction.class);

	/**
	 * Ejecuta la consulta del cliente recibiendo tipo y número de documento
	 */
	@Override
	public void execute() {

		LOGGER.info("Iniciando transacción de consulta de cliente");

		// Obtener parámetros de entrada
		String identityDocumentTypeId = this.requestContext.getInputParameter("identityDocumentTypeId");
		String identityDocumentNumber = this.requestContext.getInputParameter("identityDocumentNumber");

		LOGGER.info("Parámetros recibidos - tipo: {}, número: {}", identityDocumentTypeId, identityDocumentNumber);

		// Obtener la librería de negocio
		PRUERLB2 prueRLB2 = this.getServiceLibrary(PRUERLB2.class);

		// Llamar a la librería de negocio
		CustomerDTO customer = prueRLB2.executeGetCustomer(identityDocumentTypeId, identityDocumentNumber);

		// Verificar si se encontró el cliente
		if (customer == null) {
			LOGGER.warn("No se encontró el cliente");
			return;
		}

		// Setear parámetros de salida
		this.requestContext.setOutputParameter("firstName", customer.getFirstName());
		this.requestContext.setOutputParameter("lastName", customer.getLastName());
		this.requestContext.setOutputParameter("customerId", customer.getCustomerId());
		this.requestContext.setOutputParameter("nationalityId", customer.getNationalityId());
		this.requestContext.setOutputParameter("genderId", customer.getGenderId());
		this.requestContext.setOutputParameter("identityDocument", customer.getIdentityDocument());
		this.requestContext.setOutputParameter("identityDocumentNumber", customer.getIdentityDocumentNumber());

		LOGGER.info("Transacción de consulta de cliente finalizada exitosamente");
	}
}