package com.wildbitsfoundry.filterstudio.analog.activefilter.filtertype.service;

import com.wildbitsfoundry.filterstudio.analog.activefilter.filtertype.entity.AnalogActiveFilterType;
import com.wildbitsfoundry.filterstudio.analog.activefilter.filtertype.repository.AnalogActiveFilterTypeRepository;
import com.wildbitsfoundry.filterstudio.analog.activefilter.filtertype.request.AnalogActiveFilterTypeRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnalogActiveFilterTypeService {

    private AnalogActiveFilterTypeRepository analogActiveFilterTypeRepository;

    public AnalogActiveFilterTypeService(AnalogActiveFilterTypeRepository analogActiveFilterTypeRepository) {
        this.analogActiveFilterTypeRepository = analogActiveFilterTypeRepository;
    }

    public List<String> getAllFilterTypes() {
        return analogActiveFilterTypeRepository.findAll().stream().map(t -> t.getType()).collect(Collectors.toList());
    }

    public Long createFilterTYpe(AnalogActiveFilterTypeRequest analogActiveFilterTypeRequest) {
        AnalogActiveFilterType analogActiveFilterType = new AnalogActiveFilterType();
        analogActiveFilterType.setType(analogActiveFilterTypeRequest.getFilterType());
        return analogActiveFilterTypeRepository.save(analogActiveFilterType).getId();
    }
}
