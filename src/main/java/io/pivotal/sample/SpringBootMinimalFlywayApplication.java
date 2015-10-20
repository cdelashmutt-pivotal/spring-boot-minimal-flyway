package io.pivotal.sample;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

@SpringBootApplication
public class SpringBootMinimalFlywayApplication implements CommandLineRunner {
	@Autowired
	private JdbcTemplate template;

	@Override
	public void run(String... args) throws Exception {
		System.err.println(this.template.query("SELECT id, first_name, last_name from PERSON", new RowMapper<Person>() {
			@Override
			public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
				return new Person(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"));
			}
		}));
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SpringBootMinimalFlywayApplication.class, args);
	}

}
