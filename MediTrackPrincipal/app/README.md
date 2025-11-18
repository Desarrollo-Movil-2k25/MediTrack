# 🚀 Proyecto Individual — MediTracker

**🗓 Fecha de inicio:** 18/10/2025

---

## 📌 Primer Avance

### ✨ Descripción General

En esta primera etapa del **Proyecto Individual** se estableció la **estructura base del proyecto**, siguiendo un enfoque cercano al **patrón de diseño MVC (Modelo-Vista-Controlador)**.

### 🗂 Estructura de Directorios y Archivos

#### 📁 `entity/`
Contiene las clases principales del proyecto que representan las **entidades del dominio**:

- `Person` → Clase base que define los atributos y comportamientos generales de una persona.
- `User` → Clase que **hereda de `Person`**, agregando atributos específicos de un usuario del sistema.
- `Medication` → Clase que define las características de los medicamentos: nombre, dosis, frecuencia y opcionalmente imagen.

#### 📁 `controller/`
Encargado de la **lógica de control**, coordinando la interacción entre las entidades y la interfaz del usuario.  
Actualmente se implementan controladores para `User` y `Medication`.

#### 📁 `data/`
Gestiona el **almacenamiento y manipulación de datos**, incluyendo interfaces y clases que simulan la persistencia de información de usuarios y medicamentos.

#### 📁 `util/`
Contiene **utilidades y helpers** para operaciones recurrentes o compartidas entre las diferentes capas del proyecto.

---

## 📌 Segundo Avance

### ✨ Descripción General

- Creación de los layouts de login y register.

---
## 📌 Tercer Avance — Task 5

### 🧑‍💻 Autor: *Esteban Amores – MediTracker*  
**🗓 Fecha:** 03/11/2025 — 🕐 Hora: 12:41 a.m.

### ✨ Descripción del Avance

Durante este avance se implementó la lógica principal correspondiente al flujo de **autenticación** dentro de la clase `MainActivity`, encargada del proceso de inicio de sesión de usuarios en la aplicación **MediTracker**.

### 🔍 Detalles del Desarrollo

#### ✅ Validación de Usuario

Se estableció una verificación para comprobar si el **nombre de usuario** ingresado existe dentro del sistema:

- En caso de que el usuario no exista, se notifica al usuario mediante un mensaje adecuado.
- Si el usuario existe pero la **contraseña es incorrecta**, se muestra un mensaje de error correspondiente.
- Si las credenciales son válidas, el sistema permite el acceso al resto de la aplicación. *(Falta desarrollar)*

#### 🆕 Gestión de Creación de Cuentas

Se configuró el botón **“Crear cuenta”**, el cual redirige al usuario hacia la pantalla `RegisterActivity`.

Esta vista tiene la funcionalidad exclusiva de **registrar nuevos usuarios**, realizando una **inserción en la lista general de usuarios** dentro de la capa de datos, garantizando así la **persistencia y disponibilidad** de la información para futuros inicios de sesión.

---
**🗓 Fecha:** 04/11/2025 — 🕐 Hora: 12:27 a.m.

### ✨ Descripción del Avance

Durante este avance se implementó la interfaz principal del apartado de **medicamentos** dentro de la aplicación **MediTracker**, incluyendo la creación de la actividad `MedicationActivity` y su respectivo diseño en XML.

### 🔍 Detalles del Desarrollo

#### ✅ Interfaz de Medicamentos

Se desarrolló la estructura visual y funcional básica para la gestión de medicamentos:

- Se creó la actividad `MedicationActivity` junto con su archivo XML correspondiente.
- Se implementó el método `showTimePickerDialog`, encargado de mostrar un selector de hora para programar la toma de medicamentos.

#### ⚠️ Pendientes por Implementar

- Lógica completa del **CRUD** para medicamentos (crear, modificar, eliminar).
- Layouts faltantes que impiden la edición o eliminación desde otras vistas:
  - Pantalla de **Inicio**
  - Pantalla de **Calendario**
  - Pantalla de **Perfil de Usuario**

---

**🗓 Fecha:** 04/11/2025 — 🕐 Hora: 11:38 a.m.

### ✨ Descripción del Avance

Durante este avance se completó la implementación del **CRUD (Create, Read, Update, Delete)** para la gestión de **medicamentos** dentro de la aplicación **MediTracker**, asegurando un flujo funcional y estable en el manejo de registros desde la interfaz de usuario.

### 🔍 Detalles del Desarrollo

#### ✅ Controlador y Lógica de Datos

- Se modifico la clase `MedicationController`, la cual gestiona las operaciones CRUD apoyándose en `MemoryDataManagerMedication`.
- Se añadió el control de estado `isEditMode` para distinguir entre inserciones nuevas y actualizaciones.
- Se mejoró la conversión y formateo de fechas y horas (`parseStringToDateModern`, `parseStringToTimeModern`) con soporte para formato AM/PM.

#### 💻 Interfaz de Usuario
- Se actualizó la vista de registro de medicamentos para permitir:
  - Validaciones visuales y mensajes `Toast` informativos.
- Se deshabilitan campos de edición durante el modo **editar** tras realizar una búsqueda (`searchMedication()`).

#### 🧩 Funcionalidades Clave
- **Agregar medicamento:** con verificación de campos y formato.
- **Buscar medicamento:** carga automática de datos y modo edición.
- **Editar medicamento:** permite actualizar registros existentes.
- **Eliminar medicamento:** disponible desde la interfaz de mantenimiento.

#### ⚠️ Pendientes por Implementar
- Layouts faltantes que impiden la edición o eliminación desde otras vistas:
  - Pantalla de **Inicio**
  - Pantalla de **Calendario**
  - Pantalla de **Perfil de Usuario**
- CRUD
    - El crud respectivo de cada layout faltante.
---
# **🗓 Fecha:** 16/11/2025 — 🕐 Hora: 2:16 a.m.

### ✨ Descripción del Avance

Durante este avance se trabajó en varias áreas clave del proyecto **MediTracker**, abordando mejoras en la autenticación, la interfaz de usuario, la gestión interna de datos y la experiencia general de navegación del usuario. También se solucionaron problemas funcionales importantes relacionados con el manejo de medicamentos por usuario.

---

### 🔍 Detalles del Desarrollo

#### ✅ **1. Lógica de Inicio de Sesión y Manejo de Sesión**
- Se implementó la clase `SessionManager` para almacenar y recuperar el usuario actual.
- Integración completa del `SessionManager` con `LoginActivity`, `MedicationActivity`, `HomeActivity` y `ProfileActivity`.
- El flujo de sesión ahora permite:
  - Guardar el usuario logueado.
  - Cargar automáticamente su información en pantallas relacionadas.
  - Cerrar sesión limpiando completamente el historial de actividad.

---

#### 🎨 **2. Reordenamiento de Colores y Mejora Visual de Layouts**
- Se reorganizó la paleta de colores base utilizando los tonos:
  - Azul principal `#4180ab`
  - Azul claro `#8ab3cf`
  - Celeste grisáceo `#bdd1de`
  - Fondo claro `#e4ebf0`
- Se mejoraron los layouts principales:
  - Login
  - Registro
  - Perfil
  - Medicamentos
- Se incorporaron nuevos fondos degradados, tarjetas estilizadas y bordes redondeados.

---

#### 🧩 **3. Solución de Problemas Críticos en CRUD de Medicamentos por Usuario**
- Se corrigió un error donde modificar un medicamento de un usuario eliminaba medicamentos de otros usuarios, debido a que el sistema filtraba solo por `id`.
- Se añadió el atributo `ownerUser` en la clase `Medication`.
- Se actualizaron las funciones:
  - `add()`
  - `update()`
  - `remove()`
  - `getById()`

  Para trabajar con la combinación `id + ownerUser` y evitar conflictos entre usuarios.

---

#### 💻 **4. Perfil de Usuario**
- Se creó la pantalla `ProfileActivity` con carga automática del usuario actual.
- Se implementó:
  - Búsqueda del usuario por nombre.
  - Visualización de sus datos personales.
  - Botón funcional para "Cerrar Sesión".
- Se ajustó la navegación mediante el footer.
- Se dejó preparado el entorno para la futura funcionalidad de **actualización de datos del usuario**.

---

#### 🗃️ **5. RecyclerView en HomeActivity**
- Se corrigió la estructura del RecyclerView que anteriormente no mostraba elementos debido a una declaración incorrecta.
- Ahora se muestra correctamente entre el header y el footer.
- Se dejó listo para conectar el adaptador y mostrar medicamentos asociados al usuario.

---

#### 🖼️ **6. Imagen del Medicamento**
- Se preparó la estructura visual para soportar:
  - Selección de foto desde la cámara.
  - Selección desde la galería.
  - Vista previa de imagen.
- Falta implementar la lógica funcional para almacenar y mostrar imágenes reales.

---

#### 📅 **7. Selector de Fecha (DatePicker) Mejorado**
- Se corrigió un error donde el selector de fecha mostraba el año 1900.
- Se ajustó para que:
  - Abra en la fecha actual.
  - Recuerde la última fecha seleccionada.
- Se mejoró el formateo de fechas para mayor claridad.

---

### ⚠️ Pendientes por Implementar

#### 📌 **Interfaz**
- Completar las pantallas:
  - **Inicio** (RecyclerView con CRUD completo)
  - **Calendario**
  - **Perfil** (actualizar datos)
- Implementar vista detallada del medicamento (opcional).

#### 📌 **CRUD Adicional**
- Implementar CRUD del usuario (actualizar datos personales).
- CRUD para la pantalla de inicio y calendario.

#### 📌 **Imágenes**
- Guardar imágenes reales en memoria.
- Mostrar miniaturas en el RecyclerView.

---

### 🧩 Conclusión
El proyecto avanzó considerablemente, fortaleciéndose en áreas críticas como manejo de sesión, experiencia de usuario, estabilidad del CRUD y diseño visual. Las funcionalidades esenciales están sólidas y listas para integrar las vistas faltantes y mejorar la persistencia de datos.

# 🗓 Fecha: 18/11/2025 — 🕐 Hora: 12:34 a.m.

## ✨ Resumen del Avance

Hoy se completaron dos áreas principales del proyecto **MediTracker**:  
la **actualización de perfil del usuario** y la **integración de imágenes en medicamentos**, además de solucionar fallos internos en el manejo de datos.

---

## 🔧 Cambios Realizados

### ✅ 1. Actualización de Perfil
- Se implementó la edición completa de datos del usuario en `ProfileActivity`.
- Corrección de valores nulos y construcción adecuada del objeto `User`.
- Se agregó trazabilidad con logs para depuración.
- Se solucionó el problema donde el perfil no se actualizaba visualmente.

### 📸 3. Manejo de Imágenes en Medicamentos
- Integración completa de fotos desde **cámara** y **galería**.
- Visualización y carga de imagen al editar un medicamento.
---

## 🧩 Conclusión

El sistema ahora permite **actualizar el perfil**, manejar **imágenes en medicamentos** y gestiona correctamente la actualización de usuarios en memoria, mejorando la estabilidad y funcionalidad general del proyecto.


