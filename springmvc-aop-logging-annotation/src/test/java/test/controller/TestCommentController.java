package test.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.servlet.ServletContext;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.rmpksoft.aop.config.ServiceConfig;
import com.rmpksoft.aop.config.WebMvcConfig;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = { WebMvcConfig.class, ServiceConfig.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestCommentController {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@BeforeAll
	void initAll() {
		System.out.println("Inside initAll.........");
		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
		this.mockMvc = builder.build();
	}

	@Test
	@DisplayName("Initializing testInit")
	void testInit() {
		System.out.println("Inside testInit.........");
		assertNotNull(wac);
		assertNotNull(mockMvc);
		
		ServletContext servletContext = wac.getServletContext();
		assertNotNull(servletContext);
		assertTrue(servletContext instanceof MockServletContext);
	}
	

	@Test
    public void testHomeController() throws Exception {
        ResultMatcher ok = MockMvcResultMatchers.status().isOk();
        ResultMatcher view = MockMvcResultMatchers.view().name("test-page");
        ResultMatcher msg = MockMvcResultMatchers.model()
                            .attribute("msg", "Spring test start!!");

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/test");
        this.mockMvc.perform(builder)
               .andExpect(ok)
               .andExpect(msg);
    }
	
	@Test
    public void testHomeController_list() throws Exception {
        ResultMatcher ok = MockMvcResultMatchers.status().isOk();

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/comments");
        
        ResultActions rs = this.mockMvc.perform(builder)
               .andExpect(ok);
        
        MvcResult mvc = rs.andReturn();
        
        System.out.println("result json -> " + mvc.getResponse().getContentAsString());
        
    }
	
	
/*	@Test
	public void givenHomePageURI_whenMockMVC_thenReturnsIndexJSPViewName() {
		
		ResultActions resultActions = mockMvc.perform(get("/test" )
						.param( "sampleParam1", "sampleValue1" )
						.param( "sampleParam2", "sampleValue2" )
						.accept( MediaType.TEXT_PLAIN ) );
		
	    this.mockMvc.perform(get("/homePage")).andDo(print())
	      .andExpect(view().name("index"));
	}*/
	/*
	@Test
	public void givenGreetURI_whenMockMVC_thenVerifyResponse() {
	    MvcResult mvcResult = this.mockMvc.perform(get("/greet"))
	      .andDo(print()).andExpect(status().isOk())
	      .andExpect(jsonPath("$.message").value("Hello World!!!"))
	      .andReturn();
	     
	    Assert.assertEquals("application/json;charset=UTF-8", 
	      mvcResult.getResponse().getContentType());
	}
	
	@Test
	public void givenGreetURIWithPathVariable_whenMockMVC_thenResponseOK() {
	    this.mockMvc
	      .perform(get("/greetWithPathVariable/{name}", "John"))
	      .andDo(print()).andExpect(status().isOk())
	       
	      .andExpect(content().contentType("application/json;charset=UTF-8"))
	      .andExpect(jsonPath("$.message").value("Hello World John!!!"));
	}
	
	
	@Test
	public void givenGreetURIWithQueryParameter_whenMockMVC_thenResponseOK() {
	    this.mockMvc.perform(get("/greetWithQueryVariable")
	      .param("name", "John Doe")).andDo(print()).andExpect(status().isOk())
	      .andExpect(content().contentType("application/json;charset=UTF-8"))
	      .andExpect(jsonPath("$.message").value("Hello World John Doe!!!"));
	}
	
	@Test
	public void givenGreetURIWithPost_whenMockMVC_thenVerifyResponse() {
	    this.mockMvc.perform(post("/greetWithPost")).andDo(print())
	      .andExpect(status().isOk()).andExpect(content()
	      .contentType("application/json;charset=UTF-8"))
	      .andExpect(jsonPath("$.message").value("Hello World!!!"));
	}
	
	@Test
	public void givenGreetURIWithPostAndFormData_whenMockMVC_thenResponseOK() {
	    this.mockMvc.perform(post("/greetWithPostAndFormData").param("id", "1")
	      .param("name", "John Doe")).andDo(print()).andExpect(status().isOk())
	       
	      .andExpect(content().contentType("application/json;charset=UTF-8"))
	      .andExpect(jsonPath("$.message").value("Hello World John Doe!!!"))
	      .andExpect(jsonPath("$.id").value(1));
	}*/

	@AfterAll
	void tearDownAll() {
		System.out.println("Inside tearDownAll.........");
	}

}
