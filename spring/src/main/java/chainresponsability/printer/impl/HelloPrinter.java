package chainresponsability.printer.impl;

import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import chainresponsability.printer.GenericPrinter;

@Component
public class HelloPrinter extends GenericPrinter {

	private static final String GREETING = "Hello";

    @Override
    protected String getGreeting() {
            return GREETING;
    }

    @Override
    public int getOrder() {
            return Ordered.HIGHEST_PRECEDENCE;
    }

}
