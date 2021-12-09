package com.cicdlectures.menuserver.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.cicdlectures.menuserver.dto.DishDto;
import com.cicdlectures.menuserver.dto.MenuDto;
import com.cicdlectures.menuserver.model.Dish;
import com.cicdlectures.menuserver.model.Menu;
import com.cicdlectures.menuserver.repository.MenuRepository;

public class ListMenuServiceTests {

    private MenuRepository menuRepository;

    private ListMenuService subject;

    @BeforeEach
    public void init() {
    this.menuRepository = mock(MenuRepository.class);
    this.subject = new ListMenuService(this.menuRepository);
    }
  
    @Test
    @DisplayName("lists all known menus")
    public void listsKnownMenus() {
        Iterable<Menu> existingMenu = Arrays.asList(
            new Menu(
                Long.valueOf(1),
                "Christmas menu",
                new HashSet<>(
                    Arrays.asList(
                    new Dish(Long.valueOf(1), "Turkey", null),
                    new Dish(Long.valueOf(2), "Pecan Pie", null)
                    )
                )
            )
        );

        when(this.menuRepository.findAll()).thenReturn(existingMenu);

        List<MenuDto> listMenu = subject.listMenus();

        Iterable<MenuDto> expectedMenu = Arrays.asList(
            new MenuDto(
                Long.valueOf(1),
                "Christmas menu",
                new HashSet<>(
                    Arrays.asList(
                    new DishDto(Long.valueOf(1), "Turkey"),
                    new DishDto(Long.valueOf(2), "Pecan Pie")
                    )
                )
            )
        );
        assertEquals(expectedMenu, listMenu);
    }
}
