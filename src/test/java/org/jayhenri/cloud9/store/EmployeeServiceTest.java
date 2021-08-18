package org.jayhenri.cloud9.store;

import org.jayhenri.cloud9.interfaces.service.other.EmployeeServiceI;
import org.jayhenri.cloud9.model.store.Employee;
import org.jayhenri.cloud9.repository.store.EmployeeRepository;
import org.jayhenri.cloud9.service.store.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

/**
 * The type Employee service test.
 */
@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    /**
     * The Test me.
     */
    private EmployeeServiceI employeeService;

    /**
     * The Employee repository.
     */
    @Mock
    private EmployeeRepository employeeRepository;

    /**
     * The Captor employee.
     */
    @Captor
    private ArgumentCaptor<Employee> captorEmployee;

    /**
     * The Captor string.
     */
    @Captor
    private ArgumentCaptor<String> captorString;

    /**
     * The Captor string.
     */
    @Captor
    private ArgumentCaptor<UUID> captorUUID;

    /**
     * The Employee.
     */
    private Employee employee;

    /**
     * The Employee.
     */
    private UUID uuid;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        employee = new Employee();

        employeeService = new EmployeeService(employeeRepository);

        uuid = UUID.randomUUID();
    }

    /**
     * Test add.
     */
    @Test
    void testAdd() {
        employeeService.add(this.employee);

        then(employeeRepository).should().save(captorEmployee.capture());

        assertThat(captorEmployee.getValue()).isEqualTo(this.employee);
    }

    /**
     * Exists by phone number.
     */
    @Test
    void existsByPhoneNumber() {
        given(employeeRepository.existsByPhoneNumber("1234567890"))
                .willReturn(true);

        Boolean bool = employeeService.existsByPhoneNumber("1234567890");
        then(employeeRepository).should().existsByPhoneNumber(captorString.capture());

        assertThat(captorString.getValue()).isEqualTo("1234567890");
        assertThat(bool).isTrue();
    }

    /**
     * Exists by phone number.
     */
    @Test
    void doesNotExistsByPhoneNumber() {
        given(employeeRepository.existsByPhoneNumber("1234567890"))
                .willReturn(false);

        Boolean bool = employeeService.existsByPhoneNumber("1234567890");
        then(employeeRepository).should().existsByPhoneNumber(captorString.capture());

        assertThat(captorString.getValue()).isEqualTo("1234567890");
        assertThat(bool).isFalse();
    }

    /**
     * Add.
     */
    @Test
    void add() {
        employeeService.add(this.employee);

        then(employeeRepository).should().save(captorEmployee.capture());

        assertThat(captorEmployee.getValue()).isEqualTo(this.employee);
    }

    /**
     * Delete.
     */
    @Test
    void delete() {
        employeeService.remove(this.employee);

        then(employeeRepository).should().delete(captorEmployee.capture());

        assertThat(captorEmployee.getValue()).isEqualTo(this.employee);
    }

    /**
     * Update.
     */
    @Test
    void update() {
        employeeService.update(this.employee);

        then(employeeRepository).should().save(captorEmployee.capture());

        assertThat(captorEmployee.getValue()).isEqualTo(this.employee);
    }

    /**
     * Find all employees.
     * Do later.
     */
    @Test
    @Disabled
    void findAllEmployees() {
    }

    /**
     * Exists by email.
     */
    @Test
    void existsByEmail() {
        String email = "employeeService@gmail.com";
        given(employeeRepository.existsByEmail(email)).willReturn(true);

        boolean bool = employeeService.existsByEmail(email);

        then(employeeRepository).should().existsByEmail(captorString.capture());

        assertThat(captorString.getValue()).isEqualTo(email);
        assertThat(bool).isTrue();
    }

    /**
     * Exists by email.
     */
    @Test
    void doesNotExistsByEmail() {
        String email = "employeeService@gmail.com";
        given(employeeRepository.existsByEmail(email)).willReturn(false);

        boolean bool = employeeService.existsByEmail(email);

        then(employeeRepository).should().existsByEmail(captorString.capture());

        assertThat(captorString.getValue()).isEqualTo(email);
        assertThat(bool).isFalse();
    }

    /**
     * Gets by email.
     */
    @Test
    void getByEmail() {
        String email = "employeeService@gmail.com";
        given(employeeRepository.getByEmail(email)).willReturn(employee);
        Employee _employee = employeeService.getByEmail(email);

        then(employeeRepository).should().getByEmail(captorString.capture());

        assertThat(captorString.getValue()).isEqualTo(email);
        assertThat(employee).isEqualTo(_employee);
    }
}