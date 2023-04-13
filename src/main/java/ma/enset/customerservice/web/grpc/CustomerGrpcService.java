package ma.enset.customerservice.web.grpc;

import io.grpc.stub.StreamObserver;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.enset.customerservice.entities.Customer;
import ma.enset.customerservice.mappers.CustomerMapper;
import ma.enset.customerservice.repository.CustomerRepository;
import ma.enset.customerservice.web.grpc.stub.CustomerGrpcServiceGrpc;
import ma.enset.customerservice.web.grpc.stub.CustomerService;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.List;
import java.util.stream.Collectors;

@GrpcService
@Slf4j
@AllArgsConstructor
public class CustomerGrpcService extends CustomerGrpcServiceGrpc.CustomerGrpcServiceImplBase {
    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;
    @Override
    public void getCustomer(CustomerService.GetCustomerRequest request, StreamObserver<CustomerService.GetCustomerResponse> responseObserver) {
        Customer customer = customerRepository.findById(request.getId()).get();
        CustomerService.Customer grpcCustomer = customerMapper.fromCustomer(customer);
        CustomerService.GetCustomerResponse customerResponse=CustomerService.GetCustomerResponse.newBuilder()
                .setCustomer(grpcCustomer)
                .build();
        responseObserver.onNext(customerResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void getListCustomers(CustomerService.GetAllCustomersRequest request, StreamObserver<CustomerService.GetAllCustomersResponse> responseObserver) {
        List<Customer> customerList = customerRepository.findAll();
        CustomerService.GetAllCustomersResponse.Builder customersBuilder = CustomerService.GetAllCustomersResponse.newBuilder();
        List<CustomerService.Customer> customers = customerList.stream().map(customerMapper::fromCustomer).collect(Collectors.toList());
        customersBuilder.addAllCustomers(customers);
        responseObserver.onNext(customersBuilder.build());
        responseObserver.onCompleted();
    }

    @Override
    public void saveCustomer(CustomerService.SaveCustomerRequest request, StreamObserver<CustomerService.SaveCustomerResponse> responseObserver) {
        Customer customer=new Customer();
        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());
        customer.setEmail(request.getEmail());
        Customer savedCustomer = customerRepository.save(customer);
        CustomerService.Customer grpcCustomer = customerMapper.fromCustomer(savedCustomer);
        CustomerService.SaveCustomerResponse response = CustomerService.SaveCustomerResponse.newBuilder()
                .setCustomer(grpcCustomer)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
