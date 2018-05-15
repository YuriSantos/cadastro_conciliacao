package br.jus.jfpb.cadastro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LayoutController {
    @RequestMapping(method = RequestMethod.GET, value ="top-menu")
    public String topmenu(ModelMap map) {
        String URL_BASE = "cadastro_conciliacao";
        map.addAttribute("BaseURL",URL_BASE);
        return "/layout/top-menu";
    }
    @RequestMapping(method = RequestMethod.GET, value ="left-menu")
    public String leftmenu(ModelMap map) {
        return "/layout/left-menu";
    }
    @RequestMapping(method = RequestMethod.GET, value ="rodape")
    public String rodape(ModelMap map) {
        return "/layout/rodape";
    }

}
