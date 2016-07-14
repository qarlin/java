package net.carlosu.statictask;

public class Consts {
	public enum TASKS_NAMESPACE implements TaskConfig {
		EXAMPLE1_TASK("net.carlosu.statictask.Example1Task",1);
		
		TASKS_NAMESPACE(String aTaskName,	int aThreads){
			try {
				mTask=(AbstractTask) Class.forName(aTaskName).newInstance();
			} catch (Exception x){
				throw new RuntimeException();
			}
			mThreads = aThreads;
		}
		
		AbstractTask mTask;
		int mThreads;
		
		@Override
		public AbstractTask getTask() {
			return mTask;
		}

		@Override
		public int getThreads() {
			return mThreads;
		}

	

	}
	public static interface TaskConfig{
		public AbstractTask getTask();
		public int getThreads();
	}
}
