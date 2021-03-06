package Employee_payroll_project;

import static org.junit.Assert.*;

import java.security.PublicKey;
import java.sql.ResultSet;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.gson.Gson;

import Employee_payroll_project.EmployeePayrollService.IOService;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class EmployeePayrollServiceTest {
/*
	@Test
	public void givenEmployeePayrollInDB_ShouldMatchEmployeeCount() throws EmployeeException {
		EmployeePayrollService employeePayrollService = new EmployeePayrollService();
		List<EmployeePayrollData> employeePayrollData = employeePayrollService.readEmployeePayrollData(IOService.DB_IO);
		assertEquals(3, employeePayrollData.size());
	}
	
	@Test
	public void givenNewSalaryForEmployee_WhenUpdated_ShouldMatch() throws EmployeeException {
		EmployeePayrollService employeePayrollService = new EmployeePayrollService();
		employeePayrollService.updateEmployeeSalary("Terisa", 3000000.00);
		boolean result = employeePayrollService.checkEmployeePayrollInSyncWithDB("Terisa");
		assertTrue(result);
	}
	
	@Test
	public void givenStarAndEndDates_ShouldReturnEmployeeJoinedBetweenDates() throws EmployeeException {
		EmployeePayrollService employeePayrollService = new EmployeePayrollService();
		List<EmployeePayrollData> employeePayrollData = employeePayrollService.readEmployeePayrollDataByDate(IOService.DB_IO, "2016-01-01","2020-01-01");
		System.out.println(employeePayrollData);
		assertEquals(2, employeePayrollData.size());
	}
	

	@Test
	public void givenEmployeeDataInDB_ShouldReturnCountOfEmployee() throws EmployeeException {
		EmployeePayrollService employeePayrollService = new EmployeePayrollService();
		Map<String, Integer> map = employeePayrollService.getCountByGender(IOService.DB_IO);
		assertEquals((int) map.get("M"), 2);
		assertEquals((int) map.get("F"), 1);
	}	
	
	@Test
	public void givenEmployeeDataInDB_ShouldReturnEmployeeByGender_ByMinSalary() throws EmployeeException {
		EmployeePayrollService employeePayrollService = new EmployeePayrollService();
		Map<String, Integer> map = employeePayrollService.getLeastSalaryByGender();
		assertEquals((int) map.get("M"), 1000000);
		assertEquals((int) map.get("F"), 3000000);
	}
	
	@Test
	public void givenEmployeeDataInDB_ShouldReturnEmployeeByGender_ByAverageSalary() throws EmployeeException {
		EmployeePayrollService employeePayrollService = new EmployeePayrollService();
		Map<String, Integer> map = employeePayrollService.getAverageSalaryByGender();
		assertEquals((int) map.get("M"), 2000000);
		assertEquals((int) map.get("F"), 3000000);
	}

	@Test
	public void givenNewAddedShouldBeInSyncWithDB() throws EmployeeException {
		EmployeePayrollService employeePayrollService = new EmployeePayrollService();
		employeePayrollService.readEmployeePayrollData(IOService.DB_IO);
		employeePayrollService.addEmployeeToPayroll("Mark","M",5000000,LocalDate.now());
		boolean result = employeePayrollService.checkEmployeePayrollInSyncWithDB("Mark");
		assertTrue(result);
	}

		
	@Test
	public void givenEmployee_whenDeleted_shouldBeRemovedFromEmployeeList() throws EmployeeException {
		EmployeePayrollService employeePayrollService = new EmployeePayrollService();;
		employeePayrollService .readEmployeePayrollData(IOService.DB_IO);
		List<EmployeePayrollData> list = employeePayrollService.deleteEmployee("Mark",false);
		assertEquals(3, list.size());
	}
	
	@Test
	public void given_Employee_WhenAddedToDB_ShouldMatchEmployeeEntries() throws EmployeeException {
		EmployeePayrollData[] arrayOfEmployee = {
				new EmployeePayrollData(0, "Jeff Bezos", "M", 1000000, LocalDate.now()),
				new EmployeePayrollData(0, "Bill Gates", "M", 2000000, LocalDate.now()),
				new EmployeePayrollData(0, "Mark Zukerberg", "M", 3000000, LocalDate.now()),
				new EmployeePayrollData(0, "Sunder Picchai", "M", 6000000, LocalDate.now()),
				new EmployeePayrollData(0, "Mukesh", "M", 1000000, LocalDate.now()),
				new EmployeePayrollData(0, "Anil", "M", 2000000, LocalDate.now()),
		};
		EmployeePayrollService employeePayrollService = new EmployeePayrollService() ;
		employeePayrollService.readEmployeePayrollData(IOService.DB_IO);
		Instant start = Instant.now();
		employeePayrollService.addEmployeesToPayroll(Arrays.asList(arrayOfEmployee));
		Instant end = Instant.now();
		System.out.println("Duration without  thread : " + Duration.between(start, end));
		Instant threadStart = Instant.now();
		employeePayrollService.addEmployeesToPayrollByThreads(Arrays.asList(arrayOfEmployee));
		Instant threadEnd = Instant.now();
		System.out.println("Duration with thread : " + Duration.between(threadStart, threadEnd));
		assertEquals(12, employeePayrollService.countEntries(IOService.DB_IO))   ;
	}
	
	@Test
	public void givenNewSalary_whenUpdated_shouldMatch() throws EmployeeException {
		EmployeePayrollService employeePayrollService = new EmployeePayrollService() ;
		List<EmployeePayrollData> list = employeePayrollService.readEmployeePayrollData(IOService.DB_IO);
		Map<Integer, Double> nameSalaryMap = new HashMap<>();
		nameSalaryMap.put(216, (double) 11000);
//		nameSalaryMap.put(31, (double) 50000);
//		nameSalaryMap.put(77, (double) 20000);
		Instant start = Instant.now();
		employeePayrollService.updateSalary(nameSalaryMap);
		Instant end =Instant.now();
		System.out.println("Duration with thread: "+ Duration.between(start, end));
		boolean result = employeePayrollService.checkEmployeePayrollInSyncWithDB("Mukesh");
		assertTrue(result);
	}
*/	
	@Before
	public void setup() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 3000;
	}
	
	@Test
	public void test1() {
		EmployeePayrollData[] arrayofEmps = getEmployeeList();
		EmployeePayrollService employeePayrollService;
		employeePayrollService = new EmployeePayrollService(Arrays.asList(arrayofEmps));
		long entries = employeePayrollService.countEntries(IOService.REST_IO);
		assertEquals(2, entries);
	}

	private EmployeePayrollData[] getEmployeeList() {
		Response response = RestAssured.get("/employees");
		System.out.println("Entries : \n"+ response.asString());
		EmployeePayrollData[] array = new Gson().fromJson(response.asString(), EmployeePayrollData[].class);
		return array;
	}
	
	@Test
	public void givenNewEmployee_ShouldMatchResponseAndCount() {
		EmployeePayrollData[] arrayofEmps = getEmployeeList();
		EmployeePayrollService employeePayrollService;
		employeePayrollService = new EmployeePayrollService(Arrays.asList(arrayofEmps));
		EmployeePayrollData employeePayrollData = new EmployeePayrollData(0, "Jeff Bezos", "M", 400000, LocalDate.now());
		Response response = addEmployeeToJsonServer(employeePayrollData);
		int statusCode = response.statusCode();
		assertEquals(201, statusCode);
		
		employeePayrollData = new Gson().fromJson(response.asString(), EmployeePayrollData.class);
		employeePayrollService.addEmployeesToPayroll(employeePayrollData,IOService.REST_IO);
		long entries = employeePayrollService.countEntries(IOService.REST_IO);
		assertEquals(3, entries);
		
	}
	
	@Test
	public void updateTest() {
		EmployeePayrollData[] arrayofEmps = getEmployeeList();
		EmployeePayrollService employeePayrollService;
		employeePayrollService = new EmployeePayrollService(Arrays.asList(arrayofEmps));
		
		employeePayrollService.updateEmployeeSalary("Jeff Bezos", 5000000, IOService.REST_IO);
		EmployeePayrollData employeePayrollData = employeePayrollService.getEmployeePayrollData("Jeff Bezos");
		
		String empJson = new Gson().toJson(employeePayrollData);
		RequestSpecification requestSpecification = RestAssured.given();
		requestSpecification.header("Content-Type","application/json");
		requestSpecification.body(empJson);
		Response response = requestSpecification.put("/employees/" + employeePayrollData.id);
		int statusCode = response.statusCode();
		assertEquals(200, statusCode);
	}
	
	@Test
	public void deleteTest() {
		EmployeePayrollData[] arrayofEmps = getEmployeeList();
		EmployeePayrollService employeePayrollService;
		employeePayrollService = new EmployeePayrollService(Arrays.asList(arrayofEmps));
		EmployeePayrollData employeePayrollData = employeePayrollService.getEmployeePayrollData("Jeff Bezos");
		employeePayrollService.deletePayrollData("Jeff Bezos");
		RequestSpecification requestSpecification = RestAssured.given();
		requestSpecification.header("Content-Type","application/json");
		Response response = requestSpecification.delete("/employees/" + employeePayrollData.id);
		int statusCode = response.statusCode();
		assertEquals(200, statusCode);
		long entries = employeePayrollService.countEntries(IOService.REST_IO);
		assertEquals(2, entries);
	}

	private Response addEmployeeToJsonServer(EmployeePayrollData employeePayrollData) {
		String empJson = new Gson().toJson(employeePayrollData);
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type","application/json");
		request.body(empJson);
		return request.post("/employees");
	}
	
}
