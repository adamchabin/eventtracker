package eu.chabin.eventtracker.dao;

import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;
import eu.chabin.eventtracker.dao.mappers.HostMapper;
import eu.chabin.eventtracker.representations.Host;

public interface HostDAO {
	
	@Mapper(HostMapper.class)
	@SqlQuery("select * from host where id = :id")
	Host getHostById(@Bind("id") int id);
	
	@GetGeneratedKeys
	@SqlUpdate("insert into host (id, hostName, hostDescription, hostKey) values (NULL, :hostName, :hostDescription, :hostKey)")
	int createHost(@Bind("hostName") String hostName,
	@Bind("description") String description, @Bind("hostKey") String hostKey);
	
	@SqlUpdate("update host set hostName = :hostName, hostDescription =:hostDescription where id = :id")
	void updateHost(@Bind("id") int id, @Bind("hostName")
	String hostName, @Bind("hostDescription") String hostDescription);
	
	@SqlUpdate("delete from host where id = :id")
	void deleteHost(@Bind("id") int id);
}
