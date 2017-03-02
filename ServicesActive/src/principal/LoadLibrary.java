package principal;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class LoadLibrary {
	
	public void loadSWT(){
		String osName = System.getProperty("os.name").toLowerCase();

	    String osPart = 
	        osName.contains("mac") ? "macOS" :
	        osName.contains("linux")  ? "linux" :
	        null;

	    System.out.println(osPart);
	    if (null == osPart)
	        throw new RuntimeException ("Cannot determine correct swt jar from os.name [" + osName + "]");

	    String swtFileName = "swt_" +osPart+".jar";
	    System.out.println(swtFileName);
	    String workingDir = System.getProperty("user.dir");
	    System.out.println(workingDir);
	    File file = new File(swtFileName);
	    if (!file.exists ())
	        System.out.println("Can't locate SWT Jar " + file.getAbsolutePath());

	    try {
	        URLClassLoader classLoader = (URLClassLoader) getClass().getClassLoader ();
	        Method addUrlMethod = URLClassLoader.class.getDeclaredMethod ("addURL", URL.class);
	        addUrlMethod.setAccessible (true);

	        URL swtFileUrl = file.toURI().toURL();
	        System.out.println("Adding to classpath: " + swtFileUrl);
	        addUrlMethod.invoke (classLoader, swtFileUrl);
	    }
	    catch (Exception e) {
	        throw new RuntimeException ("Unable to add the swt jar to the class path: " + file.getAbsoluteFile (), e);
	    }
	}
}
