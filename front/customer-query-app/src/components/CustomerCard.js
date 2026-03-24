import React from 'react';

const GENDER_LABELS = {
  M: 'Masculino',
  F: 'Femenino'
};

const CustomerCard = ({ customer }) => {
  return (
    <div className="customer-card">
      <div className="card-header">
        <div className="avatar">
          {customer.firstName.charAt(0)}{customer.lastName.charAt(0)}
        </div>
        <div className="card-title">
          <h2>{customer.firstName} {customer.lastName}</h2>
          <span className="customer-id">ID: {customer.customerId}</span>
        </div>
      </div>

      <div className="card-body">
        <div className="card-field">
          <span className="field-label">Tipo de Documento</span>
          <span className="field-value">{customer.identityDocument}</span>
        </div>
        <div className="card-field">
          <span className="field-label">Número de Documento</span>
          <span className="field-value">{customer.identityDocumentNumber}</span>
        </div>
        <div className="card-field">
          <span className="field-label">Nacionalidad</span>
          <span className="field-value">{customer.nationalityId}</span>
        </div>
        <div className="card-field">
          <span className="field-label">Género</span>
          <span className="field-value">{GENDER_LABELS[customer.genderId] || customer.genderId}</span>
        </div>
      </div>
    </div>
  );
};

export default CustomerCard;