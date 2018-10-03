package com.francisco.cursomc.controllers;

import com.francisco.cursomc.model.Categoria;
import com.francisco.cursomc.model.Pedido;
import com.francisco.cursomc.service.CategoriaService;
import com.francisco.cursomc.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Pedido> find(@PathVariable Integer id){
        Pedido obj = pedidoService.find(id);

        return ResponseEntity.ok().body(obj);
    }
}
