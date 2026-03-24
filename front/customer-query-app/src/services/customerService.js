import axios from 'axios';

const APX_BASE_URL = 'http://localhost:8080/ARQ/REST/v1.0';
const TRANSACTION_ID = 'PRUETTR1-01-CO';

// Mock de datos para pruebas locales sin entorno APX activo
const MOCK_DATA = {
  CC: {
    '1234567890': {
      firstName: 'Alejandro',
      lastName: 'Rojas',
      customerId: 'C001',
      nationalityId: 'CO',
      genderId: 'M',
      identityDocument: 'Cédula de Ciudadanía',
      identityDocumentNumber: '1234567890',
      identityDocumentTypeId: 'CC'
    },
    '0987654321': {
      firstName: 'María',
      lastName: 'González',
      customerId: 'C002',
      nationalityId: 'CO',
      genderId: 'F',
      identityDocument: 'Cédula de Ciudadanía',
      identityDocumentNumber: '0987654321',
      identityDocumentTypeId: 'CC'
    }
  },
  PA: {
    'AB123456': {
      firstName: 'Carlos',
      lastName: 'Martínez',
      customerId: 'C003',
      nationalityId: 'CO',
      genderId: 'M',
      identityDocument: 'Pasaporte',
      identityDocumentNumber: 'AB123456',
      identityDocumentTypeId: 'PA'
    }
  }
};

const USE_MOCK = true; // Cambiar a false cuando el entorno APX esté activo

export const getCustomer = async (identityDocumentTypeId, identityDocumentNumber) => {
  if (USE_MOCK) {
    // Simular delay de red
    await new Promise(resolve => setTimeout(resolve, 1000));

    const customer = MOCK_DATA[identityDocumentTypeId]?.[identityDocumentNumber];
    if (!customer) {
      throw new Error('Cliente no encontrado');
    }
    return customer;
  }

  // Llamada real a la transacción APX
  const response = await axios.post(`${APX_BASE_URL}/${TRANSACTION_ID}`, {
    identityDocumentTypeId,
    identityDocumentNumber
  });

  if (response.data.result?.code !== '200') {
    throw new Error('Cliente no encontrado');
  }

  return response.data.data;
};