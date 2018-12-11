package gr.di.uoa.chicagoincidents.repositories;

import gr.di.uoa.chicagoincidents.model.TreeTrim;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TreeTrimRepository extends CrudRepository<TreeTrim, Long> {

    Page<TreeTrim> findAll(Pageable pageable);

}
