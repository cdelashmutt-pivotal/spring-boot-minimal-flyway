package io.pivotal.sample;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

	@Autowired
	private JdbcTemplate template;

	@RequestMapping("/person")
	public List<Person> getPeople()
	{
		return this.template.query("SELECT id, first_name, last_name from PERSON", new RowMapper<Person>() {
			@Override
			public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
				return new Person(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"));
			}
		});
	}
}
