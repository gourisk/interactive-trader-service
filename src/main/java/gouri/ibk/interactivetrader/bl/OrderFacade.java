package gouri.ibk.interactivetrader.bl;

import gouri.ibk.interactivetrader.model.OrderMaster;
import org.springframework.context.annotation.Scope;

import javax.inject.Named;
import java.util.Map;

@Named
@Scope("prototype")
public class OrderFacade {


    public OrderMaster bookNewOrder(Map<String, Object> orderParams) {
        return null;
    }

}
