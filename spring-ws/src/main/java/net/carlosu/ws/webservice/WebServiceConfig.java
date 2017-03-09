package net.carlosu.ws.webservice;

import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.server.endpoint.interceptor.PayloadLoggingInterceptor;
import org.springframework.ws.soap.server.endpoint.SoapFaultDefinition;
import org.springframework.ws.soap.server.endpoint.SoapFaultMappingExceptionResolver;
import org.springframework.ws.soap.server.endpoint.interceptor.PayloadRootSmartSoapEndpointInterceptor;
import org.springframework.ws.soap.server.endpoint.interceptor.PayloadValidatingInterceptor;
import org.springframework.ws.soap.server.endpoint.interceptor.SoapEnvelopeLoggingInterceptor;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

import net.carlosu.ws.webservice.interceptor.GlobalInterceptor;
import net.carlosu.ws.webservice.interceptor.UserInterceptor;

@EnableWs
@Configuration
@ComponentScan
public class WebServiceConfig extends WsConfigurerAdapter {
	public static final String NAMESPACE = "http://localhost:8080/ws/model";

	@Autowired
	private UserInterceptor userInterceptor;
	@Autowired
	private GlobalInterceptor globalInterceptor;

	//To check the wsdl http://localhost:8080/ws/userDetail.wsdl
	@Bean(name = "userDetail")
	public DefaultWsdl11Definition userDetails(XsdSchema userDetailsSchema) {
		DefaultWsdl11Definition wsdlDefinition = new DefaultWsdl11Definition();
		wsdlDefinition.setTargetNamespace(NAMESPACE);
		wsdlDefinition.setSchema(userDetailsSchema);
		wsdlDefinition.setPortTypeName("UserDetailsPort");
		wsdlDefinition.setLocationUri("/ws/");
		return wsdlDefinition;
	}

	@Bean
	public XsdSchema userDetailsSchema() {
		return new SimpleXsdSchema(new ClassPathResource("userDetailsSchema.xsd"));
	}

	@Bean
	public ServletRegistrationBean dispatcherServlet(ApplicationContext applicationContext) {
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		servlet.setApplicationContext(applicationContext);
		servlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean(servlet, "/ws/*");
	}

	@Bean
	public SoapFaultMappingExceptionResolver exceptionResolver() {
		SoapFaultMappingExceptionResolver exceptionResolver = new SoapFaultMappingExceptionResolver();

		SoapFaultDefinition defaultSoapFault = new SoapFaultDefinition();
		defaultSoapFault.setFaultCode(SoapFaultDefinition.SERVER);
		exceptionResolver.setDefaultFault(defaultSoapFault);

		Properties errorMappings = new Properties();
		errorMappings.put(IllegalStateException.class.getName(), SoapFaultDefinition.CLIENT.toString());
		exceptionResolver.setExceptionMappings(errorMappings);
		exceptionResolver.setOrder(1);

		return exceptionResolver;
	}

	@Override
	public void addInterceptors(List<EndpointInterceptor> interceptors) {

		interceptors.add(new PayloadRootSmartSoapEndpointInterceptor(userInterceptor, NAMESPACE, "UserRequest"));

		PayloadRootSmartSoapEndpointInterceptor smartLoggingInterceptor = new PayloadRootSmartSoapEndpointInterceptor(
				new PayloadLoggingInterceptor(), NAMESPACE, "UserRequest");
		interceptors.add(smartLoggingInterceptor);

		interceptors.add(globalInterceptor);

		interceptors.add(new SoapEnvelopeLoggingInterceptor());

		PayloadValidatingInterceptor validationInterceptor = new PayloadValidatingInterceptor();
		SimpleXsdSchema schema = new SimpleXsdSchema(new ClassPathResource("userDetailsSchema.xsd"));
		validationInterceptor.setXsdSchema(schema);
		validationInterceptor.setValidateRequest(true);
		validationInterceptor.setValidateResponse(true);
		interceptors.add(validationInterceptor);
	}
}
