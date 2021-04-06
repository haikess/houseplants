package hh.fin.houseplants.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import hh.fin.houseplants.domain.HouseplantRepository;

@Controller
public class HouseplantController {
	
	@Autowired
	private HouseplantRepository repository;
	
	@ResponseBody
	@RequestMapping("/index")	
	public String Hello() {
		return "testi";
	}
	

}
