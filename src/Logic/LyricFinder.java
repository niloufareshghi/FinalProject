package Logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class LyricFinder
{
    public static String getURLSource(String Artist,String Title) throws IOException
    {
        Artist=splite(Artist);
        Title=splite(Title);
        System.out.println(Artist);
        URL urlObject = new URL("https://www.azlyrics.com/lyrics/"+splite(Artist)+"/"+splite(Title)+".html");
        URLConnection urlConnection = urlObject.openConnection();
        urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");

        return toString(urlConnection.getInputStream());
    }
    private static String splite(String str){
        String str1 = "";
        String [] tmp = str.split(" ");
        for(int i=0;i<tmp.length;i++){
            System.out.println(tmp[i]);
            str1+=""+(tmp[i]);
        }

        return str1;
    }
    private static String toString(InputStream inputStream) throws IOException
    {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8")))
        {
            String inputLine;
            String lyric;
            int i,j;
            StringBuilder stringBuilder = new StringBuilder();
            while ((inputLine = bufferedReader.readLine()) != null)
            {
                stringBuilder.append(inputLine);
            }

            i=stringBuilder.toString().indexOf("<div>");
            j=stringBuilder.toString().lastIndexOf("<!-- MxM banner -->");
            lyric="<html>"+stringBuilder.toString().substring(i,j)+"</html>";
            return lyric;
        }
    }

}