package com.example.banco.controller;

import com.example.banco.dto.ClienteDTO;
import com.example.banco.dto.PaginaDTO;
import com.example.banco.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private IClienteService clienteService;

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> findById(@PathVariable("id")Integer id){
        ClienteDTO clientebuscada = clienteService.findClienteById(id);
        return new ResponseEntity<>(clientebuscada, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<ClienteDTO> create(@RequestBody ClienteDTO clientedto){
        ClienteDTO respuestaCliente = clienteService.createCliente(clientedto);
        return new ResponseEntity<>(respuestaCliente, HttpStatus.OK);
    }


    @PutMapping
    public ResponseEntity<ClienteDTO> update(@RequestBody ClienteDTO clienteDTO){
        ClienteDTO newClienteDTO = clienteService.updateCliente(clienteDTO);
        return new ResponseEntity<>(newClienteDTO, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id")Integer id){
        clienteService.deleteClienteById(id);
        return new ResponseEntity<>("Cliente Eliminado", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<PaginaDTO<ClienteDTO>> findAll(
            @RequestParam(value = "pagina", required = false)Integer numeroPagina,
            @RequestParam(value = "tamanio", required = false)Integer tamanioPagina,
            HttpServletRequest request
    ) {

        PaginaDTO<ClienteDTO> paginaClientes;
        paginaClientes = clienteService.findAllClientes(numeroPagina, tamanioPagina);

        String url= request.getRequestURL().toString();
        return new ResponseEntity<>(paginaClientes, HttpStatus.OK);
    }
}

