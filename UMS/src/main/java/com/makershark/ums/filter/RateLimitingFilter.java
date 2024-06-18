package com.makershark.ums.filter;

import java.io.IOException;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SuppressWarnings("deprecation")
@Component(value = "rateLimitFilter")
@Order(1)
public class RateLimitingFilter extends HttpFilter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7879604572435310329L;
	private final Map<String, Bucket> cache;
	
	public RateLimitingFilter() {
		this.cache = new ConcurrentHashMap<>();
	}
	
	@Override
	public void doFilter(HttpServletRequest httpRequest, HttpServletResponse httpResponse, FilterChain chain)
			throws IOException, ServletException {
		log.info("====== RateLimitingFilter intercepted the request ==========");
		
		String ipAddress = httpRequest.getRemoteAddr();
		Bucket bucket = cache.get(ipAddress);
		log.info("fetched ip address-----> {}",ipAddress);
		if(null == bucket)	{
			log.info("-- bucket is null --");
			bucket = createNewBucket();
			cache.put(ipAddress, bucket);
		}
		log.info("avaialale tokens in bucket : {}",bucket.getAvailableTokens());
		if(bucket.tryConsume(1))	{
			log.info("-- consumed one token from bucket ----");
			chain.doFilter(httpRequest, httpResponse);
		}
		else	{
			log.info("*** too may request ***");
			httpResponse.setStatus(429);
			httpResponse.getWriter().write("Too many requests");
		}
	}
	
	private Bucket createNewBucket()	{
		log.info("== creating new buacket ===");
		return Bucket4j
				.builder()
				.addLimit(Bandwidth.classic(10, Refill.greedy(10, Duration.ofMinutes(1))))
				.build();
	}
}
