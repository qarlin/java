package factory.strategy;


public interface PrintStrategyFactory {
	
	IPrintStrategy getStrategy(String strategyName);
}
