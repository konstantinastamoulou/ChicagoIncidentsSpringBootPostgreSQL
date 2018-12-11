package gr.di.uoa.chicagoincidents.repositories;

import gr.di.uoa.chicagoincidents.model.SanitationCodeComplaint;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SanitationCodeComplaintRepository extends CrudRepository<SanitationCodeComplaint, Long> {

    Page<SanitationCodeComplaint> findAll(Pageable pageable);

}
