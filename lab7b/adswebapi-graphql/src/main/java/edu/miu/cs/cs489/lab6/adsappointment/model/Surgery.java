package edu.miu.cs.cs489.lab6.adsappointment.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "surgeries")
public class Surgery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer surgeryId;

    @Column(nullable = false, unique = true)
    private String surgeryNo;

    @Column(nullable = false)
    private String name;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @OneToMany(mappedBy = "surgery")
    private List<Appointment> appointments;

    public Surgery(String surgeryNo, String name, Address address) {
        this.surgeryNo = surgeryNo;
        this.name = name;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Surgery{" +
                "surgeryId=" + surgeryId +
                ", surgeryNo='" + surgeryNo + '\'' +
                ", name='" + name + '\'' +
                ", address=" + address +
                '}';
    }
}
