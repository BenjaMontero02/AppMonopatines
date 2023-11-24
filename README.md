# AppMonopatines
Trabajo final de la materia arquitecturas web

Este proyecto representó el trabajo final de la materia Arquitecturas Web, enfocándose en la aplicación práctica de los conocimientos adquiridos en Spring Boot. Se desarrolló una aplicación con una arquitectura basada en microservicios, implementando Spring Cloud Gateway junto con Spring Security + JWT.

El proyecto se centra en una aplicación dedicada a la gestión de monopatines, donde los usuarios tienen la capacidad de utilizar estos vehículos. La aplicación cuenta con una sólida lógica de negocios que opera en segundo plano. En este caso, el enfoque se limitó al backend.

El sistema consta de cuatro microservicios, siendo el gateway el punto de entrada que se comunica con el microservicio de autenticación (auth). Tras la validación exitosa, redirige las solicitudes al microservicio correspondiente, ya sea para gestionar usuarios o monopatines. La implementación integral y la colaboración efectiva de nuestro equipo permitieron alcanzar un notable resultado en este proyecto
