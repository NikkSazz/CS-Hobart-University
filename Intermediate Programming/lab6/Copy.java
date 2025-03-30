package lab6;

import java.io.*;
import java.net.URL;

public class Copy {

	static void copy(InputStream input, OutputStream output) throws IOException {
		int read;
        while ((read = input.read()) != -1) {
            output.write(read);
        }
	}
	
	public static void main(String[] args) {
		if (args.length < 2) {
			return;
		}
		
		String source = args[0];
		String destination = args[1];
		
		try {
			
			InputStream inpStream;
			if ("-".equals(source)) {
				inpStream = System.in;
	        } 
			else if (source.startsWith("http")) {
	            URL url = new URL(source);
	            inpStream = url.openStream();
	        } else {
	        	inpStream = new FileInputStream(source);
	        }
			
			
			OutputStream outStream;
			if ("-".equals(destination)) {
	            outStream = System.out;
	        } 
			else {
	            outStream = new FileOutputStream(destination);
	        }
			
			copy(inpStream, outStream);
			
		} catch (IOException e) {	}
		
	}

}
