package sg.nus.iss.d16walkthrough;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import sg.nus.iss.d16walkthrough.Model.Address;
import sg.nus.iss.d16walkthrough.Model.Employee;


@SpringBootApplication
public class D16walkthroughApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(D16walkthroughApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		//slide 7: JSON P object
		JsonObjectBuilder empBuilder = Json.createObjectBuilder();
		empBuilder.add("firstName", "Will")
			.add("lastName", "Smith")
			.add("salary", 2000);

			JsonObjectBuilder addressBuilder = Json.createObjectBuilder();
			addressBuilder.add("line1", "Potong Pasir Ciew")
				.add("line2", "8 Potong Pasir Ave 8")
				.add("postal", "365268");

			empBuilder.add("address", addressBuilder);

			JsonObjectBuilder phone1 = Json.createObjectBuilder();
			phone1.add("type", "mobile")
				.add("number", "97826812");
			JsonObjectBuilder phone2 = Json.createObjectBuilder();
			phone2.add("type", "home")
				.add("number", "65428932");
			JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
			arrayBuilder.add(phone1).add(phone2);
			empBuilder.add("phones", arrayBuilder);

			JsonObject employee = empBuilder.build(); //import jakarta json (not google)

			//To map JsonObject back to Employee entity
			Employee emp = new Employee();
			emp.setFirstName(employee.get("firstName").toString());
			emp.setLastName(employee.get("lastName").toString());
			emp.setSalary(Long.parseLong(employee.get("salary").toString()));

			JsonObject addressObject = employee.getJsonObject("address");
			Address myAddress = new Address();
			myAddress.setLine1(addressObject.get("line1").toString());
			myAddress.setLine2(addressObject.get("line2").toString());
			myAddress.setPostal(addressObject.get("postal").toString());
			emp.setAddress(myAddress);

			System.out.println("Employee Entity: " + employee.toString());

	}

}
