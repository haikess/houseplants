package hh.fin.houseplants.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ClassificationRepository extends CrudRepository<Classification, Long> {
	
	List<Classification> findByName(String name);
}
