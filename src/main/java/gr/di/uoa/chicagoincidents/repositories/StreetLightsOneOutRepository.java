package gr.di.uoa.chicagoincidents.repositories;

import gr.di.uoa.chicagoincidents.model.StreetLightsOneOut;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StreetLightsOneOutRepository extends CrudRepository<StreetLightsOneOut, Long> {

    Page<StreetLightsOneOut> findAll(Pageable pageable);

}
