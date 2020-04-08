package gouri.ibk.interactivetrader.web;

import gouri.ibk.interactivetrader.InteractiveTraderServiceTestConfig;
import gouri.ibk.interactivetrader.bl.InstrumentFacade;
import gouri.ibk.interactivetrader.model.InstrumentMaster;
import gouri.ibk.interactivetrader.model.InstrumentPrice;
import gouri.ibk.interactivetrader.repo.InstrumentRepo;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {InteractiveTraderServiceTestConfig.class})
class InstrumentControllerTest {

    @Autowired
    ApplicationContext context;

    @InjectMocks
    InstrumentController instrumentController;

    @Mock
    InstrumentFacade instrumentFacade;

    @Mock
    SimpMessagingTemplate messagingTemplate;

    @Mock
    InstrumentRepo instrumentRepo;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getMyPrices() {
        Mockito.when(instrumentFacade.getCurrentPriceMulti(Mockito.any())).thenReturn(new ArrayList<>());
        assertNotNull(instrumentController.getMyPrices("AAPL"));
    }

    @Test
    void publishPrice() {
        Mockito.when(instrumentFacade.publishPrice(Mockito.any())).thenReturn(new InstrumentPrice());
        Mockito.doNothing().when(messagingTemplate).convertAndSend(Mockito.any());
        assertNotNull(instrumentController.publishPrice(new InstrumentPrice()));
    }

    @Test
    void getInstrument() {
        Mockito.when(instrumentRepo.findById("AAPL")).thenReturn(Optional.of(new InstrumentMaster()));
        assertNotNull(instrumentController.getInstrument("AAPL"));
    }

}