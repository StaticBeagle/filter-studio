package com.wildbitsfoundry.filterstudio.analog.activefilter.filtertype.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "ANALOG_ACTIVE_FILTER_TYPE")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnalogActiveFilterType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ANALOG_FILTER_TYPE_ID", nullable = false, unique = true)
    private Long id;

    @Column(name = "FILTER_TYPE", nullable = false, unique = true)
    private String type;
}
