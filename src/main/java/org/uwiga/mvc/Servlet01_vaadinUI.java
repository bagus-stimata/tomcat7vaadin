package org.uwiga.mvc;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.spi.DirStateFactory.Result;
import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@Theme("servlet01_vaadin")
public class Servlet01_vaadinUI extends UI {

	TextField teks1;
	TextField teks2;
	
	@WebServlet(value = "/*", asyncSupported = true)
/*	@WebServlet(value = "/Servlet01", asyncSupported = true)*/
	@VaadinServletConfiguration(productionMode = false, ui = Servlet01_vaadinUI.class)
	public static class Servlet extends VaadinServlet {
	}

	@Override
	protected void init(VaadinRequest request) {
		final VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);
		setContent(layout);

		teks1 = new TextField("Nim");
		teks2 = new TextField("Nama");
		
		Button button = new Button("Click Me");
		button.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				String message = "Kosong";
				if (teks1.getValue() != null){
					message = teks1.getValue().toString().trim();
				}	
				layout.addComponent(new Label(message));
				
			}
		});
		
			try {
			   Class.forName("com.mysql.jdbc.Driver").newInstance();
			   
			}
			catch(ClassNotFoundException ex) {
			   System.out.println("Error: unable to load driver class!");			   
			}catch(IllegalAccessException ex) {
			   System.out.println("Error: access problem while loading!");
			}catch(InstantiationException ex) {
			   System.out.println("Error: unable to instantiate driver!");
			}
			Connection conn = null;	
			Statement stat = null;
			try {
				conn = DriverManager.getConnection("jdbc:mysql://tomcat7vaadin-bagusstimata.rhcloud.com:3306/tomcat7vaadin","adminNKNpS2j", "JjRBuGv7pKAP");		 
				stat = conn.createStatement();
				String sql = "SELECT * FROM mhs";
				ResultSet rs = stat.executeQuery(sql);
				while(rs.next()){
					layout.addComponent(new Label(rs.getString(1)));
					layout.addComponent(new Label(rs.getString(2)));
				}
			} catch (SQLException e) {
				System.out.println("Connection Failed! Check output console");
				e.printStackTrace();
				return;
			}	
		
		layout.addComponent(teks1);
		layout.addComponent(teks2);
		layout.addComponent(button);
	}


	
}