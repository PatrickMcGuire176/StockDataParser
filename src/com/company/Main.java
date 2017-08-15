package com.company;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;


public class Main {

    public static void main(String[] args) {
        ArrayList stockTickerArray = new ArrayList<String>();
        ArrayList<ArrayList<String>> arrayOfArrayOfStockData = new ArrayList<ArrayList<String>>();
        ArrayList<String> arrayOfStockData = new ArrayList<String>();

        ReadFile myFile = new ReadFile();
        stockTickerArray = myFile.ReadFile("C:/Users/McGuirePC/workspace/StockTickerParser/StockTickerSpreadsheet.xlsx");

        Random myRandomizer = new Random();
        String[] browserList = {"chrome", "firefox", "internet explorer"};
        String phantomJSPath = "C:/Users/McGuirePC/Downloads/phantomjs-2.1.1-windows/phantomjs-2.1.1-windows/bin/phantomjs.exe";
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setJavascriptEnabled(false);
        caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, phantomJSPath);
        caps.setBrowserName(browserList[myRandomizer.nextInt(browserList.length)]);
        WebDriver driver = new PhantomJSDriver(caps);

        RequestData yahooDataArray = new RequestData();
        arrayOfArrayOfStockData = yahooDataArray.getYahooFinanceData(stockTickerArray, driver);


        InsertDatabase insertDataToDatabase = new InsertDatabase();
        insertDataToDatabase.insertToDatabase(arrayOfArrayOfStockData);

    }
}

