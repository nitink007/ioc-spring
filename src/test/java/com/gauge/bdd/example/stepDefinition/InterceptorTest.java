package com.gauge.bdd.example.stepDefinition;

import com.gauge.bdd.core.DriverFactory;
import com.thoughtworks.gauge.Step;

import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.filters.RequestFilter;
import net.lightbody.bmp.filters.ResponseFilter;
import net.lightbody.bmp.proxy.CaptureType;
import net.lightbody.bmp.util.HttpMessageContents;
import net.lightbody.bmp.util.HttpMessageInfo;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.DriverManager;

@Component
public class InterceptorTest {

    @Autowired
    DriverFactory driverM;


    @Step("User open <https://reqres.in/> url")
    public void openUrl(String url) throws IOException {
        // start the proxy
        BrowserMobProxy proxy = new BrowserMobProxyServer();
        proxy.start();;
        System.out.println(proxy.isStarted());
        System.out.println(((BrowserMobProxyServer) proxy).isMitmDisabled());
        System.out.println(proxy.getPort());
//        System.out.println(proxy.isStarted());
//        System.out.println(proxy.isStarted());
//        System.out.println(proxy.isStarted());


        proxy.addRequestFilter(new RequestFilter() {
            @Override
            public HttpResponse filterRequest(HttpRequest request, HttpMessageContents contents, HttpMessageInfo messageInfo) {
                if (messageInfo.getOriginalUrl().endsWith("/api/users")) {
                    // retrieve the existing message contents as a String or, for binary contents, as a byte[]
                    String messageContents = contents.getTextContents();
                    System.out.println(messageContents);

                    // do some manipulation of the contents
                    String newContents = messageContents.replaceAll("original-string", "my-modified-string");
                    //[...]

                    // replace the existing content by calling setTextContents() or setBinaryContents()
                    contents.setTextContents(newContents);
                }

                // in the request filter, you can return an HttpResponse object to "short-circuit" the request
                return null;
            }
        });


        // get the Selenium proxy object
        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);

        // configure it as a desired capability
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);

        // start the browser up
//        WebDriver driver = driverM.getDriver(capabilities);
        System.setProperty("webdriver.chrome.driver","Binaries/chromedriver.exe");
        WebDriver driver = new ChromeDriver(capabilities);

        // enable more detailed HAR capture, if desired (see CaptureType for the complete list)
        proxy.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT, CaptureType.RESPONSE_CONTENT);

        // create a new HAR with the label "yahoo.com"
        proxy.newHar(url);

        // open yahoo.com
        driver.get(url);

        // get the HAR data
        Har har = proxy.getHar();

        FileOutputStream file = new FileOutputStream(new File("C:\\Users\\Nitin\\IdeaProjects\\ioc-spring\\dataFiles\\har1.txt"));

        har.writeTo(file);


        // responses are equally as simple:
//        proxy.addResponseFilter(new ResponseFilter() {
//            @Override
//            public void filterResponse(HttpResponse response, HttpMessageContents contents, HttpMessageInfo messageInfo) {
//                if (false) {
//                    contents.setTextContents("This message body will appear in all responses!");
//                }
//            }
//        });

//        driver.quit();

    }


    @Step("User clicks on <List User> link")
    public void clickOnLink(String objectName) {

    }


    @Step("<Response> should be displayed")
    public void verifyText(String objectName) {

    }

    @Step("User sets <proxy>")
    public void setProxy(String proxyUrl) {
        Proxy proxy = new Proxy();
        // The URL here is the URL that the browsermob proxy is using
//        proxy.setHttpProxy("localhost:9100");
        proxy.setProxyAutoconfigUrl("localhost:9100");

//        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
//        capabilities.setCapability(CapabilityType.PROXY, proxy);

        ChromeOptions options = new ChromeOptions();
        options.setProxy(proxy);

        WebDriver driver = driverM.getDriver(options);
        driver.get("http://google.com");

    }
}
