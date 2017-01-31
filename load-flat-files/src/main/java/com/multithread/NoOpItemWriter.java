package com.multithread;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

public class NoOpItemWriter<T> implements ItemWriter<T> {
	@Override
	public void write(List<? extends T> arg0) throws Exception {
		
	}
}