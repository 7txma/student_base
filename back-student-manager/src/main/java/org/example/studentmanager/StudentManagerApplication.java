package org.example.studentmanager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// 强制扫描 Mapper 包，替代 @Mapper 注解，稳定性更高
@MapperScan("org.example.studentmanager.mapper")
public class StudentManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(StudentManagerApplication.class, args);
    }
}