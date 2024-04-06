package com.example.gemini.Controller;

import edu.gemini.app.ocs.OCS;
import edu.gemini.app.ocs.example.MySciencePlan;
import edu.gemini.app.ocs.model.DataProcRequirement;
import edu.gemini.app.ocs.model.SciencePlan;
import edu.gemini.app.ocs.model.StarSystem;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class SciecncePlanController {
    OCS ocs = new OCS(true);

    //  Get all the science plans
    @GetMapping("/getAllSP")
    public String getAllSciencePlans(Model model) {
        ArrayList<SciencePlan> sciencePlans = ocs.getAllSciencePlans();
        model.addAttribute("sciencePlans", sciencePlans);
        return "astronomer";
    }

    // create science plan
    @PostMapping("/createSP")
    public Object createSciencePlans(
            @RequestParam("creator") String creator,
            @RequestParam("submitter") String submitter,
            @RequestParam("fundingInUSD") double fundingInUSD,
            @RequestParam("objectives") String objectives,
            @RequestParam("starSystem") StarSystem.CONSTELLATIONS starSystem,
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate,
            @RequestParam("telescopeLocation") SciencePlan.TELESCOPELOC telescopeLocation,
            @RequestParam("fileType") String fileType,
            @RequestParam("fileQuality") String fileQuality,
            @RequestParam("colorType") String colorType,
            @RequestParam("contrast") double contrast,
            @RequestParam("brightness") double brightness,
            @RequestParam("saturation") double saturation,
            @RequestParam("highlights") double highlights,
            @RequestParam("exposure") double exposure,
            @RequestParam("shadows") double shadows,
            @RequestParam("whites") double whites,
            @RequestParam("blacks") double blacks,
            @RequestParam("luminance") double luminance,
            @RequestParam("hue") double hue
    ) {
        MySciencePlan mySP = new MySciencePlan();
        mySP.setCreator(creator);
        mySP.setSubmitter(submitter);
        mySP.setFundingInUSD(fundingInUSD);
        mySP.setObjectives(objectives);
        mySP.setStarSystem(StarSystem.CONSTELLATIONS.valueOf(String.valueOf(starSystem)));
        mySP.setStartDate(startDate);
        SciencePlan.TELESCOPELOC location = SciencePlan.TELESCOPELOC.valueOf(String.valueOf(telescopeLocation));
        mySP.setTelescopeLocation(location);
        mySP.setEndDate(endDate);
        DataProcRequirement dpr1 = new DataProcRequirement();
        dpr1.setFileType(fileType);
        dpr1.setFileQuality(fileQuality);
        dpr1.setColorType(colorType);
        dpr1.setContrast(contrast);
        dpr1.setBrightness(brightness);
        dpr1.setSaturation(saturation);
        dpr1.setHighlights(highlights);
        dpr1.setExposure(exposure);
        dpr1.setShadows(shadows);
        dpr1.setWhites(whites);
        dpr1.setBlacks(blacks);
        dpr1.setLuminance(luminance);
        dpr1.setHue(hue);
        mySP.setDataProcRequirements(dpr1);

        ocs.createSciencePlan(mySP);
        ocs.updateSciencePlanStatus(mySP.getPlanNo(), SciencePlan.STATUS.TESTED);
        ModelAndView modelAndView = new ModelAndView("astronomer");
        modelAndView.addObject("message", "Science Plan created successfully!");
        return new ModelAndView("redirect:/getAllSP");
    }


    //  Get id the science plans
    @PostMapping("/byidSP")
    public ModelAndView getByIdSciencePlans(
            @RequestParam("id") int id) {
        SciencePlan mySP = ocs.getSciencePlanByNo(id);
        ModelAndView modelAndView = new ModelAndView("astronomer");
        modelAndView.addObject("sciencePlansTocontrollerByID", mySP);
        return modelAndView;
    }
    //  submit the science plans
    @PostMapping("/submitSP")
    public ModelAndView submitSciencePlans(
            @RequestParam("id") int id) {
        SciencePlan mySP = ocs.getSciencePlanByNo(id);
        ocs.updateSciencePlanStatus(mySP.getPlanNo(), SciencePlan.STATUS.TESTED);
        String submitSP = ocs.submitSciencePlan(mySP);
        System.out.println(submitSP);
        ModelAndView modelAndView = new ModelAndView("astronomer");
        modelAndView.addObject("submitSPByID", mySP);
        // Show บน table getall
//        return new ModelAndView("redirect:/getAllSP");
        // Show เเค่ที่ ID นั้นๆ
        return modelAndView;
    }


}


