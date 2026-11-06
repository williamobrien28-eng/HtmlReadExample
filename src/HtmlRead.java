import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class HtmlRead {

    public static void main(String[] args) {
        HtmlRead html = new HtmlRead();
    }

    public HtmlRead() {

        try {
            System.out.println();
            System.out.print("hello \n");
            URL url = new URL("https://www.espn.com/");

            URLConnection urlc = url.openConnection();
            urlc.setRequestProperty("User-Agent", "Mozilla 5.0 (Windows; U; " + "Windows NT 5.1; en-US; rv:1.8.0.11) ");

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(urlc.getInputStream())
            );
            String line;
            while ( (line = reader.readLine()) != null ) {
                // Finds the first link on the line
                int startIndex = line.indexOf("http");
                // Keeps searching until there are no more links
                while (startIndex != -1) {

                    String newSubstring = line.substring(startIndex);
                    //finds end quotation
                    // Finds the ending double or single quotation mark
                    int doubleQuote = newSubstring.indexOf("\"");
                    int singleQuote = newSubstring.indexOf("'");

                    int endIndex = doubleQuote;

                    // Uses the single quotation mark if it comes first
                    if (endIndex == -1 || (singleQuote != -1 && singleQuote < endIndex)) {
                        endIndex = singleQuote;
                    }
                    if (endIndex != -1) {
                        //extracts just the link
                        String newSubstring2 = newSubstring.substring(0, endIndex);

                        System.out.println(newSubstring2);
                    }
                    startIndex = line.indexOf("http", startIndex + 4);




                }
            }
            reader.close();
        } catch(Exception ex) {
            System.out.println(ex);
        }

    }

}
