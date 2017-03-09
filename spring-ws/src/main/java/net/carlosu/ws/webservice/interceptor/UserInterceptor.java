package net.carlosu.ws.webservice.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.EndpointInterceptor;

@Component
public class UserInterceptor implements EndpointInterceptor {
  @Override
  public boolean handleRequest(MessageContext messageContext, Object endpoint)
      throws Exception {
    System.out.println("Endpoint handleRequest");
    return true;
  }

  @Override
  public boolean handleResponse(MessageContext messageContext, Object endpoint)
      throws Exception {
    System.out.println("Endpoint handleResponse");
    return true;
  }

  @Override
  public boolean handleFault(MessageContext messageContext, Object endpoint)
      throws Exception {
    System.out.println("Endpoint handleFault");
    return true;
  }

  @Override
  public void afterCompletion(MessageContext messageContext, Object endpoint,
      Exception ex) throws Exception {
    System.out.println("Endpoint afterCompletion");
  }
}
