package net.carlosu.ftp.protocol;



public class ApacheFtpMultithreadClient extends ApacheFtpClient{
	/*private static final int NUM_THREADS = 5;  
	  
    @Override
	public void uploadContent(HashMap<String, String> content) throws BusinessException {
		connect();
		
		ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>(content);
		
		for (final Entry<String, String> entry : content.entrySet()) {
			Thread th = new Thread() {
				public void run(){
					try {
						uploadString(entry.getKey(), entry.getValue());
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			};
		}
		
		disconnect();
	}*/
}
