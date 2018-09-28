package de.signaliduna.alexa.rest;

import com.amazon.ask.model.RequestEnvelope;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;

public class HelloWorldTest {

	@Test
	public void testVoice() throws IOException {
		String jsonInput = "{\"version\":\"1.0\",\"session\":{\"new\":true,\"sessionId\":\"amzn1.echo-api.session.e9128c1c-0912-4434-9646-159a87b535b9\",\"application\":{\"applicationId\":\"amzn1.ask.skill.f991fa10-4563-4fcd-9fd8-21e346de54f0\"},\"user\":{\"userId\":\"amzn1.ask.account.AGHTXAW2BZYCY7ZMT5QF3WDWV5UNGMVTCIXWD6NZY45LZFE653DAM3DEHVVADZWLNMCIMRTLV2B3E3UUFPUZB5PKQWQFKWHJUNBZEJQ5NHNH5E5LADIVVWS2MBREGPGHHJ5QOUPMYAGZCGVR3WBMHMGNRVLTJJB7DZN3BY3WSOMQ4RZWEPNWACRFLQIMZUHZU5C5OJD6JPYDCMY\"}},\"context\":{\"System\":{\"application\":{\"applicationId\":\"amzn1.ask.skill.f991fa10-4563-4fcd-9fd8-21e346de54f0\"},\"user\":{\"userId\":\"amzn1.ask.account.AGHTXAW2BZYCY7ZMT5QF3WDWV5UNGMVTCIXWD6NZY45LZFE653DAM3DEHVVADZWLNMCIMRTLV2B3E3UUFPUZB5PKQWQFKWHJUNBZEJQ5NHNH5E5LADIVVWS2MBREGPGHHJ5QOUPMYAGZCGVR3WBMHMGNRVLTJJB7DZN3BY3WSOMQ4RZWEPNWACRFLQIMZUHZU5C5OJD6JPYDCMY\"},\"device\":{\"deviceId\":\"amzn1.ask.device.AEMNYNLAHPLPBRHDNN4CBK5RJ76YMINZRCHQKLBUPUR3JQENXNYWKT6QUB7XRX7DFYU4EMTCXE52I4GEAJGHRVR3RT2NJMDHG3RF6YSU3EHLWQVWHVCKRBPYC6IPPPHDYQSMSEKQBBDZMKBVOT2EIML5RESMGTAQ6KYUWWDXZJCILOX4NJZTM\",\"supportedInterfaces\":{}},\"apiEndpoint\":\"https://api.eu.amazonalexa.com\",\"apiAccessToken\":\"eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImtpZCI6IjEifQ.eyJhdWQiOiJodHRwczovL2FwaS5hbWF6b25hbGV4YS5jb20iLCJpc3MiOiJBbGV4YVNraWxsS2l0Iiwic3ViIjoiYW16bjEuYXNrLnNraWxsLmY5OTFmYTEwLTQ1NjMtNGZjZC05ZmQ4LTIxZTM0NmRlNTRmMCIsImV4cCI6MTUzODEzOTAwMSwiaWF0IjoxNTM4MTM1NDAxLCJuYmYiOjE1MzgxMzU0MDEsInByaXZhdGVDbGFpbXMiOnsiY29uc2VudFRva2VuIjpudWxsLCJkZXZpY2VJZCI6ImFtem4xLmFzay5kZXZpY2UuQUVNTllOTEFIUExQQlJIRE5ONENCSzVSSjc2WU1JTlpSQ0hRS0xCVVBVUjNKUUVOWE5ZV0tUNlFVQjdYUlg3REZZVTRFTVRDWEU1Mkk0R0VBSkdIUlZSM1JUMk5KTURIRzNSRjZZU1UzRUhMV1FWV0hWQ0tSQlBZQzZJUFBQSERZUVNNU0VLUUJCRFpNS0JWT1QyRUlNTDVSRVNNR1RBUTZLWVVXV0RYWkpDSUxPWDROSlpUTSIsInVzZXJJZCI6ImFtem4xLmFzay5hY2NvdW50LkFHSFRYQVcyQlpZQ1k3Wk1UNVFGM1dEV1Y1VU5HTVZUQ0lYV0Q2TlpZNDVMWkZFNjUzREFNM0RFSFZWQURaV0xOTUNJTVJUTFYyQjNFM1VVRlBVWkI1UEtRV1FGS1dISlVOQlpFSlE1TkhOSDVFNUxBRElWVldTMk1CUkVHUEdISEo1UU9VUE1ZQUdaQ0dWUjNXQk1ITUdOUlZMVEpKQjdEWk4zQlkzV1NPTVE0UlpXRVBOV0FDUkZMUUlNWlVIWlU1QzVPSkQ2SlBZRENNWSJ9fQ.YGYSlojesWiuRpiYjx8aAhdQ-ad3LS__MJFgdEsjxWFwVCcHixv7Me5Gi5AO0Oon2BW3gaHrd-E32z5C113T-KcPbzQSzoUBf34KGKhZVUT-Ygf_BR52g9_BqlATq7HK6UKn5n88nBmZn6wTVvDa8BWOZQulPOWckpvn30QSD1pWBuXcfHK2WtBmUpSwgxtPoQwOx6cDkA_1HVUQwZ9wxeCChK8C5e-NUy5DYC_OsRZ5taHNEwLZp9GPVlNtVEQfvl-EPtjOIPemFaBGDdJ65HaECX3SOcc1t0pqbhkQcYXaAm4XIo8dblfi9m076aHemQAsf8k3e_0Ro2zTkR30JQ\"}},\"request\":{\"type\":\"IntentRequest\",\"requestId\":\"amzn1.echo-api.request.5b658d5b-68e2-4438-997b-b82f9f1dee3c\",\"timestamp\":\"2018-09-28T11:50:01Z\",\"locale\":\"de-DE\",\"intent\":{\"name\":\"HelloWorldIntent\",\"confirmationStatus\":\"NONE\"}}}";
		ObjectMapper mapper = new ObjectMapper();
		mapper.findAndRegisterModules();
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		RequestEnvelope envelope = mapper.readValue(jsonInput, RequestEnvelope.class);
		System.out.println(envelope);
	}

}
