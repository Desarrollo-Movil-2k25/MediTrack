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
