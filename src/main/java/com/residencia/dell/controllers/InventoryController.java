package com.residencia.dell.controllers;

import com.residencia.dell.entities.Inventory;
import com.residencia.dell.services.InventoryService;
import com.residencia.dell.vo.InventoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/{id}")
    public ResponseEntity<Inventory> findById(@PathVariable Integer id) {
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(inventoryService.findById(id), headers, HttpStatus.OK);
    }

    @GetMapping("/listar-inventory")
    public ResponseEntity<List<InventoryVO>> findAllVO(
            @RequestParam(required = false) Integer pagina,
            @RequestParam(required = false) Integer qtdRegistros)
            throws Exception {

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(inventoryService.findAllVO(pagina,
                qtdRegistros), headers, HttpStatus.OK);
    }

    @GetMapping("/count")
    public Long count() {
        return inventoryService.count();
    }

    @PostMapping
    public ResponseEntity<InventoryVO> save(@RequestBody InventoryVO inventoryVO) {
        HttpHeaders headers = new HttpHeaders();

        InventoryVO novoInventoryVO = inventoryService.saveVO(inventoryVO);
        if (null != novoInventoryVO)
            return ResponseEntity.ok().body(novoInventoryVO);
        else
            return new ResponseEntity<>(inventoryService.saveVO(novoInventoryVO), headers, HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Inventory> update(@PathVariable Integer id, @RequestBody Inventory inventory) {
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(inventoryService.update(id, inventory), headers, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Inventory> delete(@PathVariable Integer id) {
        try {
            inventoryService.delete(id);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

//    @GetMapping
//    public ResponseEntity<List<Inventory>> findAll(
//            @RequestParam(required = false) Integer pagina,
//            @RequestParam(required = false) Integer qtdRegistros)
//            throws Exception {
//
//        HttpHeaders headers = new HttpHeaders();
//        return new ResponseEntity<>(inventoryService.findAll(pagina,
//                qtdRegistros), headers, HttpStatus.OK);
//    }
}