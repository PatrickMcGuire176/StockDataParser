package com.company;

import javax.xml.ws.http.HTTPException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by McGuirePC on 6/20/2017.
 */
public class InsertDatabase {
    //String connectionString = "jdbc:sqlserver://localhost:13175; user=test2; password=test; databaseName=StockData;";
    String connectionString = "jdbc:sqlserver://localhost:3579; user=test2; password=test; databaseName=StockData;";
    Date sysDate = new Date();
    String modifiedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(sysDate);

    public void insertToDatabase(ArrayList<ArrayList<String>> arrayOfArrayOfStockData) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e)
        {
            System.out.println(e);
        }
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(connectionString);
            Statement stmt = connection.createStatement();
            // Insert string for Company Info tbl
            for (ArrayList myArray : arrayOfArrayOfStockData) {
                System.out.println(myArray);
                try {
                    String companyInfoSQL = "use stockdata; IF not exists (select stockSymbol from dbo.tbl_company_info where stockSymbol = '" + myArray.get(0) + "') "
                            + "insert into dbo.tbl_Company_Info(stockName, stockSymbol, sector, industry) "
                            + "VALUES('" + myArray.get(15) + "','" + myArray.get(0) + "','" + myArray.get(16) + "','" + myArray.get(17) + "') "
                            + "ELSE IF EXISTS (select industry from dbo.tbl_company_info where stockSymbol = '" + myArray.get(0) + "' and (industry IS NULL or sector IS NULL)) "
                            + "update dbo.tbl_Company_Info SET sector = '" + myArray.get(16) + "', industry = '" + myArray.get(17) + "' "
                            + "where  stockSymbol = '" + myArray.get(0) + "' "
                            + "IF EXISTS (select industry, sector from dbo.tbl_company_info where stockSymbol = '" + myArray.get(0) + "' and (industry = '' or sector = '')) "
                            + "update dbo.tbl_Company_Info SET sector = '" + myArray.get(16) + "', industry = '" + myArray.get(17) + "' where  stockSymbol = '" + myArray.get(0) + "'";
                    stmt.executeUpdate(companyInfoSQL);
                    System.out.println("Successful profile insert");
                    String keyIndicatorSQL = "use stockdata; Insert Into dbo.tbl_Key_Indicator( companyInfoPK, endOfDay, stockPrice, revenue, sharesOutstanding, "
                            + "trailingPE, forwardPE, pEG, priceBook, ePS, debtToEquity, rOE, rOA, operatingCashFlow, leveredFreeCashFlow, averageVolumeTenDays) Select distinct CompanyInfoPK, "
                            + "'" + modifiedDate + "','" + myArray.get(1) + "','" + myArray.get(2) + "','"
                            + myArray.get(3) + "'," + "'" + myArray.get(4) + "','" + myArray.get(5)
                            + "','" + myArray.get(6) + "','" + myArray.get(7)
                            + "'," + "'" + myArray.get(8) + "','" + myArray.get(9) + "','" + myArray.get(10) + "','"
                            + myArray.get(11) + "','" + myArray.get(12) + "','" + myArray.get(13) + "'," + "'"
                            + myArray.get(14) + "' from dbo.tbl_Company_Info where stockSymbol='"
                            + myArray.get(0) + "'";
                    stmt.executeUpdate(keyIndicatorSQL);
                    System.out.println("Successful Stat insert");
                } catch (HTTPException | NullPointerException e) {
                    System.out.println("Catch 8");
                    System.out.println(e.getMessage());
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Index was out of bounds " + e);
                    System.out.print("The stock symbol was " + myArray.get(0));
                    continue;
                } catch (SQLException err1) {
                    continue;
                }
                // Insert string for key indicator tbl
            }
            stmt.close();

        } catch (SQLException err1) {
            System.out.println("Catch 10");
            System.out.println(err1.getMessage());

        } finally {

        }
    }
}

