package com.example.jpa_empleos;

import com.example.jpa_empleos.models.Categoria;
import com.example.jpa_empleos.repository.CategoriasJPARepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

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
		buscarTodosPaginacionOrdenados();
	}

	/**
	 * Metodo findAll [Con paginacion y Ordenados]
	 * Interfaz PagingAndSortingRepository
	 */
	private void buscarTodosPaginacionOrdenados() {

		Page<Categoria> page =
				categoriasJPARepo.findAll(
						PageRequest.of(
								0,
								5,
								Sort.by("nombre").descending()
						)
				);

		System.out.println(
				"Total Registros: " + page.getTotalElements()
		);

		System.out.println(
				"Total Paginas: " + page.getTotalPages()
		);

		for (Categoria c : page.getContent()) {
			System.out.println(
					c.getId() + " " + c.getNombre()
			);
		}
	}
}