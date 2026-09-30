package com.example.jpa_empleos;

import com.example.jpa_empleos.models.Categoria;
import com.example.jpa_empleos.repository.CategoriasRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;

@SpringBootApplication
public class JpaEmpleosApplication implements CommandLineRunner {

	private final CategoriasRepository categoriasRepo;

	public JpaEmpleosApplication(CategoriasRepository categoriasRepo) {
		this.categoriasRepo = categoriasRepo;
	}

	public static void main(String[] args) {
		SpringApplication.run(JpaEmpleosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		modificar();
	}

	private void modificar() {

		Optional<Categoria> categoriaBuscada = categoriasRepo.findById(1);

		if (categoriaBuscada.isPresent()) {

			Categoria categoriaTmp = categoriaBuscada.get();

			categoriaTmp.setNombre("Ingeniería de Software");
			categoriaTmp.setDescripcion("Desarrollo de sistemas");

			categoriasRepo.save(categoriaTmp);

			System.out.println(categoriaBuscada);
			System.out.println("Categoría actualizada...");

		} else {
			System.out.println("Categoría no encontrada");
		}
	}
}