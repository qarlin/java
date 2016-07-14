package net.carlosu.statictask;

import net.carlosu.statictask.Consts.TASKS_NAMESPACE;
import net.carlosu.statictask.Consts.TaskConfig;

public class StaticTaskCall {

	public static void main(String[] args) {
		TaskConfig tTask = TASKS_NAMESPACE.valueOf("EXAMPLE1_TASK");
		System.out.println(tTask);
	}
}
