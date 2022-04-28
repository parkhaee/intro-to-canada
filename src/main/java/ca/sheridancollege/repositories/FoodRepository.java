package ca.sheridancollege.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.sheridancollege.beans.Food;
import ca.sheridancollege.beans.FoodType;

public interface FoodRepository extends JpaRepository<Food,Long>{
	public Food findByFoodName(String name);
	public List<Food> findByFoodType(FoodType foodType);
}
