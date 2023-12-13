package edu.example.clientessqs.controller;

import edu.example.clientessqs.model.Clientes;
import edu.example.clientessqs.model.SqsClienteResponse;
import edu.example.clientessqs.services.ClienteSQSServices;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cliente")
public class ClientesController {

    private final ClienteSQSServices clienteSQSServices;

    @PostMapping("/new")
    public Mono<SqsClienteResponse> postMessageQueue(@RequestBody Clientes clientes) {
        return clienteSQSServices.publishClientQueueMessage(10, clientes);
    }
}
