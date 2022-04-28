package ca.sheridancollege.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.sheridancollege.beans.User;

public interface UserRepository extends JpaRepository<User,Long> {
	public User findByUserName(String userName);
}
