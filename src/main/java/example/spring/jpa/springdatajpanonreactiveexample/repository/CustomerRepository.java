package example.spring.jpa.springdatajpanonreactiveexample.repository;

import example.spring.jpa.springdatajpanonreactiveexample.entity.Customer;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends PagingAndSortingRepository<Customer, Integer> {
}
