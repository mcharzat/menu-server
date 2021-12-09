package com.cicdlectures.menuserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Console;

import com.cicdlectures.menuserver.exception.IdNotFoundException;
import com.cicdlectures.menuserver.repository.MenuRepository;

@Service
public class DeleteMenuService {
    
    private final MenuRepository menuRepository;

    @Autowired
    public DeleteMenuService(MenuRepository menuRepository) {
      this.menuRepository = menuRepository;
    }

    public void deleteMenu(long id) throws IdNotFoundException{
      if (menuRepository.existsById(id)) menuRepository.deleteById(id);
      else throw new IdNotFoundException(id);
    }
}
