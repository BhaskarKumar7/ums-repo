package com.makershark.ums.filter;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.text.StringEscapeUtils;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

@Component
public class CustomRequestWrapper extends HttpServletRequestWrapper {

	public CustomRequestWrapper(HttpServletRequest request) {
		super(request);
	}

	@Override
	public String getParameter(String name) {
		String value = super.getParameter(name);
		return StringEscapeUtils.escapeHtml4(value);
	}

	@Override
	public String[] getParameterValues(String name) {
		String[] values = super.getParameterValues(name);
		if (values == null) {
			return null;
		}
		String[] sanitizedValues = new String[values.length];
		for (int i = 0; i < values.length; i++) {
			sanitizedValues[i] = StringEscapeUtils.escapeHtml4(values[i]);
		}
		return sanitizedValues;
	}

	@Override
	public Map<String, String[]> getParameterMap() {
		Map<String, String[]> map = new HashMap<>(super.getParameterMap());
		map.replaceAll((key, value) -> {
			if (value == null) {
				return null;
			}
			String[] sanitizedValues = new String[value.length];
			for (int i = 0; i < value.length; i++) {
				sanitizedValues[i] = StringEscapeUtils.escapeHtml4(value[i]);
			}
			return sanitizedValues;
		});
		return map;
	}
}
