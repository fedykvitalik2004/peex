package org.vitalii.fedyk.peex.testing;

import org.junit.jupiter.api.Test;
import org.vitalii.fedyk.peex.databases.MockarooDataRetriever;
import org.vitalii.fedyk.peex.databases.responses.UserResponse;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MockarooDataRetrieverTests {
  private final MockarooDataRetriever mockarooDataRetriever = new MockarooDataRetriever("5d7dcb20"); //Bad practice. Should be hidden

  @Test
  void generateUsersTest() {
    final int count = 3;
    final List<UserResponse> users = mockarooDataRetriever.generateUsers(count);

    assertNotNull(users);
    assertEquals(count, users.size());
    users.forEach(o -> {
      assertNotNull(o.getId());
      assertNotNull(o.getPassword());
      assertNotNull(o.getEmail());
      assertNotNull(o.getUsername());
      assertNotNull(o.getCreated());
    });
  }

}
