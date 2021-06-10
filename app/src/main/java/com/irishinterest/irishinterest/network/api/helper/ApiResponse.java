package com.irishinterest.irishinterest.network.api.helper;


import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "response",
        "token"
})
public class ApiResponse<T extends Response> {
        @JsonProperty("response")
        private List<T> response = null;
        @JsonProperty("token")
        private String token;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        @JsonProperty("response")
        public List<T> getResponse() {
            return response;
        }

        @JsonProperty("response")
        public void setResponse(List<T> response) {
            this.response = response;
        }

        @JsonProperty("token")
        public String getToken() {
            return token;
        }

        @JsonProperty("token")
        public void setToken(String token) {
            this.token = token;
        }

        @JsonAnyGetter
        public Map<String, Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        @JsonAnySetter
        public void setAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
        }
}
