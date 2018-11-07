package com.qkrwjdgus.springbootredis;

import com.qkrwjdgus.springbootredis.account.Account;
import com.qkrwjdgus.springbootredis.account.AccountRepository;
import java.util.Optional;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

@Component
public class RedisRunner implements ApplicationRunner {

    private StringRedisTemplate redisTemplate;
    private AccountRepository accountRepository;

    public RedisRunner(StringRedisTemplate redisTemplate, AccountRepository accountRepository) {
        this.redisTemplate = redisTemplate;
        this.accountRepository = accountRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        ValueOperations<String, String> values = redisTemplate.opsForValue();
        values.set("kykkyn2", "park");
        values.set("psiloveyou24", "jung");
        values.set("love", "fantastic");

        Account account = new Account();
        account.setUsername("kykkyn2");
        account.setEmail("kykkyn2@gmail.com");

        accountRepository.save(account);

        Optional<Account> byId = accountRepository.findById(account.getId());
        System.out.println(byId.get().getUsername());
        System.out.println(byId.get().getEmail());

    }
}
