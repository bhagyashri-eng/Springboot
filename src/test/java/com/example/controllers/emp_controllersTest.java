package com.example.controllers;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
@WebMvcTest(emp_controllersTest.class)


public class emp_controllersTest<emp, emp_repos> {
	@Autowired
	private emp_controllersTest empc;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private emp_repos emprepo;
	
	@Test
	void listAllEmp()throws Exception
	{
		List<emp> listEmp=new ArrayList<>();
		Mockito.when(emprepo).findAll()).thenReturn(listEmp);
		listEmp.add(new emp("abcd","pqrs",1));
		listEmp.add(new emp("ad","rtgf",2));
		
		String url="/emps";
		MvcResult mvResult=mockMvc.perform(get(url)).andExcept(status().isok()).andreturn();
		 String actualJsonResponse=mvResult.getResponse().getContentAsString();
		 System.out.println(actualJsonResponse);
		  
		 String expectrdJsonResponse=objectMapper.writeValueAsString(listEmp);
		 assertThat(actualJsonResponse).isEqualToIgnoringWhitespace(expectrdJsonResponse);
	}

}
