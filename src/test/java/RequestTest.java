import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.testng.annotations.Test;

import java.io.IOException;


public class RequestTest {

    @Test
    public void test() {
        String jsonString = "{\"status_id\": \"1\", \"comment\": \"Пользователь {login: ; role: мастер/диспетчер/инженер ТО}. Тест выполнен с машины <>. Статус проставлен автотестом.\\n" +
                "|||:Шаг|:Действие|:Статус|:Завершён\\n" +
                "||1|PROGRAMM IS RUNNING IN DEBUGGING MODE!!!|FAILED|16:22:44\\n" +
                "||2| >> Инициализация (Запуск браузера. Логирование версии. Логин)|V|16:22:44\\n" +
                "||3|Переход на страницу логина 'http://185.173.2.55' (с прохождением basic-аутентификации, если есть)|V|16:22:57\\n" +
                "||4|Логин в систему <; >|V|16:22:59\\n" +
                "||5|Проверяем успешность логина|V|16:23:01\\n" +
                "||6|Ожидание исчезновения прогресс-бара загрузки (60000ms.)...|V|16:23:05\\n" +
                "||7|Получаем версию ЕТС|V|16:23:05\\n" +
                "||8|Получаем ИД тест-рана для текущих версий (Создаем майлстоун/тест-ран, если их нет).|V|16:23:35\", \"version\": \"V.2.0.27.285\"}";     //*/


        HttpClient httpClient = HttpClients.createDefault();
        StringEntity requestEntity = new StringEntity(
                jsonString,
                ContentType.APPLICATION_JSON);
        HttpPost post = new HttpPost("https://testrail.some.ru/index.php?/api/v2/add_result_for_case/495/1753");
        post.setHeader("Authorization", "Basic " + "dGVXXXXXXXXXXXXXXXXXXXXXX");
        post.setEntity(requestEntity);
        try {
            HttpResponse httpResponse = httpClient.execute(post);
            System.out.println(httpResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
