package hh.fin.houseplants;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import hh.fin.houseplants.domain.Classification;
import hh.fin.houseplants.domain.ClassificationRepository;
import hh.fin.houseplants.domain.Houseplant;
import hh.fin.houseplants.domain.HouseplantRepository;
import hh.fin.houseplants.domain.User;
import hh.fin.houseplants.domain.UserRepository;

@SpringBootApplication
public class HouseplantsApplication implements WebMvcConfigurer {

	private static final Logger log = LoggerFactory.getLogger(HouseplantsApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(HouseplantsApplication.class, args);
	}

	@Bean
	public SessionLocaleResolver localeResolver() {
	    SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
	    sessionLocaleResolver.setDefaultLocale(new Locale("en",""));
	    return sessionLocaleResolver;
	}
	
	/*
	@Bean
	public CookieLocaleResolver localeResolver() {
		return new CookieLocaleResolver();
	}
	*/

	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
		lci.setParamName("lang");
		return lci;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor());
	}


    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("i18n/messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
    
	@Bean
	public CommandLineRunner demo(HouseplantRepository houseplantRepository,
			ClassificationRepository classificationRepository, UserRepository userRepository) {
		return (args) -> {

			Classification c1 = new Classification("Viherkasvi");
			Classification c2 = new Classification("Lehdetön");
			Classification c3 = new Classification("Yksilöinen");
			Classification c4 = new Classification("Runkoinen");

			classificationRepository.save(c1);
			classificationRepository.save(c2);
			classificationRepository.save(c3);
			classificationRepository.save(c4);

			Houseplant hp1 = new Houseplant("Appelsiinipuu", "Suuri", "Ei ole", "Lehtien nuokkuessa", "2010", c4);
			Houseplant hp2 = new Houseplant("Mandariinipuu", "Suuri", "Ei ole", "Kahdesti viikossa", "2011", c4);
			Houseplant hp3 = new Houseplant("Takiainen", "Keväisin suurimmillaan", "Merileväuute", "Joka toinen viikko", "2019", c3);
			Houseplant hp4 = new Houseplant("Mänty", "Pärjää myös varjoisassa", "Ei ole", "Joka viikko", "2017", c4);
			Houseplant hp5 = new Houseplant("Pitaija", "Kesällä eniten, talvella ei tarvitse paljoa", "Keväisin kevätlannoitetta", "Kerran kuukaudessa", "2018", c3);

			houseplantRepository.save(hp1);
			houseplantRepository.save(hp2);
			houseplantRepository.save(hp3);
			houseplantRepository.save(hp4);
			houseplantRepository.save(hp5);

			User user1 = new User("User", "$2y$10$2oFlsvP8O27gRzmMBnw7numoV.JUO4e0NNFPBMbbSnc/0dJgqtGxS", "USER");
			User user2 = new User("Admin", "$2y$10$62OarEyovAr0hxrCshCAZeUFECSfxlJbhx7MDNeW7qMfSXnp2F9VO", "ADMIN");
			userRepository.save(user1);
			userRepository.save(user2);

			log.info("Fetch all the houseplants");
			for (Houseplant houseplant : houseplantRepository.findAll()) {
				log.info(houseplant.toString());
			}
		};
	}

}
