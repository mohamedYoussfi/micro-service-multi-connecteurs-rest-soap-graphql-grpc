package ma.enset.customerservice.mappers;

import ma.enset.customerservice.entities.Customer;
import ma.enset.customerservice.web.grpc.stub.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    private ModelMapper modelMapper=new ModelMapper();

    public Customer fromGrpcCustomer(CustomerService.Customer source){
        return modelMapper.map(source,Customer.class);
    }
    public CustomerService.Customer fromCustomer(Customer customer){
        return modelMapper.map(customer,CustomerService.Customer.Builder.class).build();
    }
}
