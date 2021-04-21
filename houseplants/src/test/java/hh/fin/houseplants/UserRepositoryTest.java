package hh.fin.houseplants;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hh.fin.houseplants.domain.User;
import hh.fin.houseplants.domain.UserRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UserRepositoryTest {
	
	@Autowired
	private UserRepository uRepository;
	
	@Test
	public void findByNameShouldReturnUser() {
		User users = uRepository.findByUsername("User");
		 
		assertThat(users);		
	}

}
