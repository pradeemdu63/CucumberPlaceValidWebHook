package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Utils {

	static RequestSpecification reqSpec;
	ResponseSpecification respSpec;

	public RequestSpecification requestSpec() throws IOException {

		while (reqSpec == null) {
			PrintStream printLog = new PrintStream(new FileOutputStream("logging.txt"));
			reqSpec = new RequestSpecBuilder().setBaseUri(globalProp("baseUrl")).setContentType(ContentType.JSON)
					.addQueryParam("key", "qaclick123").addFilter(RequestLoggingFilter.logRequestTo(printLog))
					.addFilter(ResponseLoggingFilter.logResponseTo(printLog)).build();
			return reqSpec;
		}
		return reqSpec;
	}

	/*
	 * public ResponseSpecification responseSpec() {
	 * 
	 * respSpec= new ResponseSpecBuilder().expectStatusCode(200)
	 * .expectContentType(ContentType.JSON) .build(); return respSpec; }
	 */

	public String rawJson(Response jsonResponse,String key) {

		String jsonString = jsonResponse.asString();
		JsonPath js = new JsonPath(jsonString);
		return js.get(key).toString();
	}

	public String globalProp(String key) throws IOException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				"C:\\Users\\Pradee\\eclipse-workspace\\RestScratchCucumberFramework\\src\\test\\java\\resources\\globalProperties.properties");
		prop.load(fis);
		return prop.getProperty(key);
	}
}
