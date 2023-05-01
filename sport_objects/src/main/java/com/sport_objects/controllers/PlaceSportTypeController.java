package com.sport_objects.controllers;

import com.sport_objects.entities.PlaceSportType;
import com.sport_objects.entities.SportType;
import com.sport_objects.services.PlaceService;
import com.sport_objects.services.PlaceSportTypeService;
import com.sport_objects.services.SportTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/place-sport-type")
public class PlaceSportTypeController {

    @Autowired
    private PlaceSportTypeService service;

    @Autowired
    private PlaceService placeService;

    @Autowired
    private SportTypeService sportTypeService;

    @RequestMapping("{id}")
    public String index(Model model, @PathVariable(name = "id") Long id) {
        List<PlaceSportType> placeSportTypes = service.findByPlaceID(id);

        model.addAttribute("placeId", id);
        model.addAttribute("List", placeSportTypes);
        model.addAttribute("title", "Админ | Виды спорта места  " + id);

        return "place-sport-type/index";
    }

    @RequestMapping("{id}/add")
    public ModelAndView add(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("place-sport-type/add");
        PlaceSportType placeSportType = new PlaceSportType(placeService.get(id));
        mav.addObject("obj", placeSportType);

        List<SportType> sportTypes = sportTypeService.findAll();
        List<Long> sportTypesID = new ArrayList<>();
        for (SportType st : sportTypes)
            sportTypesID.add(st.getId());

        List<PlaceSportType> placeSportTypes = service.findByPlaceID(id);
        for (PlaceSportType pst : placeSportTypes) {
            long current_id = pst.getPlace().getId();

            if (sportTypesID.contains(current_id)) {
                int index = sportTypesID.indexOf(current_id);
                sportTypes.remove(index);
                sportTypesID.remove(index);
            }
        }

        mav.getModelMap().addAttribute("placeId", id);
        mav.getModelMap().addAttribute("title", "Админ | Добавить вид спорта для места");
        mav.getModelMap().addAttribute("sport_types", sportTypes);

        return mav;
    }

    @RequestMapping("{id}/edit/{sport_type_id}")
    public ModelAndView addUser(@PathVariable(name = "id") Long id, @PathVariable(name = "sport_type_id") Long sport_type_id) {
        ModelAndView mav = new ModelAndView("place-sport-type/edit");
        List<PlaceSportType> placeSportTypes = service.findByPlaceID(id);
        for (PlaceSportType pst : placeSportTypes) {
            if (pst.getSportType().getId() == sport_type_id) {
                mav.addObject("obj", pst);
                break;
            }
        }

        mav.getModelMap().addAttribute("placeId", id);
        mav.getModelMap().addAttribute("title", "Админ | Редактирование вида спорта для места");

        return mav;
    }

    @RequestMapping(value = "{id}/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("obj") PlaceSportType placeSportType, @PathVariable(name = "id") Long id) {
        service.save(placeSportType);

        return "redirect:/place-sport-type/" + id;
    }

    @RequestMapping("{id}/delete/{sport_type_id}")
    public String delete(@PathVariable(name = "id") Long id, @PathVariable(name = "sport_type_id") Long sport_type_id) {
        List<PlaceSportType> placeSportTypes = service.findByPlaceID(id);
        for (PlaceSportType pst : placeSportTypes) {
            if (pst.getSportType().getId() == sport_type_id) {
                service.del(pst.getId());
                break;
            }
        }

        return "redirect:/place-sport-type/" + id;
    }

}
