package jaxb;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/exchangerates/rates")
public class AnswersGetter {

	@GET
	@Path("{table}/{code}/{topCount}")
	@Produces(MediaType.TEXT_PLAIN)
	public String getAvg(@PathParam("table") String tableName,
						  @PathParam("code") String codeName,
						  @PathParam("topCount") String topCountName){
		String answ = InfoGetter.getMeanFromNBPUsingUnmarshalling(tableName, codeName, topCountName);
		return answ;
	}
	
	@GET
	@Path("{table}/{code}/{topCount}")
	@Produces(MediaType.TEXT_HTML)
	public String getAvgHTML(@PathParam("table") String tableName,
			  @PathParam("code") String codeName,
			  @PathParam("topCount") String topCountName){
		String answ = InfoGetter.getMeanFromNBPUsingUnmarshalling(tableName, codeName, topCountName);
		return "<html><body><h1>" + answ + "</h1></body></html>";
	}
	
	
	@GET
	@Path("{table}/{code}/{topCount}")
	@Produces(MediaType.TEXT_XML)
	public String getAvgXML(@PathParam("table") String tableName,
			  @PathParam("code") String codeName,
			  @PathParam("topCount") String topCountName){
			String answ = InfoGetter.getMeanFromNBPUsingUnmarshalling(tableName, codeName, topCountName);
			return "<?xml version=\"1.0\"?>" + "<average_exchange_rate>" + answ.toString() + "</average_exchange_rate>";
	}
	
	@GET
	@Path("{table}/{code}/{topCount}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAvgJSON(@PathParam("table") String tableName,
			  @PathParam("code") String codeName,
			  @PathParam("topCount") String topCountName){
			String answ = InfoGetter.getMeanFromNBPUsingUnmarshalling(tableName, codeName, topCountName);
			JsonObjectBuilder builder = Json.createObjectBuilder();
			builder.add("average_exchange_rate", answ);
			JsonObject j = builder.build();
			return j.toString();
	}

	
}