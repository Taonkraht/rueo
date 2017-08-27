package com.rueo.android.rueo.network;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.json.*;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class InputParser
{
    //метод принимает хттп страницы rueo со статьей и убирает лишнее
    public static String httpParser (String input)
    {
        String output;
        Document doc = Jsoup.parse(input);
        Element div = doc.select("div.search_result").first();
        div.select("div.kom").remove();
        Elements hrefs = div.select("a[href]");
        hrefs.unwrap();
        output = div.outerHtml();

        //Log.d("sout", output);

        return output;
    }

    public static Boolean pageExists (String input)
    {
        if (input.contains("Подходящей словарной статьи не найдено"))
            return false;
        return true;
    }

    //метод парсит JSON-ответ на AJAX запрос страницы при вводе текста
    public static ArrayList<String> ajaxParser (String input)
    {
        JSONArray ajaxAnswer = null;
        ArrayList<String> answer = new ArrayList<>();
        try {ajaxAnswer = new JSONArray(input);}catch (JSONException e){}
        for (int i = 0; i < ajaxAnswer.length(); i++)
        {
            try {answer.add(ajaxAnswer.getJSONObject(i).getString("value"));}
            catch (JSONException e){}
        }
        return answer;
    }
}
