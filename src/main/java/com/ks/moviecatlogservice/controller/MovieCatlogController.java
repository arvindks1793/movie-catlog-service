package com.ks.moviecatlogservice.controller;

import java.util.List;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@RestController
public class MovieCatlogController {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private DiscoveryClient discoveryclient;

	@RequestMapping("/catlog/{userid}")
	public String getMovieRatignById(@PathVariable String userid)
			throws JSONException {
		List<ServiceInstance> instances = discoveryclient
				.getInstances("movie-rating-service");
		ServiceInstance sintance = instances.get(0);
		String BaseUrl = sintance.getUri().toString();
		BaseUrl = BaseUrl + "/rating" + "/" + userid;
		ResponseEntity<String> responseentity = null;
		String movieInfo = null;
		try {
			responseentity = restTemplate.exchange(BaseUrl, HttpMethod.GET,
					getHeaders(), String.class);
			JSONObject movieratingObj = new JSONObject(responseentity.getBody()
					.toString());
			JSONArray movieratingArray = new JSONArray();
			movieratingArray = movieratingArray.put(movieratingObj);
			String movieId = movieratingArray.optJSONObject(0).get("movieID")
					.toString();
			movieInfo = getMovieInformation(movieId);

		} catch (RestClientException e) {
			System.out.println(e.getMessage().getClass());
			throw e;
		} catch (Exception e) {
			System.out.println(e.getMessage().getClass());
			throw e;
		}
		return responseentity.getBody() + "" + movieInfo;
	}

	private String getMovieInformation(String movieId) {
		List<ServiceInstance> instances = discoveryclient
				.getInstances("movie-info-service");
		ServiceInstance sintance = instances.get(0);
		String BaseUrl = sintance.getUri().toString();
		BaseUrl = BaseUrl + "/info" + "/" + movieId;
		ResponseEntity<String> responseentity = null;
		try {
			responseentity = restTemplate.exchange(BaseUrl, HttpMethod.GET,
					getHeaders(), String.class);
		} catch (RestClientException e) {
			System.out.println(e.getMessage());
			throw e;
		} catch (Exception e) {
			System.out.println(e.getMessage().getClass());
			throw e;
		}
		return responseentity.getBody();
	}

	private static HttpEntity<?> getHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		return new HttpEntity<>(headers);
	}

}
