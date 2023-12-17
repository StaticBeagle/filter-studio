package com.wildbitsfoundry.filterstudio.analog.activefilter.filtertype.dto;

import com.wildbitsfoundry.filterstudio.analog.activefilter.filtertype.entity.AnalogActiveFilterType;
import lombok.Data;

@Data
public class AnalogActiveFilterTypeDTO {

    private String filterType;

    public AnalogActiveFilterTypeDTO(AnalogActiveFilterType analogActiveFilterType) {
        this.filterType = analogActiveFilterType.getType();
    }
}
