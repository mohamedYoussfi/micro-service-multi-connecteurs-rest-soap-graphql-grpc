package ma.enset.customerservice.web.soap;

import ma.enset.customerservice.web.soap.CustomerSoapService;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CXFSoapWebServiceConfig {
    @Autowired
    private Bus bus;
    @Autowired
    private CustomerSoapService customerSoapService;

    @Bean
    public EndpointImpl endpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, customerSoapService);
        endpoint.publish("/CustomerService");
        return endpoint;
    }
}
