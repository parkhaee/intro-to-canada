package ca.sheridancollege.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Country implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String countryName;
	private Integer population;
	private String leaderName;
	
	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "country")
	private List<Region> regionList = new ArrayList<Region>();

	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "country")
	private List<Sports> sportsList = new ArrayList<Sports>();
	
	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "country")
	private List<Invention> inventionList = new ArrayList<Invention>();
	
	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "country")
	private List<Language> languageList = new ArrayList<Language>();
	
	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "country")
	private List<Food> foodList = new ArrayList<Food>();
	
}
