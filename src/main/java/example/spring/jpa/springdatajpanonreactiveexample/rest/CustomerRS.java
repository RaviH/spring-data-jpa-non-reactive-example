package example.spring.jpa.springdatajpanonreactiveexample.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import example.spring.jpa.springdatajpanonreactiveexample.client.CarsClient;
import example.spring.jpa.springdatajpanonreactiveexample.dto.CarDTO;
import example.spring.jpa.springdatajpanonreactiveexample.entity.Customer;
import example.spring.jpa.springdatajpanonreactiveexample.repository.CustomerRepository;
import example.spring.jpa.springdatajpanonreactiveexample.service.TestDataGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("customer")
@Validated
public class CustomerRS {

    private CustomerRepository customerRepository;
    private TestDataGenerator testDataGenerator;
    private CarsClient carsClient;

    @Autowired
    public CustomerRS(
            CustomerRepository customerRepository,
            TestDataGenerator testDataGenerator,
            CarsClient carsClient
    ) {

        this.customerRepository = customerRepository;
        this.testDataGenerator = testDataGenerator;
        this.carsClient = carsClient;
    }

    @GetMapping(produces = "application/json")
    public List<Customer> getAllCustomers(
            @Min(0)
            @Max(Integer.MAX_VALUE)
            @RequestParam(value = "page", defaultValue = "1")
                    int page,
            @Min(1)
            @Max(Integer.MAX_VALUE)
            @RequestParam(value = "size", defaultValue = "10")
                    int size
    ) {

        return customerRepository.findAll(PageRequest.of(page, size))
                .get()
                .collect(Collectors.toList());
    }

    @GetMapping(value = "top/{number}", produces = "application/json")
    public List<Customer> findTop10Customers(
            @Min(10)
            @Max(Integer.MAX_VALUE)
            @PathVariable(value = "number")
            final int topN
    ) {

        final PageRequest pageRequest = PageRequest.of(0, topN, Sort.Direction.ASC, "id");
        return customerRepository.findAll(pageRequest).stream().collect(Collectors.toList());
    }

    @GetMapping(value = "{id}/cars", produces = "application/json")
    public List<CarDTO> getCarsThatCustomerLikes(
            @Min(1)
            @Max(Integer.MAX_VALUE)
            @PathVariable(value = "id")
            final int customerId
    ) throws JsonProcessingException {


        return carsClient.getAllCars(customerId);
    }

    @PostMapping(path = "/generate", produces = "application/json")
    public Iterable<Customer> generateCustomers(
            @RequestParam(value = "numberOfCustomers", defaultValue = "10")
            final int numberOfCustomers
    ) {

        final List<Customer> customers = testDataGenerator.createCustomer(numberOfCustomers);
        return customerRepository.saveAll(customers);
    }
}
