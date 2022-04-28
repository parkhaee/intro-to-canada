package ca.sheridancollege.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.sheridancollege.beans.Sports;

public interface SportsRepository extends JpaRepository<Sports,Long>{
	public List<Sports> findByMaxPlayerNumLessThan(int playerNum);
}
