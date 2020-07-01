package powerplant.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;
import powerplant.model.Solar;

import java.util.UUID;

@Repository
public interface SolarRepository extends CassandraRepository<Solar, UUID> {

}
