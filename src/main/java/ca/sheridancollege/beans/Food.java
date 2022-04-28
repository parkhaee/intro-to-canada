package ca.sheridancollege.beans;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Builder
@Entity
public class Food implements Serializable{
	@ManyToOne
	@JoinColumn(name = "country_id")
	@NonNull
	public Country country;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NonNull
	private String foodName;
	
	@NonNull
	@Enumerated(EnumType.STRING)
	private FoodType foodType;
	
	@OneToOne
	@JoinTable(name="FOOD_REGION", 
	joinColumns = @JoinColumn(name="FOOD_ID"), 
	inverseJoinColumns=@JoinColumn(name="REGION_ID"))
	private Region origin;
	
	@Lob
	private String recipeLinks;
}
