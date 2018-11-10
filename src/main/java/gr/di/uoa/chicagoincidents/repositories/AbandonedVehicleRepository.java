package gr.di.uoa.chicagoincidents.repositories;

import gr.di.uoa.chicagoincidents.model.AbandonedVehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbandonedVehicleRepository extends CrudRepository<AbandonedVehicle, Long>{

    Page<AbandonedVehicle> findAll(Pageable pageable);
}
