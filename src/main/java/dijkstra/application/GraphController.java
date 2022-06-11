package dijkstra.application;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GraphController {

    @GetMapping
    public String homepage(){
        return "homepage";
    }
}
