package org.echoed.methods;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class GrandExchange {
    public static GEItem getGEItemByID(int itemID) {

        try {
            GEItem item = new GEItem();
            URL url = new URL("http://services.runescape.com/m=itemdb_rs/api/catalogue/detail.json?item=" + itemID);
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));

            String line;
            String rawItemDetail = "";
            while((line = br.readLine()) != null) rawItemDetail += line;

            br.close();

            item.setID(itemID);
            item.setPrice(GEItem.priceToInt(rawItemDetail.replaceFirst(".+\"current\":.+?\"price\":\"?(.+?)\"?}.+", "$1")));
            item.setType(rawItemDetail.replaceFirst(".+\"type\":\"(.+?)\".+", "$1"));
            item.setName(rawItemDetail.replaceFirst(".+\"name\":\"(.+?)\".+", "$1"));
            item.setDescription(rawItemDetail.replaceFirst(".+\"description\":\"(.+?)\".+", "$1"));
            item.setImageSmall(rawItemDetail.replaceFirst(".+\"icon\":\"(.+?)\".+", "$1"));
            item.setImageBig(rawItemDetail.replaceFirst(".+\"icon_large\":\"(.+?)\".+", "$1"));
            item.setMembersOnly(rawItemDetail.replaceFirst(".+\"members\":\"(.+?)\".+", "$1").equals("true"));

            return item;
        } catch(Exception e) {
            System.out.println("Error while retrieving item details from Grand Exchange: " + e.getMessage());
        }

        return null;
    }
    public static GEItem getGEItemByName(String itemName) {
        try {
            URL url = new URL("http://services.runescape.com/m=itemdb_rs/results.ws");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            DataOutputStream out = new DataOutputStream(conn.getOutputStream());

            out.writeBytes("query=" + URLEncoder.encode(itemName, "UTF-8"));
            out.flush();
            out.close();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String saveFrom = "<table class=\"results\">";
            String saveTill = "</td>";

            String line;
            String content = "";

            boolean save = false;
            while((line = in.readLine()) != null) {
                if(line.contains(saveFrom))
                    save = true;

                if(save)
                    content += line;

                if(save && line.contains(saveTill))
                    break;
            }
            in.close();

            try {
                Integer.parseInt(content.toLowerCase().replaceFirst(".+obj=(.+?)\">" + itemName.toLowerCase() + "</a>.+", "$1"));
            } catch (Exception e) {
                throw new Exception("Item \""+ itemName +"\" was not found.");
            }

            return GrandExchange.getGEItemByID(Integer.parseInt(content.toLowerCase().replaceFirst(".+obj=(.+?)\">" + itemName.toLowerCase() + "</a>.+", "$1")));
        } catch(Exception e) {
            System.out.println("Error while retrieving an item from the Grand Exchange: " + e.getMessage());
        }
        return null;
    }
}