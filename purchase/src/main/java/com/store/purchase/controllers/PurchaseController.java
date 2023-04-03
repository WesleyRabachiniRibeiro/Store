package com.store.purchase.controllers;

import com.store.purchase.models.dtos.purchase.PurchaseByUserDTO;
import com.store.purchase.models.dtos.purchase.PurchaseDTO;
import com.store.purchase.models.DataPresenter;
import com.store.purchase.models.ErrorPresenter;
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
        service.purchase(dto);
        return ResponseEntity.ok().build();
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
    public ResponseEntity<DataPresenter<List<PurchaseByUserDTO>>> findPurchasesByUser(@PathVariable("id") Long userId) {
        return ResponseEntity.ok(new DataPresenter(service.findPurchasesByUser(userId)));
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
    public ResponseEntity<DataPresenter<PurchaseByUserDTO>> findPurchaseOfUser(@PathVariable("userId") Long userId, @PathVariable("purchaseId") String id) {
        return ResponseEntity.ok(new DataPresenter(service.findPurchaseOfUser(userId, id)));
    }

}
