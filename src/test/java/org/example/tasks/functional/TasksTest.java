package org.example.tasks.functional;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class TasksTest {

    public WebDriver acessarApp() throws MalformedURLException {
//        System.setProperty("webdriver.chrome.driver","C:\\Users\\kleber.santos\\Documents\\devops\\selenium_drivers\\chromedriver.exe");
//        WebDriver driver = new ChromeDriver();
        DesiredCapabilities cap = DesiredCapabilities.chrome();
        WebDriver driver = new RemoteWebDriver(new URL("http://10.120.164.166:4444/wd/hub"), cap);
        //acessar a aplicacao
        driver.navigate().to("http://10.120.164.166:8001/tasks");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }

    @Test
    public void deveSalvarTarefaComSucesso() throws MalformedURLException {

        WebDriver driver = acessarApp();
        try {

            //clicar em add todo
            driver.findElement(By.id("addTodo")).click();

            //escrever a descricao
            driver.findElement(By.id("task")).sendKeys("Teste via selenium2");

            //escrever a data
            driver.findElement(By.id("dueDate")).sendKeys("10/10/2021");

            //clicar em salvar
            driver.findElement(By.id("saveButton")).click();

            //validar mensagem de sucesso
            String mensagem = driver.findElement(By.id("message")).getText();
            Assert.assertEquals("Success!", mensagem);

        } finally {
            //fechar o browser
            driver.quit();
        }

    }

    @Test
    public void naoDeveSalvarTarefaSemDescricao() throws MalformedURLException {

        WebDriver driver = acessarApp();
        try {

            //clicar em add todo
            driver.findElement(By.id("addTodo")).click();

            //escrever a descricao
//            driver.findElement(By.id("task")).sendKeys("Teste via selenium2");

            //escrever a data
            driver.findElement(By.id("dueDate")).sendKeys("10/10/2022");

            //clicar em salvar
            driver.findElement(By.id("saveButton")).click();

            //validar mensagem de sucesso
            String mensagem = driver.findElement(By.id("message")).getText();
            Assert.assertEquals("Fill the task description", mensagem);

        } finally {
            //fechar o browser
            driver.quit();
        }

    }

    @Test
    public void naoDeveSalvarTarefaSemData() throws MalformedURLException {

        WebDriver driver = acessarApp();
        try {

            //clicar em add todo
            driver.findElement(By.id("addTodo")).click();

            //escrever a descricao
            driver.findElement(By.id("task")).sendKeys("Teste via selenium3");

            //escrever a data
//            driver.findElement(By.id("dueDate")).sendKeys("10/10/2022");

            //clicar em salvar
            driver.findElement(By.id("saveButton")).click();

            //validar mensagem de sucesso
            String mensagem = driver.findElement(By.id("message")).getText();
            Assert.assertEquals("Fill the due date", mensagem);

        } finally {
            //fechar o browser
            driver.quit();
        }

    }

    @Test
    public void naoDeveSalvarTarefaComDataPassada() throws MalformedURLException {

        WebDriver driver = acessarApp();
        try {

            //clicar em add todo
            driver.findElement(By.id("addTodo")).click();

            //escrever a descricao
            driver.findElement(By.id("task")).sendKeys("Teste via selenium2");

            //escrever a data
            driver.findElement(By.id("dueDate")).sendKeys("10/10/2020");

            //clicar em salvar
            driver.findElement(By.id("saveButton")).click();

            //validar mensagem de sucesso
            String mensagem = driver.findElement(By.id("message")).getText();
            Assert.assertEquals("Due date must not be in past", mensagem);

        } finally {
            //fechar o browser
            driver.quit();
        }

    }

    @Test
    public void deveRemoverTarefaComSucesso() throws MalformedURLException {

        WebDriver driver = acessarApp();
        //inserir uma tarefa
        try {
            driver.findElement(By.id("addTodo")).click();
            driver.findElement(By.id("task")).sendKeys("Teste para excluir");
            driver.findElement(By.id("dueDate")).sendKeys("17/12/2020");
            driver.findElement(By.id("saveButton")).click();
            String mensagem = driver.findElement(By.id("message")).getText();
            Assert.assertEquals("Success!", mensagem);

            //remover a tarefa
            driver.findElement(By.xpath("//a[@class='btn btn-outline-danger btn-sm']")).click();
            mensagem = driver.findElement(By.id("message")).getText();
            Assert.assertEquals("Success!", mensagem);

        } finally {
            //fechar o browser
            driver.quit();
        }

    }
}
