package ca.sheridancollege.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.sheridancollege.beans.Language;

public interface LanguageRepository extends JpaRepository<Language,Long>{
	public List<Language> findByLanguageNameContaining(String languageName);
}
