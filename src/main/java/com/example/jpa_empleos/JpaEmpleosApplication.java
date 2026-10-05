package com.example.jpa_empleos;

import com.example.jpa_empleos.repository.CategoriasJPARepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JpaEmpleosApplication implements CommandLineRunner {

	private final CategoriasJPARepository categoriasJPARepo;

	public JpaEmpleosApplication(CategoriasJPARepository categoriasJPARepo) {
		this.categoriasJPARepo = categoriasJPARepo;
	}

	public static void main(String[] args) {
		SpringApplication.run(JpaEmpleosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		borrarTodasEnBloque();
	}

	/**
	 * Método deleteAllInBatch [Usar con precaución] - Interfaz JPARepository
	 */
	private void borrarTodasEnBloque() {
		categoriasJPARepo.deleteAllInBatch();
	}
}