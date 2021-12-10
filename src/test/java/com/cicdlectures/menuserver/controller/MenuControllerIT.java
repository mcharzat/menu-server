package com.cicdlectures.menuserver.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import com.cicdlectures.menuserver.dto.DishDto;
import com.cicdlectures.menuserver.dto.MenuDto;
import com.cicdlectures.menuserver.model.Dish;
import com.cicdlectures.menuserver.model.Menu;
import com.cicdlectures.menuserver.repository.MenuRepository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

// Lance l'application sur un port aléatoire.
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// Indique de relancer l'application à chaque test.
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)

public class MenuControllerIT {

    // Injecte automatiquement l'instance du menu repository
    @Autowired
    private MenuRepository menuRepository;

    @LocalServerPort
    private int port;

    // Injecte automatiquement l'instance du TestRestTemplate
    @Autowired
    private TestRestTemplate template;

    private URL getMenusURL() throws Exception {
        return new URL("http://localhost:" + port + "/menus");
    }

    @Test
    @DisplayName("lists all known menus")
    public void listExitingMenus() throws Exception {
        Menu newMenu = new Menu(
            null,
            "Menu spécial du chef",
            new HashSet<>(
                Arrays.asList(
                new Dish(null, "Bananes aux fraises", null),
                new Dish(null, "Bananes flambées", null)
                )
            )
        );

        MenuDto expectedMenu = new MenuDto(
            Long.valueOf(1),
            "Menu spécial du chef",
            new HashSet<>(
                Arrays.asList(
                new DishDto(Long.valueOf(1), "Bananes aux fraises"),
                new DishDto(Long.valueOf(2), "Bananes flambées")
                )
            )
        );

        menuRepository.save(newMenu);

        // Effectue une requête GET /menus
        ResponseEntity<MenuDto[]> response = this.template.getForEntity(getMenusURL().toString(), MenuDto[].class);

        //Parse le payload de la réponse sous forme d'array de MenuDto
        MenuDto[] gotMenus = response.getBody();
        int status = response.getStatusCodeValue();

        assertEquals(200, status);
        assertEquals(1, gotMenus.length);
        assertEquals(expectedMenu, gotMenus[0]);
    }
}
