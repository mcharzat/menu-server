package com.cicdlectures.menuserver.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

import com.cicdlectures.menuserver.dto.MenuDto;
import com.cicdlectures.menuserver.service.CreateMenuService;
import com.cicdlectures.menuserver.service.DeleteMenuService;
import com.cicdlectures.menuserver.service.ListMenuService;

@RestController
public class MenuController {

  private final CreateMenuService createMenuService;

  private final DeleteMenuService deleteMenuService;

  private final ListMenuService listMenuService;

  @Autowired
  MenuController(CreateMenuService createMenuService, DeleteMenuService deleteMenuService, ListMenuService listMenuService) {
    this.createMenuService = createMenuService;
    this.deleteMenuService = deleteMenuService;
    this.listMenuService = listMenuService;
  }

  @GetMapping(path = "/menus", produces = "application/json")
  public List<MenuDto> listMenus() {
    return listMenuService.listMenus();
  }

  @PostMapping(path = "/menus", produces = "application/json", consumes = "application/json")
  @ResponseStatus(HttpStatus.CREATED)
  public MenuDto createMenu(@RequestBody MenuDto menu) {
    return createMenuService.createMenu(menu);
  }

  @DeleteMapping(path = "/menus/{id}")
  @ResponseStatus(HttpStatus.OK)
  public void deleteMenu(@PathVariable(value = "id") long  id) {
    deleteMenuService.deleteMenu(id);
  }
}
