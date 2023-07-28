package com.company.customerdataservice.controller;

import com.company.customerdataservice.model.Customer;
import com.company.customerdataservice.repository.CustomerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;
// import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CustomerRepository customerRepository;

    private ObjectMapper mapper = new ObjectMapper();

    private List<Customer> customerList;

    @BeforeEach
    public void setUp() {
    }

    // Testing find customer by id
    @Test
    public void shouldReturnCustomerByIdWithGET() throws Exception {
        Customer customer = new Customer();
        customer.setId(1);
        customer.setFirstName("Thea");
        customer.setLastName("DeLaCruz");
        customer.setEmail("thea.delacruz@gmail.com");
        customer.setCompany("LA Hospital");
        customer.setPhone("310-981-8464");
        customer.setAddress1("123 Torrance Dr");
        customer.setAddress2("456 Irvine Ave");
        customer.setCity("Torrance");
        customer.setState("CA");
        customer.setPostalCode(12345);
        customer.setCountry("USA");

        String customerAsJson = mapper.writeValueAsString(customer);

        mockMvc.perform(get("/customers/1")
                        .content(customerAsJson)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andExpect(status().isOk());
    }

    // Testing get customers by state
    @Test
    public void shouldReturnCustomersByStateWithGET() throws Exception {
        Customer customer1 = new Customer();
        customer1.setFirstName("Thea");
        customer1.setLastName("DeLaCruz");
        customer1.setEmail("thea.delacruz@gmail.com");
        customer1.setCompany("LA Hospital");
        customer1.setPhone("310-981-8464");
        customer1.setAddress1("123 Torrance Dr");
        customer1.setAddress2("456 Irvine Ave");
        customer1.setCity("Torrance");
        customer1.setState("CA");
        customer1.setPostalCode(12345);
        customer1.setCountry("USA");

        String customerAsJson = mapper.writeValueAsString(customer1);

        mockMvc.perform(get("/customers/state/CA"))
                        .andDo(print())
                        .andExpect(status().isOk());
    }

    // Testing create new customer
    @Test
    public void shouldCreateNewCustomerWithPOST() throws Exception {
        Customer customer = new Customer();
        customer.setFirstName("Thea");
        customer.setLastName("DeLaCruz");
        customer.setEmail("thea.delacruz@gmail.com");
        customer.setCompany("LA Hospital");
        customer.setPhone("310-981-8464");
        customer.setAddress1("123 Torrance Dr");
        customer.setAddress2("456 Irvine Ave");
        customer.setCity("Torrance");
        customer.setState("CA");
        customer.setPostalCode(12345);
        customer.setCountry("USA");

        String customerAsJson = mapper.writeValueAsString(customer);

        mockMvc.perform(post("/customers")
                        .content(customerAsJson)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andExpect(status().isCreated());
    }

    // Testing update customer
    @Test
    public void shouldUpdateCustomerWithPUT() throws Exception {
        Customer customer = new Customer();
        customer.setFirstName("Thea");
        customer.setLastName("DeLaCruz");
        customer.setEmail("thea.delacruz@gmail.com");
        customer.setCompany("LA Hospital");
        customer.setPhone("310-981-8464");
        customer.setAddress1("123 Torrance Dr");
        customer.setAddress2("456 Irvine Ave");
        customer.setCity("Torrance");
        customer.setState("CA");
        customer.setPostalCode(12345);
        customer.setCountry("USA");

        String customerAsJson = mapper.writeValueAsString(customer);

        mockMvc.perform(put("/customers")
                        .content(customerAsJson)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andExpect(status().isNoContent());
    }

    // Testing delete customer
    @Test
    public void shouldDeleteCustomerByIdWithDELETE() throws Exception {
        Customer customer = new Customer();
        customer.setId(1);
        customer.setFirstName("Theaa");
        customer.setLastName("DeLaCruz");
        customer.setEmail("thea.delacruz@gmail.com");
        customer.setCompany("LA Hospital");
        customer.setPhone("310-981-8464");
        customer.setAddress1("123 Torrance Dr");
        customer.setAddress2("456 Irvine Ave");
        customer.setCity("Torrance");
        customer.setState("CA");
        customer.setPostalCode(12345);
        customer.setCountry("USA");

        customer = customerRepository.save(customer);

        mockMvc.perform(delete("/customers/" + customer.getId()))
                .andDo(print())
                .andExpect(status().isNoContent());

        Customer deletedCustomer = customerRepository.findById(customer.getId()).orElse(null);
        assertNull(deletedCustomer);
    }
}