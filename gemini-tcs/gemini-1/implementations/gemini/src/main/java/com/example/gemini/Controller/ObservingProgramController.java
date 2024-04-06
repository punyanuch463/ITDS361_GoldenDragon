package com.example.gemini.Controller;

import edu.gemini.app.ocs.OCS;
import edu.gemini.app.ocs.model.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ObservingProgramController {
    OCS ocs = new OCS(true);

    // Create ObservingProgram
    @PostMapping("/createop")
    public ModelAndView createObservingProgram(
            @RequestParam("planNo") int planNo,
            @RequestParam("opticsPrimary") String opticsPrimary,
            @RequestParam("fStop") double fStop,
            @RequestParam("opticsSecondaryRMS") double opticsSecondaryRMS,
            @RequestParam("scienceFoldMirrorDegree") double scienceFoldMirrorDegree,
            @RequestParam("scienceFoldMirrorType") String scienceFoldMirrorType,
            @RequestParam("moduleContent") int moduleContent,
            @RequestParam("calibrationUnit") String calibrationUnit,
            @RequestParam("lightType") String lightType,
            @RequestParam("telePosition1X") double telePosition1X,
            @RequestParam("telePosition1Y") double telePosition1Y,
            @RequestParam("telePosition2X") double telePosition2X,
            @RequestParam("telePosition2Y") double telePosition2Y,
            @RequestParam("telePosition3X") double telePosition3X,
            @RequestParam("telePosition3Y") double telePosition3Y,
            @RequestParam("telePosition4X") double telePosition4X,
            @RequestParam("telePosition4Y") double telePosition4Y,
            @RequestParam("telePosition5X") double telePosition5X,
            @RequestParam("telePosition5Y") double telePosition5Y
    ) {
        TelePositionPair[] telePositionPairs = {
                new TelePositionPair(telePosition1X, telePosition1Y),
                new TelePositionPair(telePosition2X, telePosition2Y),
                new TelePositionPair(telePosition3X, telePosition3Y),
                new TelePositionPair(telePosition4X, telePosition4Y),
                new TelePositionPair(telePosition5X, telePosition5Y)
        };
        SciencePlan mySciencePlan = ocs.getSciencePlanByNo(planNo);
        ObservingProgram op =  ocs.createObservingProgram(
                mySciencePlan,
                opticsPrimary,
                fStop,
                opticsSecondaryRMS,
                scienceFoldMirrorDegree,
                ObservingProgramConfigs.FoldMirrorType.valueOf(String.valueOf(scienceFoldMirrorType)),
                moduleContent,
                ObservingProgramConfigs.CalibrationUnit.valueOf(String.valueOf(calibrationUnit)),
                ObservingProgramConfigs.LightType.valueOf(String.valueOf(lightType)),
                telePositionPairs);
        op.validateObservingCondition(op);
        ocs.saveObservingProgram(op);
        List<ObservingProgram> allObservingPrograms = new ArrayList<>();
        ArrayList<SciencePlan> sciencePlans = ocs.getAllSciencePlans();
        for (SciencePlan sp : sciencePlans) {
            ObservingProgram observingProgram = ocs.getObservingProgramBySciencePlan(sp);
            if (observingProgram != null) {
                allObservingPrograms.add(observingProgram);
            }
        }
        ModelAndView modelAndView = new ModelAndView("scienceobserver");
        modelAndView.addObject("observingPrograms", allObservingPrograms);
        return modelAndView;
    }


}
