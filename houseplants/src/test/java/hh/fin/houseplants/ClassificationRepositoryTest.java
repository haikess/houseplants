package hh.fin.houseplants;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hh.fin.houseplants.domain.Classification;
import hh.fin.houseplants.domain.ClassificationRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ClassificationRepositoryTest {
	
	@Autowired
	private ClassificationRepository repository;
	
	@Test
	public void findByNameShouldReturnClassificatio() {
		List<Classification> classifications = repository.findByName("Viherkasvi");
		
		assertThat(classifications).hasSize(1);
	}
	
	@Test
	public void createNewClassification() {
		
		Classification classification = new Classification("Tumpeloinen");
		repository.save(classification);
		assertThat(classification.getId()).isNotNull();
	}

}
