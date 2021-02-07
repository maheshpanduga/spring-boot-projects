package com.mahi.controller.tests;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.mahi.controller.AtmController;
import com.mahi.model.Atm;
import com.mahi.service.AtmLocatorService;

@WebMvcTest
@ContextConfiguration(classes = AtmController.class)
public class AtmControllerTest {

	@Autowired
    private MockMvc mvc;
	
	@MockBean
	private AtmLocatorService atmLocatorService;

	@Test
    public void getAllAtms() throws Exception {
		Atm atm = new Atm();
	    List<Atm> allAtms = new ArrayList<Atm>();
	    allAtms.add(atm);

	    when(atmLocatorService.getAllAtms()).thenReturn(allAtms);

	    mvc.perform(get("/atm/"))
	      .andExpect(status().isOk());
    }
	
	@Test
    public void getAtmsByCity() throws Exception {
		
		Atm atm = new Atm();
	    List<Atm> allAtms = new ArrayList<Atm>();
	    allAtms.add(atm);

	    when(atmLocatorService.getAllAtms()).thenReturn(allAtms);
	    
        mvc.perform(get("/atm/city/Zuidbroek"))
      	      .andExpect(status().isOk());
    }
}
