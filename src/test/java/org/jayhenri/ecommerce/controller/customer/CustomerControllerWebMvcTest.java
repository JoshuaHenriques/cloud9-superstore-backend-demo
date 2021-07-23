package org.jayhenri.ecommerce.controller.customer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jayhenri.ecommerce.controller.InventoryController;
import org.jayhenri.ecommerce.model.Customer;
import org.jayhenri.ecommerce.model.Inventory;
import org.jayhenri.ecommerce.model.Item;
import org.jayhenri.ecommerce.service.CustomerService;
import org.jayhenri.ecommerce.service.InventoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
@WebMvcTest(InventoryController.class)
public class CustomerControllerWebMvcTest {
 
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;
    

    private Customer customer;

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
