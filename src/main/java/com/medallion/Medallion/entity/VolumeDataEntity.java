package com.medallion.Medallion.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "volume_data")
public class VolumeDataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String metric;

    private String label;

    private double avgVolume;

    private long currVolume;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<VolumeEntry> values;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMetric() {
        return metric;
    }

    public void setMetric(String metric) {
        this.metric = metric;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public double getAvgVolume() {
        return avgVolume;
    }

    public void setAvgVolume(double avgVolume) {
        this.avgVolume = avgVolume;
    }

    public long getCurrVolume() {
        return currVolume;
    }

    public void setCurrVolume(long currVolume) {
        this.currVolume = currVolume;
    }

    public List<VolumeEntry> getValues() {
        return values;
    }

    public void setValues(List<VolumeEntry> values) {
        this.values = values;
    }
}