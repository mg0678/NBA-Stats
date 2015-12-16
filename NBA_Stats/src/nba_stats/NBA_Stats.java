
package nba_stats;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.lang.model.util.Elements;
import javax.swing.text.Document;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

/**
 *
 * @author Michael.Guilmette
 */
public class NBA_Stats {
    
    public static void main(String[] args) throws IOException {
        ArrayList<String> urls = new ArrayList<String>();
        org.jsoup.nodes.Document doc = Jsoup.connect("http://espn.go.com/nba/teams").get();
        org.jsoup.select.Elements newsHeadlines = doc.select("bi");
        org.jsoup.select.Elements classes = doc.select("a[href].bi");
        System.out.println(classes.size());
        
        for (Element e : classes){
            urls.add(e.attr("href"));
        }
        
        for (int i = 0; i < urls.size(); i++) {
            System.out.println(urls.get(i));
        }
    }    
}
