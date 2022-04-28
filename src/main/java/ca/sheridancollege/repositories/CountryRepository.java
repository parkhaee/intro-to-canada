package ca.sheridancollege.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.sheridancollege.beans.Country;


public interface CountryRepository extends JpaRepository<Country,Long>{
	public Country findByCountryName(String name);
}
