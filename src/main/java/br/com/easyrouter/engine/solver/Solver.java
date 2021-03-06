
package br.com.easyrouter.engine.solver;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.time.DateUtils;

import br.com.easyrouter.engine.api.DeliveryPoint;
import br.com.easyrouter.engine.api.DirectionLeg;
import br.com.easyrouter.engine.api.DistributionCenter;
import com.graphhopper.jsprit.core.problem.driver.Driver;
import com.graphhopper.jsprit.core.problem.driver.DriverImpl;
import br.com.easyrouter.engine.api.Order;
import br.com.easyrouter.engine.api.RouteRequest;
import br.com.easyrouter.engine.api.Vehicle;
import com.graphhopper.jsprit.core.algorithm.VehicleRoutingAlgorithm;
import com.graphhopper.jsprit.core.algorithm.io.VehicleRoutingAlgorithms;
import com.graphhopper.jsprit.core.problem.Location;
import com.graphhopper.jsprit.core.problem.VehicleRoutingProblem;
import com.graphhopper.jsprit.core.problem.VehicleRoutingProblem.FleetSize;
import com.graphhopper.jsprit.core.problem.job.Shipment;
import com.graphhopper.jsprit.core.problem.solution.VehicleRoutingProblemSolution;
import com.graphhopper.jsprit.core.problem.solution.route.activity.TimeWindow;
import com.graphhopper.jsprit.core.problem.vehicle.VehicleImpl;
import com.graphhopper.jsprit.core.problem.vehicle.VehicleType;
import com.graphhopper.jsprit.core.problem.vehicle.VehicleTypeImpl;
import com.graphhopper.jsprit.core.util.Solutions;
import com.graphhopper.jsprit.core.util.VehicleRoutingTransportCostsMatrix;

/**
 * Class responsible to translate the {@link RouteRequest} object into a {@link VehicleRoutingProblem} model and resolve it with the best solution
 * @author luizmiranda
 */
public class Solver {

    private static final int WEIGHT_INDEX = 0;
    private static final int VOLUME_INDEX = 1;

    private RouteRequest routeRequest = null;
    private Set<VehicleImpl> vehicleImpls = new HashSet<VehicleImpl>();
    private Set<Shipment> shipments = new HashSet<Shipment>();
    private Set<Driver> drivers = new HashSet<Driver>();
    private VehicleRoutingTransportCostsMatrix costMatrix = null;
    private Date now = new Date();

    /**
     * Constructs a vehicle routing problem with time windows
     *
     * @param routeRequest
     */
    public Solver(RouteRequest routeRequest) {
        DistributionCenter distributionCenter = routeRequest.getOrders().iterator().next().getDistributionCenter();
        this.vehicleImpls = this.loadVehicles(routeRequest.getVehicles(), distributionCenter);
        this.shipments = this.loadShipments(routeRequest.getOrders(), distributionCenter);
        this.costMatrix = this.loadCostMatrix(routeRequest.getDirectionLegs());
        this.drivers = this.loadDrivers(routeRequest.getDrivers());
        this.routeRequest = routeRequest;

    }

    /**
     * Inputs all {@link DirectionLeg} from {@link RouteRequest} into {@link VehicleRoutingProblem} model
     * @param directionLegs
     * @return
     */
    private VehicleRoutingTransportCostsMatrix loadCostMatrix(Set<DirectionLeg> directionLegs) {
        VehicleRoutingTransportCostsMatrix.Builder costMatrixBuilder = VehicleRoutingTransportCostsMatrix.Builder.newInstance(true);

        for (DirectionLeg directionLeg : directionLegs) {
            String initialPointId = directionLeg.getInitialPoint().toString();
            String finalPointId = directionLeg.getFinalPoint().toString();
            costMatrixBuilder.addTransportDistance(
                    initialPointId,
                    finalPointId,
                    directionLeg.getDistance());

            costMatrixBuilder.addTransportTime(
                    initialPointId,
                    finalPointId,
                    directionLeg.getDuration());
        }
        return costMatrixBuilder.build();
    }

    /**
     * Loads all vehicle available from {@link RouteRequest}
     *
     * @param vehicles
     * @param distributionCenter
     * @return
     */
    private Set<VehicleImpl> loadVehicles(Set<Vehicle> vehicles, DistributionCenter distributionCenter) {
        HashSet<VehicleImpl> result = new HashSet<VehicleImpl>();

        for (Vehicle vehicle : vehicles) {
            VehicleType vehicleType =
                    VehicleTypeImpl.Builder.newInstance(vehicle.getExternalCode().toString())
                            .addCapacityDimension(this.WEIGHT_INDEX, vehicle.getTotalWeight().intValue())
                            .addCapacityDimension(this.VOLUME_INDEX, vehicle.getTotalVolume().intValue())
                            .setCostPerServiceTime(vehicle.getCostPerTime())
                            .setCostPerTransportTime(vehicle.getCostPerTime())
                            .setCostPerWaitingTime(vehicle.getCostPerTime())
                            .setCostPerDistance(vehicle.getCostPerDistance())
                            .setMaxVelocity(vehicle.getMaxVelocity().doubleValue())
                            .build();

            result.add(VehicleImpl.Builder.newInstance(vehicle.getExternalCode().toString())
                    .setStartLocation(Location.newInstance(distributionCenter.getRoutePointExternalCode().toString()))
                    .setEndLocation(Location.newInstance(distributionCenter.getRoutePointExternalCode().toString()))
                    .setEarliestStart(vehicle.getEarliestStart().getTime())
                    .setLatestArrival(vehicle.getLatestEnd().getTime()).setType(vehicleType).build());
        }
        return result;
    }

    /**
     * Loads all orders from {@link RouteRequest}
     *
     * @param orders
     * @param distributionCenter
     * @return
     */
    private Set<Shipment> loadShipments(Set<Order> orders, DistributionCenter distributionCenter) {
        HashSet<Shipment> result = new HashSet<Shipment>();
        for (Order order : orders) {
            DeliveryPoint deliveryPoint = order.getDeliveryPoint();

            result.add(Shipment.Builder.newInstance(order.getExternalCode().toString())
                    .addSizeDimension(WEIGHT_INDEX, order.getWeight())
                    .addSizeDimension(VOLUME_INDEX, order.getVolume())
                    .setDeliveryServiceTime(deliveryPoint.getDeliveryDuration())
                    .setDeliveryTimeWindow(TimeWindow.newInstance(order.getDeliverTimeWindow().getStart().getTime(), order.getDeliverTimeWindow().getEnd().getTime()))
                    .setDeliveryLocation(Location.newInstance(deliveryPoint.getRoutePointExternalCode().toString()))
                    .setPickupServiceTime(distributionCenter.getPrepareDuration())
                    .setPickupLocation(Location.newInstance(distributionCenter.getRoutePointExternalCode().toString()))
                    .setDeliveryTimeWindow(TimeWindow.newInstance(order.getPickupTimeWindow().getStart().getTime(), order.getPickupTimeWindow().getEnd().getTime()))
                    .setName(order.getName())
                    .build());
        }

        return result;
    }
    
    private Set<Driver> loadDrivers(Set<br.com.easyrouter.engine.api.Driver> drivers){
        Set<Driver> result = new HashSet<Driver>();
    	for (br.com.easyrouter.engine.api.Driver driver : drivers) {
    		DriverImpl driverImpl = new DriverImpl(driver.getExternalCode().toString());
    		driverImpl.setEarliestStart(driver.getEarliestStart().getTime());
    		driverImpl.setLatestEnd(driver.getLatestEnd().getTime());
    		driverImpl.setHomeLocation(driver.getCurrentDistributionCenter().getRoutePointExternalCode().toString());
			result.add(driverImpl);
		}
    	
    	return result;
    }

    /**
     * Solves the current routing problem
     */
    public VehicleRoutingProblemSolution solve() {

        VehicleRoutingProblem problem = VehicleRoutingProblem.Builder
                .newInstance()
                .setFleetSize(FleetSize.FINITE)
                .addAllJobs(this.shipments)
                .setRoutingCost(this.costMatrix)
                .addAllVehicles(this.vehicleImpls)
                .build();

        VehicleRoutingAlgorithm algorithm = VehicleRoutingAlgorithms.readAndCreateAlgorithm(problem, "input/algorithmConfig.xml");
        Collection<VehicleRoutingProblemSolution> solutions = algorithm.searchSolutions();
        return Solutions.bestOf(solutions);
    }

    public RouteRequest getRouteRequest() {
        return routeRequest;
    }

    public void setRouteRequest(RouteRequest routeRequest) {
        this.routeRequest = routeRequest;
    }

}
