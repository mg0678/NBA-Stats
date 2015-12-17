
package nba_stats;

import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

/**
 *
 * @author Michael.Guilmette
 */
public class NBA_Stats {
    
    public static void main(String[] args) throws IOException {
        ArrayList<String> urls = new ArrayList<String>();
        ArrayList<String> teamUrls = new ArrayList<String>();
        ArrayList<String> players = new ArrayList<String>();
        
        org.jsoup.nodes.Document doc = Jsoup.connect("http://espn.go.com/nba/teams").get();
        org.jsoup.select.Elements classes = doc.select("a[href].bi");
        
        for (Element e : classes){
            urls.add(e.attr("href"));
        }
        
        for (int i = 0; i < urls.size(); i++) {
            String url = urls.get(i);
            int index = url.lastIndexOf('/');
            teamUrls.add(urls.get(i).substring(0, index));
        }
        
        for (int i = 0; i < teamUrls.size(); i++) {
            String x = teamUrls.get(i);
            x = x.substring(0, 27) + "/roster" + x.substring(27, x.length());
            System.out.println(x);
            
            org.jsoup.nodes.Document plyrs = Jsoup.connect(x).get();
            org.jsoup.select.Elements player_urls = plyrs.select(".sortcell");
            
            for (Element e : player_urls){
                players.add(e.attr("href"));
                System.out.println(e);
            }
        }
        
        System.out.println(players.size());
    }
}