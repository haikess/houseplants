package hh.fin.houseplants;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.fin.houseplants.domain.Classification;
import hh.fin.houseplants.domain.ClassificationRepository;
import hh.fin.houseplants.domain.Houseplant;
import hh.fin.houseplants.domain.HouseplantRepository;

@SpringBootApplication
public class HouseplantsApplication {

	public static void main(String[] args) {
		SpringApplication.run(HouseplantsApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(HouseplantRepository houseplantRepository, 
			ClassificationRepository classificationRepository) {
		return (args) -> {
			
			Classification c1 = new Classification("Viherkasvi");
			Classification c2 = new Classification("Lehdetön");
			Classification c3 = new Classification("Yksilöinen");
			Classification c4 = new Classification("Runkoinen");
			
			classificationRepository.save(c1);
			classificationRepository.save(c2);
			classificationRepository.save(c3);
			classificationRepository.save(c4);
			
			Houseplant hp1 = new Houseplant("Appelsiinipuu", "Suuri", "Ei ole", "Kun nuokkuu", "2010", c1);
			
			houseplantRepository.save(hp1);
			
		};
	}

}
