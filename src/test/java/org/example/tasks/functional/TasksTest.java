package org.example.tasks.functional;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class TasksTest {

    public WebDriver acessarApp(){
        WebDriver driver = new ChromeDriver();
        //acessar a aplicacao
        driver.navigate().to("http://localhost:8001/tasks");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }

    @Test
    public void deveSalvarTarefaComSucesso(){

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
    public void naoDeveSalvarTarefaSemDescricao(){

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
    public void naoDeveSalvarTarefaSemData(){

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
    public void naoDeveSalvarTarefaComDataPassada(){

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
}
