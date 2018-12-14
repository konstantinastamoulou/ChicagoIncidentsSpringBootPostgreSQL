package gr.di.uoa.chicagoincidents.repositories;

import gr.di.uoa.chicagoincidents.model.UserHistory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserHistoryRepository extends CrudRepository<UserHistory, Long> {
}
