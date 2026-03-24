# Prueba Técnica: Consulta de Información de Clientes
## Arquitectura APX + React

---

## 1. Descripción General

Este proyecto implementa un flujo de **Consulta de Información de Clientes** usando la arquitectura APX (Back-end) y React (Front-end). Permite buscar un cliente por tipo y número de documento, y visualizar sus datos en pantalla.

---

## 2. Decisiones Técnicas

### Back-end (APX)
- **UUAA:** `PRUE` — identificador del proyecto dentro del ecosistema APX.
- **Arquitectura por capas:** Se siguió el estándar APX separando claramente:
  - **DTO:** Estructura de datos del cliente sin lógica de negocio.
  - **Librería de Infraestructura:** Acceso a la base de datos vía utilidad JDBC de APX.
  - **Librería de Negocio:** Orquesta la llamada a infraestructura y aplica validaciones.
  - **Transacción:** Único punto de entrada al servicio, expone la funcionalidad al exterior.
- **Base de datos:** PostgreSQL 16 simulando datos locales, conectado vía JDBC usando la utilidad nativa de APX.
- **Orden de creación de componentes:** DTOs → Librerías → Transacción. Este orden es obligatorio en APX porque cada componente depende del anterior.

### Front-end (React)
- **Framework:** React — elegido por su agilidad para construir interfaces modulares sin el boilerplate de Angular, cumpliendo el criterio de modularidad exigido en la prueba.
- **Consumo de API:** Se consume la transacción APX mediante fetch. Se incluye mock de API para pruebas locales sin entorno APX activo.

---

## 3. Estructura del Proyecto

```
/
├── back/
│   └── apx-du-copruecustomerquery/   # Unidad de despliegue APX
│       ├── dto/                       # Data Transfer Objects
│       ├── lib/                       # Librerías (negocio + infraestructura)
│       └── transaction/               # Transacción APX
└── front/
    └── customer-query-app/            # Aplicación React
```

---

## 4. Pasos de Construcción APX

### Paso 1 — Crear Unidad de Despliegue
Se inicializa el proyecto APX con el UUAA `PRUE`:

```bash
apx init du-online -u="PRUE" -n="apx-du-copruecustomerquery" -d="Customer query unit for technical test"
cd apx-du-copruecustomerquery
```

> **¿Por qué?** La unidad de despliegue (DU) es el contenedor raíz de todos los componentes APX. Sin ella no se pueden crear DTOs, librerías ni transacciones.

---

### Paso 2 — Crear DTOs
*(pendiente de completar)*

---

### Paso 3 — Crear Librerías
*(pendiente de completar)*

---

### Paso 4 — Crear Dependencias
*(pendiente de completar)*

---

### Paso 5 — Configurar JDBC con PostgreSQL
*(pendiente de completar)*

---

### Paso 6 — Crear Transacción
*(pendiente de completar)*

---

### Paso 7 — Compilar y desplegar local
*(pendiente de completar)*

---

## 5. Instrucciones para Ejecutar Localmente

### Back-end

```bash
# 1. Entrar a la unidad de despliegue
cd back/apx-du-copruecustomerquery

# 2. Compilar con Maven
mvn clean install

# 3. Levantar el entorno local APX
# (Ver instrucciones específicas del entorno ENTORNO_LOCAL_APX)
```

### Front-end

```bash
cd front/customer-query-app
npm install
npm start
# Abre http://localhost:3000
```

---

## 6. Flujo del Sistema

```
[React Form] 
    → ingresa tipo y número de documento
    → POST /transaction/PRUE/getCustomer
        → Transacción APX
            → Librería de Negocio (valida entradas)
                → Librería de Infraestructura (JDBC → PostgreSQL)
                    → retorna CustomerDTO
    → renderiza Card con datos del cliente
    → muestra error si no se encuentra
```

---

## 7. Manejo de Errores

- **Cliente no encontrado:** La librería de infraestructura retorna error estructurado; la transacción lo propaga; React muestra mensaje "Cliente no encontrado".
- **Falla de conexión DB:** Error capturado en la librería de infraestructura y retornado como error estructurado APX.
- **Validación de entrada:** La librería de negocio valida que los parámetros no sean nulos/vacíos antes de llamar a infraestructura.

---

*Documentación generada y actualizada progresivamente durante el desarrollo.*
