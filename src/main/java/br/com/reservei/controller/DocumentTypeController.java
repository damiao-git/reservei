package br.com.reservei.controller;

import br.com.reservei.dto.DocumentTypeDto;
import br.com.reservei.entity.DocumentType;
import br.com.reservei.entity.User;
import br.com.reservei.service.DocumentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/document_type")
public class DocumentTypeController {

    public final DocumentTypeService documentTypeService;

    @Autowired
    public DocumentTypeController(DocumentTypeService documentTypeService){
        this.documentTypeService = documentTypeService;
    }

    @PostMapping
    public ResponseEntity<DocumentType> createDocumentType(@RequestBody DocumentType documentType) {
        DocumentType createdDocumentType = documentTypeService.saveDocumentType(documentType);
        return ResponseEntity.ok(createdDocumentType);
    }

    @GetMapping
    public ResponseEntity<List<DocumentType>> getAll(){
        List<DocumentType> documentTypes = documentTypeService.getAllDocumentType();
        return ResponseEntity.ok(documentTypes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocumentType> getDocumentById(@PathVariable(value = "id") Integer id){

        return documentTypeService.getDocumentTypeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateDocumentType(@PathVariable Integer id, @RequestBody DocumentTypeDto documentTypeDto) {
        return documentTypeService.updateDocumentType(id, documentTypeDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDocumentType(@PathVariable Integer id){
        documentTypeService.deletedocumentType(id);
        return ResponseEntity.noContent().build();
    }

}
