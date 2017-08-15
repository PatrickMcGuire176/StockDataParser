package com.company;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.UnreachableBrowserException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by McGuirePC on 6/20/2017.
 */
public class RequestData {
    WebDriver driver;

    ArrayList stockTickerArray = new ArrayList<String>();
    ArrayList<ArrayList<String>> arrayOfArrayOfStockData = new ArrayList<ArrayList<String>>();
    ArrayList<String> arrayOfStockData = new ArrayList<String>();

    // Database - Company Info Variables
    String beforeModificationCompanyName = null;
    String companyName = null;
    String stockSymbol = null;
    String sector = null;
    String industry = null;
    // Database - Key Indicator Variables
    String stockPrice;
    String revenue = null;
    String sharesOutstanding = null;
    String trailingPE = null;
    String forwardPE = null;
    String pEG;
    String priceBook;
    String ePS;
    String debtToEquity = null;
    String rOE = null;
    String rOA = null;
    String operatingCashFlow = null;
    String leveredCashFlow = null;
    String averageVolumeTenDays;

    String phantomJSPath = "C:/Users/McGuirePC/Downloads/phantomjs-2.1.1-windows/phantomjs-2.1.1-windows/bin/phantomjs.exe";

    public ArrayList<ArrayList<String>> getYahooFinanceData(ArrayList<String> stockTickerArray, WebDriver driver) {
        this.driver = driver;
        this.stockTickerArray = stockTickerArray;
        int counter = 0;
        for (Object stockTicker : stockTickerArray) {
            counter = 0;
            int randomNum = ThreadLocalRandom.current().nextInt(1, 4 + 1);
            try {
                Thread.sleep(randomNum * 1000);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
            while (counter < 2)
                try {
                    driver.get("https://finance.yahoo.com/quote/" + stockTicker + "/key-statistics?p=" + stockTicker);
                    System.out.println("ATTEMPTING GET 1");

                    stockSymbol = stockTicker.toString();
                    arrayOfStockData.add(stockSymbol);
                    System.out.println(stockSymbol);
                    try {
                        stockPrice = driver.findElement(By.xpath("//*[@id=\"quote-header-info\"]/div[3]/div[1]/div/span[1]")).getText();
                        arrayOfStockData.add(stockPrice);
                        System.out.println(stockPrice);
                    } catch (org.openqa.selenium.NoSuchElementException | StaleElementReferenceException e) {
                        System.out.println("no element found");
                        System.out.println(e);
                        stockPrice = "Not Found";
                        arrayOfStockData.add(stockPrice);
                        // System.out.println(driver.getPageSource());
                    }
                    try {
                        revenue = driver.findElement(By.xpath("//*[@id=\"quote-leaf-comp\"]/div/section/div[2]/div[1]/div[2]/div[4]/table/tbody/tr[1]/td[2]")).getText();
                        arrayOfStockData.add(revenue);
                        System.out.println("Revenue is " + revenue);
                    } catch (org.openqa.selenium.NoSuchElementException | StaleElementReferenceException e) {
                        System.out.println("no element found");
                        System.out.println(e);
                        revenue = "Not Found";
                        arrayOfStockData.add(revenue);
                        //System.out.println(driver.getPageSource());
                    }
                    try {
                        sharesOutstanding = driver.findElement(By.xpath("//*[@id=\"quote-leaf-comp\"]/div/section/div[2]/div[2]/div/div[2]/table/tbody/tr[3]/td[2]")).getText();
                        arrayOfStockData.add(sharesOutstanding);
                        System.out.println(sharesOutstanding);
                    } catch (org.openqa.selenium.NoSuchElementException | StaleElementReferenceException e) {
                        System.out.println("no element found");
                        System.out.println(e);
                        sharesOutstanding = "Not Found";
                        arrayOfStockData.add(sharesOutstanding);
                    }
                    try {
                        trailingPE = driver.findElement(By.xpath("//*[@id=\"quote-leaf-comp\"]/div/section/div[2]/div[1]/div[1]/div/table/tbody/tr[3]/td[2]")).getText();
                        arrayOfStockData.add(trailingPE);
                        System.out.println(trailingPE);
                    } catch (org.openqa.selenium.NoSuchElementException | StaleElementReferenceException e) {
                        System.out.println("no element found");
                        System.out.println(e);
                        trailingPE = "Not Found";
                        arrayOfStockData.add(trailingPE);
                    }
                    try {
                        forwardPE = driver.findElement(By.xpath("//*[@id=\"quote-leaf-comp\"]/div/section/div[2]/div[1]/div[1]/div/table/tbody/tr[4]/td[2]")).getText();
                        arrayOfStockData.add(forwardPE);
                        System.out.println(forwardPE);
                    } catch (org.openqa.selenium.NoSuchElementException | StaleElementReferenceException e) {
                        System.out.println("no element found");
                        System.out.println(e);
                        forwardPE = "Not Found";
                        arrayOfStockData.add(forwardPE);
                    }
                    try {
                        pEG = driver.findElement(By.xpath("//*[@id=\"quote-leaf-comp\"]/div/section/div[2]/div[1]/div[1]/div/table/tbody/tr[5]/td[2]")).getText();
                        arrayOfStockData.add(pEG);
                        System.out.println(pEG);
                    } catch (org.openqa.selenium.NoSuchElementException | StaleElementReferenceException e) {
                        System.out.println("no element found");
                        System.out.println(e);
                        pEG = "Not Found";
                        arrayOfStockData.add(pEG);
                    }
                    try {
                        priceBook = driver.findElement(By.xpath("//*[@id=\"quote-leaf-comp\"]/div/section/div[2]/div[1]/div[1]/div/table/tbody/tr[7]/td[2]")).getText();
                        arrayOfStockData.add(priceBook);
                        System.out.println(priceBook);
                    } catch (org.openqa.selenium.NoSuchElementException | StaleElementReferenceException e) {
                        System.out.println("no element found");
                        System.out.println(e);
                        priceBook = "Not Found";
                        arrayOfStockData.add(priceBook);
                    }
                    try {
                        ePS = driver.findElement(By.xpath("//*[@id=\"quote-leaf-comp\"]/div/section/div[2]/div[1]/div[2]/div[4]/table/tbody/tr[7]/td[2]")).getText();
                        arrayOfStockData.add(ePS);
                        System.out.println(ePS);
                    } catch (org.openqa.selenium.NoSuchElementException | StaleElementReferenceException e) {
                        System.out.println("no element found");
                        System.out.println(e);
                        ePS = "Not Found";
                        arrayOfStockData.add(ePS);
                    }
                    try {
                        debtToEquity = driver.findElement(By.xpath("//*[@id=\"quote-leaf-comp\"]/div/section/div[2]/div[1]/div[2]/div[5]/table/tbody/tr[4]/td[2]")).getText();
                        arrayOfStockData.add(debtToEquity);
                        System.out.println(debtToEquity);
                    } catch (org.openqa.selenium.NoSuchElementException | StaleElementReferenceException e) {
                        System.out.println("no element found");
                        System.out.println(e);
                        debtToEquity = "Not Found";
                        arrayOfStockData.add(debtToEquity);
                    }
                    try {
                        rOE = driver.findElement(By.xpath("//*[@id=\"quote-leaf-comp\"]/div/section/div[2]/div[1]/div[2]/div[3]/table/tbody/tr[2]/td[2]")).getText();
                        arrayOfStockData.add(rOE);
                        System.out.println(rOE);
                    } catch (org.openqa.selenium.NoSuchElementException | StaleElementReferenceException e) {
                        System.out.println("no element found");
                        System.out.println(e);
                        rOE = "Not Found";
                        arrayOfStockData.add(rOE);
                    }
                    try {
                        rOA = driver.findElement(By.xpath("//*[@id=\"quote-leaf-comp\"]/div/section/div[2]/div[1]/div[2]/div[3]/table/tbody/tr[1]/td[2]")).getText();
                        arrayOfStockData.add(rOA);
                        System.out.println(rOA);
                    } catch (org.openqa.selenium.NoSuchElementException | StaleElementReferenceException e) {
                        System.out.println("no element found");
                        System.out.println(e);
                        rOA = "Not Found";
                        arrayOfStockData.add(rOA);
                    }
                    try {
                        operatingCashFlow = driver.findElement(By.xpath("//*[@id=\"quote-leaf-comp\"]/div/section/div[2]/div[1]/div[2]/div[6]/table/tbody/tr[1]/td[2]")).getText();
                        arrayOfStockData.add(operatingCashFlow);
                        System.out.println(operatingCashFlow);
                    } catch (org.openqa.selenium.NoSuchElementException | StaleElementReferenceException e) {
                        System.out.println("no element found");
                        System.out.println(e);
                        operatingCashFlow = "Not Found";
                        arrayOfStockData.add(operatingCashFlow);
                    }
                    try {
                        leveredCashFlow = driver.findElement(By.xpath("//*[@id=\"quote-leaf-comp\"]/div/section/div[2]/div[1]/div[2]/div[6]/table/tbody/tr[2]/td[2]")).getText();
                        arrayOfStockData.add(leveredCashFlow);
                        System.out.println(leveredCashFlow);
                    } catch (org.openqa.selenium.NoSuchElementException | StaleElementReferenceException e) {
                        System.out.println("no element found");
                        System.out.println(e);
                        leveredCashFlow = "Not Found";
                        arrayOfStockData.add(leveredCashFlow);
                    }
                    try {
                        averageVolumeTenDays = driver.findElement(By.xpath("//*[@id=\"quote-leaf-comp\"]/div/section/div[2]/div[2]/div/div[2]/table/tbody/tr[2]/td[2]")).getText();
                        arrayOfStockData.add(averageVolumeTenDays);
                        System.out.println(averageVolumeTenDays);
                    } catch (org.openqa.selenium.NoSuchElementException | StaleElementReferenceException e) {
                        System.out.println("no element found");
                        System.out.println(e);
                        averageVolumeTenDays = "Not Found";
                        arrayOfStockData.add(averageVolumeTenDays);
                    }
                    counter = 2;
                } catch (UnreachableBrowserException e) {
                    System.out.println("CAUGHT IT 1 with counter " + counter);
                    DesiredCapabilities caps = new DesiredCapabilities();
                    caps.setJavascriptEnabled(true);
                    caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, phantomJSPath);
                    driver.close();
                    driver = new PhantomJSDriver(caps);
                    counter++;
                    stockPrice = "Not Found";
                    revenue = "Not Found";
                    sharesOutstanding = "Not Found";
                    trailingPE = "Not Found";
                    forwardPE = "Not Found";
                    pEG = "Not Found";
                    priceBook = "Not Found";
                    ePS = "Not Found";
                    debtToEquity = "Not Found";
                    rOE = "Not Found";
                    rOA = "Not Found";
                    operatingCashFlow = "Not Found";
                    leveredCashFlow = "Not Found";
                    averageVolumeTenDays = "Not Found";
                }
            counter = 0;
            randomNum = ThreadLocalRandom.current().nextInt(1, 4 + 1);
            try {
                Thread.sleep(randomNum * 1000);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
            while (counter < 2)
                try {
                    System.out.println("Attempting get 2");
                    driver.get("https://finance.yahoo.com/quote/" + stockTicker + "/profile?p=" + stockTicker);

                    try {
                        beforeModificationCompanyName = driver.findElement(By.xpath("//*[@id=\"quote-header-info\"]/div[2]/div[1]/div/h1")).getText();
                        String companyName = beforeModificationCompanyName.replaceAll("'", "''");
                        arrayOfStockData.add(companyName);
                        System.out.println(companyName);
                    } catch (org.openqa.selenium.NoSuchElementException | StaleElementReferenceException e) {
                        System.out.println("no element found");
                        System.out.println(e);
                        companyName = "Not Found";
                        arrayOfStockData.add(companyName);
                    }
                    try {
                        sector = driver.findElement(By.xpath("//*[@id=\"quote-leaf-comp\"]/div/div[1]/div/div/p[2]/strong[1]")).getText();
                        arrayOfStockData.add(sector);
                        System.out.println(sector);
                    } catch (org.openqa.selenium.NoSuchElementException | StaleElementReferenceException e) {
                        System.out.println("no element found");
                        System.out.println(e);
                        sector = "Not Found";
                        arrayOfStockData.add(sector);
                    }
                    try {
                        industry = driver.findElement(By.xpath("//*[@id=\"quote-leaf-comp\"]/div/div[1]/div/div/p[2]/strong[2]")).getText();
                        arrayOfStockData.add(industry);
                        System.out.println(industry);
                    } catch (org.openqa.selenium.NoSuchElementException | StaleElementReferenceException e) {
                        System.out.println("no element found");
                        System.out.println(e);
                        industry = "Not Found";
                        arrayOfStockData.add(industry);
                    }
                    counter = 2;
                    throw new UnreachableBrowserException("test");
                } catch (UnreachableBrowserException e) {
                    System.out.println("CAUGHT IT 2 with counter " + counter);
                    DesiredCapabilities caps = new DesiredCapabilities();
                    caps.setJavascriptEnabled(true);
                    caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, phantomJSPath);
                    driver.quit();
                    driver = new PhantomJSDriver(caps);
                    companyName = "Not Found";
                    sector = "Not Found";
                    industry = "Not Found";
                    counter++;
                }
            arrayOfArrayOfStockData.add(new ArrayList(arrayOfStockData));
            arrayOfStockData.clear();
            driver.quit();
        }
        return arrayOfArrayOfStockData;
    }
}




