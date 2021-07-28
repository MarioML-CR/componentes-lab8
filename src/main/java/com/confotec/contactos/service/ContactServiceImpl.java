package com.confotec.contactos.service;

import com.confotec.contactos.domian.Contact;
import com.confotec.contactos.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactServiceImpl implements ContactService{
    @Autowired
    ContactRepository contactRepository;

    @Override
    public List<Contact> getAll() {
        return contactRepository.findAll();
    }

    @Override
    public Optional<Contact> findById(long id) {
        return contactRepository.findById(id).map(record -> Optional.of(record)).orElse(Optional.empty());
    }

    @Override
    public Optional<Contact> save(Contact contact) {
        return Optional.of(contactRepository.save(contact));
    }

    @Override
    public Optional<Contact> update(Contact contact) {
        Optional<Contact> record = contactRepository.findById(contact.getId());
        if (record.isPresent()) {
            Contact data = record.get();
            data.setName((contact.getName()));
            data.setEmail(contact.getEmail());
            data.setPhone(contact.getPhone());
            return Optional.of(contactRepository.save(data));
        }
        return Optional.empty();
    }

    @Override
    public boolean delete(Long id) {
        Optional<Contact> result = contactRepository.findById(id);
        if (result.isPresent()) {
            contactRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
