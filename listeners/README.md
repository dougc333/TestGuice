A listener in java is a class which "listens" for an event or waits
for a specific event to do something. 

Listeners are used in ServletContext, HttpSession, and ServletRequest and the 
designer is responsible for implementing interfaces for these: 
javax.servlet.ServletContextListener, javax.servlet.http.HttpSessionListener, 
javax.servlet.ServletRequestListener

Listeners are 

Typelisteners: run when Guice injects type/class. Once per type.

MembersInnectors/InjectionListeners: runs when Guice injects instance. 
