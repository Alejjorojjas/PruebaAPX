import React, { useState } from 'react';

const DOCUMENT_TYPES = [
  { value: 'CC', label: 'Cédula de Ciudadanía' },
  { value: 'CE', label: 'Cédula de Extranjería' },
  { value: 'PA', label: 'Pasaporte' },
  { value: 'TI', label: 'Tarjeta de Identidad' }
];

const SearchForm = ({ onSearch, loading }) => {
  const [identityDocumentTypeId, setIdentityDocumentTypeId] = useState('');
  const [identityDocumentNumber, setIdentityDocumentNumber] = useState('');
  const [errors, setErrors] = useState({});

  const validate = () => {
    const newErrors = {};
    if (!identityDocumentTypeId) {
      newErrors.identityDocumentTypeId = 'Seleccione el tipo de documento';
    }
    if (!identityDocumentNumber.trim()) {
      newErrors.identityDocumentNumber = 'Ingrese el número de documento';
    } else if (!/^[a-zA-Z0-9]+$/.test(identityDocumentNumber.trim())) {
      newErrors.identityDocumentNumber = 'El número de documento solo puede contener letras y números';
    }
    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    if (validate()) {
      onSearch(identityDocumentTypeId, identityDocumentNumber.trim());
    }
  };

  return (
    <form onSubmit={handleSubmit} className="search-form">
      <h2>Consulta de Cliente</h2>

      <div className="form-group">
        <label htmlFor="documentType">Tipo de Documento</label>
        <select
          id="documentType"
          value={identityDocumentTypeId}
          onChange={(e) => setIdentityDocumentTypeId(e.target.value)}
          disabled={loading}
          className={errors.identityDocumentTypeId ? 'input-error' : ''}
        >
          <option value="">Seleccione...</option>
          {DOCUMENT_TYPES.map(type => (
            <option key={type.value} value={type.value}>
              {type.label}
            </option>
          ))}
        </select>
        {errors.identityDocumentTypeId && (
          <span className="error-message">{errors.identityDocumentTypeId}</span>
        )}
      </div>

      <div className="form-group">
        <label htmlFor="documentNumber">Número de Documento</label>
        <input
          id="documentNumber"
          type="text"
          value={identityDocumentNumber}
          onChange={(e) => setIdentityDocumentNumber(e.target.value)}
          placeholder="Ej: 1234567890"
          disabled={loading}
          className={errors.identityDocumentNumber ? 'input-error' : ''}
        />
        {errors.identityDocumentNumber && (
          <span className="error-message">{errors.identityDocumentNumber}</span>
        )}
      </div>

      <button type="submit" disabled={loading} className="btn-search">
        {loading ? 'Buscando...' : 'Buscar Cliente'}
      </button>
    </form>
  );
};

export default SearchForm;