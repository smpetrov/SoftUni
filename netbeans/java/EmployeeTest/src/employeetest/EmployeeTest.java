package employeetest;

import java.beans.PropertyChangeEvent;

public class EmployeeTest {
    
    public static void main(String[] args) {
        final Employee e1 = new Employee("John Jacobs", 2000.0);
        
        //Compute the tax
        computeTax(e1.getSalary());
        
        //Add a property change listener to e1
        e1.addPropertyChangeListener(EmployeeTest::handlePropertyChange);
        
        //Change the salary
        e1.setSalary(3000.0);
        e1.setSalary(3000.0); //No chnage notification is sent
        e1.setSalary(6000.0);
    }
    
    public static void handlePropertyChange(PropertyChangeEvent e){
        String propertyNmae = e.getPropertyName();
        if("salary".equals(propertyNmae)){
            System.out.print("Salary has changed.");
            System.out.print("Old: " + e.getOldValue());
            System.out.println("New: " + e.getNewValue());
            computeTax((Double) e.getNewValue());
        }
    }
    
    public static void computeTax(double salary){
        final double TAX_PERCENT = 20.0;
        double tax = salary * TAX_PERCENT/100.0;
        System.out.println("Salary: " + salary + ",Tax: " + tax);
    }
    
}
