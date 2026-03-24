package com.bbva.prue.lib.rlb1.impl;

import com.bbva.prue.dto.customer.CustomerDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * Librería de infraestructura para consulta de cliente en base de datos
 */
public class PRUERLB1Impl extends PRUERLB1Abstract {

	private static final Logger LOGGER = LoggerFactory.getLogger(PRUERLB1Impl.class);

	/**
	 * Busca un cliente en la base de datos por tipo y número de documento
	 * @param identityDocumentTypeId tipo de documento
	 * @param identityDocumentNumber número de documento
	 * @return CustomerDTO con la información del cliente, o null si no existe
	 */
	public CustomerDTO executeGetCustomer(String identityDocumentTypeId, String identityDocumentNumber) {

		LOGGER.info("Buscando cliente con tipo: {} y número: {}", identityDocumentTypeId, identityDocumentNumber);

		// Validación de entradas
		if (identityDocumentTypeId == null || identityDocumentTypeId.isEmpty()) {
			LOGGER.error("El tipo de documento no puede ser nulo o vacío");
			return null;
		}
		if (identityDocumentNumber == null || identityDocumentNumber.isEmpty()) {
			LOGGER.error("El número de documento no puede ser nulo o vacío");
			return null;
		}

		try {
			// Consulta SQL usando la utilidad JDBC de APX
			List<Map<String, Object>> result = jdbcUtils.queryForList(
					"sql.select_customer",
					identityDocumentTypeId,
					identityDocumentNumber
			);

			if (result == null || result.isEmpty()) {
				LOGGER.warn("No se encontró cliente con los documentos proporcionados");
				return null;
			}

			// Mapear resultado a CustomerDTO
			Map<String, Object> row = result.get(0);
			CustomerDTO customer = new CustomerDTO();
			customer.setFirstName((String) row.get("FIRST_NAME"));
			customer.setLastName((String) row.get("LAST_NAME"));
			customer.setCustomerId((String) row.get("CUSTOMER_ID"));
			customer.setNationalityId((String) row.get("NATIONALITY_ID"));
			customer.setGenderId((String) row.get("GENDER_ID"));
			customer.setIdentityDocument((String) row.get("IDENTITY_DOCUMENT"));
			customer.setIdentityDocumentNumber((String) row.get("IDENTITY_DOCUMENT_NUMBER"));
			customer.setIdentityDocumentTypeId((String) row.get("IDENTITY_DOCUMENT_TYPE_ID"));

			LOGGER.info("Cliente encontrado: {} {}", customer.getFirstName(), customer.getLastName());
			return customer;

		} catch (Exception e) {
			LOGGER.error("Error al consultar la base de datos: {}", e.getMessage());
			return null;
		}
	}
}