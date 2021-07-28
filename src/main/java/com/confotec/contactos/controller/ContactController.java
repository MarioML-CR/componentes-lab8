package com.confotec.contactos.controller;

import com.confotec.contactos.domian.Contact;
import com.confotec.contactos.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@ResponseBody
@RequestMapping({"/contacts"})
public class ContactController {
    @Autowired
    private ContactService contactService;

    @GetMapping
    public List getAll(){
        return contactService.getAll();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<Contact> findById(@PathVariable long id) {
        Optional<Contact> result = contactService.findById(id);
        if (result.isPresent()) {
            return ResponseEntity.ok().body(result.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Contact create(@RequestBody Contact contact) {
        return contactService.save(contact).get();
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<Contact> update(@PathVariable("id") long id, @RequestBody Contact contact) {
        Optional<Contact> result = contactService.update(contact);
        contact.setId(id);
        if (result.isPresent()) {
            return ResponseEntity.ok().body(result.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id) {
        if (contactService.delete(id)){
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
