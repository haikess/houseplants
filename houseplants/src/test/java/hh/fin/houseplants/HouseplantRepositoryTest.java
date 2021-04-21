package hh.fin.houseplants;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
// import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hh.fin.houseplants.domain.Classification;
import hh.fin.houseplants.domain.Houseplant;
import hh.fin.houseplants.domain.HouseplantRepository;

// @RunWith(SpringRunner.class) // JUnit4
@ExtendWith(SpringExtension.class) // (JUnit5) Jupiter
@DataJpaTest
public class HouseplantRepositoryTest {
	
	@Autowired
	private HouseplantRepository hpRepository;
	
	@Test
	public void findByNameShouldReturnHouseplant() {
		List<Houseplant> houseplants = hpRepository.findByName("Appelsiinipuu");
		
		assertThat(houseplants).hasSize(1);
		assertThat(houseplants.get(0).getDateOfPurchase()).isEqualTo("2010");
	}
	
	@Test
	public void createNewHouseplant() {
		
		Houseplant houseplant = new Houseplant("Kissankello", "Puolivarjo", "Ei ole", "Kerran viikkossa", "2019", new Classification("Viherkasvi"));
		hpRepository.save(houseplant);
		assertThat(houseplant.getId()).isNotNull();
				
	}

}
