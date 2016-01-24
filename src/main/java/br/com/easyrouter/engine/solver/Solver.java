
package br.com.easyrouter.engine.solver;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import br.com.easyrouter.engine.api.DeliveryPoint;
import br.com.easyrouter.engine.api.DirectionLeg;
import br.com.easyrouter.engine.api.DistributionCenter;
import br.com.easyrouter.engine.api.Order;
import br.com.easyrouter.engine.api.RouteRequest;
import br.com.easyrouter.engine.api.RouteResponse;
import br.com.easyrouter.engine.api.Vehicle;
import jsprit.core.algorithm.VehicleRoutingAlgorithm;
import jsprit.core.algorithm.io.VehicleRoutingAlgorithms;
import jsprit.core.problem.Location;
import jsprit.core.problem.VehicleRoutingProblem;
import jsprit.core.problem.VehicleRoutingProblem.FleetSize;
import jsprit.core.problem.driver.DriverImpl;
import jsprit.core.problem.job.Shipment;
import jsprit.core.problem.solution.VehicleRoutingProblemSolution;
import jsprit.core.problem.solution.route.activity.TimeWindow;
import jsprit.core.problem.vehicle.VehicleImpl;
import jsprit.core.problem.vehicle.VehicleType;
import jsprit.core.problem.vehicle.VehicleTypeImpl;
import jsprit.core.util.Solutions;
import jsprit.core.util.VehicleRoutingTransportCostsMatrix;


public class Solver {

    private final int WEIGHT_INDEX = 0;
    private final int VOLUME_INDEX = 1;

    private Set<VehicleImpl> vehicleImpls = new HashSet<VehicleImpl>();
    private Set<Shipment> shipments = new HashSet<Shipment>();
    private VehicleRoutingTransportCostsMatrix costMatrix = null;

    /**
     * Constructs a capacitated vehicle routing problem with time windows.
     *
     * @param routeRequest
     */
    public Solver(RouteRequest routeRequest) {
        DistributionCenter distributionCenter = routeRequest.getOrders().iterator().next().getDistributionCenter();
        this.vehicleImpls = this.loadVehicles(routeRequest.getVehicles(), distributionCenter);
        this.shipments = this.loadShipments(routeRequest.getOrders(), distributionCenter);
        this.costMatrix = this.loadCostMatrix(routeRequest.getDirectionLegs());
        
    }

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

	private Set<VehicleImpl> loadVehicles(Set<Vehicle> vehicles, DistributionCenter distributionCenter) {
		HashSet<VehicleImpl> result = new HashSet<VehicleImpl>();
		
        for (Vehicle vehicle : vehicles) {
            VehicleType vehicleType =
                    VehicleTypeImpl.Builder.newInstance(vehicle.getExternalCode().toString())
                            .addCapacityDimension(this.WEIGHT_INDEX, vehicle.getTotalWeight().intValue())
                            .addCapacityDimension(this.VOLUME_INDEX, vehicle.getTotalVolume().intValue())
                            .setCostPerDistance(vehicle.getCostPerDistance()).setCostPerTime(vehicle.getCostPerTime())
                            .setMaxVelocity(vehicle.getMaxVelocity()).build();

            result.add(VehicleImpl.Builder.newInstance(vehicle.getExternalCode().toString())
                    .setStartLocation(Location.newInstance(distributionCenter.getExternalCode().toString()))
                    .setEndLocation(Location.newInstance(distributionCenter.getExternalCode().toString()))
                    .setEarliestStart(vehicle.getEarliestStart().getTime())
                    .setLatestArrival(vehicle.getLatestEnd().getTime()).setType(vehicleType).build());
        }
        return result;
    }

    private Set<Shipment> loadShipments(Set<Order> orders, DistributionCenter distributionCenter) {
    	HashSet<Shipment> result = new HashSet<Shipment>();
        for (Order order : orders) {
            DeliveryPoint deliveryPoint = order.getDeliveryPoint();

            result.add(Shipment.Builder.newInstance(order.getExternalCode().toString())
                    .addSizeDimension(this.WEIGHT_INDEX, order.getWeight())
                    .addSizeDimension(this.VOLUME_INDEX, order.getVolume())
                    .setDeliveryServiceTime(deliveryPoint.getDeliveryDuration())
                    .setDeliveryTimeWindow(TimeWindow.newInstance(0.0, order.getDeadline().getTime()))
                    .setPickupServiceTime(distributionCenter.getPrepareDuration())
                    .setDeliveryLocation(Location.newInstance(deliveryPoint.getRoutePointExternalCode().toString()))
                    .setPickupLocation(Location.newInstance(deliveryPoint.getRoutePointExternalCode().toString()))
                    .setName(order.getName()).build());
        };
        
        return result;
    }

    /**
     * Solves the current routing problem.
     *
     * @return {@link RouteResponse} calculated
     */
    public RouteResponse solve() {

		VehicleRoutingProblem problem = VehicleRoutingProblem.Builder
				.newInstance()
				.setRoutingCost(this.costMatrix)
				.setFleetSize(FleetSize.FINITE)
				.addAllVehicles(this.vehicleImpls)
				.addAllJobs(this.shipments)
				.build();
		
		VehicleRoutingAlgorithm algorithm = VehicleRoutingAlgorithms.readAndCreateAlgorithm(problem, "input/algorithmConfig.xml");
		Collection<VehicleRoutingProblemSolution> solutions = algorithm.searchSolutions();
		VehicleRoutingProblemSolution bestSolution = Solutions.bestOf(solutions);
		
		return null;
    }
}
