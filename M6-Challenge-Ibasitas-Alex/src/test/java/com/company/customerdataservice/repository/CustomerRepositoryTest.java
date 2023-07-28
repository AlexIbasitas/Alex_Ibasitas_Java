package com.company.customerdataservice.repository;

import com.company.customerdataservice.controller.CustomerController;
import com.company.customerdataservice.model.Customer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class CustomerRepositoryTest {
    @Autowired
    CustomerRepository customerRepo;

    private ObjectMapper mapper = new ObjectMapper();

    private List<Customer> customerList;

    @BeforeEach
    public void setUp() {
        customerRepo.deleteAll();
    }

    // Testing find customer by id
    @Test
    public void testFindCustomerById() throws Exception {
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

        customerRepo.save(customer);

        Optional<Customer> customer1 = customerRepo.findById(customer.getId());

        assertEquals(customer1.get(), customer);
    }

    // Testing get customers by state
    @Test
    public void testGetCustomerByState() throws Exception {
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
        customerRepo.save(customer1);

        Customer customer2 = new Customer();
        customer2.setFirstName("Alex");
        customer2.setLastName("Ibasitas");
        customer2.setEmail("alex.ibasitas@gmail.com");
        customer2.setCompany("S&P Global");
        customer2.setPhone("949-889-0987");
        customer2.setAddress1("123 Putnam Dr");
        customer2.setAddress2("456 Irvine Ave");
        customer2.setCity("New York City");
        customer2.setState("NY");
        customer2.setPostalCode(45678);
        customer2.setCountry("USA");
        customerRepo.save(customer2);

        Customer customer3 = new Customer();
        customer3.setFirstName("Emilio");
        customer3.setLastName("Kosta");
        customer3.setEmail("emilio.kosta@gmail.com");
        customer3.setCompany("S&P Global");
        customer3.setPhone("123-456-7890");
        customer3.setAddress1("55 Water Street");
        customer3.setAddress2("123 Queens Ave");
        customer3.setCity("New York City");
        customer3.setState("NY");
        customer3.setPostalCode(42039);
        customer3.setCountry("USA");
        customerRepo.save(customer3);


        List<Customer> residentsOfNY = customerRepo.findByState("NY");
        System.out.println(residentsOfNY);
        assertEquals(residentsOfNY.size(), 2);
    }

    // Testing create new customer
    @Test
    public void testCreateNewCustomer() throws Exception {
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

        customerRepo.save(customer);

        Optional<Customer> customer1 = customerRepo.findById(customer.getId());

        assertEquals(customer1.get(), customer);
    }

    // Testing update customer
    @Test
    public void testUpdateCustomer() throws Exception {
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

        customerRepo.save(customer);

        customer.setFirstName("Dorothea");

        customerRepo.save(customer);

        Optional<Customer> customer1 = customerRepo.findById(customer.getId());

        assertEquals(customer1.get(), customer);
    }

    // Testing delete customer
    @Test
    public void testDeleteCustomer() throws Exception {
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

        customerRepo.save(customer);

        customerRepo.deleteById(customer.getId());

        Optional<Customer> customer1 = customerRepo.findById(customer.getId());
        assertFalse(customer1.isPresent());
    }
}