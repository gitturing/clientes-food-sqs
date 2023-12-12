package edu.example.compratodosqs.services;

import com.google.gson.Gson;
import edu.example.compratodosqs.model.Clientes;
import edu.example.compratodosqs.model.SqsClienteResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.GetQueueUrlRequest;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;
import software.amazon.awssdk.services.sqs.model.SqsException;

@Slf4j
@RequiredArgsConstructor
@Service
public class ClienteSQSServices {

    @Value("${aws.service-queue-name}")
    private String queue;

    private final SqsClient sqsClient;

    public Mono<SqsClienteResponse> publishClientQueueMessage(Integer delaySeconds, Clientes clientes) {
        try {
            Gson gson = new Gson();
            String message = gson.toJson(clientes);

            String queueUrl = sqsClient.getQueueUrl(GetQueueUrlRequest.builder().queueName(queue).build()).queueUrl();
            SendMessageRequest sendMessageRequest = SendMessageRequest.builder()
                    .queueUrl(queueUrl)
                    .delaySeconds(delaySeconds)
                    .messageBody(message)
                    .build();

            String messageId = sqsClient.sendMessage(sendMessageRequest).messageId();

            if (messageId != null) {
                return Mono.just(SqsClienteResponse.builder()
                        .messageId(messageId)
                        .status("Ok")
                        .build());
            }
        } catch (SqsException e) {
            log.error("Error al enviar el mensaje a la cola SQS", e);
        }
        return Mono.empty();
    }
}
