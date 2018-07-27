package showcases;

import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HomeControllerTest {

	@Autowired
	private TestRestTemplate rest;

	@Test
	public void normal() {
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.set(HttpHeaders.USER_AGENT, "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.6; en-US; rv:1.9.2.12) Gecko/20101026 Firefox/3.6.12");
		ResponseEntity<String> response = rest.exchange("/", HttpMethod.GET, new HttpEntity<>(requestHeaders), String.class);
		assertThat(response.getBody()).isEqualToIgnoringWhitespace("Device is NORMAL");
	}

	@Test
	public void mobile() {
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.set(HttpHeaders.USER_AGENT, "Mozilla/5.0 (iPhone; CPU iPhone OS 7_0 like Mac OS X) AppleWebKit/537.51.1 (KHTML, like Gecko) Version/7.0 Mobile/11A465 Safari/9537.53");
		ResponseEntity<String> response = rest.exchange("/", HttpMethod.GET, new HttpEntity<>(requestHeaders), String.class);
		assertThat(response.getBody()).isEqualToIgnoringWhitespace("Device is MOBILE");
	}

	@Test
	public void tablet() {
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.set(HttpHeaders.USER_AGENT, "Mozilla/5.0 (iPad; CPU OS 7_0 like Mac OS X) AppleWebKit/537.51.1 (KHTML, like Gecko) Version/7.0 Mobile/11A465 Safari/9537.53");
		ResponseEntity<String> response = rest.exchange("/", HttpMethod.GET, new HttpEntity<>(requestHeaders), String.class);
		assertThat(response.getBody()).isEqualToIgnoringWhitespace("Device is TABLET");
	}

}

