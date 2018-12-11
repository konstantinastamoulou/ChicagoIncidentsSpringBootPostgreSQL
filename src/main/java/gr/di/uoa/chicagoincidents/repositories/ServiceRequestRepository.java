package gr.di.uoa.chicagoincidents.repositories;

import gr.di.uoa.chicagoincidents.model.ServiceRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRequestRepository extends CrudRepository<ServiceRequest, Long> {

    Page<ServiceRequest> findAll(Pageable pageable);

}
