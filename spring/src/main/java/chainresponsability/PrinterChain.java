package chainresponsability;

import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.stereotype.Component;

import chainresponsability.model.User;
import chainresponsability.printer.Printer;

@Component
public class PrinterChain {

	@Autowired
    private List<Printer> printers;

    @PostConstruct
    public void init() {
            Collections.sort(printers, AnnotationAwareOrderComparator.INSTANCE);
    }

    public void introduceUser(User user) {
            for (Printer printer : printers) {
                    printer.print(user);
            }
    }
}
