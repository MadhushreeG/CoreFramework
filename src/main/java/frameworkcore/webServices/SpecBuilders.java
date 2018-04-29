///**
// * This project is using Spring 
// */
//package frameworkcore.webServices;
//
//import static io.restassured.RestAssured.given;
//import static org.hamcrest.Matchers.hasSize;
//
//import org.testng.annotations.Test;
//import io.restassured.builder.ResponseSpecBuilder;
//import io.restassured.http.ContentType;
//import io.restassured.specification.ResponseSpecification;
///**
// * @author dtiwa1
// *
// */
//public class SpecBuilders {
//	
//	static ResponseSpecification validationSpec;
//
//	public static void BuildContentTypeSpec(){
//		validationSpec = 
//			    new ResponseSpecBuilder().
//			        expectStatusCode(200).
//			        expectContentType(ContentType.JSON).
//			        build();
//	}
//	
//	
//	public static void BuildStatusCode200Spec(){
//		validationSpec = 
//			    new ResponseSpecBuilder().
//			        expectStatusCode(200).
//			        build();
//	}
//	
//	@Test
//	public void test_NumberOfCircuits_ShouldBe20_UsingResponseSpec() {
//	        
//	    given().
//	    when().
//	        get("http://ergast.com/api/f1/2017/circuits.json").
//	    then().
//	        assertThat().
//	        spec(validationSpec).
//	    and().
//	        body("MRData.CircuitTable.Circuits.circuitId",hasSize(20));
//	}
//	
//	
//}
