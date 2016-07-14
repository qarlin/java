package concurrency.executors;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Server {
	private static final Logger logger = LogManager.getLogger();
	
	private ThreadPoolExecutor executor;
	
	public Server(){
		//Executor creates a new thread for each task
		//executor = (ThreadPoolExecutor)Executors.newCachedThreadPool();
		
		//Executor with 5 fixed pool
		executor = (ThreadPoolExecutor)Executors.newFixedThreadPool(5);
	}
	
	public void executeTask(Task task){
		logger.printf(Level.INFO, "Server: A new Task has arrived\n");
		executor.execute(task);
		logger.printf(Level.INFO, "Server: Pool Size: %d\n", executor.getPoolSize());
		logger.printf(Level.INFO, "Server: Active Account: %d\n", executor.getActiveCount());
		logger.printf(Level.INFO, "Server: Completed Tasks: %d\n", executor.getCompletedTaskCount());
		logger.printf(Level.INFO, "Server: Task count: %d\n", executor.getTaskCount());
	}
	
	public void endServer(){
		executor.shutdown();
	}
	
	public static void main(String[] args){
		Server server = new Server();
		for (int i = 0; i < 100; i++) {
			Task task = new Task("Task" + i);
			server.executeTask(task);
		}
		server.endServer();
	}
}
