import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class HtmlRead {

    public static void main(String[] args) {
        HtmlRead html = new HtmlRead();
    }

    public HtmlRead() {

        try {
            //asks the user whta website to scan
            Scanner input = new Scanner (System.in);
            System.out.println("Enter Website To Scan: ");
            String website = input.nextLine();

            //opens the website entered
            URL  url = new URL(website);

            URLConnection urlc = url.openConnection();
            urlc.setRequestProperty("User-Agent", "Mozilla 5.0 (Windows; U; " + "Windows NT 5.1; en-US; rv:1.8.0.11) ");

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(urlc.getInputStream())
            );
            String line;
            while ((line = reader.readLine()) != null) {
                // Finds the first link on the line
                int startIndex = line.indexOf("href=");
                // Keeps searching until there are no more links
                while (startIndex != -1) {

                    String newSubstring = line.substring(startIndex);
                    // Finds the ending double or single quotation mark
                    int equalsIndex = newSubstring.indexOf("=");
                    if (equalsIndex != -1) {
                        int doubleQuote = newSubstring.indexOf("\"", equalsIndex + 1);
                        int singleQuote = newSubstring.indexOf("'", equalsIndex + 1);

                        int startQuote = doubleQuote;

                        if (singleQuote != -1 && (startQuote == -1 || singleQuote < startQuote)) {
                            startQuote = singleQuote;
                        }

                        if (startQuote != -1) {
                            char quote = newSubstring.charAt(startQuote);
                            int endIndex = newSubstring.indexOf(quote, startQuote + 1);

                            if (endIndex != -1) {
                                String newSubstring2 = newSubstring.substring(startQuote + 1, endIndex);

                                // Only prints complete links that start with http or https
                                if (newSubstring2.startsWith("https://") || newSubstring2.startsWith("http://")) {
                                    System.out.println(newSubstring2);
                                }
                            }
                        }
                    }
                    startIndex = line.indexOf("href=", startIndex + 5);


                }
            }
                reader.close();
            } catch(Exception ex){
                System.out.println(ex);}


        }

    }

