package edu.example.compratodosqs.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SqsClienteResponse {

    private String status;
    private String messageId;
}
