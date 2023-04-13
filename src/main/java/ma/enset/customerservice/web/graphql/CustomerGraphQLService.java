package ma.enset.customerservice.web.graphql;

import lombok.AllArgsConstructor;
import ma.enset.customerservice.entities.Customer;
import ma.enset.customerservice.repository.CustomerRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
public class CustomerGraphQLService {
    private CustomerRepository customerRepository;

    @QueryMapping
    public List<Customer> customers(){
        return customerRepository.findAll();
    }
    @QueryMapping
    public Customer customerById(@Argument Long id){
        return customerRepository.findById(id).get();
    }
    @MutationMapping
    public Customer saveCustomer(@Argument Customer customer){
        return customerRepository.save(customer);
    }
}
