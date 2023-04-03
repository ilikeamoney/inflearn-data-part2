package hello.itemservice.config;

import hello.itemservice.repository.ItemRepository;
import hello.itemservice.repository.jpa.JpaItemRepositoryV3;
import hello.itemservice.repository.v2.ItemRepositoryV2;
import hello.itemservice.repository.v2.QueryDslRepositoryV2;
import hello.itemservice.service.ItemService;
import hello.itemservice.service.ItemServiceV1;
import hello.itemservice.service.ItemServiceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

/**
 * MyBatis
 * DataSource DB Connection 추상화
 * TransactionManager 트랙잭션 추상화
 */

@Configuration
@RequiredArgsConstructor
public class V2Config {

    private final EntityManager em;
    private final ItemRepositoryV2 repositoryV2; // Spring Data Jpa 가 제공

    @Bean
    public ItemService itemService() {
        return new ItemServiceV2(repositoryV2, queryDslRepositoryV2());
    }

    @Bean
    public QueryDslRepositoryV2 queryDslRepositoryV2() {
        return new QueryDslRepositoryV2(em);
    }

    @Bean
    public ItemRepository itemRepository() {
        return new JpaItemRepositoryV3(em);
    }
}
