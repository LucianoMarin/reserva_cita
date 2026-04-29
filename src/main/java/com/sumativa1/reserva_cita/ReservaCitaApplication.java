package com.sumativa1.reserva_cita;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import com.sumativa1.reserva_cita.model.Especialidad;
import com.sumativa1.reserva_cita.model.Medico;
import com.sumativa1.reserva_cita.model.Paciente;
import com.sumativa1.reserva_cita.repository.EspecialidadRepository;
import com.sumativa1.reserva_cita.repository.MedicoRepository;
import com.sumativa1.reserva_cita.repository.PacienteRepository;

@SpringBootApplication
public class ReservaCitaApplication {

	public static void main(String[] args) {

		SpringApplication.run(ReservaCitaApplication.class, args);

	}

	@Bean
@Profile("dev")
CommandLineRunner initData(
        EspecialidadRepository espRepo,
        MedicoRepository medRepo,
        PacienteRepository pacRepo) {

    return args -> {

        if (espRepo.count() == 0) {

            List<Especialidad> especialidades = List.of(
                    new Especialidad("Cardiologo"),
                    new Especialidad("Diabetologo"),
                    new Especialidad("Cirujano"),
                    new Especialidad("Medico General"),
                    new Especialidad("Enfermero"));

            espRepo.saveAll(especialidades);
        }

        if (medRepo.count() == 0) {

            Especialidad cardiologo = espRepo
                    .findBynombreEspecialidad("Cardiologo")
                    .orElseThrow();

            Especialidad medicoGeneral = espRepo
                    .findBynombreEspecialidad("Medico General")
                    .orElseThrow();

            List<Medico> medicos = List.of(
                    new Medico("MED000001", "19332143-2", "Alberto", "Cortez", "Cortez", cardiologo),
                    new Medico("MED000002", "13848223-1", "Camilo", "Villanueva", "Perez", cardiologo),
                    new Medico("MED000003", "15552233-2", "Daniel", "Frez", "Frez", medicoGeneral),
                    new Medico("MED000004", "18586023-2", "Carlos", "Ruiz", "Tagle", medicoGeneral),
                    new Medico("MED000005", "8856823-7", "Javiera", "Cid", "Cid", medicoGeneral),
                    new Medico("MED000006", "9332143-4", "Camila", "Aguero", "Jara", medicoGeneral));

            medRepo.saveAll(medicos);
        }

        if (pacRepo.count() == 0) {
            List<Paciente> pacientes = List.of(
                    new Paciente("18775116-0","Luciano","Marin"),
                    new Paciente("9415449-9","Lujana","Eunice"),
                    new Paciente("9862879-7","Mario","Marin"),
                    new Paciente("19029771-3","Carolina","Perez"),
                    new Paciente("11111111-1","Pedro","Pascal")
            );

            pacRepo.saveAll(pacientes);
        }
    };
}

}
