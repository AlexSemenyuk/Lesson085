package org.itstep.dao;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;

@Data
@Repository
//@Scope("singleton")
//@Scope("prototype")
//@Scope("request") - only web
//@Scope("session") - only web
@PropertySource("classpath:db.properties")
public class BookDaoJdbc {
    @Value("${db.username:root}")
    private String username;
    @Value("${db.password}")
    private String password;
    @Value("${db.url}")
    private String url;
}
