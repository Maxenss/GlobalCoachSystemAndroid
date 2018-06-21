package com.easylabs.globalcoachsystemandroid.server;

import com.easylabs.globalcoachsystemandroid.data.SessionData;
import com.easylabs.globalcoachsystemandroid.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.SeekableByteChannel;

/**
 * Created by Maxim on 10.04.2018.
 */

public class ApiWorker {
    static String protocol = "http://";
    //static String rootUrl = "wishlist-001-site1.itempurl.com/";
   // static String rootUrl = "192.168.0.104:43653/";
    static String rootUrl = "maxens-002-site3.ctempurl.com/";

    public enum SignUpStatus {
        DATA_ERROR(0), NOT_CONNECt(1), CREATE_ACCOUNT(2), LOGIN_EXIST_ERROR(3), UNKNOWN_ERROR(4);

        private int val;

        private SignUpStatus(int value) {
            val = value;
        }

        public int getValue() {
            return val;
        }
    }

    public enum SignInStatus {
        DATA_ERROR(0), NOT_CONNECt(1), LOGIN_ACCOUNT(2), UNKNOWN_ERROR(3);

        private int val;

        private SignInStatus(int value) {
            val = value;
        }

        public int getValue() {
            return val;
        }
    }

    public enum NewUserSpecializationStatus {
        Correct(0), ALREADY_EXIST(1), UNKNOWN_ERROR(2);

        private int val;

        private NewUserSpecializationStatus(int value) {
            val = value;
        }

        public int getValue() {
            return val;
        }
    }

    public enum NewCouchSpecializationStatus {
        Correct(0), ALREADY_EXIST(1), UNKNOWN_ERROR(2);

        private int val;

        private NewCouchSpecializationStatus(int value) {
            val = value;
        }

        public int getValue() {
            return val;
        }
    }

    // Метод для логина
    public static SignInStatus signIn(String login, String password) {
        // Если логин null, или равен пустой строке
        if (login == null || login.length() == 0) {
            return SignInStatus.DATA_ERROR;
        }

        if (password == null || password.length() == 0) {
            return SignInStatus.DATA_ERROR;
        }

        // Адрес по которому мы шлем запрос
        String myURL = protocol + rootUrl + "api/Account/login/";
        // Данные которые мы шлем в теле запроса
        String params = "login=" + login
                + "&" + "password=" + password;

        // Массив байтов, ответ от сервера
        byte[] data = null;
        // Входящий поток данных от сервера
        InputStream is = null;
        // Флаг, указываюший на то, корректно прошел запрос или нет
        boolean isCorrect = true;

        try {
            // Создаем объект класса URL
            URL url = new URL(myURL);
            // На url открываем соединение, соеденение с сервером
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // Указываем тип запроса
            conn.setRequestMethod("POST");
            // Указываем, что в запросе, будут исходящие данные
            conn.setDoOutput(true);
            // Указываем, что мы ожидаем получить ответ от сервера
            conn.setDoInput(true);
            // Указываем заголовки запроса
            // Указываем сколько символова мы отправляем, в байтах
            conn.setRequestProperty("Content-Length", Integer.toString(params.getBytes().length));
            // Получаем поток исходящих от клиента данных
            OutputStream os = conn.getOutputStream();
            // Преобразовываем строку с данными, в массив байтов.
            // При чем, для кодирование в массив байтов, используем кодировку UTF_8
            data = params.getBytes("UTF-8");
            // В исходящий поток, записываем данные с паролем и логином, в виде байтов
            os.write(data);
            // Обнуляем массив data
            data = null;
            // ОТправлеям запрос
            conn.connect();
            // Получаем код ответа от сервера
            int responseCode = conn.getResponseCode();
            // Создаем поток исходящий данные
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            // Получяем данные от сервера, сохраняем их в потоке
            is = conn.getInputStream();
            // Создаём буффер, для записи данных из потока, в буффер
            byte[] buffer = new byte[8192]; // Такого вот размера буфер
            // Далее, например, вот так читаем ответ
            int bytesRead;
            // Читаем данные из потока входящих от сервера данных
            // и записываем их в другой поток
            while ((bytesRead = is.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesRead);
            }
            // Поток, с данными, преобразовываем к массиву байтов
            data = baos.toByteArray();
            // Ответ от сервера, перегоняем из байтов в строку
            String response = new String(data);
            // Из ответа от сервер формируем json-объект
            JSONObject jsonObject = new JSONObject(response);
            System.out.println("Response: " + response);

            // Парсим данные
            SessionData.token = jsonObject.getString("token");
            SessionData.currentUser = JsonWorker.getUserFromJson(jsonObject, "user");
            System.out.println("User Login: " + SessionData.currentUser.getLogin());

            return SignInStatus.LOGIN_ACCOUNT;

        } catch (Exception e) {
            e.printStackTrace();
            return SignInStatus.NOT_CONNECt;
        }
    }

    // Метод для логина
    public static SignUpStatus signUp(String login
            , String password
            , String firstName
            , String lastName
            , int age) {

        // Если логин null, или равен пустой строке
        if (login == null || login.length() == 0) {
            return SignUpStatus.DATA_ERROR;
        }

        if (password == null || password.length() == 0) {
            return SignUpStatus.DATA_ERROR;
        }

        if (firstName == null || firstName.length() == 0) {
            return SignUpStatus.DATA_ERROR;
        }

        if (lastName == null || lastName.length() == 0) {
            return SignUpStatus.DATA_ERROR;
        }

        if (age == 0) {
            return SignUpStatus.DATA_ERROR;
        }

        // Адрес по которому мы шлем запрос
        String myURL = protocol + rootUrl + "api/Account/";
        // Данные которые мы шлем в теле запроса
        String params = "login=" + login
                + "&" + "password=" + password
                + "&" + "firstName=" + firstName
                + "&" + "lastName=" + lastName
                + "&" + "age=" + age;

        // Массив байтов, ответ от сервера
        byte[] data = null;
        // Входящий поток данных от сервера
        InputStream is = null;
        // Флаг, указываюший на то, корректно прошел запрос или нет
        boolean isCorrect = true;

        System.out.println("В signup");

        try {
            // Создаем объект класса URL
            URL url = new URL(myURL);
            // На url открываем соединение, соеденение с сервером
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // Указываем тип запроса
            conn.setRequestMethod("POST");
            // Указываем, что в запросе, будут исходящие данные
            conn.setDoOutput(true);
            // Указываем, что мы ожидаем получить ответ от сервера
            conn.setDoInput(true);
            // Указываем заголовки запроса
            // Указываем сколько символова мы отправляем, в байтах
            conn.setRequestProperty("Content-Length", Integer.toString(params.getBytes().length));
            // Получаем поток исходящих от клиента данных
            OutputStream os = conn.getOutputStream();
            // Преобразовываем строку с данными, в массив байтов.
            // При чем, для кодирование в массив байтов, используем кодировку UTF_8
            data = params.getBytes("UTF-8");
            // В исходящий поток, записываем данные с паролем и логином, в виде байтов
            os.write(data);
            // Обнуляем массив data
            data = null;
            // ОТправлеям запрос
            conn.connect();
            // Получаем код ответа от сервера
            int responseCode = conn.getResponseCode();
            // Создаем поток исходящий данные
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            // Получяем данные от сервера, сохраняем их в потоке
            is = conn.getInputStream();
            // Создаём буффер, для записи данных из потока, в буффер
            byte[] buffer = new byte[8192]; // Такого вот размера буфер
            // Далее, например, вот так читаем ответ
            int bytesRead;
            // Читаем данные из потока входящих от сервера данных
            // и записываем их в другой поток
            while ((bytesRead = is.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesRead);
            }
            // Поток, с данными, преобразовываем к массиву байтов
            data = baos.toByteArray();
            // Ответ от сервера, перегоняем из байтов в строку
            String response = new String(data);

            System.out.println("Resp SignUp: " + response);

            // Статус, создания аккаунта
            int singUpStatus = Integer.parseInt(response);

            if (singUpStatus == SignUpStatus.CREATE_ACCOUNT.getValue()) {
                return SignUpStatus.CREATE_ACCOUNT;
            } else if (singUpStatus == SignUpStatus.LOGIN_EXIST_ERROR.getValue()) {
                return SignUpStatus.LOGIN_EXIST_ERROR;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return SignUpStatus.NOT_CONNECt;
        }

        return SignUpStatus.UNKNOWN_ERROR;
    }

    public static boolean getCategories() {
        // Адрес по которому мы шлем запрос
        String myURL = protocol + rootUrl + "api/Categories";
        // Массив байтов, ответ от сервера
        byte[] data = null;
        // Входящий поток данных от сервера
        InputStream is = null;
        // Флаг, указываюший на то, корректно прошел запрос или нет
        boolean isCorrect = true;

        try {
            // Создаем объект класса URL
            URL url = new URL(myURL);
            // На url открываем соединение, соеденение с сервером
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // Указываем тип запроса
            conn.setRequestMethod("GET");
            // Указываем, что мы ожидаем получить ответ от сервера
            conn.setDoInput(true);
            // Указываем заголовки запроса
            conn.setRequestProperty("Authorization", "Bearer " + SessionData.token);
            // Обнуляем массив data
            data = null;
            // ОТправлеям запрос
            conn.connect();
            // Получаем код ответа от сервера
            int responseCode = conn.getResponseCode();
            // Создаем поток исходящий данные
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            // Получяем данные от сервера, сохраняем их в потоке
            is = conn.getInputStream();
            // Создаём буффер, для записи данных из потока, в буффер
            byte[] buffer = new byte[8192]; // Такого вот размера буфер
            // Далее, например, вот так читаем ответ
            int bytesRead;
            // Читаем данные из потока входящих от сервера данных
            // и записываем их в другой поток
            while ((bytesRead = is.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesRead);
            }
            // Поток, с данными, преобразовываем к массиву байтов
            data = baos.toByteArray();
            // Ответ от сервера, перегоняем из байтов в строку
            String response = new String(data);
            System.out.println("getCategories Response: " + response);
            // Из ответа от сервер формируем json-объект
            JSONObject jsonRoot = new JSONObject(response);

            // Запоминаем категории
            SessionData.categories = JsonWorker.getCategoriesFromJson(jsonRoot);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean getSpecializations() {
        // Адрес по которому мы шлем запрос
        String myURL = protocol + rootUrl + "api/Specializations";
        // Массив байтов, ответ от сервера
        byte[] data = null;
        // Входящий поток данных от сервера
        InputStream is = null;
        // Флаг, указываюший на то, корректно прошел запрос или нет
        boolean isCorrect = true;

        try {
            // Создаем объект класса URL
            URL url = new URL(myURL);
            // На url открываем соединение, соеденение с сервером
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // Указываем тип запроса
            conn.setRequestMethod("GET");
            // Указываем, что мы ожидаем получить ответ от сервера
            conn.setDoInput(true);
            // Указываем заголовки запроса
            conn.setRequestProperty("Authorization", "Bearer " + SessionData.token);
            // Обнуляем массив data
            data = null;
            // ОТправлеям запрос
            conn.connect();
            // Получаем код ответа от сервера
            int responseCode = conn.getResponseCode();
            // Создаем поток исходящий данные
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            // Получяем данные от сервера, сохраняем их в потоке
            is = conn.getInputStream();
            // Создаём буффер, для записи данных из потока, в буффер
            byte[] buffer = new byte[8192]; // Такого вот размера буфер
            // Далее, например, вот так читаем ответ
            int bytesRead;
            // Читаем данные из потока входящих от сервера данных
            // и записываем их в другой поток
            while ((bytesRead = is.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesRead);
            }
            // Поток, с данными, преобразовываем к массиву байтов
            data = baos.toByteArray();
            // Ответ от сервера, перегоняем из байтов в строку
            String response = new String(data);
            System.out.println("getSpec Response: " + response);
            // Из ответа от сервер формируем json-объект
            JSONObject jsonRoot = new JSONObject(response);

            // Запоминаем категории
            SessionData.specializations = JsonWorker.getSpecializationsFromJson(jsonRoot);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean getUserSpecializations(int userId) {
        // Адрес по которому мы шлем запрос
        String myURL = protocol + rootUrl + "api/UserSpecializations/" + userId;
        // Массив байтов, ответ от сервера
        byte[] data = null;
        // Входящий поток данных от сервера
        InputStream is = null;
        // Флаг, указываюший на то, корректно прошел запрос или нет
        boolean isCorrect = true;

        try {
            // Создаем объект класса URL
            URL url = new URL(myURL);
            // На url открываем соединение, соеденение с сервером
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // Указываем тип запроса
            conn.setRequestMethod("GET");
            // Указываем, что мы ожидаем получить ответ от сервера
            conn.setDoInput(true);
            // Указываем заголовки запроса
            conn.setRequestProperty("Authorization", "Bearer " + SessionData.token);
            // Обнуляем массив data
            data = null;
            // ОТправлеям запрос
            conn.connect();
            // Получаем код ответа от сервера
            int responseCode = conn.getResponseCode();
            // Создаем поток исходящий данные
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            // Получяем данные от сервера, сохраняем их в потоке
            is = conn.getInputStream();
            // Создаём буффер, для записи данных из потока, в буффер
            byte[] buffer = new byte[8192]; // Такого вот размера буфер
            // Далее, например, вот так читаем ответ
            int bytesRead;
            // Читаем данные из потока входящих от сервера данных
            // и записываем их в другой поток
            while ((bytesRead = is.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesRead);
            }
            // Поток, с данными, преобразовываем к массиву байтов
            data = baos.toByteArray();
            // Ответ от сервера, перегоняем из байтов в строку
            String response = new String(data);

            System.out.println("getUserSpec Response: " + response);
            // Из ответа от сервер формируем json-объект
            JSONObject jsonRoot = new JSONObject(response);
            // Парсим json
            SessionData.currentUser.setUserSpecializations(
                    JsonWorker.getUserSpecializationsFromJson(jsonRoot));

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean getNewStudentsRequests(int couchId){
        // Адрес по которому мы шлем запрос
        String myURL = protocol + rootUrl + "api/Couches/newstudents/" + couchId;
        // Массив байтов, ответ от сервера
        byte[] data = null;
        // Входящий поток данных от сервера
        InputStream is = null;
        // Флаг, указываюший на то, корректно прошел запрос или нет
        boolean isCorrect = true;

        try {
            // Создаем объект класса URL
            URL url = new URL(myURL);
            // На url открываем соединение, соеденение с сервером
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // Указываем тип запроса
            conn.setRequestMethod("GET");
            // Указываем, что мы ожидаем получить ответ от сервера
            conn.setDoInput(true);
            // Указываем заголовки запроса
            conn.setRequestProperty("Authorization", "Bearer " + SessionData.token);
            // Обнуляем массив data
            data = null;
            // ОТправлеям запрос
            conn.connect();
            // Получаем код ответа от сервера
            int responseCode = conn.getResponseCode();
            // Создаем поток исходящий данные
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            // Получяем данные от сервера, сохраняем их в потоке
            is = conn.getInputStream();
            // Создаём буффер, для записи данных из потока, в буффер
            byte[] buffer = new byte[8192]; // Такого вот размера буфер
            // Далее, например, вот так читаем ответ
            int bytesRead;
            // Читаем данные из потока входящих от сервера данных
            // и записываем их в другой поток
            while ((bytesRead = is.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesRead);
            }
            // Поток, с данными, преобразовываем к массиву байтов
            data = baos.toByteArray();
            // Ответ от сервера, перегоняем из байтов в строку
            String response = new String(data);

            System.out.println("getNewStudentsRequests Response: " + response);
            // Из ответа от сервер формируем json-объект
            JSONArray jsonRoot = new JSONArray(response);
            // Парсим json
            SessionData.newStudentsRequestList = JsonWorker.getNewStudentsFromJson(jsonRoot);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean getMasterSkills(int couchId) {
        // Адрес по которому мы шлем запрос
        String myURL = protocol + rootUrl + "api/Couches/" + couchId;
        // Массив байтов, ответ от сервера
        byte[] data = null;
        // Входящий поток данных от сервера
        InputStream is = null;
        // Флаг, указываюший на то, корректно прошел запрос или нет
        boolean isCorrect = true;

        try {
            // Создаем объект класса URL
            URL url = new URL(myURL);
            // На url открываем соединение, соеденение с сервером
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // Указываем тип запроса
            conn.setRequestMethod("GET");
            // Указываем, что мы ожидаем получить ответ от сервера
            conn.setDoInput(true);
            // Указываем заголовки запроса
            conn.setRequestProperty("Authorization", "Bearer " + SessionData.token);
            // Обнуляем массив data
            data = null;
            // ОТправлеям запрос
            conn.connect();
            // Получаем код ответа от сервера
            int responseCode = conn.getResponseCode();
            // Создаем поток исходящий данные
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            // Получяем данные от сервера, сохраняем их в потоке
            is = conn.getInputStream();
            // Создаём буффер, для записи данных из потока, в буффер
            byte[] buffer = new byte[8192]; // Такого вот размера буфер
            // Далее, например, вот так читаем ответ
            int bytesRead;
            // Читаем данные из потока входящих от сервера данных
            // и записываем их в другой поток
            while ((bytesRead = is.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesRead);
            }
            // Поток, с данными, преобразовываем к массиву байтов
            data = baos.toByteArray();
            // Ответ от сервера, перегоняем из байтов в строку
            String response = new String(data);

            System.out.println("getMasterSkills Response: " + response);
            // Из ответа от сервер формируем json-объект
            JSONArray jsonRoot = new JSONArray(response);
            // Парсим json
            SessionData.currentUser.masterSkills =
                    JsonWorker.getMasterSkillsFromJson(jsonRoot);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean getStudents(int couchId) {
        // Адрес по которому мы шлем запрос
        String myURL = protocol + rootUrl + "api/UserSpecializations/couch/" + couchId;
        // Массив байтов, ответ от сервера
        byte[] data = null;
        // Входящий поток данных от сервера
        InputStream is = null;
        // Флаг, указываюший на то, корректно прошел запрос или нет
        boolean isCorrect = true;

        try {
            // Создаем объект класса URL
            URL url = new URL(myURL);
            // На url открываем соединение, соеденение с сервером
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // Указываем тип запроса
            conn.setRequestMethod("GET");
            // Указываем, что мы ожидаем получить ответ от сервера
            conn.setDoInput(true);
            // Указываем заголовки запроса
            conn.setRequestProperty("Authorization", "Bearer " + SessionData.token);
            // Обнуляем массив data
            data = null;
            // ОТправлеям запрос
            conn.connect();
            // Получаем код ответа от сервера
            int responseCode = conn.getResponseCode();
            // Создаем поток исходящий данные
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            // Получяем данные от сервера, сохраняем их в потоке
            is = conn.getInputStream();
            // Создаём буффер, для записи данных из потока, в буффер
            byte[] buffer = new byte[8192]; // Такого вот размера буфер
            // Далее, например, вот так читаем ответ
            int bytesRead;
            // Читаем данные из потока входящих от сервера данных
            // и записываем их в другой поток
            while ((bytesRead = is.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesRead);
            }
            // Поток, с данными, преобразовываем к массиву байтов
            data = baos.toByteArray();
            // Ответ от сервера, перегоняем из байтов в строку
            String response = new String(data);

            System.out.println("getStudents Response: " + response);
            // Из ответа от сервер формируем json-объект
            JSONObject jsonRoot = new JSONObject(response);

            // Парсим json
            SessionData.currentUser.studentsSpecialization =
                    JsonWorker.getStudentSpecsFromJson(jsonRoot);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean acceptStudentRequest(int couchId, int userSpecializationId){
        // Адрес по которому мы шлем запрос
        String myURL = protocol + rootUrl + "api/Couches/acceptStudent/"
                + "couchId=" + couchId
                + "&userSpecializationId=" + userSpecializationId;
        // Массив байтов, ответ от сервера
        byte[] data = null;
        // Входящий поток данных от сервера
        InputStream is = null;
        // Флаг, указываюший на то, корректно прошел запрос или нет
        boolean isCorrect = true;

        try {
            // Создаем объект класса URL
            URL url = new URL(myURL);
            // На url открываем соединение, соеденение с сервером
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // Указываем тип запроса
            conn.setRequestMethod("GET");
            // Указываем, что мы ожидаем получить ответ от сервера
            conn.setDoInput(true);
            // Указываем заголовки запроса
            conn.setRequestProperty("Authorization", "Bearer " + SessionData.token);
            // Обнуляем массив data
            data = null;
            // ОТправлеям запрос
            conn.connect();
            // Получаем код ответа от сервера
            int responseCode = conn.getResponseCode();
            // Создаем поток исходящий данные
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            // Получяем данные от сервера, сохраняем их в потоке
            is = conn.getInputStream();
            // Создаём буффер, для записи данных из потока, в буффер
            byte[] buffer = new byte[8192]; // Такого вот размера буфер
            // Далее, например, вот так читаем ответ
            int bytesRead;
            // Читаем данные из потока входящих от сервера данных
            // и записываем их в другой поток
            while ((bytesRead = is.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesRead);
            }
            // Поток, с данными, преобразовываем к массиву байтов
            data = baos.toByteArray();
            // Ответ от сервера, перегоняем из байтов в строку
            String response = new String(data);

            System.out.println("acceptStudentRequest Response: " + response);
           // // Из ответа от сервер формируем json-объект
           // JSONArray jsonRoot = new JSONArray(response);
           // // Парсим json
           // SessionData.newStudentsRequestList = JsonWorker.getNewStudentsFromJson(jsonRoot);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean getStudentSpec(int userSpecsId) {
        // Адрес по которому мы шлем запрос
        String myURL = protocol + rootUrl + "api/couches/getSpecsInfo/specsId=" + userSpecsId;
        // Массив байтов, ответ от сервера
        byte[] data = null;
        // Входящий поток данных от сервера
        InputStream is = null;
        // Флаг, указываюший на то, корректно прошел запрос или нет
        boolean isCorrect = true;

        try {
            // Создаем объект класса URL
            URL url = new URL(myURL);
            // На url открываем соединение, соеденение с сервером
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // Указываем тип запроса
            conn.setRequestMethod("GET");
            // Указываем, что мы ожидаем получить ответ от сервера
            conn.setDoInput(true);
            // Указываем заголовки запроса
            conn.setRequestProperty("Authorization", "Bearer " + SessionData.token);
            // Обнуляем массив data
            data = null;
            // ОТправлеям запрос
            conn.connect();
            // Получаем код ответа от сервера
            int responseCode = conn.getResponseCode();
            // Создаем поток исходящий данные
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            // Получяем данные от сервера, сохраняем их в потоке
            is = conn.getInputStream();
            // Создаём буффер, для записи данных из потока, в буффер
            byte[] buffer = new byte[8192]; // Такого вот размера буфер
            // Далее, например, вот так читаем ответ
            int bytesRead;
            // Читаем данные из потока входящих от сервера данных
            // и записываем их в другой поток
            while ((bytesRead = is.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesRead);
            }
            // Поток, с данными, преобразовываем к массиву байтов
            data = baos.toByteArray();
            // Ответ от сервера, перегоняем из байтов в строку
            String response = new String(data);

            System.out.println("getStudentSpecs Response: " + response);
            // Из ответа от сервер формируем json-объект
            JSONObject jsonRoot = new JSONObject(response);

            SessionData.currentUser.couch.currentStudent =
                    JsonWorker.getUserFromJson(jsonRoot, "student");

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean getCouchSpec(int userSpecsId) {
        // Адрес по которому мы шлем запрос
        String myURL = protocol + rootUrl + "api/UserSpecializations/getSpecsInfo/specsId=" + userSpecsId;
        // Массив байтов, ответ от сервера
        byte[] data = null;
        // Входящий поток данных от сервера
        InputStream is = null;
        // Флаг, указываюший на то, корректно прошел запрос или нет
        boolean isCorrect = true;

        try {
            // Создаем объект класса URL
            URL url = new URL(myURL);
            // На url открываем соединение, соеденение с сервером
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // Указываем тип запроса
            conn.setRequestMethod("GET");
            // Указываем, что мы ожидаем получить ответ от сервера
            conn.setDoInput(true);
            // Указываем заголовки запроса
            conn.setRequestProperty("Authorization", "Bearer " + SessionData.token);
            // Обнуляем массив data
            data = null;
            // ОТправлеям запрос
            conn.connect();
            // Получаем код ответа от сервера
            int responseCode = conn.getResponseCode();
            // Создаем поток исходящий данные
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            // Получяем данные от сервера, сохраняем их в потоке
            is = conn.getInputStream();
            // Создаём буффер, для записи данных из потока, в буффер
            byte[] buffer = new byte[8192]; // Такого вот размера буфер
            // Далее, например, вот так читаем ответ
            int bytesRead;
            // Читаем данные из потока входящих от сервера данных
            // и записываем их в другой поток
            while ((bytesRead = is.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesRead);
            }
            // Поток, с данными, преобразовываем к массиву байтов
            data = baos.toByteArray();
            // Ответ от сервера, перегоняем из байтов в строку
            String response = new String(data);

            System.out.println("getStudentSpecs Response: " + response);
            // Из ответа от сервер формируем json-объект
            JSONObject jsonRoot = new JSONObject(response);

            SessionData.currentUser.student.currentCouch =
                    JsonWorker.getUserFromJson(jsonRoot, "couch");

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Метод для отправки запроса на обучение
    public static NewUserSpecializationStatus postUserSpecialization(int userId, int specializationId, int categoryId, int languageId) {
        // Адрес по которому мы шлем запрос
        String myURL = protocol + rootUrl + "api/UserSpecializations";
        // Данные которые мы шлем в теле запроса
        String params = "UserId=" + userId
                + "&" + "SpecializationId=" + specializationId
                + "&" + "categoryId=" + categoryId
                + "&" + "LanguageId=" + languageId;

        // Массив байтов, ответ от сервера
        byte[] data = null;
        // Входящий поток данных от сервера
        InputStream is = null;
        // Флаг, указываюший на то, корректно прошел запрос или нет
        boolean isCorrect = true;

        try {
            // Создаем объект класса URL
            URL url = new URL(myURL);
            // На url открываем соединение, соеденение с сервером
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // Указываем тип запроса
            conn.setRequestMethod("POST");
            // Указываем, что в запросе, будут исходящие данные
            conn.setDoOutput(true);
            // Указываем, что мы ожидаем получить ответ от сервера
            conn.setDoInput(true);
            // Указываем заголовки запроса
            // Указываем сколько символова мы отправляем, в байтах
            conn.setRequestProperty("Content-Length", Integer.toString(params.getBytes().length));
            // Получаем поток исходящих от клиента данных
            OutputStream os = conn.getOutputStream();
            // Преобразовываем строку с данными, в массив байтов.
            // При чем, для кодирование в массив байтов, используем кодировку UTF_8
            data = params.getBytes("UTF-8");
            // В исходящий поток, записываем данные с паролем и логином, в виде байтов
            os.write(data);
            // Обнуляем массив data
            data = null;
            // ОТправлеям запрос
            conn.connect();
            // Получаем код ответа от сервера
            int responseCode = conn.getResponseCode();
            // Создаем поток исходящий данные
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            // Получяем данные от сервера, сохраняем их в потоке
            is = conn.getInputStream();
            // Создаём буффер, для записи данных из потока, в буффер
            byte[] buffer = new byte[8192]; // Такого вот размера буфер
            // Далее, например, вот так читаем ответ
            int bytesRead;
            // Читаем данные из потока входящих от сервера данных
            // и записываем их в другой поток
            while ((bytesRead = is.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesRead);
            }
            // Поток, с данными, преобразовываем к массиву байтов
            data = baos.toByteArray();
            // Ответ от сервера, перегоняем из байтов в строку
            String response = new String(data);
            // Из ответа от сервер формируем json-объект
            System.out.println("postUserSpecialization Response: " + response);

            // Статус, создания аккаунта
            int newUserSpecializationStatus = Integer.parseInt(response);

            if (newUserSpecializationStatus == NewUserSpecializationStatus.Correct.getValue()) {
                return NewUserSpecializationStatus.Correct;
            } else
                return NewUserSpecializationStatus.ALREADY_EXIST;

        } catch (Exception e) {
            e.printStackTrace();
            return NewUserSpecializationStatus.UNKNOWN_ERROR;
        }
    }

    // Метод для добавления скилла коучу
    public static NewCouchSpecializationStatus postCouchSpecialization(int couchId, int specializationId, int categoryId, int languageId) {
        // Адрес по которому мы шлем запрос
        String myURL = protocol + rootUrl + "api/Couches";
        // Данные которые мы шлем в теле запроса
        String params = "CouchId=" + couchId
                + "&" + "SpecializationId=" + specializationId
                + "&" + "categoryId=" + categoryId
                + "&" + "LanguageId=" + languageId;

        // Массив байтов, ответ от сервера
        byte[] data = null;
        // Входящий поток данных от сервера
        InputStream is = null;
        // Флаг, указываюший на то, корректно прошел запрос или нет
        boolean isCorrect = true;

        try {
            // Создаем объект класса URL
            URL url = new URL(myURL);
            // На url открываем соединение, соеденение с сервером
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // Указываем тип запроса
            conn.setRequestMethod("POST");
            // Указываем, что в запросе, будут исходящие данные
            conn.setDoOutput(true);
            // Указываем, что мы ожидаем получить ответ от сервера
            conn.setDoInput(true);
            // Указываем заголовки запроса
            // Указываем сколько символова мы отправляем, в байтах
            conn.setRequestProperty("Content-Length", Integer.toString(params.getBytes().length));
            // Получаем поток исходящих от клиента данных
            OutputStream os = conn.getOutputStream();
            // Преобразовываем строку с данными, в массив байтов.
            // При чем, для кодирование в массив байтов, используем кодировку UTF_8
            data = params.getBytes("UTF-8");
            // В исходящий поток, записываем данные с паролем и логином, в виде байтов
            os.write(data);
            // Обнуляем массив data
            data = null;
            // ОТправлеям запрос
            conn.connect();
            // Получаем код ответа от сервера
            int responseCode = conn.getResponseCode();
            // Создаем поток исходящий данные
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            // Получяем данные от сервера, сохраняем их в потоке
            is = conn.getInputStream();
            // Создаём буффер, для записи данных из потока, в буффер
            byte[] buffer = new byte[8192]; // Такого вот размера буфер
            // Далее, например, вот так читаем ответ
            int bytesRead;
            // Читаем данные из потока входящих от сервера данных
            // и записываем их в другой поток
            while ((bytesRead = is.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesRead);
            }
            // Поток, с данными, преобразовываем к массиву байтов
            data = baos.toByteArray();
            // Ответ от сервера, перегоняем из байтов в строку
            String response = new String(data);
            // Из ответа от сервер формируем json-объект
            System.out.println("postCouchSpecialization Response: " + response);

            // Статус, создания аккаунта
            int newUserSpecializationStatus = Integer.parseInt(response);

            if (newUserSpecializationStatus == NewCouchSpecializationStatus.Correct.getValue()) {
                return NewCouchSpecializationStatus.ALREADY_EXIST.Correct;
            } else
                return NewCouchSpecializationStatus.ALREADY_EXIST;

        } catch (Exception e) {
            e.printStackTrace();
            return NewCouchSpecializationStatus.UNKNOWN_ERROR;
        }
    }

    public static boolean updateUserInfo(User user) {
        // Адрес по которому мы шлем запрос
        String myURL = protocol + rootUrl + "api/account/update/" + user.getId();

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        // Данные которые мы шлем в теле запроса
        String params = gson.toJson(user);

        // Массив байтов, ответ от сервера
        byte[] data = null;
        // Входящий поток данных от сервера
        InputStream is = null;
        // Флаг, указываюший на то, корректно прошел запрос или нет
        boolean isCorrect = true;

        try {
            // Создаем объект класса URL
            URL url = new URL(myURL);
            // На url открываем соединение, соеденение с сервером
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // Указываем тип запроса
            conn.setRequestMethod("POST");
            // Указываем, что в запросе, будут исходящие данные
            conn.setDoOutput(true);
            // Указываем, что мы ожидаем получить ответ от сервера
            conn.setDoInput(true);
            // Указываем заголовки запроса
            // Указываем сколько символова мы отправляем, в байтах
            conn.setRequestProperty("Content-Length", Integer.toString(params.getBytes().length));
            conn.setRequestProperty("Content-Type", "application/json");

            // Получаем поток исходящих от клиента данных
            OutputStream os = conn.getOutputStream();
            // Преобразовываем строку с данными, в массив байтов.
            // При чем, для кодирование в массив байтов, используем кодировку UTF_8
            data = params.getBytes("UTF-8");
            // В исходящий поток, записываем данные с паролем и логином, в виде байтов
            os.write(data);
            // Обнуляем массив data
            data = null;
            // ОТправлеям запрос
            conn.connect();
            // Получаем код ответа от сервера
            int responseCode = conn.getResponseCode();
            // Создаем поток исходящий данные
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            // Получяем данные от сервера, сохраняем их в потоке
            is = conn.getInputStream();
            // Создаём буффер, для записи данных из потока, в буффер
            byte[] buffer = new byte[8192]; // Такого вот размера буфер
            // Далее, например, вот так читаем ответ
            int bytesRead;
            // Читаем данные из потока входящих от сервера данных
            // и записываем их в другой поток
            while ((bytesRead = is.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesRead);
            }
            // Поток, с данными, преобразовываем к массиву байтов
            data = baos.toByteArray();
            // Ответ от сервера, перегоняем из байтов в строку
            String response = new String(data);
            // Из ответа от сервер формируем json-объект
            System.out.println("updateUserInfo Response: " + response);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}