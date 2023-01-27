package pl.lotto.infrastructure.http;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.lotto.resultchecker.RetrieverWonNumbersClient;
import pl.lotto.resultchecker.dto.WinningNumbersDto;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class ResultCheckerHttpClient implements RetrieverWonNumbersClient {
    private RestTemplate restTemplate;

    @Value("${name.service.url}")
    private String NumberGeneratorServiceUrl;

    @Autowired
    public ResultCheckerHttpClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public WinningNumbersDto retrieveWonNumbers(String drawDate) {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<String> httpEntity = new HttpEntity<>(headers);

        String uri = NumberGeneratorServiceUrl + "/winningNumbers?request=" + drawDate;

        ResponseEntity<WinningNumbersDto> response = restTemplate.exchange(
                uri,
                HttpMethod.GET,
                httpEntity,
                WinningNumbersDto.class);

        return response.getBody();
    }
}