package gouri.ibk.interactivetrader.repo;

import gouri.ibk.interactivetrader.InteractiveTraderServiceTestConfig;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ContextConfiguration(classes = {InteractiveTraderServiceTestConfig.class})
@Profile("test")
class OrderMasterRepoTest {

    @Before
    void setUp() {

    }

    @Test
    void findTop5ByAccountByTraderId_AccountId() {

    }

    @Test
    void countByTradeDateAndAccountByTraderId_AccountId() {
    }
}