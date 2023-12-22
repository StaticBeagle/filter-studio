package com.wildbitsfoundry.filterstudio.analog.activefilter.filtertype;

import com.wildbitsfoundry.etk4j.control.TransferFunction;
import com.wildbitsfoundry.etk4j.control.ZeroPoleGain;
import com.wildbitsfoundry.etk4j.signals.filters.*;
import com.wildbitsfoundry.filterstudio.analog.activefilter.filtertype.entity.AnalogActiveFilterType;
import com.wildbitsfoundry.filterstudio.analog.activefilter.filtertype.request.AnalogActiveFilterTypeRequest;
import com.wildbitsfoundry.filterstudio.analog.activefilter.filtertype.service.AnalogActiveFilterTypeService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping(AnalogActiveFilterTypeController.PATH)
@CrossOrigin(origins = "http://localhost:4200")
public class AnalogActiveFilterTypeController {

    private AnalogActiveFilterTypeService analogActiveFilterTypeService;

    public static final String PATH = "/api/v1/analog-active-filters/supported-filter-types";

    public AnalogActiveFilterTypeController(AnalogActiveFilterTypeService analogActiveFilterTypeService) {
        this.analogActiveFilterTypeService = analogActiveFilterTypeService;
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<String>> getAllSupportedFilterTypes() {
//        // Specs for low pass filter
//        LowPassSpecs lpSpecs = new LowPassSpecs();
//        lpSpecs.setPassBandRipple(1.5); // 1.5 dB gain/ripple refer to note
//        lpSpecs.setStopBandAttenuation(60.0); // 60 dB at the stop band
//        lpSpecs.setPassBandFrequency(2500); // 2500 rad/s cutoff frequency
//        lpSpecs.setStopBandFrequency(10000); // 10000 rad/s stop band frequency
//
//        LowPassResults lpr = ButterWorth.buttord(lpSpecs);
//        TransferFunction bu = ButterWorth.newLowPass(lpr.getOrder(), lpr.getCutoffFrequency());
//        ZeroPoleGain zpk = ButterWorth.newLowPassZPK(lpr.getOrder(), lpr.getCutoffFrequency());
//
//        lpr = Chebyshev1.cheb1ord(lpSpecs);
//        TransferFunction cb1 = Chebyshev1.newLowPass(lpr.getOrder(), lpSpecs.getPassBandRipple(), lpr.getCutoffFrequency());
//
//        lpr = Chebyshev2.cheb2ord(lpSpecs);
//        TransferFunction cb2 = Chebyshev2.newLowPass(lpr.getOrder(), lpSpecs.getStopBandAttenuation(), lpr.getCutoffFrequency());
//
//        lpr = Elliptic.ellipord(lpSpecs);
//        TransferFunction el = Elliptic.newLowPass(lpr.getOrder(), lpSpecs.getPassBandRipple(),
//                lpSpecs.getStopBandAttenuation(), lpr.getCutoffFrequency());
//        return new String[] {zpk.toString()};
        return ResponseEntity.ok(analogActiveFilterTypeService.getAllFilterTypes());
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> createFilterType(@RequestBody AnalogActiveFilterTypeRequest analogActiveFilterTypeRequest) {
        Long id = analogActiveFilterTypeService.createFilterTYpe(analogActiveFilterTypeRequest);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequestUri().path(id.toString()).build().toUri())
                .body(id);
    }
}
