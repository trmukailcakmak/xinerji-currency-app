package com.xinerji.currency.service.impl;

import com.xinerji.currency.constant.ExternalServiceEndPointConstant;
import com.xinerji.currency.constant.MessageKey;
import com.xinerji.currency.exceptions.XinerjiException;
import com.xinerji.currency.model.dto.currency.Currency;
import com.xinerji.currency.model.dto.currency.CurrencyResponseDto;
import com.xinerji.currency.model.mapper.CurrencyMapper;
import com.xinerji.currency.service.RestTemplateService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class RestTemplateServiceImpl implements RestTemplateService {

    private static final Logger logger = LoggerFactory.getLogger(RestTemplateServiceImpl.class);

    private final CurrencyMapper currencyMapper= CurrencyMapper.INSTANCE;
    private final MessageSource messageSource;

    @Override
    public List<CurrencyResponseDto> callCurrencyServiceByDate(LocalDateTime dateTime) {
        try {
            StringBuilder url = insertDateTimeIntoToUrlForCurrencyService(dateTime);

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> restResponse = restTemplate.getForEntity( url.toString(), String.class);

            List<Currency> currencyList = parseXmlToCurrencyList(restResponse.getBody());
            List<CurrencyResponseDto> responseDtoList = currencyMapper.mapEntityListToResponseDtoList(currencyList);
            return responseDtoList;
        } catch (RestClientException e) {
            throw new XinerjiException(MessageKey.ERR019, this.messageSource.getMessage(MessageKey.ERR019, null, Locale.ENGLISH));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private StringBuilder insertDateTimeIntoToUrlForCurrencyService(LocalDateTime dateTime) {
        String year = String.valueOf(dateTime.getYear());
        String month = String.valueOf(dateTime.getMonthValue());
        String day = String.valueOf(dateTime.getDayOfMonth());
        month = zeroNumberCorrection(month);
        day = zeroNumberCorrection(day);

        StringBuilder url = new StringBuilder();
        url.append(ExternalServiceEndPointConstant.GET_CURRENCY_RATE_SERVICE_URL);
        url.append(year);
        url.append(month);
        url.append("/");
        url.append(day);
        url.append(month);
        url.append(year);
        url.append(".xml");
        return url;
    }

    private String zeroNumberCorrection(String number) {
        if (Integer.valueOf(number)<10) {
            number = "0" + number;
        }
        return number;
    }
    public static List<Currency> parseXmlToCurrencyList(String xml) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputSource is = new InputSource(new StringReader(xml));
        Document doc = builder.parse(is);

        NodeList currencyNodes = doc.getElementsByTagName("Currency");
        List<Currency> currencyList = new ArrayList<>();

        for (int i = 0; i < currencyNodes.getLength(); i++) {
            Element currencyElement = (Element) currencyNodes.item(i);
            String isim = currencyElement.getElementsByTagName("Isim").item(0).getTextContent();
            String currencyName = currencyElement.getElementsByTagName("CurrencyName").item(0).getTextContent();
            String forexBuying = currencyElement.getElementsByTagName("ForexBuying").item(0).getTextContent();
            String forexSelling = currencyElement.getElementsByTagName("ForexSelling").item(0).getTextContent();
            String banknoteBuying = currencyElement.getElementsByTagName("BanknoteBuying").item(0).getTextContent();
            String banknoteSelling = currencyElement.getElementsByTagName("BanknoteSelling").item(0).getTextContent();
            String crossRateUSD = currencyElement.getElementsByTagName("CrossRateUSD").item(0).getTextContent();
            String crossRateOther = currencyElement.getElementsByTagName("CrossRateOther").item(0).getTextContent();

            currencyList.add(new Currency(isim, currencyName, forexBuying, forexSelling, banknoteBuying, banknoteSelling, crossRateUSD, crossRateOther));
        }
        return currencyList;
    }
}
