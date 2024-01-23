package com.example;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class CrptApi {
    private static final Logger logger = LoggerFactory.getLogger(CrptApi.class);
    public void createDoc(){
        try {
            // URL для отправки POST-запроса
            URL url = new URI("https://ismp.crpt.ru/api/v3/lk/documents/create").toURL();

            // Открываем соединение с URL с помощью HttpURLConnection
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Устанавливаем метод запроса POST
            connection.setRequestMethod("POST");

            // Устанавливаем заголовки отправляемых и принимаемых данных запроса
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");

            // Включаем возможность отправки данных в тело запроса
            connection.setDoOutput(true);

            //Создаем JSON-объект с данными из примера
            JSONObject postData = new JSONObject();
            JSONObject description = new JSONObject();
            JSONArray productsArray = new JSONArray();

            // Заполняем данные внутри JSON-объекта
            description.put("participantInn", "string");
            postData.put("description", description);
            postData.put("doc_id", "string");
            postData.put("doc_status", "string");
            postData.put("doc_type", "LP_INTRODUCE_GOODS");
            postData.put("importRequest", true);
            postData.put("owner_inn", "string");
            postData.put("participant_inn", "string");
            postData.put("producer_inn", "string");
            postData.put("production_date", "2020-01-23");
            postData.put("production_type", "string");
            JSONObject product = new JSONObject();
            product.put("certificate_document", "string");
            product.put("certificate_document_date", "2020-01-23");
            product.put("certificate_document_number", "string");
            product.put("owner_inn", "string");
            product.put("producer_inn", "string");
            product.put("production_date", "2020-01-23");
            product.put("tnved_code", "string");
            product.put("uit_code", "string");
            product.put("uitu_code", "string");
            productsArray.put(product);
            postData.put("products", productsArray);
            postData.put("reg_date", "2020-01-23");
            postData.put("reg_number", "string");

            // Получаем поток вывода данных для записи данных в тело запроса
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = postData.toString().getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            // Получаем код ответа от сервера
            int responseCode = connection.getResponseCode();
            logger.info("Код ответа: {}", responseCode);

            // Закрываем соединение
            connection.disconnect();
        }
        catch (Exception e){
            logger.error("Exception occurred", e);
        }
    }
    public static void main(String[] args) {
        CrptApi crptApi = new CrptApi();
        crptApi.createDoc();
    }
}