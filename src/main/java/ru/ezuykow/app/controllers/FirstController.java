package ru.ezuykow.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FirstController {

    @GetMapping("/startcalc")
    public String startCalcPage() {
        return "first/startcalc";
    }

    @GetMapping("/calc")
    public String calcPage(@RequestParam("a") int val1,
                           @RequestParam("b") int val2,
                           @RequestParam("action") String action,
                           Model model) {

        int result = 0;
        char a = '.';
        switch (action) {
            case "addition":
                result = val1 + val2;
                a = '+';
                break;
            case "subtraction":
                result = val1 - val2;
                a = '-';
                break;
            case "multiply":
                result = val1 * val2;
                a = '*';
                break;
            case "division":
                result = val1 / val2;
                a = '/';
                break;
        }

        model.addAttribute("result", val1 + " " + a + " " + val2 +
                " = " + result);

        return "first/calc";
    }
}
