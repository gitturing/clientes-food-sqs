package edu.example.compratodosqs.controller;

import edu.example.compratodosqs.model.Clientes;
import edu.example.compratodosqs.model.SqsClienteResponse;
import edu.example.compratodosqs.services.ClienteSQSServices;
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
