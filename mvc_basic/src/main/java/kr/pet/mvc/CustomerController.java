package kr.pet.mvc;

import java.util.ArrayList;
import java.util.List;

public class CustomerController {
    private List<Customer> customers;
    private MedicalRecordController recordController;

    public CustomerController(MedicalRecordController recordController) {
        this.customers = new ArrayList<>();
        this.recordController = recordController;
    }

    // add customer info
    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    // remove customer info(required : remove customer medical info)
    public void removeCustomer(String phoneNumber) {
        for (int i=0; i < customers.size(); i++) {
            if (customers.get(i).getPhoneNumber().equals(phoneNumber)) {
                customers.remove(i);
                recordController.removeMedicalRecord(phoneNumber);
                break;
            }
        }
    }

    // search customer
    public Customer findCustomer(String phoneNumber) {
        for (Customer customer : customers) {
            if (customer.getPhoneNumber().equals(phoneNumber)) {
                return customer;
            }
        }
        return  null;
    }

    // 중복여부 확인
    public boolean isPhoneNumberExist(String phoneNumber) {
        for (Customer customer : customers) {
            if (customer.getPhoneNumber().equals(phoneNumber)) {
                return true;
            }
        }
        return false;
    }
}
