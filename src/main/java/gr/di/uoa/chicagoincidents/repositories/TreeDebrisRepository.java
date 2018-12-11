package gr.di.uoa.chicagoincidents.repositories;

import gr.di.uoa.chicagoincidents.model.TreeDebris;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TreeDebrisRepository extends CrudRepository<TreeDebris, Long> {

    Page<TreeDebris> findAll(Pageable pageable);

}
