# EjercicioConcesionario
Ejercicio de un concesionario. Utiliza todo tipo de relaciones con JPA y para la parte del maneja de sesiones utiliza Spring Session con Hazelcast como cache

El orden de uso debe ser:

Crear un comercial.
Crear un cliente (pasandole un comercial).
Crear un estado.
Crear unos vehiculos (un json array).
Crear un pedido (pasandole un comercial, un cliente, un estado y una lista de vehiculos).
Crear una factura (pasandole un pedido).

Si revisamos despues de esto nuevamente el pedido, veremos que todos los datos relacionados estan correctamente guardados en el pedido.

Este ejercicio final de curso incluye manejo de sesiones con Spring Session en conjunto con Spring Security, ademas del resto de tecnologias
basicas para trabajar con DAOS, services, controllers y entidades JPA (con sus relaciones).
