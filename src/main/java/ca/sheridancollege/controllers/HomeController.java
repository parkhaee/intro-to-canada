package ca.sheridancollege.controllers;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

@Controller
@AllArgsConstructor
public class HomeController {
	private FoodRepository foodRepository;
	private InventionRepository inventionRepository;
	private LanguageRepository languageRepository;
	private RegionRepository regionRepository;
	private SportsRepository sportsRepository;
	private UserRepository userRepository;
	private CountryRepository countryRepository;
	
	
	@GetMapping("/")
	public String index(Model model, HttpSession session) {

		Country canada = countryRepository.findByCountryName("Canada");
		String str = NumberFormat.getNumberInstance(Locale.US).format(canada.getPopulation());
		model.addAttribute("country", canada);
		model.addAttribute("population", str);
		model.addAttribute("sportsNames", 
				List.of(sportsRepository.findAll()
						.stream()
						.map(sports -> sports.getSportsName())
						.collect(Collectors.toList())
						.toString()
						.replace("[", "")
						.replace("]", "")));
		model.addAttribute("provinceNum", regionRepository.findByRegionType(RegionType.PROVINCE).size());
		model.addAttribute("territoryNum", regionRepository.findByRegionType(RegionType.TERRITORY).size());
		model.addAttribute("insulin", inventionRepository.findByInventionName("Insulin"));
		model.addAttribute("ketchupChip", foodRepository.findByFoodName("Ketchup Chips"));
		model.addAttribute("poutine", foodRepository.findByFoodName("Poutine"));
		model.addAttribute("languageNames", languageRepository.findAll()
				.stream()
				.map(language -> language.getLanguageName())
				.collect(Collectors.toList())
				.toString()
				.replace("[", "")
				.replace("]", ""));
		model.addAttribute("languageNum", languageRepository.findAll().size());
		model.addAttribute("beavertails", foodRepository.findByFoodName("Beavertails"));
		model.addAttribute("hawaiian", inventionRepository.findByInventionName("Hawaiian Pizza"));
		model.addAttribute("hawaiianYear", 
				inventionRepository
				.findByInventionName("Hawaiian Pizza")
				.getInventDateTime().getYear());
		model.addAttribute("user", new User());	
		User admin = userRepository.findByUserName("admin");
		session.setMaxInactiveInterval(60*30);
		
		if (session.isNew()) {
			session.setAttribute("role", null);
		}
		else if (session.getAttribute("role") != null){
			session.setAttribute("role", admin.getUserName());
		}
		else {
			session.setAttribute("role", null);
		}
		return "index";
	}
	
	@PostMapping("/login")
	public String login(Model model, HttpSession session, @RequestParam String userName, @RequestParam String password) {	
		User admin = userRepository.findByUserName("admin");
		model.addAttribute("user", admin);
		if (userName.equals(admin.getUserName()) &&
				password.equals(admin.getPassword())) {
			session.setAttribute("role", userName);
		}
		return "redirect:/";
	}
	
	@GetMapping("/loginPage")
	public String loginPage(Model model, HttpSession session) {
		model.addAttribute("user", new User());
		return "loginPage";
	}
	
	@GetMapping("/endSession")
	public String endSession(Model model, HttpSession session) {
		Country canada = countryRepository.findByCountryName("Canada");
		String str = NumberFormat.getNumberInstance(Locale.US).format(canada.getPopulation());
		model.addAttribute("country", canada);
		model.addAttribute("population", str);
		model.addAttribute("sportsNames", 
				List.of(sportsRepository.findAll()
						.stream()
						.map(sports -> sports.getSportsName())
						.collect(Collectors.toList())
						.toString()
						.replace("[", "")
						.replace("]", "")));
		model.addAttribute("provinceNum", regionRepository.findByRegionType(RegionType.PROVINCE).size());
		model.addAttribute("territoryNum", regionRepository.findByRegionType(RegionType.TERRITORY).size());
		model.addAttribute("insulin", inventionRepository.findByInventionName("Insulin"));
		model.addAttribute("ketchupChip", foodRepository.findByFoodName("Ketchup Chips"));
		model.addAttribute("poutine", foodRepository.findByFoodName("Poutine"));
		model.addAttribute("languageNames", languageRepository.findAll()
				.stream()
				.map(language -> language.getLanguageName())
				.collect(Collectors.toList())
				.toString()
				.replace("[", "")
				.replace("]", ""));
		model.addAttribute("languageNum", languageRepository.findAll().size());
		model.addAttribute("beavertails", foodRepository.findByFoodName("Beavertails"));
		model.addAttribute("hawaiian", inventionRepository.findByInventionName("Hawaiian Pizza"));
		model.addAttribute("hawaiianYear", 
				inventionRepository
				.findByInventionName("Hawaiian Pizza")
				.getInventDateTime().getYear());
		model.addAttribute("user", new User());	
		session.invalidate();
		return "redirect:/";
	}
	
	@GetMapping("/search")
	public String searchPage(Model model, HttpSession session) {
		model.addAttribute("food", new Food());
		model.addAttribute("invention", new Invention());
		model.addAttribute("language", new Language());
		model.addAttribute("region", new Region());
		model.addAttribute("sports", new Sports());
		model.addAttribute("foodTypes", FoodType.values());
		model.addAttribute("regionTypes", RegionType.values());
		model.addAttribute("foodList", foodRepository.findAll());
		return "searchPage";
	}
	
	@GetMapping("/add")
	public String addPage(Model model, HttpSession session) {
		model.addAttribute("food", new Food());
		model.addAttribute("invention", new Invention());
		model.addAttribute("language", new Language());
		model.addAttribute("region", new Region());
		model.addAttribute("sports", new Sports());
		model.addAttribute("regionList", regionRepository.findAll());
		model.addAttribute("foodTypes", FoodType.values());
		model.addAttribute("regionTypes", RegionType.values());
		return "addPage";
	}
	
	// Region Update
	@GetMapping("/regionList")
	public String regionList(Model model, HttpSession session) {
		model.addAttribute("regionList", regionRepository.findAll(Sort.by("regionType")));
		model.addAttribute("regionType", RegionType.values());
		return "regionList";
	}
	
	@PostMapping("/findByRegionType")
	public String findByRegionType(Model model, HttpSession session,
			@RequestParam RegionType regionType) {
		List<Region> regionList = regionRepository.findByRegionType(regionType);
		model.addAttribute("regionList", regionList);
		return "regionList";
	}
	
	@GetMapping("/editRegion/{id}")
	public String editRegion(Model model, HttpSession session, @PathVariable Long id) {
		Optional<Region> region = regionRepository.findById(id);
		model.addAttribute("region", region);
		return "edit";
	}
	
	@PostMapping("/updateRegion")
	public String updateRegion(Model model, HttpSession session, @ModelAttribute Region region) {
		regionRepository.save(region);
		model.addAttribute("regionList", regionRepository.findAll(Sort.by("regionType")));
		return "regionList";
	}
	
	// Invention CRUD
	@GetMapping("/inventionList")
	public String inventionList(Model model, HttpSession session) {
		model.addAttribute("inventionList", inventionRepository.findAll(Sort.by("inventDateTime")));
		return "inventionList";
	}
	
	@PostMapping("/addInvention")
	public String addInvention(Model model, HttpSession session, @ModelAttribute Invention invention) {
		inventionRepository.save(invention);
		return "redirect:/add";
	}
	
	@PostMapping("/findByInventDateTimeAfter")
	public String findByInventDateTimeAfter(Model model, HttpSession session, 
			@RequestParam String inventDateTime) {
		LocalDateTime localDateTime = LocalDateTime.parse(inventDateTime);
		List<Invention> inventionList = inventionRepository.findByInventDateTimeAfter(localDateTime);
		model.addAttribute("inventionList", inventionList);
		return "inventionList";
	}
	
	@GetMapping("/editInvention/{id}")
	public String editInvention(Model model, HttpSession session, @PathVariable Long id) {
		Optional<Invention> invention = inventionRepository.findById(id);
		model.addAttribute("invention", invention);
		return "edit";
	}
	
	@PostMapping("/updateInvention")
	public String updateInvention(Model model, HttpSession session, @ModelAttribute Invention invention) {
		inventionRepository.save(invention);
		model.addAttribute("inventionList", inventionRepository.findAll(Sort.by("inventDateTime")));
		return "inventionList";
	}
	
	@GetMapping("/deleteInvention/{id}")
	public String deleteInvention(Model model, HttpSession session, @PathVariable Long id)
	{
		Invention invention = inventionRepository.findById(id).get();
		inventionRepository.delete(invention);
		model.addAttribute("inventionList", inventionRepository.findAll(Sort.by("inventDateTime")));
		return "redirect:/inventionList";
	}
	
	// Sports CRUD
	@GetMapping("/sportsList")
	public String sportsList(Model model, HttpSession session) {
		model.addAttribute("sportsList", sportsRepository.findAll());
		return "sportsList";
	}
	
	@PostMapping("/addSports")
	public String addSports(Model model, HttpSession session, @ModelAttribute Sports sports) {
		sportsRepository.save(sports);
		return "redirect:/add";
	}
	
	@PostMapping("/findByMaxPlayerNumLessThan")
	public String findByMaxPlayerNumLessThan(Model model, HttpSession session,
			@RequestParam int maxPlayerNum) {
		List<Sports> sportsList = sportsRepository.findByMaxPlayerNumLessThan(maxPlayerNum);
		model.addAttribute("sportsList", sportsList);
		return "sportsList";
	}
	
	@GetMapping("/editSports/{id}")
	public String editSports(Model model, HttpSession session, @PathVariable Long id) {
		Optional<Sports> sports = sportsRepository.findById(id);
		model.addAttribute("sports", sports);
		return "edit";
	}
	
	@PostMapping("/updateSports")
	public String updateSports(Model model, HttpSession session, @ModelAttribute Sports sports) {
		sportsRepository.save(sports);
		model.addAttribute("sportsList", sportsRepository.findAll());
		return "sportsList";
	}
	
	@GetMapping("/deleteSports/{id}")
	public String deleteSports(Model model, HttpSession session, @PathVariable Long id)
	{
		sportsRepository.deleteById(id);
		model.addAttribute("sportsList", inventionRepository.findAll());
		return "redirect:/sportsList";
	}
	
	// Language CRUD
	@GetMapping("/languageList")
	public String languageList(Model model, HttpSession session) {
		model.addAttribute("languageList", languageRepository.findAll());
		return "languageList";
	}
	
	@PostMapping("/addLanguage")
	public String addLanguage(Model model, HttpSession session, @ModelAttribute Language language) {
		languageRepository.save(language);
		return "redirect:/add";
	}
	
	@PostMapping("/findByLanguageNameContaining")
	public String findByLanguageNameContaining(Model model, HttpSession session, 
			@RequestParam String languageName) {
		List<Language> languageList = languageRepository.findByLanguageNameContaining(languageName);
		model.addAttribute("languageList", languageList);
		return "languageList";
	}
	
	@GetMapping("/editLanguage/{id}")
	public String editLanguage(Model model, HttpSession session, @PathVariable Long id) {
		Optional<Language> language = languageRepository.findById(id);
		model.addAttribute("language", language);
		return "edit";
	}
	
	@PostMapping("/updateLanguage")
	public String updateLanguage(Model model, HttpSession session, @ModelAttribute Language language) {
		languageRepository.save(language);
		model.addAttribute("languageList", languageRepository.findAll());
		return "languageList";
	}
	
	@GetMapping("/deleteLanguage/{id}")
	public String deleteLanguage(Model model, HttpSession session, @PathVariable Long id)
	{
		languageRepository.deleteById(id);
		model.addAttribute("languageList", languageRepository.findAll());
		return "redirect:/languageList";
	}
	
	// Food CRUD
	@GetMapping("/foodList")
	public String foodList(Model model, HttpSession session) {
		model.addAttribute("foodList", foodRepository.findAll());
		return "foodList";
	}
	
	@PostMapping("/addFood")
	public String addFood(Model model, HttpSession session, 
			@ModelAttribute Food food, @RequestParam String regionName) {
		Region origin = regionRepository.findByRegionName(regionName);
		food.setOrigin(origin);
		foodRepository.save(food);
		return "redirect:/add";
	}
	
	@PostMapping("/findByFoodType")
	public String findByFoodType(Model model, HttpSession session, @RequestParam FoodType foodType) {
		List<Food> foodList = foodRepository.findByFoodType(foodType);
		model.addAttribute("foodList", foodList);
		return "foodList";
	}
	
	@GetMapping("/editFood/{id}")
	public String editFood(Model model, HttpSession session, @PathVariable Long id) {
		model.addAttribute("foodTypes", FoodType.values());
		model.addAttribute("regionList", regionRepository.findAll());
		Optional<Food> food = foodRepository.findById(id);
		model.addAttribute("food", food);
		model.addAttribute("regionName", food.get().getOrigin().getRegionName());
		return "edit";
	}
	
	@PostMapping("/updateFood")
	public String updateFood(Model model, HttpSession session, 
			@ModelAttribute Food food, @RequestParam String regionName) {
		Region origin = regionRepository.findByRegionName(regionName);
		food.setOrigin(origin);
		foodRepository.save(food);
		model.addAttribute("foodList", foodRepository.findAll());
		return "foodList";
	}
	
	@GetMapping("/deleteFood/{id}")
	public String deleteFood(Model model, HttpSession session, @PathVariable Long id)
	{
		foodRepository.deleteById(id);
		model.addAttribute("foodList", foodRepository.findAll());
		return "redirect:/foodList";
	}
	
	@GetMapping("/saveLocalFile")
	public String saveLocalFile(HttpSession session) throws IOException {
		Country canada = countryRepository.findByCountryName("Canada");
		List<Language> languageList = new ArrayList<Language>();
		Language indian = new Language(canada, "Indian");
		Language spanish = new Language(canada, "Spanish");
		languageList.add(indian);
		languageList.add(spanish);
		
	    try {
			FileOutputStream fos = new FileOutputStream("languageList.txt");
		    ObjectOutputStream oos = new ObjectOutputStream(fos);
		    oos.writeObject(languageList);
		    oos.flush();
	    }
	    catch (IOException ioe) {
	    	ioe.printStackTrace();
	    }
	    
		return "redirect:/";
	}
	
	@GetMapping("/readLocalFile")
	public String readLocalFile(HttpSession session) throws IOException, ClassNotFoundException {
		ArrayList<Language> languageList = new ArrayList<Language>();
	    
	    try {
			FileInputStream fis = new FileInputStream("languageList.txt");
		    ObjectInputStream ois = new ObjectInputStream(fis);
		    
		    languageList = (ArrayList<Language>) ois.readObject();
		    languageRepository.saveAll(languageList);
		    ois.close();
	    }
	    catch (EOFException ex){
	    	ex.printStackTrace();
	    }
	    catch (ClassNotFoundException c) {
	    	c.printStackTrace();
	    }
		return "redirect:/";
	}
}
