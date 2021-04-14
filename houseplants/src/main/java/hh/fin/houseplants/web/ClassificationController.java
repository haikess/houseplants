package hh.fin.houseplants.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hh.fin.houseplants.domain.Classification;
import hh.fin.houseplants.domain.ClassificationRepository;

@Controller
public class ClassificationController {
	
	@Autowired
	private ClassificationRepository classificationRepository;
	
	@RequestMapping("/classifications")
	public String categories(Model model) {
		model.addAttribute("classifications", classificationRepository.findAll()); 
		return "classificationlist"; //classificationlist.html
	}
	// adding classification
	@RequestMapping(value = "/addlassification")
	public String addCategory(Model model) {
		model.addAttribute("classifications", new Classification());
		return "addclassification";
	}

	// saving classification
	@RequestMapping(value = "/saveClassification", method = RequestMethod.POST)
	public String saveCategory(Classification classification) {
		classificationRepository.save(classification);
		return "redirect:classifications";
	}

}
