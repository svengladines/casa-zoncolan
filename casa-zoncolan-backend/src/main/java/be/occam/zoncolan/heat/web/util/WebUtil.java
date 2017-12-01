package be.occam.zoncolan.heat.web.util;

import javax.servlet.http.HttpServletRequest;
import be.occam.zoncolan.heat.domain.people.Actor;

public class WebUtil {
	
	public static Actor actor( HttpServletRequest httpServletRequest ) {
		
		Actor actor
			= new Actor();
		
		actor.setUserID( httpServletRequest.getParameter("x") );
		actor.setPassWord( httpServletRequest.getParameter("y") );
		
		return actor;
		
	}

}
