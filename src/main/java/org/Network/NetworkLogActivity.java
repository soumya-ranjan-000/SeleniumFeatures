package org.Network;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v101.fetch.Fetch;
import org.openqa.selenium.devtools.v101.network.*;
import org.openqa.selenium.devtools.v101.network.model.*;

import java.util.Optional;

public class NetworkLogActivity {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver","E:\\Web Development\\web packages\\chromedriver_v102.exe");
        ChromeDriver driver=new ChromeDriver();
        DevTools devtools=driver.getDevTools();
        devtools.createSession();

        //send command to CDP (chrome dev tools protocol)
        //enable the network
        devtools.send(Network.enable(Optional.empty(),Optional.empty(),Optional.empty()));

        devtools.addListener(Network.requestWillBeSent(),requestWillBeSent -> {
            Request request=requestWillBeSent.getRequest();

            if(request.getUrl().equalsIgnoreCase("https://rahulshettyacademy.com/Library/GetBook.php?AuthorName=shetty")){
                System.out.println("---------------------");
                System.out.println("Request Sent To: "+request.getUrl());
                System.out.println("Request Header: "+request.getHeaders().toJson());
                System.out.println("---------------------");
            }
        });
        final RequestId[] id = new RequestId[1];
        //Event will get fired
        devtools.addListener(Network.responseReceived(),responseReceived -> {
            Response res=responseReceived.getResponse();
            if(res.getUrl().equalsIgnoreCase("https://rahulshettyacademy.com/Library/GetBook.php?AuthorName=shetty"))
            {
                System.out.println("---------------------");
                System.out.println("Response Received For: "+res.getUrl());
                System.out.println("Response Status: "+ res.getStatus());
                System.out.println("---------------------");
                id[0] = responseReceived.getRequestId();
            }
        });

        driver.get("https://rahulshettyacademy.com/angularAppdemo");
        driver.findElement(By.xpath("//button[text()=' Virtual Library ']")).click();
        Thread.sleep(3000);
        driver.findElement(By.linkText("Library")).click();
        Thread.sleep(30000);
        System.out.println(devtools.send(Network.getResponseBody(id[0])).getBody());
        driver.quit();
    }
}
