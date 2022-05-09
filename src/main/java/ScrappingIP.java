import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;

public class ScrappingIP {
    public ScrappingIP() {
        String url = "https://www.nirsoft.net/countryip/";

        // Names of countries
        List<String> countriesName =  new ArrayList<>();
        // url of countries
        List<String> urlWebCountriesList = new ArrayList<>();

        try {
            Document ipWeb = Jsoup.connect(url).get();

            ArrayList<Element> aElements = ipWeb.body().select("a");

            for (int i = 28; i < aElements.size(); i++){
                countriesName.add(aElements.get(i).text());
                String urlWebCountry = aElements.get(i).attr("href");
                urlWebCountriesList.add(urlWebCountry);
                //System.out.println(url + urlWebCountry);
            }

            for (String currentUrl : urlWebCountriesList){
                Document countryWeb = Jsoup.connect(url + currentUrl).get();
                Elements tdElements = countryWeb.select("tr");
                System.out.println(tdElements);
                break;

            }


            /*System.out.println(citiesName);*/

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /*public boolean isInRange(String inputIp, String startIpRange, String endIpRange){
        boolean inRange  = false;

    }*/

}
