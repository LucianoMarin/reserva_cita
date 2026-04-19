package com.sumativa1.reserva_cita.controller;

import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sumativa1.reserva_cita.model.Cita;
import com.sumativa1.reserva_cita.service.CitaService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/citas")
@CrossOrigin(origins = "*")

@Slf4j
public class CitaController {

    @Autowired
    private CitaService citaService;

    @GetMapping
    public List<Cita> mostrarCitas() {
        log.info("GET /citas");
        log.info("Retorna todas las citas");
        return citaService.mostrarCitas();

    }

    @GetMapping("/{id}")
    public Cita mostrarCita(@PathVariable Long id) {
        log.info("GET /citas/id}");
        log.info("Retorna todas una sola cita");
        return citaService.mostrarCitaId(id);

    }

    @PostMapping
    public Cita ingresarCita(@RequestBody Cita cita) {
        log.info("POST /citas");
        log.info("Agrega nueva cita");
        return citaService.agregarCita(cita);

    }

    @DeleteMapping("/{id}")
    public void eliminarCita(@PathVariable Long id) {
        log.info("DELETE /citas/id");
        log.info("Elimina una cita");
        citaService.eliminarCita(id);

    }

    @PutMapping("/{id}")
    public Cita editarCitacion(@RequestBody Cita cita, @PathVariable Long id) {
        log.info("PUT /citas/id");
        log.info("Modifica una cita");
        return citaService.modificarCita(cita, id);

    }

    @PutMapping("/actualizar-estados-cita")

    public ResponseEntity<String> actualizarEstadosCita() {
        log.info("PUT /citas/id");
        log.info("Actualiza los estados de las citaciones");
        citaService.actualizarEstadoCitas();

        return ResponseEntity.ok("Estados actualizados");

    }

    @GetMapping("/disponibilidad-hoy")
    public List<LocalTime> disponibilidadHoy() {
        log.info("GET /citas/disponibilidad-hoy");
        log.info("Retorna las horas disponibles hoy");
        return citaService.mostrarHorarioDisponibleHoy();
    }

    @GetMapping("/disponibilidad-fecha/{fecha}")
    public List<LocalTime> disponibilidadPorFecha(@PathVariable String fecha) {
        log.info("GET /citas/disponibilidad-fecha/fecha");
        log.info("Retorna las horas disponibles de una fecha especifica");
        return citaService.mostrarHorariosPorFecha(fecha);
    }

}
