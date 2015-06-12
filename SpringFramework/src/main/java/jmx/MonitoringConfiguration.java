package jmx;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jmx.export.MBeanExporter;
import org.springframework.jmx.export.annotation.AnnotationJmxAttributeSource;
import org.springframework.jmx.export.assembler.MetadataMBeanInfoAssembler;
import org.springframework.jmx.export.naming.MetadataNamingStrategy;

@Configuration
public class MonitoringConfiguration {

	/*
	 *  JMX Configuration
	 */
	@Bean
	public AnnotationJmxAttributeSource jmxAttributeSource(){
		return new AnnotationJmxAttributeSource();
	}
	
	@Bean
	public MetadataNamingStrategy namingStrategy(){
		MetadataNamingStrategy meta = new MetadataNamingStrategy();
		meta.setAttributeSource(jmxAttributeSource());
		return meta;
	}
	
	@Bean
	public MetadataMBeanInfoAssembler assembler(){
		MetadataMBeanInfoAssembler meta = new MetadataMBeanInfoAssembler();
		meta.setAttributeSource(jmxAttributeSource());
		return meta;
	}
	
	@Bean
	public MBeanExporter exporter(){
		MBeanExporter exporter = new MBeanExporter();
		exporter.setAssembler(assembler());
		exporter.setNamingStrategy(namingStrategy());
		exporter.setAutodetect(true);
		return exporter;
	}
}
