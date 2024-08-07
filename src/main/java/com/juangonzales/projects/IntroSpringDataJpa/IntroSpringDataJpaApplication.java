package com.juangonzales.projects.IntroSpringDataJpa;

import com.juangonzales.projects.IntroSpringDataJpa.persistence.entity.Customer;
import com.juangonzales.projects.IntroSpringDataJpa.persistence.repository.CustomerCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class IntroSpringDataJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(IntroSpringDataJpaApplication.class, args);
	}

	@Autowired
	private CustomerCrudRepository customerCrudRepository;

	@Bean
	public CommandLineRunner testCustomerRepositoryCommand(){
		return args -> {
			Customer juan = new Customer();
			juan.setName("Juan Gonzales");
			juan.setPassword("juan123");

			Customer ramon = new Customer();
			ramon.setName("Ramon Hernandez");
			ramon.setPassword("ramon123");

			Customer luis = new Customer();
			luis.setName("Luis Marquez");
			luis.setPassword("luis123");

			System.out.println("Se guardaron 3 entidades");
			List<Customer> clientes = List.of(juan,ramon,luis);
			customerCrudRepository.saveAll(clientes);

			System.out.println("\n Imprimiendo todos los clientes");
			customerCrudRepository.findAll()
					.forEach(System.out::println);


			System.out.println("\nBuscando e imprimiendo a cliente Luis");
			customerCrudRepository.findById(3L)
					.ifPresent(each -> {
						each.setName("Ramon Hernández Chávez");
						each.setPassword("ramonhc123");

						customerCrudRepository.save(each);
					});

			System.out.println("\nEliminando al cliente Ramon");
			customerCrudRepository.deleteById(2L);

			System.out.println("\n Imprimiendo todos los clientes");
			customerCrudRepository.findAll()
					.forEach(System.out::println);
		};
	}

}
