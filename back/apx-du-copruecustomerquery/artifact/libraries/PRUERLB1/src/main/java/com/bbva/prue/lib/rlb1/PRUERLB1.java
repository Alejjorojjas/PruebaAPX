package com.bbva.prue.lib.rlb1;

import com.bbva.prue.dto.customer.CustomerDTO;

/**
 * Interfaz de la librería de infraestructura para consulta de cliente
 */
public interface PRUERLB1 {

	/**
	 * Busca un cliente en la base de datos por tipo y número de documento
	 * @param identityDocumentTypeId tipo de documento
	 * @param identityDocumentNumber número de documento
	 * @return CustomerDTO con la información del cliente, o null si no existe
	 */
	CustomerDTO executeGetCustomer(String identityDocumentTypeId, String identityDocumentNumber);

}