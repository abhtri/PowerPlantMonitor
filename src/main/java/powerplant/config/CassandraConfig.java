package powerplant.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;
import org.springframework.data.cassandra.core.cql.keyspace.DropKeyspaceSpecification;
import org.springframework.data.cassandra.core.cql.keyspace.KeyspaceOption;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

import java.util.ArrayList;
import java.util.List;


@Configuration
@EnableCassandraRepositories
public class CassandraConfig extends AbstractCassandraConfiguration {

        // some other configuration

     @Override
     protected  String getKeyspaceName(){
         return "abhishek";
     }

    //@Value("${cassandra.keyspace}")
    private String keyspace = "abhishek";

        @Override
        protected List<CreateKeyspaceSpecification> getKeyspaceCreations() {
            final CreateKeyspaceSpecification specification =
                    CreateKeyspaceSpecification.createKeyspace(keyspace)
                            .ifNotExists()
                            .with(KeyspaceOption.DURABLE_WRITES, true)
                            .withSimpleReplication();
            List<CreateKeyspaceSpecification> list = new ArrayList<>();
            list.add(specification);
            return list;
        }

        @Override
        protected List<DropKeyspaceSpecification> getKeyspaceDrops() {
            List<DropKeyspaceSpecification> list = new ArrayList<>();
            list.add(DropKeyspaceSpecification.dropKeyspace(keyspace));
            return list;
        }

    @Override
    public String[] getEntityBasePackages() {
        return new String[]{"powerplant.model"};
    }

   @Override
    public SchemaAction getSchemaAction() {
        return SchemaAction.CREATE_IF_NOT_EXISTS;
    }
}
