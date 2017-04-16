package com.simplewebservice;

import com.google.inject.servlet.ServletModule;

public class MyServletModule extends ServletModule{
	@Override
	protected void configureServlets(){

		serve("/guice").with(MyServlet.class);
	}


}
