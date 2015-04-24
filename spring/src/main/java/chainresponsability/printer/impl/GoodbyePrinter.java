package chainresponsability.printer.impl;

import org.springframework.stereotype.Component;

import chainresponsability.printer.GenericPrinter;

@Component
public class GoodbyePrinter extends GenericPrinter {

    private static final String GREETING = "Goodbye";

    @Override
    protected String getGreeting() {
            return GREETING;
    }

    @Override
    public int getOrder() {
            return 2;
    }
}
