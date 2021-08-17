package core.caching;

import core.caching.domain.todos.Todo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

@EnableCaching
@SpringBootApplication
@EnableConfigurationProperties(RedisProperties.class)
public class CachingApplication {

	public static void main(String[] args) {
		SpringApplication.run(CachingApplication.class, args);
	}

	@Bean
	public RedisTemplate<Long, Todo> redisTemplate(
			RedisConnectionFactory connectionFactory) {
		RedisTemplate<Long, Todo> template = new RedisTemplate<>();
		template.setValueSerializer(new Jackson2JsonRedisSerializer(Todo.class));
		template.setHashValueSerializer(new Jackson2JsonRedisSerializer(Todo.class));
		template.setEnableDefaultSerializer(true);
		template.setConnectionFactory(connectionFactory);
		return template;
	}
}
