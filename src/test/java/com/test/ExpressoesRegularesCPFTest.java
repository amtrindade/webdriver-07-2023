package com.test;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.core.BaseTest;
import com.page.CpfPage;

public class ExpressoesRegularesCPFTest extends BaseTest {
	
	private CpfPage cpfPage;
	
	@Before
	public void setUp() {
		cpfPage = new CpfPage();		
		cpfPage.open();
	}
	
	@Test
	public void testCpfComPontuacao() {		
		cpfPage.clickCbPontuacao();
		cpfPage.clickButtonGerar();
		String cpfGerado = cpfPage.getTextCpf();	
		
		System.out.println(cpfGerado);
		
		assertTrue(cpfGerado.matches("^[0-9]{3}\\.[0-9]{3}\\.[0-9]{3}-[0-9]{2}$"));		
	}
	
	@Test
	public void testCpfSemPontuacao() {
		cpfPage.clickButtonGerar();
		String cpfGerado = cpfPage.getTextCpf();
		
		System.out.println(cpfGerado);
		
		assertTrue(cpfGerado.matches("^[0-9]{11}$"));
	}
}
