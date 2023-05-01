package com.sport_objects.controllers;

import com.sport_objects.entities.HelpfulType;
import com.sport_objects.entities.PlaceHelpfulType;
import com.sport_objects.services.HelpfulTypeService;
import com.sport_objects.services.PlaceHelpfulTypeService;
import com.sport_objects.services.PlaceService;
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
@RequestMapping("/place-helpful-type")
public class PlaceHelpfulTypeController {

    @Autowired
    private PlaceHelpfulTypeService service;

    @Autowired
    private PlaceService placeService;

    @Autowired
    private HelpfulTypeService helpfulTypeService;

    @RequestMapping("{id}")
    public String index(Model model, @PathVariable(name = "id") Long id) {
        List<PlaceHelpfulType> placeHelpfulTypes = service.findPlaceHelpfulTypesByPlaceId(id);

        model.addAttribute("placeId", id);
        model.addAttribute("List", placeHelpfulTypes);
        model.addAttribute("title", "Админ | Полезные категории места  " + id);

        return "place-helpful-type/index";
    }

    @RequestMapping("{id}/add")
    public ModelAndView add(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("place-helpful-type/add");
        PlaceHelpfulType placeHelpfulType = new PlaceHelpfulType(placeService.get(id));
        mav.addObject("obj", placeHelpfulType);

        List<HelpfulType> helpfulTypes = helpfulTypeService.findAll();
        List<Long> helpfulTypesID = new ArrayList<>();
        for (HelpfulType ht : helpfulTypes)
            helpfulTypesID.add(ht.getId());

        List<PlaceHelpfulType> placeHelpfulTypes = service.findPlaceHelpfulTypesByPlaceId(id);
        for (PlaceHelpfulType pht : placeHelpfulTypes) {
            long current_id = pht.getPlace().getId();

            if (helpfulTypesID.contains(current_id)) {
                int index = helpfulTypesID.indexOf(current_id);
                helpfulTypes.remove(index);
                helpfulTypesID.remove(index);
            }
        }

        mav.getModelMap().addAttribute("placeId", id);
        mav.getModelMap().addAttribute("title", "Админ | Добавить полезную категорию для места");
        mav.getModelMap().addAttribute("helpful_types", helpfulTypes);

        return mav;
    }

    @RequestMapping(value = "{id}/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("obj") PlaceHelpfulType placeHelpfulType, @PathVariable(name = "id") Long id) {
        service.save(placeHelpfulType);

        return "redirect:/place-helpful-type/" + id;
    }

    @RequestMapping("{id}/delete/{helpful_type_id}")
    public String delete(@PathVariable(name = "id") Long id, @PathVariable(name = "helpful_type_id") Long helpful_type_id) {
        List<PlaceHelpfulType> placeHelpfulTypes = service.findPlaceHelpfulTypesByPlaceId(id);
        for (PlaceHelpfulType pht : placeHelpfulTypes) {
            if (pht.getHelpfulType().getId() == helpful_type_id) {
                service.del(pht.getId());
                break;
            }
        }

        return "redirect:/place-helpful-type/" + id;
    }

}
