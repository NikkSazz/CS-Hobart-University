package lab6;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
// import java.io.InputStream;
// import java.io.OutputStream;

/**
 * @author Nikolai Sazonov, Nikola Stanic
 */
public class Grep {

	private static void grep(String pattern, BufferedReader r, BufferedWriter w) {
		// read text one line at a time -> BufferedReader
		
		try {
			while(true) {
				
				String line = r.readLine();
				
				if(line == null) { break; }
				
				if(line.contains(pattern)) {
					w.write(line);
					w.newLine();
				}
				
				w.flush();	
			} // while true
			System.out.println("Finished grep() void");
			
		} catch ( IOException e ) {
			// catch exception if couldnt readline
		}
		
		
	} // grep() void
	
	
	/**
	 * @param args
	 */
	public static void main ( String[] args ) {
		// TODO Auto-generated method stub
		System.out.println("Grep Main");
		try {
			String pattern = args[0];
			String src = args[1];
			
			switch (src) {
			case "-":
				grep(pattern, 
				     new BufferedReader(new InputStreamReader(System.in)), 
				     new BufferedWriter(new OutputStreamWriter(System.out)));
				break;
			default:
				if(src.startsWith("http")) {
					
					URL url = new URL(src);
					InputStream inptStrm = url.openStream();
					grep(pattern,
					     new BufferedReader(new InputStreamReader(inptStrm)),
					     new BufferedWriter(new OutputStreamWriter(System.out)));
					
					break;
				}
				
				grep(pattern,
				     new BufferedReader(new FileReader(src)),
				     new BufferedWriter(new OutputStreamWriter(System.out)));
				
				break;
			}
			
		} catch ( IOException i ) {
			// throw exception
		} catch ( ArrayIndexOutOfBoundsException i) {
			
		}
		
	}

}
