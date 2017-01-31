package com.positions;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

public class PositionWriter implements ItemWriter<Position> {

	@Override
	public void write(List<? extends Position> positions) throws Exception {
		for (Position position : positions) {
			System.out.println(position);
		}
	}

}
