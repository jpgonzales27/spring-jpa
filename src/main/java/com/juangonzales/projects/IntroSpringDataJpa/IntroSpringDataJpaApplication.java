package com.juangonzales.projects.IntroSpringDataJpa;

import com.juangonzales.projects.IntroSpringDataJpa.persistence.entity.Address;
import com.juangonzales.projects.IntroSpringDataJpa.persistence.entity.Customer;
import com.juangonzales.projects.IntroSpringDataJpa.persistence.repository.AddressCrudRepository;
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

	@Autowired
	private AddressCrudRepository addressCrudRepository;

	@Bean
	public CommandLineRunner populateDatabaseCommand(){
		return args -> {
			Customer juan = new Customer();
			juan.setName("Juan López");
			juan.setPassword("juan123");
			juan.setUsername("juan123");

			Address juanAddressOne = new Address();
			juanAddressOne.setCountry("El Salvador");
			juanAddressOne.setAddress("Casa 123, Calle Principal Col. Y, San Salvador");

			Address juanAddressTwo = new Address();
			juanAddressTwo.setCountry("Honduras");
			juanAddressTwo.setAddress("Casa 654, Calle Principal Col. ABC, Tegucigalpa");


			juan.addAddress(juanAddressOne);
			juan.addAddress(juanAddressTwo);

			customerCrudRepository.save(juan);
		};
	}

	@Bean
	public CommandLineRunner testOneToOneRelationshipsCommand(){
		return args -> {
			System.out.println("\nProbando relaciones bidireccionales entre address y customer\n");
			addressCrudRepository.findAll()
					.forEach(address -> {
						String mensaje = "Direccion: " + address.getId() + ": "+address.getAddress()+", cliente: "+address.getCustomer().getUsername();
						System.out.println(mensaje);
					});

			System.out.println("\nProbando relaciones bidireccionales entre customer y address\n");
			customerCrudRepository.findAll()
					.forEach(customer -> {
						String mensaje = "Cliente: " + customer.getUsername() +", Cant Direcciones: "+ customer.getAddresses().size();
						System.out.println(mensaje);
					});
		};
	}
}
