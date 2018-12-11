package gr.di.uoa.chicagoincidents.repositories;

import gr.di.uoa.chicagoincidents.model.StreetLightsAllOut;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StreetLightsAllOutRepository extends CrudRepository<StreetLightsAllOut, Long> {

    Page<StreetLightsAllOut> findAll(Pageable pageable);

}
