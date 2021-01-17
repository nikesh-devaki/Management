package com.ndevaki.employee.management.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.ndevaki.employee.management.controller.EmployeeController;
import com.ndevaki.employee.management.model.Employee.Gender;
import com.ndevaki.employee.management.model.Employee.Status;
import com.ndevaki.employee.management.model.dto.EmployeeDTO;
import com.ndevaki.employee.management.service.EmployeeService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = EmployeeController.class)
class EmployeeControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	EmployeeService employeeService;
	String exampleEmployeeDTO="{\"firstName\":\"Nikesh\",\"lastName\":\"Devaki\",\"emailId\":\"nikesh@gmail.com\",\"age\":25,\"address\":\"Bangalore\",\"gender\":\"MALE\"}";

	@Test
	void testCreate() throws Exception {
		EmployeeDTO employeeDTO=new EmployeeDTO(1l,"Nikesh","Devaki","nikesh@gmail.com",25,"Bangalore",
				Status.ACTIVE, Gender.MALE);
		
		Mockito.when(employeeService.save(Mockito.any(EmployeeDTO.class))).thenReturn(employeeDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/api/v1/employee/")
				.accept(MediaType.APPLICATION_JSON).content(exampleEmployeeDTO)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.CREATED.value(), response.getStatus());

		assertEquals("http://localhost/api/v1/employee/1",
				response.getHeader(HttpHeaders.LOCATION));
	}

	@Test
	void testSearch() throws Exception {
		EmployeeDTO employeeDTO=new EmployeeDTO(1l,"Nikesh","Devaki","nikesh@gmail.com",25,"Bangalore",
				Status.ACTIVE, Gender.MALE);
		Mockito.when(employeeService.search(Mockito.anyLong())).thenReturn(employeeDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/api/v1/employee/1").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		JSONAssert.assertEquals(exampleEmployeeDTO, result.getResponse()
				.getContentAsString(), false);
	}
	
	@Test
	void testDelete() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(
				"/api/v1/employee/1").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(HttpStatus.OK.value(),result.getResponse().getStatus());
	}
	
	@Test
	void testUpdate() throws Exception {
		EmployeeDTO employeeDTO=new EmployeeDTO(1l,"Nikesh","Devaki","nikesh@gmail.com",25,"Bangalore",
				Status.ACTIVE, Gender.MALE);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.patch(
				"/api/v1/employee/1")
				.accept(MediaType.APPLICATION_JSON).content(exampleEmployeeDTO)
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(HttpStatus.OK.value(),result.getResponse().getStatus());
	}

}
