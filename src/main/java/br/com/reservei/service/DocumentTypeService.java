package br.com.reservei.service;

import br.com.reservei.dto.DocumentTypeDto;
import br.com.reservei.entity.DocumentType;
import br.com.reservei.entity.Gender;
import br.com.reservei.repository.DocumentTypeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class DocumentTypeService {

    public final DocumentTypeRepository documentTypeRepository;

    @Autowired
    public DocumentTypeService(DocumentTypeRepository documentTypeRepository) {
        this.documentTypeRepository = documentTypeRepository;
    }

    public DocumentType saveDocumentType(DocumentType documentType) {
        return documentTypeRepository.save(documentType);
    }

    public Optional<DocumentType> getDocumentTypeById(Integer id){
        return documentTypeRepository.findById(id);
    }

    public List<DocumentType> getAllDocumentType(){
        return documentTypeRepository.findAll();
    }

    public ResponseEntity<Object> updateDocumentType(Integer id, DocumentTypeDto documentTypeDto) {
        Optional<DocumentType> documentTypeOptional = documentTypeRepository.findById(id);

        if (documentTypeOptional.isEmpty()){
            Map<String, String> response = new HashMap<>();
            response.put("mensagem", "Nao foi encotrado");

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        DocumentType newDocument = documentTypeOptional.get();
        BeanUtils.copyProperties(documentTypeDto, newDocument);
        return ResponseEntity.status(HttpStatus.OK).body(documentTypeRepository.save(newDocument));
    }

    public void deletedocumentType(Integer id) {
        if (!documentTypeRepository.existsById(id)) {
            throw new RuntimeException("Document type not found with id " + id);
        }
        documentTypeRepository.deleteById(id);
    }
}
