package ma.enset.customerservice;

import ma.enset.customerservice.entities.Customer;
import ma.enset.customerservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CustomerServiceApplication implements CommandLineRunner {
    @Autowired
    private CustomerRepository customerRepository;

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i <10 ; i++) {
            Customer customer=Customer.builder()
                    .firstName("firstName "+(i+1))
                    .lastName("lastName "+(i+1))
                    .email("firstName.lastName "+(i+1)+"@gmail.com")
                    .build();
            customerRepository.save(customer);
        }

    }
}
