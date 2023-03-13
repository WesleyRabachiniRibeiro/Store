package com.store.purchase.controllers;

import com.store.purchase.dtos.purchase.PurchaseByUserDTO;
import com.store.purchase.dtos.purchase.PurchaseDTO;
import com.store.purchase.dtos.ErrorPresenter;
import com.store.purchase.services.PurchaseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Purchase")
@RestController
@RequestMapping("/v1/purchases")
public class PurchaseController {

    @Autowired
    private PurchaseService service;

    @PostMapping
    @Operation(summary = "Save Purchase in Database")
    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description = "OK"),
            @ApiResponse(responseCode  = "400", description = "The request find a error",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorPresenter.class)) }),
            @ApiResponse(responseCode  = "403", description = "You don't have permission to access",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorPresenter.class)) }),
            @ApiResponse(responseCode  = "404", description = "Content Not Found",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorPresenter.class)) }),
            @ApiResponse(responseCode  = "500", description = "The server have a error",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorPresenter.class)) })
    })
    public ResponseEntity buy(@RequestBody PurchaseDTO dto) {
        try {
            service.purchase(dto);
            return ResponseEntity.ok().build();
        }catch (RuntimeException err) {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "find all product of an user")
    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description = "OK", content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = PurchaseByUserDTO.class))) }),
            @ApiResponse(responseCode  = "400", description = "The request find a error",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorPresenter.class)) }),
            @ApiResponse(responseCode  = "403", description = "You don't have permission to access",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorPresenter.class)) }),
            @ApiResponse(responseCode  = "404", description = "Content Not Found",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorPresenter.class)) }),
            @ApiResponse(responseCode  = "500", description = "The server have a error",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorPresenter.class)) })
    })
    public ResponseEntity<List<PurchaseByUserDTO>> findPurchasesByUser(@PathVariable("id") Long userId) {
        try {
            return ResponseEntity.ok(service.findPurchasesByUser(userId));
        }catch (RuntimeException err) {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/{userId}/{purchaseId}")
    @Operation(summary = "Find purchase of specific user")
    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description = "OK", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = PurchaseByUserDTO.class)) }),
            @ApiResponse(responseCode  = "400", description = "The request find a error",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorPresenter.class)) }),
            @ApiResponse(responseCode  = "403", description = "You don't have permission to access",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorPresenter.class)) }),
            @ApiResponse(responseCode  = "404", description = "Content Not Found",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorPresenter.class)) }),
            @ApiResponse(responseCode  = "500", description = "The server have a error",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorPresenter.class)) })
    })
    public ResponseEntity<PurchaseByUserDTO> findPurchaseOfUser(@PathVariable("userId") Long userId, @PathVariable("purchaseId") String id) {
        try {
            return ResponseEntity.ok(service.findPurchaseOfUser(userId, id));
        }catch (RuntimeException err) {
            return ResponseEntity.noContent().build();
        }
    }

}
