package ru.rirt.mvcHtml5Angular.mvc.Web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Leonid Malygin on 27.07.2017.
 * lenyamalygin@gmail.com
 */
@Controller
public class WebController {
    @Autowired
    WebService webService;

    //--------------------------------------MAIN PAGE-----------------------------------------------

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView getData() {
        return new ModelAndView("/home", "resultObject", webService.generateAnswer_Home());
    }

    @RequestMapping(value = "/tasks", method = RequestMethod.GET)
    public ModelAndView getTasks() {
        return new ModelAndView("/tasks", "resultObject", webService.generateAnswer_Tasks());
    }

    @RequestMapping(value = "/unstable", method = RequestMethod.GET)
    public ModelAndView getUnstable() {
        return new ModelAndView("/unstable", "resultObject", webService.generateAnswer_Unstable());
    }
    //---------------------------------------TASK PAGE----------------------------------------------

    @RequestMapping(value = "/addNewTaskButton", method = RequestMethod.GET)
    public ModelAndView addNewTaskButton() {
        return new ModelAndView("/updateTask");
    }


    //--------------------------------------TASK FORM------------------------------------------------


    @RequestMapping(value = "/addNewTask", method = RequestMethod.GET)
    public ModelAndView addNewTask(@RequestParam("tableName") String tableName,
                                   @RequestParam("cmd") String cmd,
                                   @RequestParam("channel") int channel,
                                   @RequestParam("startTime") int startTime,
                                   @RequestParam("endTime") int endTime) {
        return new ModelAndView("/tasks", "resultObject", webService.addNewTask(tableName, cmd, channel, startTime, endTime));
    }

    @RequestMapping(value = "/removeTask", method = RequestMethod.GET)
    public ModelAndView changeTask(@RequestParam("id") int id) {
        return new ModelAndView("/tasks", "resultObject", webService.removeTask(id));
    }

    //-------------------------------------UNSTABLE FORM------------------------------------------------

    @RequestMapping(value = "/addChannelForEtalon", method = RequestMethod.GET)
    public ModelAndView addChannel(@RequestParam("addChannel") int channel) {
        return new ModelAndView("/unstable", "resultObject", webService.addChannelForEtalon(channel));
    }

    @RequestMapping(value = "/removeChannelForEtalon", method = RequestMethod.GET)
    public ModelAndView removeChannel(@RequestParam("removeChannel") int channel) {
        return new ModelAndView("/unstable", "resultObject", webService.removeChannelForEtalon(channel));
    }

    @RequestMapping(value = "/unstableStart", method = RequestMethod.GET)
    public ModelAndView unstableStart(@RequestParam("measurement") int measurement,
                                      @RequestParam("sigma") int sigma,
                                      @RequestParam("observation") int observation) {
        return new ModelAndView("/unstable", "resultObject", webService.unstableStart(measurement,observation,sigma));
    }
}
