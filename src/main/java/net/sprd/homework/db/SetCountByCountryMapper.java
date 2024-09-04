package net.sprd.homework.db;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import net.sprd.homework.response.SetCountByYear;

public class SetCountByCountryMapper implements RowMapper<SetCountByYear> {

	@Override
	public SetCountByYear map(ResultSet rs, StatementContext ctx) throws SQLException {
		return new SetCountByYear(
            rs.getString("key"),
            rs.getInt("count")
        );
	}

}
