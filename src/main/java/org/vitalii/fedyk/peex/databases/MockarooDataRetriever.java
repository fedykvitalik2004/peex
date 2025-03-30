package org.vitalii.fedyk.peex.databases;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.vitalii.fedyk.peex.databases.responses.PostResponse;
import org.vitalii.fedyk.peex.databases.responses.UserResponse;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MockarooDataRetriever {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private String GENERATE_URL = "https://api.mockaroo.com/api/generate.json?";

    public MockarooDataRetriever(final String api_key) {
        GENERATE_URL = GENERATE_URL + "key=" + api_key;
    }

    public List<UserResponse> generateUsers(final int count) {
        final String USER_RULE = """
                [
                  { "name": "id", "type": "Sequence", "repeat": 1, "start": 1, "step": 1 },
                  { "name": "username", "type": "Username" },
                  { "name": "email", "type": "Email Address" },
                  { "name": "password", "type": "Password" },
                  { "name": "created", "type": "Datetime" }
                ]
                """;
        return sendPostRequest(getCount(count), USER_RULE, new TypeReference<List<UserResponse>>() {
        });
    }

    public List<PostResponse> generatePosts(final int count, List<Integer> userIdList) {
        String jsonBody = createPostJsonBody(userIdList);
        return sendPostRequest(getCount(count), jsonBody, new TypeReference<List<PostResponse>>() {
        });
    }

    private String createPostJsonBody(final List<Integer> userIdList) {
        final String POST_RULE = """
                [
                   {
                     "name": "id",
                     "type": "Sequence",
                     "repeat": 1,
                     "start": 1,
                     "step": 1
                   },
                   {
                     "name": "title",
                     "type": "Title"
                   },
                   {
                     "name": "content",
                     "type": "Paragraphs",
                     "min": 1,
                     "max": 3
                   },
                   {
                     "name": "userId",
                     "type": "Custom List",
                     "values": %s
                   }
                ]
                """;
        return POST_RULE.formatted(getUserIdListRepresentation(userIdList));
    }

    private void setHeaders(HttpPost httpPost) {
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setHeader("Accept", "application/json");
    }

    private <T> List<T> sendPostRequest(String countParam, String jsonBody, TypeReference<?> typeReference) {
        try (final CloseableHttpClient httpClient = HttpClients.createDefault()) {
            final HttpPost httpPost = new HttpPost(GENERATE_URL + "&" + countParam);
            setHeaders(httpPost);

            final StringEntity entity = new StringEntity(jsonBody);
            httpPost.setEntity(entity);

            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                final String responseBody = EntityUtils.toString(response.getEntity());
                return (List<T>) objectMapper.readValue(responseBody, typeReference);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    private String getCount(final int count) {
        return "count=" + count;
    }

    private String getUserIdListRepresentation(final List<Integer> list) {
        return "[" + list.stream().map(o -> "\"" + o + "\"").collect(Collectors.joining(", ")) + "]";
    }
}
