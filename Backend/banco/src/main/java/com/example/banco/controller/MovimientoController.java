package com.example.banco.controller;

import com.example.banco.dto.MovimientoDTO;
import com.example.banco.dto.PaginaDTO;
import com.example.banco.dto.validationinterface.CreateMovimiento;
import com.example.banco.dto.validationinterface.UpdateMovimiento;
import com.example.banco.service.IMovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {
    @Autowired
    private IMovimientoService movimientoService;

    @GetMapping("/{id}")
    public ResponseEntity<MovimientoDTO> findById(@PathVariable("id")Integer id){
        MovimientoDTO movimientoDTO = movimientoService.findMovimientoById(id);
        return new ResponseEntity<>(movimientoDTO, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<MovimientoDTO> create(@RequestBody @Validated(CreateMovimiento.class)
                                                    MovimientoDTO createMovimientoDTO){
        MovimientoDTO respuestaMovimiento = movimientoService.createMovimiento(createMovimientoDTO);
        return new ResponseEntity<>(respuestaMovimiento, HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<MovimientoDTO> updatePut(@PathVariable("id") Integer id,
                                                   @RequestBody @Validated(UpdateMovimiento.class)
                                                    MovimientoDTO updateMovimientoDTO){
        MovimientoDTO newMovimientoDTO = movimientoService.updateMovimiento(id,updateMovimientoDTO);
        return new ResponseEntity<>(newMovimientoDTO, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<MovimientoDTO> updatePatch(@PathVariable("id") Integer id,
                                                     @RequestBody @Validated(UpdateMovimiento.class)
                                                MovimientoDTO updateMovimientoDTO){
        MovimientoDTO newMovimientoDTO = movimientoService.updateMovimiento(id,updateMovimientoDTO);
        return new ResponseEntity<>(newMovimientoDTO, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id")Integer id){
        movimientoService.deleteMovimientoById(id);
        return new ResponseEntity<>("Movimiento Eliminado", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<PaginaDTO<MovimientoDTO>> findAll(
            @RequestParam(value = "pagina", required = false)Integer numeroPagina,
            @RequestParam(value = "tamanio", required = false)Integer tamanioPagina
    ) {
        PaginaDTO<MovimientoDTO> paginaMovimientos;
        paginaMovimientos = movimientoService.findAllMovimientos(numeroPagina, tamanioPagina);
        return new ResponseEntity<>(paginaMovimientos, HttpStatus.OK);
    }

}
