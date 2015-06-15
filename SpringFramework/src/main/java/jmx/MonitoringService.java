package jmx;

import javax.management.Notification;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.jmx.export.notification.NotificationPublisher;
import org.springframework.jmx.export.notification.NotificationPublisherAware;
import org.springframework.stereotype.Component;

@ManagedResource(objectName = "spring.bean:name=applicationMonitor")
@Component
public class MonitoringService implements IMonitoringService, NotificationPublisherAware {

    private static final Logger LOG = LogManager.getLogger();
    
    private boolean isDbServicesEnabled = true;

    private int notificationIndex = 0;
    private NotificationPublisher notificationPublisher;
    
    @ManagedAttribute(description = "DBServices configurator")
    public boolean isDbServicesEnabled() {
        return isDbServicesEnabled;
    }

    @ManagedAttribute(description = "DBServices configurator")
    public void setDbServicesEnabled(boolean dbServicesEnabled) {
        LOG.info("DBServices " + (isDbServicesEnabled ? "enabled" : "disabled"));
        notificationPublisher.sendNotification(buildNotification(this.isDbServicesEnabled, dbServicesEnabled) );
        isDbServicesEnabled = dbServicesEnabled;
    }

	private Notification buildNotification(boolean isDbServicesEnabled2, boolean dbServicesEnabled) {
		final String notificationType = "jmx.spring.notification.test";
	      final String message = "Converting " + isDbServicesEnabled2 + " to " + dbServicesEnabled;
	      final Notification notification =
	         new Notification( notificationType,
	                           this,
	                           notificationIndex++,
	                           System.currentTimeMillis(),
	                           message );
	      notification.setUserData("Blog Example #" + notificationIndex );
	      return notification;
	}

	@Override
	public void setNotificationPublisher(NotificationPublisher notificationPublisher) {
		this.notificationPublisher = notificationPublisher;
	}

}
