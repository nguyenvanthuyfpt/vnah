package com.util;


import com.dao.connection.DBConnector;

import com.exp.EException;

import java.awt.Color;
import java.awt.GradientPaint;

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
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.jdbc.JDBCPieDataset;


public class ChartPersonServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Connection dbConnection = null;

    public ChartPersonServlet() {
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
            
            dataset.executeQuery("select b.name ||'-'||a.total , a.total FROM\n" + 
                  "(select hdr.location_id,count(distinct per.per_id) total from kpi_data_per per inner join kpi_data_hdr hdr on per.data_id=hdr.id \n" + 
                  "where hours=0 group by hdr.location_id) a, dr_area b where a.location_id=b.tinh_id ORDER by a.location_id");
            
            JFreeChart chart = ChartFactory.createPieChart("Chart Person", // Title
                    dataset, // Data
                    true, // Display the legend
                    true, // Display tool tips
                    false) // No URLs
            ;
            
            /*
            // row keys...
            String series1 = "First";
            String series2 = "Second";
            String series3 = "Third";
  
            // column keys...
            String category1 = "Category 1";
            String category2 = "Category 2";
            String category3 = "Category 3";
            String category4 = "Category 4";
            String category5 = "Category 5";
  
            // create the dataset...
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
  
            dataset.addValue(1.0, series1, category1);
            dataset.addValue(4.0, series1, category2);
            dataset.addValue(3.0, series1, category3);
            dataset.addValue(5.0, series1, category4);
            dataset.addValue(5.0, series1, category5);
  
            dataset.addValue(5.0, series2, category1);
            dataset.addValue(7.0, series2, category2);
            dataset.addValue(6.0, series2, category3);
            dataset.addValue(8.0, series2, category4);
            dataset.addValue(4.0, series2, category5);
  
            dataset.addValue(4.0, series3, category1);
            dataset.addValue(3.0, series3, category2);
            dataset.addValue(2.0, series3, category3);
            dataset.addValue(3.0, series3, category4);
            dataset.addValue(6.0, series3, category5);
            
            return dataset;
            */

            chart.setBorderVisible(true);

            if (chart != null) {
                int width = 600;
                int height = 400;
                response.setContentType("image/jpeg");
                OutputStream out = response.getOutputStream();
                ChartUtilities.writeChartAsJPEG(out, chart, width, height);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException,
                                                            IOException {
    }
    
    private static JFreeChart createChart(CategoryDataset dataset) {
          
          // create the chart...
          JFreeChart chart = ChartFactory.createBarChart(
              "Bar Chart Demo",         // chart title
              "Category",               // domain axis label
              "Value",                  // range axis label
              dataset,                  // data
              PlotOrientation.VERTICAL, // orientation
              true,                     // include legend
              true,                     // tooltips?
              false                     // URLs?
          );

          // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...

          // set the background color for the chart...
          chart.setBackgroundPaint(Color.white);

          // get a reference to the plot for further customisation...
          CategoryPlot plot = chart.getCategoryPlot();
          plot.setBackgroundPaint(Color.lightGray);
          plot.setDomainGridlinePaint(Color.white);
          plot.setDomainGridlinesVisible(true);
          plot.setRangeGridlinePaint(Color.white);

          // set the range axis to display integers only...
          final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
          rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

          // disable bar outlines...
          BarRenderer renderer = (BarRenderer) plot.getRenderer();
          renderer.setDrawBarOutline(false);
          
          // set up gradient paints for series...
          GradientPaint gp0 = new GradientPaint(
              0.0f, 0.0f, Color.blue, 
              0.0f, 0.0f, new Color(0, 0, 64)
          );
          GradientPaint gp1 = new GradientPaint(
              0.0f, 0.0f, Color.green, 
              0.0f, 0.0f, new Color(0, 64, 0)
          );
          GradientPaint gp2 = new GradientPaint(
              0.0f, 0.0f, Color.red, 
              0.0f, 0.0f, new Color(64, 0, 0)
          );
          renderer.setSeriesPaint(0, gp0);
          renderer.setSeriesPaint(1, gp1);
          renderer.setSeriesPaint(2, gp2);

          CategoryAxis domainAxis = plot.getDomainAxis();
          domainAxis.setCategoryLabelPositions(
              CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 6.0)
          );
          // OPTIONAL CUSTOMISATION COMPLETED.
          
          return chart;          
      }
}
