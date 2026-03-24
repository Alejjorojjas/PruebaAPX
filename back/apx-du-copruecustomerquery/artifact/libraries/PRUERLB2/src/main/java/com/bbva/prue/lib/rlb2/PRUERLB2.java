package com.bbva.prue.lib.rlb2;

import com.bbva.prue.dto.customer.CustomerDTO;

/**
 * Interfaz de la librería de negocio para consulta de cliente
 */
public interface PRUERLB2 {

	/**
	 * Orquesta la búsqueda del cliente validando entradas y llamando a infraestructura
	 * @param identityDocumentTypeId tipo de documento
	 * @param identityDocumentNumber número de documento
	 * @return CustomerDTO con la información del cliente
	 */
	CustomerDTO executeGetCustomer(String identityDocumentTypeId, String identityDocumentNumber);

}