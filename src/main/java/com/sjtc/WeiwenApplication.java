package com.sjtc;

import java.util.Properties;

import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.github.pagehelper.PageHelper;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@MapperScan({"com.sjtc.weiwen.*.dao","com.sjtc.weiwen.*.*.dao"})
public class WeiwenApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeiwenApplication.class, args);
	}

	@Bean
	public PageHelper pageHelper() {
		PageHelper pageHelper = new PageHelper();
		Properties p = new Properties();
		p.setProperty("offsetAsPageNum", "true");
		p.setProperty("rowBoundsWithCount", "true");
		p.setProperty("reasonable", "true");
		p.setProperty("dialect", "mysql");
		p.setProperty("supportMethodsArguments", "false");
		p.setProperty("pageSizeZero", "true");
		pageHelper.setProperties(p);
		return pageHelper;
	}
}
