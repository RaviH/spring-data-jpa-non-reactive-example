package example.spring.jpa.springdatajpanonreactiveexample.service;

import example.spring.jpa.springdatajpanonreactiveexample.entity.Customer;
import io.github.benas.randombeans.EnhancedRandomBuilder;
import io.github.benas.randombeans.api.EnhancedRandom;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TestDataGenerator {

    private EnhancedRandom enhancedRandomBuilder = EnhancedRandomBuilder.aNewEnhancedRandomBuilder()
            .seed(123L)
            .objectPoolSize(2)
            .stringLengthRange(4, 10)
            .collectionSizeRange(1, 10)
            .scanClasspathForConcreteTypes(true)
            .build();

    public List<Customer> createCustomer(int numberOfCustomers) {

        final List<Customer> customers = new ArrayList<>(numberOfCustomers);
        for (int i = 0; i < numberOfCustomers; i++) {
            final Customer customer = enhancedRandomBuilder.nextObject(Customer.class);
            customer.setId(null);
            customers.add(customer);
        }
        return customers;
    }
}
