package com.hwpBackend.hwpSpring.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MemberJdbcRepository {

    @Autowired
    private JdbcTemplate springJdbcTemplate;

    private static String INSERT_QUERY =
            """
                insert into Member (ID, Password, Email, Name, Age, Gender)
                values('abc', '1234', 'aaa@gmail.com', '고길동', 34, '남');            
            """;

    public void insert() {
        springJdbcTemplate.update(INSERT_QUERY);
    }

}
