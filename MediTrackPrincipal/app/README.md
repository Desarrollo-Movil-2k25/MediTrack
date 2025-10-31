# 🚀 Proyecto Individual - Primer Avance

**🗓 Fecha:** 18/10/2025

---

## ✨ Descripción del Primer Avance

En esta primera etapa del **Proyecto Individual** se estableció la **estructura base del proyecto**, siguiendo un enfoque cercano al **patrón de diseño MVC (Modelo-Vista-Controlador)**.

Se crearon los siguientes **directorios y archivos principales**:

### 📁 `entity/`
Contiene las clases principales del proyecto que representan las **entidades del dominio**:

- `Person` → Clase base que define los atributos y comportamientos generales de una persona.
- `User` → Clase que **hereda de `Person`**, agregando atributos específicos de un usuario del sistema.
- `Medication` → Clase que define las características de los medicamentos: nombre, dosis, frecuencia y opcionalmente imagen.

---

### 📁 `controller/`
Encargado de la **lógica de control**, coordinando la interacción entre las entidades y la interfaz del usuario.  
Actualmente se implementan controladores para `User` y `Medication`.

---

### 📁 `data/`
Gestiona el **almacenamiento y manipulación de datos**, incluyendo interfaces y clases que simulan la persistencia de información de usuarios y medicamentos.

---

### 📁 `util/`
Contiene **utilidades y helpers** para operaciones recurrentes o compartidas entre las diferentes capas del proyecto.

---
## ✨ Descripción del Segundo Avance
- Creación de los layouts de login y register.