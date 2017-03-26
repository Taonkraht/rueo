package com.example.android.rueo.network;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.json.*;

import java.util.ArrayList;

public class InputParser
{
    //метод принимает хттп страницы rueo со статьей и убирает лишнее
    public static String httpParser (String input)
    {
        //TODO добавить аккуратный padding вокруг текста статьи
        //TODO поправить окраску текста
        String output;
        Document doc = Jsoup.parse(input);
        Element div = doc.select("div.search_result").first();
        div.select("div.kom").remove();
        output = div.outerHtml();
        return output;
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
