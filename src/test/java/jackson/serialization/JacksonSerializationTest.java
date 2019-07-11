package jackson.serialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.Test;

public class JacksonSerializationTest {

    @Test
    public void test() {
        SomeClass someClass = new SomeClass();
        someClass.fieldPublic = "sdf";
        someClass.setFieldPrivate("sss");


        ObjectMapper mapper = new ObjectMapper();
        try {
            System.out.println(mapper.writeValueAsString(someClass));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }

}
