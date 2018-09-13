package com.francisco.cursomc.controllers;

import com.francisco.cursomc.model.Cliente;
import com.francisco.cursomc.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @GetMapping
    public Cliente testaApi(){
        return clienteService.retornaCliente();
    }

    @PostMapping
    public Map<Integer, Cliente> salvaCliente(@RequestBody Cliente cliente){
        return clienteService.salvaCliente(cliente);
    }
}
