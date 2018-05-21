package jaxb;

import java.util.List;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ExchangeRatesSeries")
public class ExchangeRatesSeries {
	@XmlElement(name = "Table")
	private String Table;
	@XmlElement(name = "Currency")
	private String Currency;
	@XmlElement(name = "Code")
	private String Code;
	@XmlElement(name = "Rates")
	private Rates Rates;
	
	
	
	public static class Rates {
	
	@XmlElement(name = "Rate")
	private List<Rate> Rate = new ArrayList<Rate>();
		
		public static class Rate {
			//private String No;
			//private String EffectiveDate;
			@XmlElement(name = "Mid")
			private String Mid;
			@XmlElement(name = "Ask")
			private String Ask;
			public String getMid() {
				return Mid;
			}
			public void setMid(String mid) {
				Mid = mid;
			}
			public String getAsk() {
				return Ask;
			}
			public void setAsk(String ask) {
				Ask = ask;
			}
		}

		public List<Rate> getRate() {
			return Rate;
		}

		public void setRate(List<Rate> rate) {
			Rate = rate;
		}
		
		public double getSum(String what) {
			double sum = 0.0;
			for(Rate x: Rate) {
				double value = 0.0;
				if(what.equals("Mid"))
					value = Double.parseDouble(x.getMid());
				else
					value = Double.parseDouble(x.getAsk());
				sum += value;
			}
			return sum;
		}
	}




	public String getTable() {
		return Table;
	}




	public void setTable(String table) {
		Table = table;
	}




	public String getCurrency() {
		return Currency;
	}




	public void setCurrency(String currency) {
		Currency = currency;
	}




	public String getCode() {
		return Code;
	}




	public void setCode(String code) {
		Code = code;
	}




	public Rates getRates() {
		return Rates;
	}




	public void setRates(Rates rates) {
		Rates = rates;
	}
}