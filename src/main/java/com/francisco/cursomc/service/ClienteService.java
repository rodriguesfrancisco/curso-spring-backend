package com.francisco.cursomc.service;

import com.francisco.cursomc.model.Cliente;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ClienteService {

    Map<Integer, Cliente> mapa = new HashMap<>();

    public Map<Integer, Cliente> salvaCliente(Cliente cliente){
        mapa.put(1, cliente);

        return mapa;
    }

    public Cliente retornaCliente(){
        return mapa.get(1);
    }

}
