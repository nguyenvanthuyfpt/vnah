package com.util;


import com.dao.connection.DBConnector;

import com.exp.EException;

import java.io.IOException;
import java.io.OutputStream;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.jdbc.JDBCPieDataset;


public class ChartEventServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Connection dbConnection = null;

    public ChartEventServlet() {
        try {
            dbConnection = DBConnector.getConnection();
        } catch (EException e) {
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
                                                           IOException {
        JDBCPieDataset dataset = new JDBCPieDataset(dbConnection);

        try {
            
            String chartType = (String)request.getAttribute("type");
            System.out.println("chartType " + chartType);
            
            dataset.executeQuery("SELECT b.name ||'-'||a.total , a.total FROM \n" + 
                                "(SELECT e.location_id, count(1) total from kpi_event e GROUP BY location_id) a, dr_area b\n" + 
                                "WHERE a.location_id=b.tinh_id");
            JFreeChart chart = ChartFactory.createPieChart("Chart", // Title
                    dataset, // Data
                    true, // Display the legend
                    true, // Display tool tips
                    false) // No URLs
            ;

            chart.setBorderVisible(true);

            if (chart != null) {
                int width = 600;
                int height = 400;
                response.setContentType("image/jpeg");
                OutputStream out = response.getOutputStream();
                ChartUtilities.writeChartAsJPEG(out, chart, width, height);
                //ChartUtilities.saveChartAsPNG(arg0, arg1, arg2, arg3);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException,
                                                            IOException {
    }
}
