package gr.di.uoa.chicagoincidents.repositories;

import gr.di.uoa.chicagoincidents.model.VacantAbandonedBuilding;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VacantAbandonedBuildingRepository extends CrudRepository<VacantAbandonedBuilding, Long> {

    Page<VacantAbandonedBuilding> findAll(Pageable pageable);

}
