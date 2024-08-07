package com.juangonzales.projects.IntroSpringDataJpa;

import com.juangonzales.projects.IntroSpringDataJpa.persistence.entity.Customer;
import com.juangonzales.projects.IntroSpringDataJpa.persistence.repository.CustomerCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
//			Customer juan = new Customer();
//			juan.setName("Juan Gonzales");
//			juan.setPassword("juan123");
//
//			customerCrudRepository.save(juan);
//			System.out.println("Se guardo la entidad Juan");
//
//
//			System.out.println("\n Imprimiendo todos los clientes");
//			customerCrudRepository.findAll()
//					.forEach(System.out::println);


			System.out.println("\nBuscando e imprimiendo a cliente Juan");
			customerCrudRepository.findById(1L)
					.ifPresent(System.out::println);

			System.out.println("\nEliminando al cliente Juan");
			customerCrudRepository.deleteById(1L);

			System.out.println("\nBuscando e imprimiendo a cliente Juan");
			customerCrudRepository.findById(1L)
					.ifPresent(System.out::println);
		};
	}

}
