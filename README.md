# 🏠 Gestión Inmobiliaria — API

Backend de una plataforma inmobiliaria donde los propietarios publican inmuebles y definen su disponibilidad, y los clientes agendan citas de visita. Proyecto final del curso de Ingeniería de Software II (Universidad del Quindío), desarrollado en equipo.

![Java](https://img.shields.io/badge/Java-21-orange?logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5-6DB33F?logo=springboot&logoColor=white)
![MongoDB](https://img.shields.io/badge/MongoDB-47A248?logo=mongodb&logoColor=white)
![JWT](https://img.shields.io/badge/Auth-JWT-000000?logo=jsonwebtokens)

**Frontend:** [ProyectoFinalSoftwareII_Fronted](https://github.com/MiltonGomez775/ProyectoFinalSoftwareII_Fronted)

---

## Funcionalidades

- Registro, inicio de sesión con **JWT** y contraseñas cifradas con **BCrypt**.
- **Recuperación y restablecimiento de contraseña.**
- **Roles** de usuario (propietario y cliente) con cambio de rol.
- **Inmuebles:** CRUD y vista de detalle.
- **Agenda:** el propietario define espacios disponibles para visitas.
- **Citas:** el cliente reserva una visita en un espacio disponible y consulta sus citas.

## Tecnologías

- Java 21 · Spring Boot 3.5 (Web, Security, Data MongoDB)
- JJWT · BCrypt
- MongoDB
- Lombok · Gradle

## Estructura

```
src/main/java/edu/co/uniquindio/demo
├── config/       # CORS y seguridad
├── controller/   # Auth, Usuario, Rol, Inmueble, EspacioDisponible, Cita
├── dto/
├── model/        # Documentos de MongoDB
├── repository/
├── security/     # Utilidades JWT
└── service/
```

## Cómo ejecutarlo

**Requisitos:** JDK 21 y MongoDB corriendo en `localhost:27017`.

```bash
./gradlew bootRun
```

La API queda en `http://localhost:8080` y usa la base `projectFinal`.

## Equipo

- **[Milton Gómez](https://github.com/MiltonGomez775):** inmuebles, agenda de disponibilidad, integración con el frontend y correcciones de endpoints.
- **[Esteban](https://github.com/Steban1308):** módulo de gestión de citas.
