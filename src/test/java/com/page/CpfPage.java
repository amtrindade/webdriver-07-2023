package com.page;

import static com.core.DriverFactory.getDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CpfPage {
	
	public CpfPage open() {
		getDriver().get("https://www.geradordecpf.org");
		return this;
	}
	
	public CpfPage clickCbPontuacao() {
		WebElement cbPontos = getDriver().findElement(By.id("cbPontos"));
		cbPontos.click();
		return this;
	}
	
	public CpfPage clickButtonGerar() {
		WebElement btnGerar = getDriver().findElement(By.id("btn-gerar-cpf"));
		btnGerar.click();
		return this;
	}
	
	public String getTextCpf() {
		WebElement tfCpf = getDriver().findElement(By.id("numero"));
		return tfCpf.getAttribute("value");
	}

}
