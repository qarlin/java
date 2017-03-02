package concurrency.chapter01;

import java.io.File;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FileSearch implements Runnable {
	private static final Logger logger = LogManager.getLogger();
	
	private String initPath;
	private String fileName;
	
	public FileSearch(String initPath, String fileName){
		this.initPath = initPath;
		this.fileName = fileName;
	}
	
	@Override
	public void run() {
		File file = new File(initPath);
		if (file.isDirectory()) {
			try {
				directoryProcess(file);
			} catch (InterruptedException e) {
				logger.printf(Level.TRACE, "%s: The search has been interrupted, %s", Thread.currentThread().getName(), e.getMessage());
			}
		}
	}
	
	private void directoryProcess(File file) throws InterruptedException {
		File list[] = file.listFiles();
		
		if (list == null){
			return;
		}
		
		for (File file2 : list) {
			if (file2.isDirectory()){
				logger.printf(Level.TRACE, "Search in folder: %s", file2.getName().toString());
				directoryProcess(file2);
			} else if (file2.isFile()){
				logger.printf(Level.TRACE, "Process file: %s", file2.getName());
				fileProcess(file2);
			}
		}
		if (Thread.interrupted()) {
			throw new InterruptedException("Folder Interrupteds");
		}
	}
	
	private void fileProcess(File file) throws InterruptedException {
		if (file.getName().equals(fileName))
			logger.printf(Level.TRACE, "%s: %s\n", Thread.currentThread().getName(), file.getAbsolutePath());
		
		if (Thread.interrupted()){
			throw new InterruptedException("File Interrupted");
		}
		
	}

}
