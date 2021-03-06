package com.qa.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class RestClient {

	
	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException {
		CloseableHttpClient httpsClient = HttpClients.createDefault();
		HttpGet httpsget = new HttpGet(url);
		httpsget.addHeader("Authorization", "Bearer eyJhbGciOiJBMjU2S1ciLCJlbmMiOiJBMjU2Q0JDLUhTNTEyIiwidHlwIjoiSldUIn0.R6AiR3cstjy3uETJmQIoMovnu46v-dQ8AOlM7xhPclkG5tebsShg3QQhvaxNsZWjNUOGlS8NqpPRZpqa0AG-zRoG-UJO1MMo.ICYmeaYfq0_Je0wuteSxZg.3PknNfn-gXn3u1l9rNhPdjxu-y7y4ZekU0jpc24FXVyQH8vgydKJtzKcmkXRyq5vq7XnGkEg3rP3OWblVotAU0xgR2suBdya6FAaNpC4_kN5nB5JFf_q-Fbxfq_B-pVWwSNtgbRnsjeyMvZ6kKYOEYfQUoKIsaBeAk5NtoUX3XzO26A9kc468J5tDMVNZBCtAfvwz4eJ-TGYi8KYvjVG4r8ZGiZdj3L9-vLyP6JMS6G-dHtU5CD4OhIDO7bJXTA3ujLO1jIrJRKkBxTpkmjSLgry0SSjQAHEM9r0YVCUqYajhCWehssLOfHqLM7qkWXzWsVINzg-qNVnglAJYmA_UDeqzRoVvg3fv6sDyL35lFWRyh4V_ztAhM5u-o_F5Rkit6Yt7_0NiZKWGJ5v8S8lLkveFJOOCib4jihfkPRu3AsQxL38rigyI4VF1aTp6UtJfDk37yeEMtWCQ0Vc2b8Cf-az4XtFVoJjgbfg1xW9oe4mnCNKcsPRNnOqG1aO714TYoqALi1JLTDFMLLwtbi0n3TyFZT58HKvqevgqVv7P1CQPJKt8uiu9zsLi5OxNUHnuHZti95rlV6PqOCdBhmKy0s_1qvK5sxLBIOfU9tnQFFH-y2fQHMfGx77lgSrVhjA7qbaN7GiU_8cGneLGocMv4vaElgmGzCxHoDWEeV3g83RgJk8nH0H4mTexHoSG7-96wQcZmD-ANR4dOxBle9lrA-T0JlmRuHwDKZ3xnRIbCutGNRhXGQgOefCC8-KZLnxjrpS_QSdpLP8QDbpc5hSYuvOR-ysz-1jP9L19BhH9zjr49ydrC9SnafSe9wsUaQMWnrDmsKxgG-6mi9ImC7bXUTk2NdviqgYI-NpTEcAdrmn4vYdSjbvw00FAJ8ueBtaVPoWgVP9bu8fXjLKmZLwc-uj46fjzKN7QTd7Z-MSQF1FB1Qd9h8Mk28mYnVmwI6CB81Mt0SEBjVikNzROMqr-PQcer2ZcRmRMEhfhnv116AlKfZ1LIH4XCLNDTKztiuV_emEFGYCgMzz5F1kwF5nYZl2BT6TDo5jFF-c1uItskwaXl4_DVbP4kZAMX339hsx_EC0BBT3QO3oa4PSw6MOBIYkyXDZHnNtXZszYW4Mt3P77vhEh3jUUza0aih2mR8sANKdyKRTVmDS9LHCx3SwvHFOwOzHHiLnGtdgItzVGcLtomNOhxvtjv4wyjmvsAb_cuqhWLLUxbPoLrGIALqRxFrntNB1MLRJtSN8UQgNYR5wLqp6C_8MgXcLBWBIFwwoQH-rDKSzZxVgysiizOWuFNjjYQYMyQtHOXoqlUUCbD3sAWme9krj2-bqNyDja_Q7BoKgR_a1ZyWi-jcNbj2ymyj9bs-CBAAgkgloUJQAb1tnaIkOpoE-pAZIxRroppTIYZNO22v2OL-5FcFfnqcJb75CkoZMMat1-rvuPbS44PQbzyP4-rSviORpgYbBxamU9MkMsA0pws2uJf1wQdus8UiXk0aCZe-wQSQthCnlxMnNlLvXIsFP83kkHFsCazQjcHnWyvhTK1QO0hEwuBn-NmlxplmwdVV0_0b89WXm6LSnfJfxUO39ZZ71YJINhY3VgDIJJA-OOmmM1jBNpCcFoVpipt9suLpah0M-U3dzYonYgdyTMU8pujG951vO77769iL6wkIUYRE4eqPkN4sDrAu4-UHhaSNON5d5zPMRxHwpygnN1BOII2EvtGYtm7UYXLlnj6uNYcSScjqhvMUMFON1VRhTvUJr47Ijl_Wk7Eh-PQ0gVcFOR56dRZlhvtk-RoKZbZI3C8iZpcL5tLy1gI8HWTfUA7TOMO-tsQ5zg3Gr09NTQ8HBk3pm3r8KqRv1nMQ1cq9m3LtbpoQUQS2EEit-TydJPFdbJNowQJlSSi9oyZAIlCZIJQJonLCmKGZY.OXa5o1g2DMPj9g7hz6cUUTllijMhpoSXog8-K7i4N5Y");
		CloseableHttpResponse closeableHttpResponse = httpsClient.execute(httpsget);	
		return closeableHttpResponse;
	
	}
	
	public CloseableHttpResponse get(String url, HashMap<String, String> headermap) throws ClientProtocolException, IOException {
		CloseableHttpClient httpsClient = HttpClients.createDefault();
		HttpGet httpsget = new HttpGet(url);
		for (Map.Entry<String, String> entry : headermap.entrySet()) {
			httpsget.addHeader(entry.getKey(), entry.getValue());
		}
		CloseableHttpResponse closeableHttpResponse = httpsClient.execute(httpsget);
		return closeableHttpResponse;
		}
	
	//Create POST method
	
	public CloseableHttpResponse post(String url, String payload, HashMap<String, String> headermap) throws ClientProtocolException, IOException {
		
		CloseableHttpClient httpscliet =  HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url);
		httppost.setEntity(new StringEntity(payload));
				
		for (Map.Entry<String, String> entry : headermap.entrySet()) {
			httppost.addHeader(entry.getKey(), entry.getValue());
		}
		CloseableHttpResponse closeableresponse = httpscliet.execute(httppost);
		return closeableresponse;
	}
	
	
	public CloseableHttpResponse put(String url, String payload, HashMap<String, String> headermap) throws ClientProtocolException, IOException {
		
		CloseableHttpClient httpclient =  HttpClients.createDefault();
		HttpPut httpput =  new HttpPut(url);
		httpput.setEntity(new StringEntity(payload));
		
		for (Map.Entry<String, String> entry : headermap.entrySet()) {
			httpput.addHeader(entry.getKey(), entry.getValue());
		}
		
		CloseableHttpResponse closeableresponse = httpclient.execute(httpput);
		return closeableresponse;

	}
	
	
	public CloseableHttpResponse postwihformdata(String url, List<NameValuePair> urlParameters, HashMap<String, String> headermap) throws ClientProtocolException, IOException {
		
		CloseableHttpClient httpscliet =  HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url);
		httppost.setEntity(new UrlEncodedFormEntity(urlParameters));
		
		
//		httppost.setEntity(payload);
		
		for (Map.Entry<String, String> entry : headermap.entrySet()) {
			httppost.addHeader(entry.getKey(), entry.getValue());
		}
		CloseableHttpResponse closeableresponse = httpscliet.execute(httppost);
		return closeableresponse;
	}
	
	public CloseableHttpResponse delete(String url, HashMap<String, String> headermap) throws ClientProtocolException, IOException {
		CloseableHttpClient httpsClient = HttpClients.createDefault();
		
		HttpDelete httpsdelete =  new HttpDelete(url);
		for (Map.Entry<String, String> entry : headermap.entrySet()) {
			httpsdelete.addHeader(entry.getKey(), entry.getValue());
		}
		CloseableHttpResponse closeableHttpResponse = httpsClient.execute(httpsdelete);
		return closeableHttpResponse;
		}
}
