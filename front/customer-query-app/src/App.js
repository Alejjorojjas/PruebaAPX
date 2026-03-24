import React, { useState } from 'react';
import SearchForm from './components/SearchForm';
import CustomerCard from './components/CustomerCard';
import Spinner from './components/Spinner';
import { getCustomer } from './services/customerService';
import './App.css';

const App = () => {
  const [customer, setCustomer] = useState(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  const handleSearch = async (identityDocumentTypeId, identityDocumentNumber) => {
    setLoading(true);
    setError(null);
    setCustomer(null);

    try {
      const result = await getCustomer(identityDocumentTypeId, identityDocumentNumber);
      setCustomer(result);
    } catch (err) {
      setError(err.message || 'Error al consultar el cliente');
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="app-container">
      <header className="app-header">
        <h1>🏦 Consulta de Clientes</h1>
        <p>Ingresa el tipo y número de documento para consultar la información del cliente</p>
      </header>

      <main className="app-main">
        <SearchForm onSearch={handleSearch} loading={loading} />

        {loading && <Spinner />}

        {error && (
          <div className="error-container">
            <span className="error-icon">⚠️</span>
            <p>{error}</p>
          </div>
        )}

        {customer && <CustomerCard customer={customer} />}
      </main>
    </div>
  );
};

export default App;