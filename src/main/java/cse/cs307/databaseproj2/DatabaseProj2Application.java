package cse.cs307.databaseproj2;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cse.cs307.databaseproj2.mapper")
public class DatabaseProj2Application {
//aaa
    public static void main(String[] args) {
        SpringApplication.run(DatabaseProj2Application.class, args);
    }

}
