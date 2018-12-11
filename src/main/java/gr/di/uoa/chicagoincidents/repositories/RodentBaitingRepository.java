package gr.di.uoa.chicagoincidents.repositories;

import gr.di.uoa.chicagoincidents.model.RodentBaiting;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RodentBaitingRepository extends CrudRepository<RodentBaiting, Long> {

    Page<RodentBaiting> findAll(Pageable pageable);

}
