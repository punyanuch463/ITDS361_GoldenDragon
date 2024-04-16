package com.example.gemini.Controller;

import com.example.gemini.Model.*;
import edu.gemini.app.ocs.OCS;
import edu.gemini.app.ocs.model.*;
import edu.gemini.app.ocs.model.SciencePlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ObservingProgramController {
    OCS ocs = new OCS(true);
    @Autowired
    private SciencePlanRepository sciencePlanRepository;
    @Autowired
    private ObservingProGramRepository observingProGramRepository;
//    @Autowired
//    private AstronomerRepository astronomerRepository;

//    @GetMapping("/getAllAstronomer")
//    public String getAllAstronomer(Model model) {
////        ArrayList<SciencePlan> sciencePlans = ocs.getAllSciencePlans();
//        ArrayList<Astronomer> Astronomers = (ArrayList<Astronomer>) astronomerRepository.findAll();
//        model.addAttribute("Astronomers", Astronomers);
//        System.out.println(Astronomers);
//        return "scienceobserverHomePage";
//        return "redirect:/getALSP";
//    }
//    @GetMapping("/getAllSP")
@GetMapping("/getALSPforobser")
    public String getAllSciencePlans(Model model) {
    ArrayList<SciencePlanModelGDDG> sciencePlans = (ArrayList<SciencePlanModelGDDG>) sciencePlanRepository.findAll();
    model.addAttribute("sciencePlans", sciencePlans);
    return "scienceobserverHomePage";
    }

    @GetMapping("/scienceobserverCreateOP")
    public String showScienceobserverCreateOP() {
        return "scienceobserverCreateOP"; // ชื่อหน้า HTML ที่ต้องการเชื่อมโยง
    }
    // Create ObservingProgram
    @PostMapping("/createop")
    public ModelAndView createObservingProgram(
            @RequestParam("planNo") int planNo,
            @RequestParam("geminiLocation") String geminiLocation,
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
        SciencePlanModelGDDG myPlan = sciencePlanRepository.findByplanNo(planNo);
            ObservingProgramModelGDDG op = new ObservingProgramModelGDDG();
            op.setPlanNo(myPlan.getPlanNo());
            op.setGeminiLocation(geminiLocation);
            op.setOpticsPrimary(opticsPrimary);
            op.setfStop(fStop);
            op.setOpticsSecondaryRMS(opticsSecondaryRMS);
            op.setScienceFoldMirrorDegree(scienceFoldMirrorDegree);
            op.setScienceFoldMirrorType(ObservingProgramConfigs.FoldMirrorType.valueOf(scienceFoldMirrorType));
            op.setModuleContent(moduleContent);
            op.setCalibrationUnit(ObservingProgramConfigs.CalibrationUnit.valueOf(calibrationUnit));
            op.setLightType(ObservingProgramConfigs.LightType.valueOf(lightType));
            op.setTelePositionPair(telePositionPairs);
        observingProGramRepository.save(op);
            op.validateObservingCondition(op);
        ocs.saveObservingProgram(op);
//        SciencePlan mySciencePlan = ocs.getSciencePlanByNo(planNo);
//        ObservingProgramModelGDDG op = (ObservingProgramModelGDDG) ocs.createObservingProgram(
//                mySciencePlan,
//                opticsPrimary,
//                fStop,
//                opticsSecondaryRMS,
//                scienceFoldMirrorDegree,
//                ObservingProgramConfigs.FoldMirrorType.valueOf(String.valueOf(scienceFoldMirrorType)),
//                moduleContent,
//                ObservingProgramConfigs.CalibrationUnit.valueOf(String.valueOf(calibrationUnit)),
//                ObservingProgramConfigs.LightType.valueOf(String.valueOf(lightType)),
//                telePositionPairs);
//        op.validateObservingCondition(op);
//        ocs.saveObservingProgram(op);
//        List<ObservingProgramModelGDDG> allObservingPrograms = new ArrayList<>();
//        ArrayList<SciencePlan> sciencePlans = ocs.getAllSciencePlans();
//        for (SciencePlan sp : sciencePlans) {
//            ObservingProgramModelGDDG observingProgram = (ObservingProgramModelGDDG) ocs.getObservingProgramBySciencePlan(sp);
//            if (observingProgram != null) {
//                allObservingPrograms.add(observingProgram);
//            }
//        }
//        ModelAndView modelAndView = new ModelAndView("scienceobserverCreateOP");
//        modelAndView.addObject("observingPrograms", allObservingPrograms);
//        return modelAndView;
//    }
//        List<ObservingProgramModelGDDG> allObservingPrograms = new ArrayList<>();
//        ArrayList<SciencePlanModelGDDG> sciencePlans = (ArrayList<SciencePlanModelGDDG>) sciencePlanRepository.findAll();
//        for (SciencePlanModelGDDG sp : sciencePlans) {
//            ObservingProgramModelGDDG observingProgram = (ObservingProgramModelGDDG) ocs.getObservingProgramBySciencePlan(sp);
//            if (observingProgram != null) {
//                allObservingPrograms.add(observingProgram);
//            }
//        }
        List<ObservingProgramModelGDDG> allObservingPrograms = new ArrayList<>();
        Iterable<ObservingProgramModelGDDG> observingProgramsIterable = observingProGramRepository.findAll();
        for (ObservingProgramModelGDDG observingProgram : observingProgramsIterable) {
            allObservingPrograms.add(observingProgram);
        }

        ModelAndView modelAndView = new ModelAndView("scienceobserverCreateOP");
        modelAndView.addObject("observingPrograms", allObservingPrograms);
        return modelAndView;



    }
}
