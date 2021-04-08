package hh.fin.houseplants.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hh.fin.houseplants.domain.HouseplantRepository;
import hh.fin.houseplants.domain.ClassificationRepository;
import hh.fin.houseplants.domain.Houseplant;

@Controller
public class HouseplantController {
	
	@Autowired
	private HouseplantRepository hpRepository;
	
	@Autowired
	private ClassificationRepository classificationRepository;
	
	/*
	@ResponseBody
	@RequestMapping("/index")	
	public String Hello() {
		return "testi2";
	}
	*/
	
	@RequestMapping(value="/houseplants", method = RequestMethod.GET)
    public @ResponseBody List<Houseplant> houseplantListRest() {	
        return (List<Houseplant>) hpRepository.findAll();
    } 
	
	@RequestMapping(value = "/allhouseplants", method = RequestMethod.GET)
	public String getAllHouseplant(Model model) {
		//ei vielä haeta kirjalistaa tietokannasta -> tehdään tyhjä kirjalista

		List<Houseplant> houseplants = (List<Houseplant>) hpRepository.findAll();
		//String title, String author, String year, String isbn, Integer price
		//books.add(new Book("Harry Potter", "J.K. Rowling", "2015", "9789513184872", 19));
		
		//talletetaan kirjalista model-mapin attribuuttiolioksi, jotka näkee myös thymeleaf-templaten
		model.addAttribute("houseplants", houseplants);
		return "houseplantlist"; // houseplantlist.html
	}
	
		//add new houseplant
		@RequestMapping(value = "/add")
		public String addHouseplant(Model model) {
			model.addAttribute("houseplant", new Houseplant());
	    	model.addAttribute("classificationList", classificationRepository.findAll());
			return "addhouseplant";
		}
		
		//save new houseplant
		@RequestMapping(value = "/save", method = RequestMethod.POST)
		public String save(Houseplant houseplant) {
			hpRepository.save(houseplant);
			return "redirect:/allhouseplants";
		}
		
		@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
		public String deleteHouseplant(@PathVariable("id") Long houseplantId, Model model) {
			hpRepository.deleteById(houseplantId);
			return "redirect:../allhouseplants";
		}
}
