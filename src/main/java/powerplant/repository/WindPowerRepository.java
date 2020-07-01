package powerplant.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;
import powerplant.model.WindPower;

import java.util.Date;

@Repository
public interface WindPowerRepository extends CassandraRepository<WindPower, Date> {


}
