package com.example.banco.controller;

import com.example.banco.dto.MovimientoDTO;
import com.example.banco.dto.PaginaDTO;
import com.example.banco.service.IMovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {
    @Autowired
    private IMovimientoService movimientoService;

    @GetMapping("/{id}")
    public ResponseEntity<MovimientoDTO> findById(@PathVariable("id")Integer id){
        MovimientoDTO movimientobuscada = movimientoService.findMovimientoById(id);
        return new ResponseEntity<>(movimientobuscada, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<MovimientoDTO> create(@RequestBody MovimientoDTO movimientodto){
        MovimientoDTO respuestaMovimiento = movimientoService.createMovimiento(movimientodto);
        return new ResponseEntity<>(respuestaMovimiento, HttpStatus.OK);
    }


    @PutMapping
    public ResponseEntity<MovimientoDTO> update(@RequestBody MovimientoDTO movimientoDTO){
        MovimientoDTO newMovimientoDTO = movimientoService.updateMovimiento(movimientoDTO);
        return new ResponseEntity<>(newMovimientoDTO, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id")Integer id){
        movimientoService.deleteMovimientoById(id);
        return new ResponseEntity<>("Movimiento Eliminado", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<PaginaDTO<MovimientoDTO>> findAll(
            @RequestParam(value = "pagina", required = false)Integer page,
            @RequestParam(value = "tamanio", required = false)Integer size,
            HttpServletRequest request
    ) {

        PaginaDTO<MovimientoDTO> paginaMovimientos;
        paginaMovimientos = movimientoService.findAllMovimientos(page, size);

        String url= request.getRequestURL().toString();
        return new ResponseEntity<>(paginaMovimientos, HttpStatus.OK);
    }

}
