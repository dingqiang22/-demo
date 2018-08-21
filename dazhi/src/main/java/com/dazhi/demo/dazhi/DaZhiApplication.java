package com.dazhi.demo.dazhi;

import io.shardingjdbc.core.api.MasterSlaveDataSourceFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.util.ResourceUtils;

import javax.sql.DataSource;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;


@SpringBootApplication
@MapperScan("com.dazhi.demo.dazhi.dao")
@EnableScheduling
public class DaZhiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DaZhiApplication.class, args);
	}

	/**;
	 * 配置读写分离数据源
	 * @return
	 * @throws FileNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	@Bean
	public DataSource dataSource() throws FileNotFoundException, SQLException, IOException {
		return MasterSlaveDataSourceFactory.createDataSource(ResourceUtils.getFile("classpath:sharding.yml"));
	}
}
