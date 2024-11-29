package hello.itemservice.message;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
public class MessageSourceTest {

    @Autowired
    MessageSource ms;

    @Test
    void helloMessage() {
        String result = ms.getMessage("hello", null, null);
        assertThat(result).isEqualTo("안녕");
    }

    @Test
    void notFoundMessageCode() {
        // 테스트 중 특정 코드 블록이 예외를 던지는지 확인하기 위한 단정문.
        assertThatThrownBy(() -> {
            String result = ms.getMessage("nocode", null, null);
        }).isInstanceOf(NoSuchMessageException.class);
    }

    @Test
    void notFoundMessageCodeDefaultMessage() {
        // 테스트 중 특정 코드 블록이 예외를 던지는지 확인하기 위한 단정문.
        String result = ms.getMessage("nocode", null, "기본 메시지", null);
        assertThat(result).isEqualTo("기본 메시지");
    }

    @Test
    void argumentMessage() {
        // argument 는 배열 형태로 전달.
        String result = ms.getMessage("hello.name", new String[]{"Spring"}, "기본 메시지", Locale.ENGLISH);
        assertThat(result).isEqualTo("hello Spring");
    }

}
