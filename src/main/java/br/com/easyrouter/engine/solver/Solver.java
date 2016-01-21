
package br.com.easyrouter.engine.solver;


import java.util.HashSet;
import java.util.Set;

import jsprit.core.problem.Location;
import jsprit.core.problem.driver.DriverImpl;
import jsprit.core.problem.job.Service;
import jsprit.core.problem.solution.route.activity.TimeWindow;
import jsprit.core.problem.vehicle.VehicleImpl;
import jsprit.core.problem.vehicle.VehicleType;
import jsprit.core.problem.vehicle.VehicleTypeImpl;
import br.com.easyrouter.engine.api.DeliveryPoint;
import br.com.easyrouter.engine.api.DistributionCenter;
import br.com.easyrouter.engine.api.Driver;
import br.com.easyrouter.engine.api.Order;
import br.com.easyrouter.engine.api.RouteRequest;
import br.com.easyrouter.engine.api.RouteResponse;
import br.com.easyrouter.engine.api.Vehicle;


public class Solver {

    private final int WEIGHT_INDEX = 0;
    private final int VOLUME_INDEX = 1;

    private Set<VehicleImpl> vehicleImpls = new HashSet<VehicleImpl>();
    private Set<Service> services = new HashSet<Service>();
    private Set<DriverImpl> drivers = new HashSet<DriverImpl>();

    /**
     * Constructs a capacitated vehicle routing problem with time windows.
     *
     * @param routeRequest
     */
    public Solver(RouteRequest routeRequest) {
        DistributionCenter distributionCenter = routeRequest.getOrders().iterator().next().getDistributionCenter();
        this.loadVehicles(routeRequest.getVehicles(), distributionCenter);
        this.loadDrivers(routeRequest.getDrivers(), distributionCenter);
        this.loadOrders(routeRequest.getOrders());
    }

    private void loadVehicles(Set<Vehicle> vehicles, DistributionCenter distributionCenter) {
        for (Vehicle vehicle : vehicles) {
            VehicleType vehicleType =
                    VehicleTypeImpl.Builder.newInstance(vehicle.getExternalCode().toString())
                            .addCapacityDimension(this.WEIGHT_INDEX, vehicle.getTotalWeight().intValue())
                            .addCapacityDimension(this.VOLUME_INDEX, vehicle.getTotalVolume().intValue())
                            .setCostPerDistance(vehicle.getCostPerDistance()).setCostPerTime(vehicle.getCostPerTime())
                            .setMaxVelocity(vehicle.getMaxVelocity()).build();

            this.vehicleImpls.add(VehicleImpl.Builder.newInstance(vehicle.getExternalCode().toString())
                    .setStartLocation(Location.newInstance(distributionCenter.getExternalCode().toString()))
                    .setEndLocation(Location.newInstance(distributionCenter.getExternalCode().toString()))
                    .setEarliestStart(vehicle.getEarliestStart().getTime())
                    .setLatestArrival(vehicle.getLatestEnd().getTime()).setType(vehicleType).build());
        }
    }

    private void loadOrders(Set<Order> orders) {

        for (Order order : orders) {
            DeliveryPoint deliveryPoint = order.getDeliveryPoint();

            this.services.add(Service.Builder.newInstance(order.getExternalCode().toString())
                    .addSizeDimension(this.WEIGHT_INDEX, order.getWeight())
                    .addSizeDimension(this.VOLUME_INDEX, order.getVolume())
                    .setServiceTime(deliveryPoint.getDeliveryDuration())
                    .setTimeWindow(TimeWindow.newInstance(0.0, order.getDeadline().getTime()))
                    .setLocation(Location.newInstance(deliveryPoint.getRoutePointExternalCode().toString()))
                    .setName(order.getName()).build());
        }
    }

    private void loadDrivers(Set<Driver> drivers, DistributionCenter distributionCenter) {
        for (Driver driver : drivers) {
            DriverImpl driverImpl = DriverImpl.noDriver();
            driverImpl.setEarliestStart(driver.getEarliestStart().getTime());
            driverImpl.setLatestEnd(driver.getLatestEnd().getTime());
            driverImpl.setHomeLocation(distributionCenter.getRoutePointExternalCode().toString());
            this.drivers.add(driverImpl);
        }
    }

    /**
     * Solves the current routing problem.
     *
     * @return {@link RouteResponse} calculated
     */
    public RouteResponse solve() {
        return null;
    }
}
