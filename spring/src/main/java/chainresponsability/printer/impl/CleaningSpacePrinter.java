package chainresponsability.printer.impl;

import org.springframework.stereotype.Component;

import chainresponsability.printer.GenericPrinter;

@Component
public class CleaningSpacePrinter extends GenericPrinter {

    private static final String GREETING = "Cleaning space after";

    @Override
    protected String getGreeting() {
            return GREETING;
    }

}
