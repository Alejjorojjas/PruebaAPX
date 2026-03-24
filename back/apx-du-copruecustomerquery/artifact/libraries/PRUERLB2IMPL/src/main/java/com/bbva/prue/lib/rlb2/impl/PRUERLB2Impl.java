package com.bbva.prue.lib.rlb2.impl;

import com.bbva.prue.dto.customer.CustomerDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Librería de negocio que orquesta la consulta de información del cliente
 */
public class PRUERLB2Impl extends PRUERLB2Abstract {

	private static final Logger LOGGER = LoggerFactory.getLogger(PRUERLB2Impl.class);

	/**
	 * Orquesta la búsqueda del cliente validando entradas y llamando a infraestructura
	 * @param identityDocumentTypeId tipo de documento
	 * @param identityDocumentNumber número de documento
	 * @return CustomerDTO con la información del cliente
	 */
	public CustomerDTO executeGetCustomer(String identityDocumentTypeId, String identityDocumentNumber) {

		LOGGER.info("Iniciando consulta de cliente");

		// Validaciones de negocio
		if (identityDocumentTypeId == null || identityDocumentTypeId.trim().isEmpty()) {
			LOGGER.error("El tipo de documento es requerido");
			this.addAdvice("PRUE00001");
			return null;
		}

		if (identityDocumentNumber == null || identityDocumentNumber.trim().isEmpty()) {
			LOGGER.error("El número de documento es requerido");
			this.addAdvice("PRUE00002");
			return null;
		}

		// Llamada a la librería de infraestructura
		CustomerDTO customer = prueRLB1.executeGetCustomer(
				identityDocumentTypeId.trim(),
				identityDocumentNumber.trim()
		);

		if (customer == null) {
			LOGGER.warn("Cliente no encontrado");
			this.addAdvice("PRUE00003");
			return null;
		}

		LOGGER.info("Consulta de cliente exitosa");
		return customer;
	}
}