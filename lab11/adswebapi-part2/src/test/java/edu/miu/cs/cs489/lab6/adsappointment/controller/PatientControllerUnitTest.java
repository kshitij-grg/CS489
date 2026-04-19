package edu.miu.cs.cs489.lab6.adsappointment.controller;

import edu.miu.cs.cs489.lab6.adsappointment.model.Address;
import edu.miu.cs.cs489.lab6.adsappointment.model.Patient;
import edu.miu.cs.cs489.lab6.adsappointment.service.AddressService;
import edu.miu.cs.cs489.lab6.adsappointment.service.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PatientController.class)
class PatientControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatientService patientService;

    @MockBean
    private AddressService addressService;

    @Test
    void getAllPatients_returnsAllPatientsData() throws Exception {
        Address address1 = new Address(1, "100 Main St", "Fairfield", "IA", "52557");
        Address address2 = new Address(2, "200 Oak Ave", "Iowa City", "IA", "52240");

        Patient patient1 = new Patient(1, "P100", "Gillian", "White", address1, null);
        Patient patient2 = new Patient(2, "P106", "Ian", "MacKay", address2, null);

        when(patientService.getAllPatientsSortedByLastName()).thenReturn(List.of(patient2, patient1));

        mockMvc.perform(get("/adsweb/api/v1/patients"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].patientId").value(2))
                .andExpect(jsonPath("$[0].patientNo").value("P106"))
                .andExpect(jsonPath("$[0].firstName").value("Ian"))
                .andExpect(jsonPath("$[0].lastName").value("MacKay"))
                .andExpect(jsonPath("$[0].primaryAddress.city").value("Iowa City"))
                .andExpect(jsonPath("$[1].patientId").value(1))
                .andExpect(jsonPath("$[1].patientNo").value("P100"))
                .andExpect(jsonPath("$[1].firstName").value("Gillian"))
                .andExpect(jsonPath("$[1].lastName").value("White"))
                .andExpect(jsonPath("$[1].primaryAddress.city").value("Fairfield"));

        verify(patientService).getAllPatientsSortedByLastName();
    }
}
