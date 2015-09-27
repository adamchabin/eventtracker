package eu.chabin.eventtracker.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import eu.chabin.eventtracker.representations.Host;

public class HostMapper implements ResultSetMapper<Host> {
	public Host map(int index, ResultSet r, StatementContext ctx)
			throws SQLException {
		return new Host(r.getInt("id"), r.getString("hostName"),
				r.getString("hostDescription"), r.getString("hostKey"));
	}
}