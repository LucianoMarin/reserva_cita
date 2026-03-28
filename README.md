Microservico creado en JAVA SPRINGBOOT usando endpoints del tipo GET.
El cual nos permitirá ver los detalles de citas médicas y la disponibilidad de horario.

para usarlo, usar las siguientes url:

http://localhost:8080/citas
Permite mostrar todas las citas generadas y sus detalles

http://localhost:8080/citas/1 <- (1 es una variable)
Permite mostrar una cita y sus detalles
http://localhost:8080/citas/horarios_disponibles
Permite mostrar las horas disponibles, usando como variable el día de hoy 
“LocalDate.now()”

http://localhost:8080/citas/horarios_disponibles/2026-03-28
Permite mostrar las horas disponibles en un día especifico.

