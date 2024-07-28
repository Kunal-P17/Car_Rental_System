package crs;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CarRentalSystem {
	
	private List<Car> cars;
	
	private List<Customer> customers;
	
	private List<Rental> rentals;

//	public carRentalSystem(List<Car> cars, List<Customer> customers, List<Rental> rentals) {
//		super();
//		this.cars = cars;
//		this.customers = customers;
//		this.rentals = rentals;
//	}
	
	public CarRentalSystem() {
		cars = new ArrayList<>();
		customers = new ArrayList<>();
		rentals = new ArrayList<>();
	}
	
	public void addcar(Car car) {
		cars.add(car);
	}
	
	public void addCustomer(Customer customer) {
		customers.add(customer);
	}
	
	public void rentCar(Car car, Customer customer, int days) {
		if(car.isAvailable()) {
			car.rent();
			rentals.add(new Rental(car,customer,days));
		} else {
			System.out.println("Car is not available for Rent.");
		}
		
	}
	
	public void returnCar(Car car) {
		Rental rentalToRemove = null;
		for(Rental rental : rentals) {
			if(rental.getCar() == car) {
				rentalToRemove = rental;
				break;
			}
		}
		if(rentalToRemove!=null) {
			rentals.remove(rentalToRemove);
			//System.out.println("Car returned successfully.");
			car.returnCar();
		} else {
			System.out.println("Car was not rented.");
		}
	}
	
	public void menu() {
		Scanner s = new Scanner(System.in);
		
		while(true) {
			System.out.println("===== Car Rental System =====");
			System.out.println("1. Rent A Car");
			System.out.println("2. Return The Car");
			System.out.println("3.Exit");
			System.out.print("Enter your Choice: ");
			
			int choice = s.nextInt();
			s.nextLine();
			
			if(choice == 1) {
				System.out.println("\n== Rent A Car==\n");
				System.out.print("Enter Your Name: ");
				String customerName = s.nextLine();
				
				System.out.println("\nAvailable Cars:");
				for(Car car : cars) {
					if(car.isAvailable()) {
						System.out.println(car.getCarId()+" - " + car.getBrand()+" "+car.getModel());
					}
				}
				
				System.out.print("\nEnter the car ID ypu want to rent: ");
				String carId = s.nextLine();
				
				System.out.print("Enter thr number of days for rental: ");
				int rentalDays = s.nextInt();
				s.nextLine();
				
				Customer newCustomer = new Customer("CUS"+(customers.size()+1), customerName);
				addCustomer(newCustomer);
				
				Car selectedCar = null;
				for(Car car : cars) {
					if(car.getCarId().equals(carId) && car.isAvailable()) {
						selectedCar = car;
						break;
					}
				}
				
				if (selectedCar != null) {
					double totalPrice = selectedCar.calculatePrice(rentalDays);
					System.out.println("\n== Rental Information ==\n");
					System.out.println("Customer ID: " + newCustomer.getCustomerId());
					System.out.println("Customer Name: " + newCustomer.getName());
					System.out.println("Car: " + selectedCar.getBrand() + " " + selectedCar.getModel());
					System.out.println("Rental Days: " + rentalDays);
					System.out.printf("Total Price: $%.2f%n", totalPrice);
					
					System.out.println("\nConfirm Rental (Y/N)");
					String confirm = s.nextLine();
					
					if(confirm.equalsIgnoreCase("Y")) {
						rentCar(selectedCar, newCustomer, rentalDays);
						System.out.println("\nCar Rented Successfully.");
					} else {
						System.out.println("\nRental Canceled");
					}
				} else {
					System.out.println("\nInvalid car selection or car is not available for rent.");
				}
			}
			
			else if (choice == 2) {
				System.out.println("\n== Return The Car ==\n");
				System.out.print("Enter the car ID you want to return: ");
				String carID = s.nextLine();
				
				Car carToReturn = null;
				for(Car car : cars) {
					if(car.getCarId().equals(carID) && !car.isAvailable()) {
						carToReturn = car;
						break;
					}
				}
				
				if(carToReturn != null) {
					Customer customer = null;
					for(Rental rental :rentals) {
						if(rental.getCar() == carToReturn) {
							customer = rental.getCustomer();
							break;
						}
					}
					
					if(customer != null) {
						returnCar(carToReturn);
						System.out.println("Car returned Successfully by "+ customer.getName());
					} else {
						System.out.println("Car was not rented or rental information is missing.");
					}
				} else {
					System.out.println("Invaild car ID or car was not rented.");
				}
			}
			
			else if(choice == 3) {
				break;
			} else {
				System.out.println("Invaild choice. Please enter a valid option.");
			}
		}
		System.out.println("\nThank you for using the Car rental System!");
	}
	
	
	
	

}
