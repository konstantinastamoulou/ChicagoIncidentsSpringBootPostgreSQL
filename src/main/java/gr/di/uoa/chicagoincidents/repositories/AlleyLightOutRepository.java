package gr.di.uoa.chicagoincidents.repositories;

import gr.di.uoa.chicagoincidents.model.AlleyLightOut;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlleyLightOutRepository extends CrudRepository<AlleyLightOut, Long> {

    Page<AlleyLightOut> findAll(Pageable pageable);

}
