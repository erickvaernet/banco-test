package com.example.banco.controller;

import com.example.banco.dto.ClienteDTO;
import com.example.banco.dto.PaginaDTO;
import com.example.banco.dto.validationinterface.CreateCliente;
import com.example.banco.dto.validationinterface.UpdateCliente;
import com.example.banco.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<ClienteDTO> create(@RequestBody @Validated(CreateCliente.class) ClienteDTO createClienteDTO){
        ClienteDTO respuestaCliente = clienteService.createCliente(createClienteDTO);
        return new ResponseEntity<>(respuestaCliente, HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> update(@PathVariable("id") Integer id ,
                                             @RequestBody @Validated(UpdateCliente.class) ClienteDTO updateClienteDTO){
        ClienteDTO newClienteDTO = clienteService.updateCliente(id, updateClienteDTO);
        return new ResponseEntity<>(newClienteDTO, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ClienteDTO> updateOneField(@PathVariable("id") Integer id ,
                                             @RequestBody @Validated(UpdateCliente.class) ClienteDTO updateClienteDTO){
        ClienteDTO newClienteDTO = clienteService.updateCliente(id, updateClienteDTO);
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
            @RequestParam(value = "tamanio", required = false)Integer tamanioPagina
    ) {
        PaginaDTO<ClienteDTO> paginaClientes;
        paginaClientes = clienteService.findAllClientes(numeroPagina, tamanioPagina);
        return new ResponseEntity<>(paginaClientes, HttpStatus.OK);
    }
}

