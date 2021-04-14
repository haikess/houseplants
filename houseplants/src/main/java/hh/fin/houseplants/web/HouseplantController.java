package hh.fin.houseplants.web;

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
	
	// not having a database so creating without it:
	@RequestMapping(value = "/allhouseplants", method = RequestMethod.GET)
	public String getAllHouseplant(Model model) {

		List<Houseplant> houseplants = (List<Houseplant>) hpRepository.findAll();
		
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
		
		// save new houseplant
		@RequestMapping(value = "/save", method = RequestMethod.POST)
		public String save(Houseplant houseplant) {
			hpRepository.save(houseplant);
			return "redirect:/allhouseplants";
		}
		
		// edit 
		@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
		public String editBook(@PathVariable(value="id") Long houseplantId, Model model) {
			model.addAttribute("houseplant", hpRepository.findById(houseplantId));
			model.addAttribute("classificationList", classificationRepository.findAll());
			System.out.println("cl size: " + ((List)model.getAttribute("classificationList")).size());
			return "edithouseplant";
		}
		
		@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
		public String deleteHouseplant(@PathVariable("id") Long houseplantId, Model model) {
			hpRepository.deleteById(houseplantId);
			return "redirect:../allhouseplants";
		}
}
