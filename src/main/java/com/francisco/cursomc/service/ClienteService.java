package com.francisco.cursomc.service;

import com.francisco.cursomc.model.Cliente;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ClienteService {

    Map<Integer, Cliente> mapa = new HashMap<>();
    private int cont;

    public Map<Integer, Cliente> salvaCliente(Cliente cliente){
        mapa.put(cont, cliente);
        cont++;
        return mapa;
    }

    public Cliente retornaCliente(Integer i){
        return mapa.get(i);
    }

    public void atualizaCliente(Integer integer, Cliente cliente){
        mapa.replace(integer, cliente);
    }

}
