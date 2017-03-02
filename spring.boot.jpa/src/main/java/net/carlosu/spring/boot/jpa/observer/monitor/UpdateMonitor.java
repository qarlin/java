package net.carlosu.spring.boot.jpa.observer.monitor;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UpdateMonitor {

	@After("execution(* net.carlosu.spring.boot.jpa.observer.repository.BaseRepository.save(..)) && @annotation(UpdateNotify)")
	public void logAuditActivity(JoinPoint jp) {
		System.out.println("Completed: " + jp);
	}

}
