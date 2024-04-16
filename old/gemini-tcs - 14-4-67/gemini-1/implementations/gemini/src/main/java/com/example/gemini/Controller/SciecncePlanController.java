package com.example.gemini.Controller;

import com.example.gemini.Model.*;
import edu.gemini.app.ocs.OCS;
import edu.gemini.app.ocs.model.SciencePlan;
import edu.gemini.app.ocs.model.StarSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class SciecncePlanController {
    OCS ocs = new OCS(true);

    @Autowired
    private SciencePlanRepository sciencePlanRepository;
    @Autowired
    private DataProcReqMentRepository dataProcReqMentRepository;
    @Autowired
    private AstronomerRepository astronomerRepository;
    //  Get all the science plans
    @GetMapping("/getAllSP")
    public String getAllSciencePlans(Model model) {
//        ArrayList<SciencePlan> sciencePlans = ocs.getAllSciencePlans();
        ArrayList<SciencePlanModelGDDG> sciencePlans = (ArrayList<SciencePlanModelGDDG>) sciencePlanRepository.findAll();
        model.addAttribute("sciencePlans", sciencePlans);
        return "astronomerHomePage";
    }

        @GetMapping("/getAllAstronomer")
    public String getAllAstronomer(Model model) {
//        ArrayList<SciencePlan> sciencePlans = ocs.getAllSciencePlans();
        ArrayList<Astronomer> Astronomers = (ArrayList<Astronomer>) astronomerRepository.findAll();
        model.addAttribute("Astronomers", Astronomers);
        System.out.println(Astronomers);
        return "astronomerCreateSP";
//        return "redirect:/getALSP";
    }


//    @GetMapping("/getAllData")
//    public String getAllData(Model model) {
//        ArrayList<Astronomer> Astronomers = (ArrayList<Astronomer>) astronomerRepository.findAll();
//        ArrayList<SciencePlanModelGDDG> sciencePlans = (ArrayList<SciencePlanModelGDDG>) sciencePlanRepository.findAll();
//
//        model.addAttribute("Astronomers", Astronomers);
//        model.addAttribute("sciencePlans", sciencePlans);
//
//        return "astronomerCreateSP";
//    }
    //  Get all the science plans
    @GetMapping("/test")
    public String all(Model model) {
        ArrayList<SciencePlan> sciencePlans = ocs.getAllSciencePlans();
//        ArrayList<SciencePlanModelGDDG> sciencePlans = (ArrayList<SciencePlanModelGDDG>) sciencePlanRepository.findAll();
        model.addAttribute("sciencePlans", sciencePlans);
        return "astronomerHomePage";
    }
//    @GetMapping("/addAllSPToDatabase")
//    public String addAllSPToDatabase(Model model) {
//        // ดึงข้อมูล Science Plan ทั้งหมดจาก ocs
//        ArrayList<SciencePlan> sciencePlansFromOCS = ocs.getAllSciencePlans();
//
//        // สร้าง List เพื่อเก็บข้อมูล SciencePlanModelGDDG ที่จะบันทึกลงในฐานข้อมูล
//        List<SciencePlanModelGDDG> sciencePlansToSave = new ArrayList<>();
//
//        // วนลูปเพื่อแปลงข้อมูลจาก ArrayList<SciencePlan> ให้เป็น List<SciencePlanModelGDDG>
//        for (SciencePlan sp : sciencePlansFromOCS) {
//            SciencePlanModelGDDG newSciencePlan = new SciencePlanModelGDDG();
//            // ทำการกopy ข้อมูลจาก sp ไปยัง newSciencePlan โดยแต่ละ attribute
////            newSciencePlan.setCreator(String.valueOf(sp.getPlanNo()));
//            newSciencePlan.getPlanNo();
//            newSciencePlan.setCreator(sp.getCreator());
//            newSciencePlan.setSubmitter(sp.getSubmitter());
//            newSciencePlan.setFundingInUSD(sp.getFundingInUSD());
//            newSciencePlan.setObjectives(sp.getObjectives());
//            newSciencePlan.setStarSystem(sp.getStarSystem());
//            newSciencePlan.setStartDate(sp.getStartDate());
//            newSciencePlan.setEndDate(sp.getEndDate());
//            newSciencePlan.setTelescopeLocation(sp.getTelescopeLocation());
//            newSciencePlan.setStatus(sp.getStatus());
//            // อื่น ๆ ตามที่ต้องการ copy จาก sp
//
//            // เพิ่ม newSciencePlan เข้าไปใน List ที่จะบันทึกลงในฐานข้อมูล
//            sciencePlansToSave.add(newSciencePlan);
//        }
//
//        // บันทึกข้อมูลลงในฐานข้อมูล
//        sciencePlanRepository.saveAll(sciencePlansToSave);
//        model.addAttribute("sciencePlans", sciencePlansToSave);
//        // ส่งกลับไปยังหน้าที่เหมาะสม (หรือส่งข้อความเพื่อแจ้งผู้ใช้)
//        return "astronomerHomePage"; // เป็นตัวอย่างเท่านั้น คุณสามารถส่งกลับไปยังหน้าอื่นๆ หรือทำอะไรก็ได้ตามต้องการ
//
//}

    @GetMapping("/astronomerCreateSP")
    public String showAstronomerCreateSPPage(  Model model) {
        ArrayList<Astronomer> Astronomers = (ArrayList<Astronomer>) astronomerRepository.findAll();
        model.addAttribute("Astronomers", Astronomers);

        return "astronomerCreateSP"; // ชื่อหน้า HTML ที่ต้องการเชื่อมโยง
    }
    @GetMapping("/astronomerSubmitSP")
    public String showAstronomerSubmitSPPage() {
        return "astronomerSubmitSP"; // ชื่อหน้า HTML ที่ต้องการเชื่อมโยง
    }
    // create science plan
    @PostMapping("/createSP")
    public Object createSciencePlans(
            @RequestParam("planNo") int planNo,
            @RequestParam("creator") String creator,
            @RequestParam("submitter") String submitter,
            @RequestParam("fundingInUSD") double fundingInUSD,
            @RequestParam("objectives") String objectives,
            @RequestParam("starSystem") String starSystem,
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
        SciencePlanModelGDDG mySP = new SciencePlanModelGDDG() ;
//        MySciencePlan mySP = new MySciencePlan();
        mySP.setPlanNo(planNo);
        mySP.setCreator(creator);
        mySP.setSubmitter(submitter);
        mySP.setFundingInUSD(fundingInUSD);
        mySP.setObjectives(objectives);
        StarSystem.CONSTELLATIONS constellation = StarSystem.CONSTELLATIONS.valueOf(String.valueOf(starSystem));
        mySP.setStarSystem(constellation);
        mySP.setStartDate(startDate);
        SciencePlan.TELESCOPELOC location = SciencePlan.TELESCOPELOC.valueOf(String.valueOf(telescopeLocation));
        mySP.setTelescopeLocation(location);
        mySP.setEndDate(endDate);
//        mySP.setStatus(SciencePlan.STATUS.SAVED);
        mySP.setStatus(SciencePlan.STATUS.TESTED);
        DataProcessReqModel dpr1 = new DataProcessReqModel();
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
        dataProcReqMentRepository.save(dpr1);
        sciencePlanRepository.save(mySP);

        return new ModelAndView("redirect:/getAllSP");
//        return new ModelAndView("redirect:/addAllSPToDatabase");
    }


    //  Get id the science plans
//    @PostMapping("/byidSP")
//    public ModelAndView getByIdSciencePlans(
//            @RequestParam("id") int id) {
//        SciencePlan mySP = ocs.getSciencePlanByNo(id);
//        ModelAndView modelAndView = new ModelAndView("astronomerSubmitSP");
//        modelAndView.addObject("sciencePlansTocontrollerByID", mySP);
//        return modelAndView;
//    }

    @PostMapping("/byidSP")
    public ModelAndView getByPlanNo(@RequestParam("planNo") int planNo) {
        SciencePlanModelGDDG mySP  =  sciencePlanRepository.findByplanNo(planNo);
//        SciencePlan idSP  =  ocs.getSciencePlanByNo(planNo);
        ModelAndView modelAndView = new ModelAndView("astronomerSubmitSP");
        modelAndView.addObject("sciencePlansTocontrollerByID", mySP);
        return modelAndView;
    }

    //  submit the science plans
//    @PostMapping("/submitSP")
//    public ModelAndView submitSciencePlans(
//            @RequestParam("id") int id) {
//        SciencePlan mySP = ocs.getSciencePlanByNo(id);
//        ocs.updateSciencePlanStatus(mySP.getPlanNo(), SciencePlan.STATUS.TESTED);
//        String submitSP = ocs.submitSciencePlan(mySP);
//        System.out.println(submitSP);
//        ModelAndView modelAndView = new ModelAndView("astronomer");
//        modelAndView.addObject("submitSPByID", mySP);
//        // Show บน table getall
////        return new ModelAndView("redirect:/getAllSP");
//        // Show เเค่ที่ ID นั้นๆ
//        return modelAndView;
//    }
    @PostMapping("/submitSP")
    public ModelAndView submitSciencePlans(
            @RequestParam("planNo") int planNo) {
        SciencePlanModelGDDG mySP  =  sciencePlanRepository.findByplanNo(planNo);
       if (mySP.getStatus() == SciencePlan.STATUS.TESTED) {
               String submitSP = ocs.submitSciencePlan(mySP);
               System.out.println(submitSP);
       ModelAndView modelAndView = new ModelAndView("astronomerSubmitSP");
          modelAndView.addObject("submitSPByID", mySP);
       return modelAndView;
        } else {
            // Create alert message for non-tested science plans
            ModelAndView modelAndView = new ModelAndView("astronomerSubmitSP");
            modelAndView.addObject("alertMessage", "This science plan has not been tested yet.");
            return modelAndView;
        }
    }

    @GetMapping("/Allsp")
    public @ResponseBody Iterable<SciencePlanModelGDDG> getAll() {
        // This returns a JSON or XML with the users
        return sciencePlanRepository.findAll();
    }
}


