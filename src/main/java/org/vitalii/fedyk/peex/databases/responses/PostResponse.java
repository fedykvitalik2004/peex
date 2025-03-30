package org.vitalii.fedyk.peex.databases.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class PostResponse {
    private String id;
    private String title;
    private String content;
    private String userId;
}
