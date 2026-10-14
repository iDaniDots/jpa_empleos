package com.example.jpa_empleos;

import com.example.jpa_empleos.models.Categoria;
import com.example.jpa_empleos.models.EstatusVacante;
import com.example.jpa_empleos.models.Vacante;
import com.example.jpa_empleos.repository.VacantesRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class JpaEmpleosApplication implements CommandLineRunner {

	private final VacantesRepository vacantesRepo;

	public JpaEmpleosApplication(VacantesRepository vacantesRepo) {
		this.vacantesRepo = vacantesRepo;
	}

	public static void main(String[] args) {
		SpringApplication.run(JpaEmpleosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		guardarVacante();
	}

	/**
	 * Guardar una vacante
	 */
	private void guardarVacante() {

		Vacante vacante = new Vacante();

		vacante.setNombre("Desarrollador Java PRO");
		vacante.setDescripcion(
				"Se busca desarrollador con experiencia en Spring Boot y JPA."
		);
		vacante.setFecha(new Date());
		vacante.setSalario(25000.0);
		vacante.setEstatus(EstatusVacante.Creada);
		vacante.setDestacado(1);
		vacante.setImagen("logo_empresa.png");
		vacante.setDetalles(
				"Trabajo remoto con horario flexible. Beneficios y capacitación incluidos."
		);

		// Categoría existente en tu BD
		Categoria categoria = new Categoria();
		categoria.setId(23);

		vacante.setCategoria(categoria);

		vacantesRepo.save(vacante);

		System.out.println("Vacante guardada correctamente");
	}
}