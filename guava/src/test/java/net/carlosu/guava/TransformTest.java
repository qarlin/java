package net.carlosu.guava;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.primitives.Ints;

public class TransformTest {
	public class ETradeInvestment {
	    
	    private String key;
	    private String name;
	    private BigDecimal price;
		public String getKey() {
			return key;
		}
		public void setKey(String key) {
			this.key = key;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public BigDecimal getPrice() {
			return price;
		}
		public void setPrice(BigDecimal price) {
			this.price = price;
		}

	}

	public class TdAmeritradeInvestment {
	    
	    public TdAmeritradeInvestment(int i, String string, double d) {
		}
		private int investmentKey;
	    private String investmentName;
	    private double investmentPrice;
		public int getInvestmentKey() {
			return investmentKey;
		}
		public void setInvestmentKey(int investmentKey) {
			this.investmentKey = investmentKey;
		}
		public String getInvestmentName() {
			return investmentName;
		}
		public void setInvestmentName(String investmentName) {
			this.investmentName = investmentName;
		}
		public double getInvestmentPrice() {
			return investmentPrice;
		}
		public void setInvestmentPrice(double investmentPrice) {
			this.investmentPrice = investmentPrice;
		}
	}
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void convert_tdinvestment_etradeinvestment () {
	    
	    List<TdAmeritradeInvestment> tdInvestments = Lists.newArrayList();
	    tdInvestments.add(new TdAmeritradeInvestment(555, "Facebook Inc", 57.51));
	    tdInvestments.add(new TdAmeritradeInvestment(123, "Micron Technology, Inc.", 21.29));
	    tdInvestments.add(new TdAmeritradeInvestment(456, "Ford Motor Company", 15.31));
	    tdInvestments.add(new TdAmeritradeInvestment(236, "Sirius XM Holdings Inc", 3.60));
	    
	    // convert a list of objects
	    Function<TdAmeritradeInvestment, ETradeInvestment> tdToEtradeFunction = new Function<TdAmeritradeInvestment, ETradeInvestment>() {

	        public ETradeInvestment apply(TdAmeritradeInvestment input) {
	            ETradeInvestment investment = new ETradeInvestment();
	            investment.setKey(Ints.stringConverter().reverse()
	                    .convert(input.getInvestmentKey()));
	            investment.setName(input.getInvestmentName());
	            investment.setPrice(new BigDecimal(input.getInvestmentPrice()));
	            return investment;
	        }
	    };

	    List<ETradeInvestment> etradeInvestments = Lists.transform(tdInvestments, tdToEtradeFunction);
	    
	    System.out.println(etradeInvestments);
	}

	@Test
	public void transform_list_to_map () {
	    
	    List<TdAmeritradeInvestment> tdInvestments = Lists.newArrayList();
	    tdInvestments.add(new TdAmeritradeInvestment(555, "Facebook Inc", 57.51));
	    tdInvestments.add(new TdAmeritradeInvestment(123, "Micron Technology, Inc.", 21.29));
	    tdInvestments.add(new TdAmeritradeInvestment(456, "Ford Motor Company", 15.31));
	    tdInvestments.add(new TdAmeritradeInvestment(236, "Sirius XM Holdings Inc", 3.60));
	    
	    ImmutableMap<Integer, TdAmeritradeInvestment> investmentMap = Maps
	            .uniqueIndex(tdInvestments,
	                    new Function<TdAmeritradeInvestment, Integer>() {

	                        public Integer apply(TdAmeritradeInvestment input) {
	                            return new Integer(input.getInvestmentKey());
	                        }
	                    });
	    
	    System.out.println(investmentMap);
	    
	}
}
