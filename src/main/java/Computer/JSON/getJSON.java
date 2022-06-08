package Computer.JSON;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Scanner;

public class getJSON {
    public static JSONObject getJSONObject(String address) {
        JSONObject obj = null;
        try {
            URL url = new URL(address);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            if (connection.getResponseCode() != 200)
//                throw new RuntimeException("Responsecoded3: " + connection.getResponseCode());
                return null; // will be changed, currently just for testing the pokemon  in the api



            StringBuilder infoString = new StringBuilder();

            Scanner scan = new Scanner(url.openStream());

            while (scan.hasNext()) {
                infoString.append(scan.nextLine());
            }

            scan.close();


            JSONParser parse = new JSONParser();
            obj = (JSONObject) parse.parse(String.valueOf(infoString));
        } catch (Exception e) {
            e.printStackTrace();
        }


//        System.out.println(obj.get("name"));

        return obj;

    }
}
