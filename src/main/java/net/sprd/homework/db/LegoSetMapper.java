package net.sprd.homework.db;

import net.sprd.homework.entities.LegoSet;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LegoSetMapper implements RowMapper<LegoSet> {
    @Override
    public LegoSet map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new LegoSet(
                rs.getString("set_num"),
                rs.getString("set_name"),
                rs.getInt("year"),
                rs.getInt("theme_id"),
                rs.getString("theme_name"),
                rs.getInt("num_parts")
        );
    }
}
