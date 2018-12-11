package gr.di.uoa.chicagoincidents.repositories;

import gr.di.uoa.chicagoincidents.model.GarbageCart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GarbageCartRepository extends CrudRepository<GarbageCart, Long> {

    Page<GarbageCart> findAll(Pageable pageable);

}
