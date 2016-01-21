package br.com.easyrouter.engine.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class RouteRequest {
	
	private Set<Order> orders;
	private Set<Driver> drivers;
	private Set<Vehicle> vehicles;
	private Set<DirectionLeg> directionLegs;
	/**
	 * @return the orders
	 */
	public Set<Order> getOrders() {
		return orders;
	}
	/**
	 * @param orders the orders to set
	 */
	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}
	/**
	 * @return the drivers
	 */
	public Set<Driver> getDrivers() {
		return drivers;
	}
	/**
	 * @param drivers the drivers to set
	 */
	public void setDrivers(Set<Driver> drivers) {
		this.drivers = drivers;
	}
	/**
	 * @return the vehicles
	 */
	public Set<Vehicle> getVehicles() {
		return vehicles;
	}
	/**
	 * @param vehicles the vehicles to set
	 */
	public void setVehicles(Set<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}
	/**
	 * @return the directionLegs
	 */
	public Set<DirectionLeg> getDirectionLegs() {
		return directionLegs;
	}
	/**
	 * @param directionLegs the directionLegs to set
	 */
	public void setDirectionLegs(Set<DirectionLeg> directionLegs) {
		this.directionLegs = directionLegs;
	}
	
	/**
	 * Returns the directionLeg identified by the 2 {@link Order}s passed as parameters
	 * 
	 * @param order
	 * @param order2
	 * @return {@link DirectionLeg} found
	 */
	public DirectionLeg getDirectionLeg(Order firstOrder, Order secondOrder) {
		if(firstOrder == null || secondOrder == null){
			return null;
		}
		
		ExternalCode firstRoutePointExternalCode = firstOrder.getDeliveryPoint().getRoutePointExternalCode();
		ExternalCode secondRoutePointExternalCode = secondOrder.getDeliveryPoint().getRoutePointExternalCode();
		for(DirectionLeg directionLeg : this.getDirectionLegs()){
			if(firstRoutePointExternalCode.equals(directionLeg.getInitialPoint()) && secondRoutePointExternalCode.equals(directionLeg.getFinalPoint())){
				return directionLeg;
			}
		}
		return null;
	}
	public int getNumberOfRoutePoints() {
		List<ExternalCode> routePointExternalCodes = new ArrayList<ExternalCode>();
		
		for (Order order : orders) {
			if(!routePointExternalCodes.contains(order.getDeliveryPoint().getRoutePointExternalCode())){
				routePointExternalCodes.add(order.getDeliveryPoint().getRoutePointExternalCode());
			}
			if(!routePointExternalCodes.contains(order.getDistributionCenter().getRoutePointExternalCode())){
				routePointExternalCodes.add(order.getDistributionCenter().getRoutePointExternalCode());
			}
		}
		
		return routePointExternalCodes.size();
	}
	
	

}
