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

		// Obtener parámetros de entrada usando los métodos generados por APX
		String identityDocumentTypeId = this.getIdentitydocumenttypeid();
		String identityDocumentNumber = this.getIdentitydocumentnumber();

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

		// Setear parámetros de salida usando los métodos generados por APX
		this.setFirstname(customer.getFirstName());
		this.setLastname(customer.getLastName());
		this.setCustomerid(customer.getCustomerId());
		this.setNationalityid(customer.getNationalityId());
		this.setGenderid(customer.getGenderId());
		this.setIdentitydocument(customer.getIdentityDocument());
		this.setIdentitydocumentnumber(customer.getIdentityDocumentNumber());

		LOGGER.info("Transacción de consulta de cliente finalizada exitosamente");
	}
}