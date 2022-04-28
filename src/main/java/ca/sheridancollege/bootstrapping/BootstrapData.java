package ca.sheridancollege.bootstrapping;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import ca.sheridancollege.beans.Country;
import ca.sheridancollege.beans.Food;
import ca.sheridancollege.beans.FoodType;
import ca.sheridancollege.beans.Invention;
import ca.sheridancollege.beans.Language;
import ca.sheridancollege.beans.Region;
import ca.sheridancollege.beans.RegionType;
import ca.sheridancollege.beans.Sports;
import ca.sheridancollege.beans.User;
import ca.sheridancollege.repositories.CountryRepository;
import ca.sheridancollege.repositories.FoodRepository;
import ca.sheridancollege.repositories.InventionRepository;
import ca.sheridancollege.repositories.LanguageRepository;
import ca.sheridancollege.repositories.RegionRepository;
import ca.sheridancollege.repositories.SportsRepository;
import ca.sheridancollege.repositories.UserRepository;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class BootstrapData implements CommandLineRunner{
	private FoodRepository foodRepository;
	private InventionRepository inventionRepository;
	private LanguageRepository languageRepository;
	private RegionRepository regionRepository;
	private SportsRepository sportsRepository;
	private UserRepository userRepository;
	private CountryRepository countryRepository;
	
	@Override
	@Transactional
	public void run(String[] args) throws Exception {
		
		User admin = User.builder()
				.userName("admin")
				.password("1234")
				.build();
		
		userRepository.save(admin);
		
		/**
		 * Set Canada
		 */
		
		Country canada = Country.builder()
				.countryName("Canada")
				.leaderName("Justin Trudeau")
				.population(38285308)
				.inventionList(new ArrayList<Invention>())
				.languageList(new ArrayList<Language>())
				.regionList(new ArrayList<Region>())
				.sportsList(new ArrayList<Sports>())
				.foodList(new ArrayList<Food>())
				.build();
		
		/**
		 * Regions 10 Provinces and 3 Territories
		 */
		List<Region> regionList = new ArrayList<Region>();
		
		Region alberta = Region.builder()
				.country(canada)
				.regionName("Alberta")
				.population(4464170)
				.regionType(RegionType.PROVINCE)
				.build();
		
		Region bc = Region.builder()
				.country(canada)
				.regionName("British Columbia")
				.population(5214805)
				.regionType(RegionType.PROVINCE)
				.build();
		
		Region manitoba = Region.builder()
				.country(canada)
				.regionName("Manitoba")
				.population(1278365)
				.regionType(RegionType.PROVINCE)
				.build();
		
		Region nb = Region.builder()
				.country(canada)
				.regionName("New Brunswick")
				.population(761214)
				.regionType(RegionType.PROVINCE)
				.build();
		
		Region nl = Region.builder()
				.country(canada)
				.regionName("Newfoundland and Labrador")
				.population(521758)
				.regionType(RegionType.PROVINCE)
				.build();
		
		Region nwt = Region.builder()
				.country(canada)
				.regionName("Northwest Territories")
				.population(44826)
				.regionType(RegionType.TERRITORY)
				.build();
		
		Region ns = Region.builder()
				.country(canada)
				.regionName("Nova Scotia")
				.population(971395)
				.regionType(RegionType.PROVINCE)
				.build();
		
		Region nunavut = Region.builder()
				.country(canada)
				.regionName("Nunavut")
				.population(38780)
				.regionType(RegionType.TERRITORY)
				.build();
		
		Region ontario = Region.builder()
				.country(canada)
				.regionName("Ontario")
				.population(14223942)
				.regionType(RegionType.PROVINCE)
				.build();
		
		Region pei = Region.builder()
				.country(canada)
				.regionName("Prince Edward Island")
				.population(156947)
				.regionType(RegionType.PROVINCE)
				.build();
		
		Region quebec = Region.builder()
				.country(canada)
				.regionName("Quebec")
				.population(8604500)
				.regionType(RegionType.PROVINCE)
				.build();
		
		Region saskatchewan = Region.builder()
				.country(canada)
				.regionName("Saskatchewan")
				.population(1180867)
				.regionType(RegionType.PROVINCE)
				.build();
		
		Region yukon = Region.builder()
				.country(canada)
				.regionName("Yukon")
				.population(40232)
				.regionType(RegionType.TERRITORY)
				.build();
		
		regionList.add(alberta);
		regionList.add(bc);
		regionList.add(manitoba);
		regionList.add(nb);
		regionList.add(nl);
		regionList.add(nwt);
		regionList.add(ns);
		regionList.add(nunavut);
		regionList.add(ontario);
		regionList.add(pei);
		regionList.add(quebec);
		regionList.add(saskatchewan);
		regionList.add(yukon);
		
		/**
		 * Language
		 */
		List<Language> languageList = new ArrayList<Language>();
		
		Language english = Language.builder()
				.country(canada)
				.languageName("English")
				.build();
		
		Language french = Language.builder()
				.country(canada)
				.languageName("French")
				.build();
		
		languageList.add(english);
		languageList.add(french);
		
		/**
		 * Inventions
		 */
		List<Invention> inventionList = new ArrayList<Invention>();
		
		Invention insulin = Invention.builder()
				.country(canada)
				.inventionName("Insulin")
				.inventor("Frederick Banting")
				.inventDateTime(LocalDateTime.of(1921, Month.of(7), 27, 8, 30))
				.build();
		
		Invention hawaiianPizza = Invention.builder()
				.country(canada)
				.inventionName("Hawaiian Pizza")
				.inventor("Sam Panopoulos")
				.inventDateTime(LocalDateTime.of(1962, Month.of(1), 1, 12, 00))
				.build();
		
		Invention basketball = Invention.builder()
				.country(canada)
				.inventionName("Basketball")
				.inventor("James Naismith")
				.inventDateTime(LocalDateTime.of(1891, Month.of(12), 21, 12, 00))
				.build();
		
		inventionList.add(insulin);
		inventionList.add(hawaiianPizza);
		inventionList.add(basketball);
		
		/**
		 * Sports
		 */
		List<Sports> sportsList = new ArrayList<Sports>();
		
		Sports hockey = Sports.builder()
				.country(canada)
				.sportsName("Ice Hockey")
				.maxPlayerNum(23)
				.outdoor(false)
				.build();
		
		Sports lacrosse = Sports.builder()
				.country(canada)
				.sportsName("Lacrosse")
				.maxPlayerNum(10)
				.outdoor(true)
				.build();
		
		sportsList.add(hockey);
		sportsList.add(lacrosse);
		
		/**
		 * Foods
		 */
		List<Food> foodList = new ArrayList<Food>();
		
		Food poutine = Food.builder()
				.country(canada)
				.foodName("Poutine")
				.foodType(FoodType.ENTREE)
				.origin(quebec)
				.recipeLinks("https://www.seasonsandsuppers.ca/authentic-canadian-poutine-recipe/")
				.build();
		
		Food bannock = Food.builder()
				.country(canada)
				.foodName("Bannock")
				.foodType(FoodType.ENTREE)
				.origin(bc)
				.recipeLinks("https://www.allrecipes.com/recipe/6919/bannock/")
				.build();
		
		Food beavertails = Food.builder()
				.country(canada)
				.foodName("Beavertails")
				.foodType(FoodType.DESSERT)
				.origin(ontario)
				.recipeLinks("https://www.hotrodsrecipes.com/cinnamon-sugar-beaver-tails/")
				.build();
		
		Food ketchupChips = Food.builder()
				.country(canada)
				.foodName("Ketchup Chips")
				.foodType(FoodType.SNACK)
				.origin(ontario)
				.recipeLinks("https://hellogiggles.com/lifestyle/homemade-ketchup-chips-recipe/")
				.build();
		
		foodList.add(poutine);
		foodList.add(bannock);
		foodList.add(beavertails);
		foodList.add(ketchupChips);
		
		canada.getInventionList().addAll(inventionList);
		canada.getLanguageList().addAll(languageList);
		canada.getSportsList().addAll(sportsList);
		canada.getRegionList().addAll(regionList);
		canada.getFoodList().addAll(foodList);
		
		
		countryRepository.save(canada);
		
		languageRepository.save(english);
		languageRepository.save(french);
		
		inventionRepository.save(insulin);
		inventionRepository.save(hawaiianPizza);
		inventionRepository.save(basketball);
		
		sportsRepository.save(hockey);
		sportsRepository.save(lacrosse);
		
		foodRepository.save(poutine);
		foodRepository.save(bannock);
		foodRepository.save(beavertails);
		foodRepository.save(ketchupChips);
		
		regionRepository.save(alberta);
		regionRepository.save(bc);
		regionRepository.save(manitoba);
		regionRepository.save(nb);
		regionRepository.save(nl);
		regionRepository.save(nwt);
		regionRepository.save(ns);
		regionRepository.save(nunavut);
		regionRepository.save(ontario);
		regionRepository.save(pei);
		regionRepository.save(quebec);
		regionRepository.save(saskatchewan);
		regionRepository.save(yukon);
		
//		countryRepository.deleteById((long) 1);

	}
}
