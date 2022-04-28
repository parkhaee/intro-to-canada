package ca.sheridancollege.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.sheridancollege.beans.Invention;

public interface InventionRepository extends JpaRepository<Invention,Long>{
	public Invention findByInventionName(String name);
	public List<Invention> findByInventDateTimeAfter(LocalDateTime inventDateTime);
}
