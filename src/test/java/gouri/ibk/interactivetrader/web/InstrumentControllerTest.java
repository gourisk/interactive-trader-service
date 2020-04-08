package gouri.ibk.interactivetrader.web;

import gouri.ibk.interactivetrader.InteractiveTraderServiceApplication;
import gouri.ibk.interactivetrader.bl.InstrumentFacade;
import gouri.ibk.interactivetrader.model.InstrumentMaster;
import gouri.ibk.interactivetrader.model.InstrumentPrice;
import gouri.ibk.interactivetrader.repo.InstrumentRepo;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader= AnnotationConfigContextLoader.class)
@ComponentScan("gouri.ibk.interactivetrader")
@SpringBootTest
class InstrumentControllerTest {

    @Autowired
    ApplicationContext context;

    @InjectMocks
    InstrumentController instrumentController;

    @Mock
    InstrumentFacade instrumentFacade;

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
        Mockito.when(instrumentFacade.publishPrice(Mockito.any())).thenReturn(new Object[]{});
        assertNotNull(instrumentController.publishPrice(new InstrumentPrice()));
    }

    @Test
    void getInstrument() {
        Mockito.when(instrumentRepo.findById("AAPL")).thenReturn(Optional.of(new InstrumentMaster()));
        assertNotNull(instrumentController.getInstrument("AAPL"));
    }

}