package ca.sheridancollege.beans;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
public class Sports implements Serializable{
	@ManyToOne
	@JoinColumn(name = "country_id")
	@NonNull
	public Country country;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NonNull
	private String sportsName;
	@NonNull
	private Integer maxPlayerNum;
	@NonNull
	private Boolean outdoor;
}
