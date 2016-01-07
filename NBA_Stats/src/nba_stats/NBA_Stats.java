
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
    public static int ordinalIndexOf(String str, char c, int n) {
    int pos = str.indexOf(c, 0);
    while (n-- > 0 && pos != -1)
        pos = str.indexOf(c, pos+1);
    return pos;
    }
    public static void main(String[] args) throws IOException {
        ArrayList<String> urls = new ArrayList<String>();
        ArrayList<String> teamUrls = new ArrayList<String>();
        ArrayList<String> players = new ArrayList<String>();
        ArrayList<String> stats = new ArrayList<String>();
        
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
            
            org.jsoup.nodes.Document plyrs = Jsoup.connect(x).get();
            org.jsoup.select.Elements player_urls = plyrs.select(".sortcell");
            
            for (Element e : player_urls){
                String g = e.toString();
                int beginIndex = ordinalIndexOf(g, '"' , 2);
                int endIndex = ordinalIndexOf(g, '"' , 3);                
                players.add(g.substring(beginIndex+1,endIndex));
            }            
        }
        
        org.jsoup.nodes.Document stat = Jsoup.connect(players.get(1)).get();
        //org.jsoup.select.Elements player_stats = stat.select(".mod-content td");
        //org.jsoup.select.Elements player_stats = stat.select("[class^=oddrow team], [class^=evenrow team]");
        org.jsoup.select.Elements player_stats = stat.select("div.mod-content [class=oddrow]:eq(1)");
        for (Element e : player_stats){
            String g = e.toString();
            stats.add(g);
            System.out.println(g);
        }
        System.out.println(players.get(1));
    }
}