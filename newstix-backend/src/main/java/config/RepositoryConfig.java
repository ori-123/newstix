package config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import repository.UserRepository;

@Configuration
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class RepositoryConfig {

}