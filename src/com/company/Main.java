package com.company;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomCharacterData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

//import org.openqa.selenium.phantomjs.PhantomJSDriver;
//import org.openqa.selenium.phantomjs.PhantomJSDriverService;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;




public class Main {

    public static void main(String[] args) {
        ArrayList stockTickerArray = new ArrayList<String>();
        ArrayList<ArrayList<String>> arrayOfArrayOfStockData = new ArrayList<ArrayList<String>>();
        ArrayList<String> arrayOfStockData = new ArrayList<String>();

        ReadFile myFile = new ReadFile();
        stockTickerArray = myFile.ReadFile("C:/Users/McGuirePC/workspace/StockTickerParser/StockTickerSpreadsheet.xlsx");

        /*
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setJavascriptEnabled(false);
        caps.setBrowserName("htmlunit");
        WebDriver driver = new HtmlUnitDriver(caps);
        */

        RequestData yahooDataArray = new RequestData();
        //arrayOfArrayOfStockData = yahooDataArray.getYahooFinanceData(stockTickerArray, driver);
        arrayOfArrayOfStockData = yahooDataArray.getYahooFinanceData(stockTickerArray);

        InsertDatabase insertDataToDatabase = new InsertDatabase();
        insertDataToDatabase.insertToDatabase(arrayOfArrayOfStockData);

    }
}

