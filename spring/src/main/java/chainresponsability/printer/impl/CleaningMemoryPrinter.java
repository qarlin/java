package chainresponsability.printer.impl;

import org.springframework.stereotype.Component;

import chainresponsability.printer.GenericPrinter;

@Component
public class CleaningMemoryPrinter extends GenericPrinter {

    private static final String GREETING = "Cleaning memory after";

    @Override
    protected String getGreeting() {
            return GREETING;
    }
}
