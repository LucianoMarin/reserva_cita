Microservico creado en JAVA SPRINGBOOT utilizando CRUD completo
Este microservicio nos permitira crear citas medicas.

para usarlo, usar las siguientes url:

ENDPOINTS GET

http://localhost:8080/citas
Permite mostrar todas las citas generadas y sus detalles

http://localhost:8080/citas/1 <- (1 es una variable)
Permite mostrar una cita y sus detalles

http://localhost:8080/citas/disponibilidad-hoy
Permite mostrar las horas disponibles, usando como variable el día de hoy 
“LocalDate.now()” o el dia de hoy.



http://localhost:8080/citas/disponibilidad-fecha/2026-03-28
Permite mostrar las horas disponibles en un día especifico.


ENDPOINTS POST

http://localhost:8080/citas
Via postman y por body, permite agregar una cita en formato JSON.

ENDPINT DELETE
http://localhost:8080/citas/1 <- (1 es el id del elemento a eliminar)
Via postman y por http DELETE permite eliminar un elemento, buscado por su id


ENDPOINT PUT
http://localhost:8080/citas/2 <- (1 es el id del elemento a modificar)
Via postman y por HTTP PUT permite editar una cita, editando por su id.


http://localhost:8080/citas/actualizar-estados-cita
Via postman y por HTTP PUT permite actualizar o refrescar los estados de la citacion (recomendado usarlos despues de hacer un POST)








