import java.util.*;

/**
 * @author Nikolai Sazonov, Nikola Stanic
 */
public class Gray {
	
	public static void main(String[] args) {

		System.out.print("Please enter a length of the Gray code: ");
		Scanner scanner = new Scanner(System.in);
		int length = scanner.nextInt();

		ArrayList<String> gray = gray(length);
		for(String s : gray) {
			System.out.println(s);
		}
		
	}
	
	public static ArrayList<String> gray(int n) {
		
		if(n < 1) {
			return null;
		}
		
		if(n == 1) {
			ArrayList<String> base = new ArrayList<String>();
			base.add("0");
			base.add("1");
			return base;
		}
		
		ArrayList<String> g = gray(n - 1);
		
		ArrayList<String> ans = new ArrayList<>();
		
		for(String s : g) {
			ans.add("0" + s);
		}
		
		for(int i = g.size() - 1; i <= 0; i--) {
			ans.add("1" + g.get(i));
		}
		
		return ans;
	}
	
}
