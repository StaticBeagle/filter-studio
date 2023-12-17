package com.wildbitsfoundry.filterstudio.analog.activefilter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.wildbitsfoundry.etk4j.signals.filters.*;
import com.wildbitsfoundry.etk4j.control.*;

@RestController
@RequestMapping("/api/v1/active-analog-filters/supported-filter-types")
@CrossOrigin(origins = "http://localhost:4200")
public class ActiveFilterController {

    @Value("${spring.application.http.auth-token-header-name}")
    private static String AUTH_TOKEN_HEADER_NAME;

    @Value("${spring.application.http.auth-token}")
    private static String AUTH_TOKEN;

    @Value("${spring.profile.active}")
    public static String Profile;

    @GetMapping("")
    public String[] getAllFilterTypes() {
        // Specs for low pass filter
        LowPassSpecs lpSpecs = new LowPassSpecs();
        lpSpecs.setPassBandRipple(1.5); // 1.5 dB gain/ripple refer to note
        lpSpecs.setStopBandAttenuation(60.0); // 60 dB at the stop band
        lpSpecs.setPassBandFrequency(2500); // 2500 rad/s cutoff frequency
        lpSpecs.setStopBandFrequency(10000); // 10000 rad/s stop band frequency

        LowPassResults lpr = ButterWorth.buttord(lpSpecs);
        TransferFunction bu = ButterWorth.newLowPass(lpr.getOrder(), lpr.getCutoffFrequency());
        ZeroPoleGain zpk = ButterWorth.newLowPassZPK(lpr.getOrder(), lpr.getCutoffFrequency());

        lpr = Chebyshev1.cheb1ord(lpSpecs);
        TransferFunction cb1 = Chebyshev1.newLowPass(lpr.getOrder(), lpSpecs.getPassBandRipple(), lpr.getCutoffFrequency());

        lpr = Chebyshev2.cheb2ord(lpSpecs);
        TransferFunction cb2 = Chebyshev2.newLowPass(lpr.getOrder(), lpSpecs.getStopBandAttenuation(), lpr.getCutoffFrequency());

        lpr = Elliptic.ellipord(lpSpecs);
        TransferFunction el = Elliptic.newLowPass(lpr.getOrder(), lpSpecs.getPassBandRipple(),
                lpSpecs.getStopBandAttenuation(), lpr.getCutoffFrequency());
        return new String[] {zpk.toString()};
    }
}
